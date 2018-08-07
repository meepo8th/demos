- ai_article 文章表

|列名|类型|列注释|
|----|------|----|
|id|int|自增id(主键)|
|info|text|内容|
|content|text|格式化内容|
|TITLE|varchar||
|pic|varchar||
|vedio|varchar||
|url|varchar||
|like_count|int||
|dis_like_count|int||

|索引名称|索引类型|索引字段|索引注释|
|----|------|----|---|
||唯一索引||无|
|PRIMARY|唯一索引|id|无|


- ai_article_collect 文章点赞表

|列名|类型|列注释|
|----|------|----|
|id|int|自增id(主键)|
|article_id|int|文章id|
|user_id|int|用户id|
|STATUS|smallint|收藏情况1:收藏;0:未收藏|

|索引名称|索引类型|索引字段|索引注释|
|----|------|----|---|
||唯一索引||无|
|PRIMARY|唯一索引|id|无|
|uni_article_collect|普通索引|article_id,user_id|无|
|idx_articlecollect_user|普通索引|user_id|无|
|idx_article_collect_article|普通索引|article_id,STATUS|无|


- ai_article_comment 文章评论表,记录文章评论

|列名|类型|列注释|
|----|------|----|
|id|int|自增id(主键)|
|article_id|int|文章id|
|user_id|int|用户id|
|ref_id|int|回复评论id|
|comment_content|varchar|评论内容|
|like_count|int|点赞数|
|dis_like_count|int|点踩数|
|comment_time|bigint|评论时间戳|

|索引名称|索引类型|索引字段|索引注释|
|----|------|----|---|
||唯一索引||无|
|PRIMARY|普通索引|id|无|
|idx_comment_article|普通索引|article_id|无|
|idx_comment_user|普通索引|user_id|无|


- ai_article_like 文章点赞表

|列名|类型|列注释|
|----|------|----|
|id|int|自增id(主键)|
|article_id|int|文章id|
|user_id|int|用户id|
|status|smallint|点赞情况1:点赞,-1:点踩,0:未操作|

|索引名称|索引类型|索引字段|索引注释|
|----|------|----|---|
||唯一索引||无|
|PRIMARY|唯一索引|id|无|
|uni_article_like|普通索引|article_id,user_id|无|
|idx_article_like_user|普通索引|user_id|无|
|idx_article_like_article|普通索引|article_id,status|无|


- ai_article_tag 用户标签表

|列名|类型|列注释|
|----|------|----|
|id|int|自增id(主键)|
|article_id|int|用户id|
|tag_id|int|标签id|
|tag_ratio|int|标签评分|
|tag_time|bigint|标签时间|

|索引名称|索引类型|索引字段|索引注释|
|----|------|----|---|
||唯一索引||无|
|PRIMARY|唯一索引|id|无|
|uni_article_tag|唯一索引|article_id,tag_id|无|


- ai_comment_like 评论点赞表

|列名|类型|列注释|
|----|------|----|
|id|int|自增id(主键)|
|comment_id|int|评论id|
|user_id|int|用户id|
|status|smallint|点赞情况1:点赞,-1:点踩,0:未操作|

|索引名称|索引类型|索引字段|索引注释|
|----|------|----|---|
||唯一索引||无|
|PRIMARY|唯一索引|id|无|
|uni_comment_like|普通索引|comment_id,user_id|无|
|idx_comment_like_user|普通索引|user_id|无|
|idx_comment_like_article|普通索引|comment_id,status|无|


- ai_sys_info 

|列名|类型|列注释|
|----|------|----|
|para_name|varchar|苹果最新版本|
|para_value|varchar||
|description|varchar||
|para_text|text||
|para_type|varchar||

|索引名称|索引类型|索引字段|索引注释|
|----|------|----|---|
||普通索引||无|


- ai_tag_content 标签表

|列名|类型|列注释|
|----|------|----|
|id|int|自增id(主键)|
|content|varchar|标签内容|
|ratio|int|标签热度|

|索引名称|索引类型|索引字段|索引注释|
|----|------|----|---|
||唯一索引||无|
|PRIMARY|唯一索引|id|无|
|uni_tag_content|唯一索引|content|无|


- ai_user_log 用户日志表,记录所有用户操作日志

|列名|类型|列注释|
|----|------|----|
|id|int|自增id(主键)|
|user_id|int|用户ID|
|log_type|int|日志类型|
|log_value|varchar|日志内容|
|log_detail|varchar|日志详情|
|log_time|bigint|日志时间戳|

|索引名称|索引类型|索引字段|索引注释|
|----|------|----|---|
||唯一索引||无|
|PRIMARY|唯一索引|id|无|


- ai_user_tag 用户标签表

|列名|类型|列注释|
|----|------|----|
|id|int|自增id(主键)|
|user_id|int|用户id|
|tag_id|int|标签id|
|tag_type|int|标签类型:0:用户添加 1:系统添加|
|tag_ratio|int|标签评分|
|tag_order|int|标签排序|
|tag_time|bigint|标签时间|

