#!/bin/bash
. /etc/profile

# 监控指定时间指定文件没有变化则退出，否则，打印指定文件新增内容
function log_monitor(){
    local file_path=${1}
    local check_time=${2}
    local last_col=0
    local index=0
    file_old_lines=0
    file_new_lines=0
    while [[ true ]]; do
        now_lines=`cat ${file_path}|wc -l`
        if [[ $now_lines -gt ${file_new_lines} ]];then
            tail -n +${file_new_lines} ${file_path}|head -n $[$now_lines-$file_new_lines]
            file_new_lines=${now_lines}
            file_old_lines=${file_new_lines}
            index=0
        else
            echo "wait ${index}"
        fi
        if [[ ${index} -gt ${check_time} ]];then
            echo "### In ${check_time}s ,${file_path} does not change ###"
            break
        fi
        index=$[${index}+1]
        sleep 1
    done
}
function waitClose(){
    log_monitor $logname 60
    echo "deploy success"
}
# 无感知发布多个app，轮流启停多节点中的一个,该脚本需要跟eureka以及其各节点在同一个内网内才可执行
#初始化并校验参数
initAndCheckCfg(){
    if [[ -f ${cfgName} ]];then
        appFound=""
        while read line
        do
            if [[ $line == app@* ]];then
                appName=`echo $line|awk -F"@" '{print $2}'|awk -F"|" '{print $1}'`
                appPath=`echo $line|awk -F"@" '{print $2}'|awk -F"|" '{print $2}'`
                appDeployPath=`echo $line|awk -F"@" '{print $2}'|awk -F"|" '{print $3}'`
                appUser=`echo $line|awk -F"@" '{print $2}'|awk -F"|" '{print $4}'`
                appPwd=`echo $line|awk -F"@" '{print $2}'|awk -F"|" '{print $5}'`
                appEnv=`echo $line|awk -F"@" '{print $2}'|awk -F"|" '{print $6}'`
                appForce=`echo $line|awk -F"@" '{print $2}'|awk -F"|" '{print $7}'`

                if [ "$appName" != "" ]&&[ "$appPath" != "" ]&&[ "$appEnv" != "" ]&&[ "$appDeployPath" != "" ]&&[ "$appUser"  != "" ]&&[ "$appPwd" != "" ];then
                    namePathMap["$appName"]=$appPath
                    nameEnvMap["$appName"]=$appEnv
                    nameForceMap["$appName"]=$appForce
                    nameUserMap["$appName"]=$appUser
                    namePwdMap["$appName"]=$appPwd
                    nameDeployPathMap["$appName"]=$appDeployPath
                    appFound="found"
                fi
            fi
            if [[ $line == eurekaHost@* ]];then
                eurekaHost=`echo $line|awk -F"@" '{print $2}'`
            fi
            if [[ $line == eurekaUser@* ]];then
                eurekaUser=`echo $line|awk -F"@" '{print $2}'`
            fi
            if [[ $line == eurekaPwd@* ]];then
                eurekaPwd=`echo $line|awk -F"@" '{print $2}'`
            fi
        done < ./${cfgName}
    else
        echo "cfgFile $cfgName not exist"
        exit 1
    fi
    if [[ "$eurekaHost" = "" ]];then
        echo "eurekaHost not set"
        exit 1
    fi
    if [[ "$eurekaUser" = "" ]];then
        echo "eurekaUser not set"
        exit 1
    fi
    if [[ "$eurekaPwd" = "" ]];then
        echo "eurekaPwd not set"
        exit 1
    fi
    if [[ "$appFound" != "found" ]];then
        echo "app not set"
        exit 1
    fi
}
#休眠5s
waitSleep(){
    waitCount=1
    if [[ $# -eq 1 ]];then
        waitCount=$1
    fi
    for(( i=0;i<waitCount;i++)){
        echo "waiting......"
        sleep 5
    }
 }
 deployOne(){
    if [[ $# -eq 2 ]];then
        instanceId=$1
        instanceHost=$2
    else
        echo "deployOne not enough param"
        return
    fi
    echo "deploy $deployApp on $instanceHost $instanceId"
    rm -rf ./sshinfo
    #2.循环发布app,当强制发布时，不对eureka做操作，否则，等前一个停完且启动后才发布后一个
    if [[ "${nameForceMap[$deployApp]}" != "1" ]];then
        #2.1 out_of_service一个节点
        echo "curl -XPUT  -m 10 -u${eurekaUser}:${eurekaPwd} "${eurekaHost}/apps/${deployApp}/${instanceId}/status?value=OUT_OF_SERVICE"  -I -s"
        curlInfo=`curl -XPUT  -m 10 -u${eurekaUser}:${eurekaPwd} "${eurekaHost}/apps/${deployApp}/${instanceId}/status?value=OUT_OF_SERVICE"  -I -s`
        code200=`echo ${curlInfo}|grep "HTTP/1.1 200"`
        code404=`echo ${curlInfo}|grep "HTTP/1.1 404"`
        if [ "${code200}" = "" ]&&[ "${code404}" = "" ];then
            echo "${deployApp} out_of_service error:"
            echo "${curlInfo}"
            exit 1
        fi
        #2.2 sleep 60s ribbon跟eureka默认的刷新时间，如果修改配置，请同步修改此处
        echo "waiting for eureka client clean cache"
        waitSleep 12
    fi
    #2.3 stop这个节点
    echo "waiting for ${deployApp} stop"

    sshpass -p "${namePwdMap[$deployApp]}" ssh -n ${nameUserMap[$deployApp]}@$instanceHost sh ${nameDeployPathMap[$deployApp]}/stop.sh

    waitSleep 1
    #2.4 备份原有jar包跟sh
    datetime=`date "+%Y%m%d%H%M%S"`
    sshpass -p "${namePwdMap[$deployApp]}" ssh -n  ${nameUserMap[$deployApp]}@$instanceHost tar -czf ${nameDeployPathMap[$deployApp]}/${deployApp}_${datetime}.tar.gz ${nameDeployPathMap[$deployApp]}/*.jar ${nameDeployPathMap[$deployApp]}/*.sh

    sshpass -p "${namePwdMap[$deployApp]}" ssh -n  ${nameUserMap[$deployApp]}@$instanceHost rm -f ${nameDeployPathMap[$deployApp]}/*.jar

    sshpass -p "${namePwdMap[$deployApp]}" ssh -n  ${nameUserMap[$deployApp]}@$instanceHost rm -f ${nameDeployPathMap[$deployApp]}/*.sh

    #2.5 发布新的内容
    sshpass -p "${namePwdMap[$deployApp]}" scp  ${namePathMap[$deployApp]}/*.jar ${nameUserMap[$deployApp]}@$instanceHost:${nameDeployPathMap[$deployApp]}/

    sshpass -p "${namePwdMap[$deployApp]}" scp  ${namePathMap[$deployApp]}/*.sh ${nameUserMap[$deployApp]}@$instanceHost:${nameDeployPathMap[$deployApp]}/

    #2.5 启动这个节点
    sshpass -p "${namePwdMap[$deployApp]}" ssh -n  ${nameUserMap[$deployApp]}@$instanceHost sh ${nameDeployPathMap[$deployApp]}/start.sh ${nameEnvMap[$deployApp]}

    if [ "${nameForceMap[$deployApp]}" != "1" ];then
        #2.6 启动 sleep 60s
        echo "waiting for ${deployApp} start"
        if [ "${nameForceMap[$deployApp]}" != "1" ];then
        waitSleep 20
        fi
        #2.7 up这个节点
        echo "curl -XPUT -m 10 -u$eurekaUser:$eurekaPwd "${eurekaHost}/apps/${deployApp}/${instanceId}/status?value=UP" -I -s"
        curlInfo=`curl -XPUT -m 10 -u$eurekaUser:$eurekaPwd "${eurekaHost}/apps/${deployApp}/${instanceId}/status?value=UP" -I -s`
        code200=`echo $curlInfo|grep "HTTP/1.1 200"`
        if [ "${code200}" = "" ];then
            echo "${deployApp} up error:${curlInfo}"
            exit 1
        fi
        #sleep 30s ribbon跟eureka默认的刷新时间，如果修改配置，请同步修改此处
        waitSleep 6
    fi
 }
#交替发布一个app
autoDeploy(){
    rm -rf ./appinfos instanceIds ipaddrs
    deployApp=""
    if [ ! $# -eq 1 ];then
        echo "please input deploy app name"
        exit 1
    else
        deployApp=$1
    fi
    if [ "${namePathMap[$deployApp]}" = "" ];then
        echo "${deployApp} has not cfg in ${cfgName}"
        return
    fi
    appinfos="${deployApp}appinfos"
    instanceIdFile="${deployApp}instanceIds"
    ipaddrFile="${deployApp}ipaddrs"
    echo "${deployApp} deploy start"
    #1.获取app列表
    echo "curl -u$eurekaUser:$eurekaPwd -m 10 "${eurekaHost}/apps/${deployApp}/" -s"
    appsInfo=`curl -u$eurekaUser:$eurekaPwd -m 10 "${eurekaHost}/apps/${deployApp}/" -s`
    curl -u$eurekaUser:$eurekaPwd -m 10 "${eurekaHost}/apps/${deployApp}/" -s > ${appinfos}
    code200=`echo $appsInfo|grep "</application>"`
    if [ "${code200}" = "" ]; then
        echo "get app info error:"
        echo "${appsInfo}"
        return
    fi
    #1.1 如果app只有一个可用节点，必须设置了强制发布才可以发布，否则会自动跳过，如果没有节点，报错返回
    instanceIds=()
    instanceHosts=()
    index=0
    cat ${appinfos}|grep "<instanceId>">${instanceIdFile}
    while read line
    do
        instanceIds[index]=`echo $line|awk -F"[<|>]" '{print $3}'`
        index=${index}+1
    done < ${instanceIdFile}
    index=0
    cat ${appinfos}|grep "<ipAddr>">${ipaddrFile}
    while read line
    do
        instanceHosts[index]=`echo $line|awk -F"[<|>]" '{print $3}'`
        index=${index}+1
    done<${ipaddrFile}
    if [ ${#instanceIds[*]} -eq 0 ];then
        echo  "${deployApp} hasn't instanceId"
        return
    elif [ ${#instanceIds[*]} -eq 1 ]&&[ "${nameForceMap[$deployApp]}" != "1" ];then
        echo  "${deployApp} has only one node and can't be force update"
        return
    fi
    index=0
    while [ $index -lt ${#instanceIds[*]} ];do
        instanceId=${instanceIds[index]}
        instanceHost=${instanceHosts[index]}
        echo "deployOne $instanceId $instanceHost "
        deployOne $instanceId $instanceHost
        let index=1+index
    done
    rm -rf ./${appinfos} ${instanceIdFile} ${ipaddrFile}
    echo "${deployApp} deploy success"
}
## 发布脚本
if [ $# -eq 0 ];then
    echo "please input deploy app name"
    exit 1
fi
cd $(dirname $0)
cfgName=autoDeploy.cfg
for var in $*
do
    if [[ $var == -f* ]];then
        cfgName=`echo $var|awk -F"-f" '{print $2}'`
    fi
done
eurekaHost=""
eurekaUser=""
eurekaPwd=""
declare -A namePathMap=()
declare -A nameEnvMap=()
declare -A nameForceMap=()
declare -A nameUserMap=()
declare -A namePwdMap=()
declare -A nameDeployPathMap=()
echo "cfg:${cfgName}"
initAndCheckCfg
datetime=`date "+%Y%m%d%H%M%S"`
logname="autoDeployLog$datetime.log"
>$logname
for app in $*
do
    if [[ $var == -f* ]];then
        echo ""
    else
        autoDeploy $app >> $logname &
    fi
done
waitClose