|索引名称|索引类型|索引字段|索引注释|
|----|------|----|---|
||唯一索引||无|
|PRIMARY|唯一索引|id|无|
|uni_user_tag|普通索引|user_id,tag_id|无|
|idx_tag_user|普通索引|tag_id|无|
|idx_tag_user_type_order|普通索引|user_id,tag_type,tag_order|无|


- deviceinfo 参数名称	参数说明	备注
device_type	客户端设备类型	1-苹果，2-安卓，3-PC
device_sn	客户端硬件串号	
当device_type=3时，传递mac地址

version	版本号码	默认：1.0.0
mac	苹果设备MAC	可选参数，安卓版本不传值
idfa	苹果广告标示符	可选参数，安卓版本不传值

|列名|类型|列注释|
|----|------|----|
|id|int||
|device_type|varchar||
|device_sn|varchar||
|version|varchar||
|mac|varchar||
|idfa|varchar||

|索引名称|索引类型|索引字段|索引注释|
|----|------|----|---|
||唯一索引||无|
|PRIMARY|普通索引|id|无|
|device_sn_index|普通索引|device_sn|无|


- springboot_permission 

|列名|类型|列注释|
|----|------|----|
|id|varchar||
|permissionName|varchar|权限名称|
|permissionNumber|varchar|权限编码|
|description|varchar|权限描述|
|permissionEnable|varchar|是否可用|
|createBy|varchar||
|modifyBy|varchar||
|createDate|datetime||
|modifyDate|datetime||

|索引名称|索引类型|索引字段|索引注释|
|----|------|----|---|
||唯一索引||无|
|PRIMARY|唯一索引|id|无|


- springboot_role 

|列名|类型|列注释|
|----|------|----|
|id|varchar||
|roleName|varchar|角色名称|
|description|varchar|描述|
|roleNumber|varchar|编码|
|createBy|varchar|创建人|
|modifyBy|varchar|修改人|
|createDate|datetime|创建日期|
|modifyDate|datetime|修改日期|

|索引名称|索引类型|索引字段|索引注释|
|----|------|----|---|
||唯一索引||无|
|PRIMARY|唯一索引|id|无|


- springboot_role_permission 

|列名|类型|列注释|
|----|------|----|
|id|varchar||
|roleId|varchar|角色id|
|permissionid|varchar|权限id|
|description|varchar|描述|
|isWork|varchar||
|createBy|varchar|创建人|
|modifyBy|varchar|修改时间|
|createDate|datetime|新增时间|
|modifyDate|datetime|修改时间|

|索引名称|索引类型|索引字段|索引注释|
|----|------|----|---|
||唯一索引||无|
|PRIMARY|普通索引|id|无|
|rolepermissionfkid|普通索引|permissionid|无|
|rolepermissionfkid2|普通索引|roleId|无|


- springboot_user 

|列名|类型|列注释|
|----|------|----|
|id|int||
|name|varchar|显示名称，可以重复|
|mobilePhone|varchar|手机号,多个手机号用逗号隔开|
|telPhone|varchar|座机号|
|gender|varchar|性别|
|passwd|varchar|密码|
|emailaddress|varchar| 邮箱|
|birthday|datetime|出生日期|
|isLocked|varchar|是否被锁定|
|salt|varchar|密码随机盐|
|passwordExpired|varchar|密码是否失效|
|account|varchar|登陆名|
|createBy|varchar|创建人|
|modifyBy|varchar|修改人|
|createDate|datetime|创建时间|
|modifyDate|datetime|修改时间|

|索引名称|索引类型|索引字段|索引注释|
|----|------|----|---|
||唯一索引||无|
|PRIMARY|唯一索引|id|无|


- springboot_user_role 

|列名|类型|列注释|
|----|------|----|
|id|varchar||
|userId|varchar|用户id|
|roleId|varchar|角色id|
|description|varchar|描述|
|isWork|varchar|是否可用|
|createBy|varchar|新增人|
|modifyBy|varchar|修改人|
|createDate|datetime|新增时间|
|modifyDate|datetime|修改时间|

|索引名称|索引类型|索引字段|索引注释|
|----|------|----|---|
||唯一索引||无|
|PRIMARY|普通索引|id|无|
|useridfk|普通索引|userId|无|
|roleidfk|普通索引|roleId|无|


- toutiao 

|列名|类型|列注释|
|----|------|----|
|id|int||
|office|varchar|科室|
|key_word|varchar|关键词|
|title|varchar|标题|
|article_url|varchar|文章链接|
|info|text|文章内容|
|content|text|文章源码内容|
|pic|text|图片|
|video|text|视频|

|索引名称|索引类型|索引字段|索引注释|
|----|------|----|---|
||唯一索引||无|
|PRIMARY|唯一索引|id|无|


