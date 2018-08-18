package util;


import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import org.apache.commons.lang3.StringUtils;


import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 */
public class CustomeStringUtil {

    /**
     * 驼峰命名改为下划线命名
     *
     * @param name
     * @return
     */
    public static String transLateUpper2UnderLine(final String name) {
        String rtn = name;
        if (null != name && name.length() > 0) {
            rtn = name.replaceAll("([A-Z])", "_$1").toUpperCase().replaceAll("^_+", "");
        }
        return rtn;
    }

    /**
     * 下划线转驼峰
     *
     * @param name
     * @return
     */
    public static String transLateUnderLine2Upper(final String name) {
        String rtn = "";
        if (null != name && name.length() > 0) {
            String[] splits = name.toLowerCase().split("_");
            for (String str : splits) {
                rtn += (str.substring(0, 1).toUpperCase() + str.substring(1));
            }
            rtn = rtn.substring(0, 1).toLowerCase() + rtn.substring(1);
        }
        return rtn;
    }

    /**
     * 返回指定格式的时间
     *
     * @param format
     * @return
     */
    public static String getNowTime(String format) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.CHINA);
        return dateFormat.format(cal.getTime());
    }

    /**
     * 首字符大写
     *
     * @param str
     * @return
     */
    public static String capFirst(final String str) {
        String rtnStr = str;
        if (null != str && str.length() > 0) {
            rtnStr = str.substring(0, 1).toUpperCase() + str.substring(1);
        }
        return rtnStr;
    }

    /**
     * 生成随机字符串包括字母数字下划线
     *
     * @param minLen
     * @param maxLen
     * @return
     */
    public static String generateRandomStr(int minLen, int maxLen) {
        int len = minLen + (int) Math.round(Math.random() * (maxLen - minLen));
        String generateSource = "0123456789abcdefghigklmnopqrstuvwxyz_";
        String rtnStr = "";
        for (int i = 0; i < len; i++) {
            rtnStr += generateSource.charAt((int) Math.floor(Math.random() * generateSource.length()));
        }
        return rtnStr;
    }

    /**
     * 给一个对象随机赋值
     *
     * @param o
     */
    public static void autoSetRandom(Object o) throws IllegalAccessException {
        Field fields[] = o.getClass().getDeclaredFields();
        StringBuffer fieilds = new StringBuffer("(");
        StringBuffer values = new StringBuffer("VALUES(");
        for (Field field : fields) {
            field.setAccessible(true);
            if (String.class == field.getType()) {
                field.set(o, generateRandomStr(3, 5));
            }
        }
    }

    /**
     * 生成报文的sendTime
     *
     * @return
     */
    public static String getSendTime() {
        return getNowTime("yyyyMMddHHmm+08");
    }

    /**
     * 获得毫秒时间
     *
     * @return
     */
    public static String getMsSendTime() {
        return getNowTime("yyyyMMddHHmmssSSS");
    }

    /**
     * 获取默认时间格式
     *
     * @return
     */
    public static String getDefaultDate() {
        return getNowTime("yyyyMMdd");
    }

    /**
     * 获取默认时间格式
     *
     * @return
     */
    public static String getDefaultDateTime() {
        return getNowTime("yyyyMMddHHmmss");
    }



    /**
     * @param sourceFile
     * @param regAnnotation
     * @param ths           取值顺序
     * @return
     */
    public static Map<String, String> getRegexMap(String sourceFile, String regAnnotation, int[] ths) {
        Map<String, String> mapRtn = new HashMap<>();
        Pattern pattern;
        pattern = Pattern.compile(regAnnotation, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(sourceFile);
        while (matcher.find()) {
            mapRtn.put(matcher.group(1).replaceAll("//", "").replaceAll(" +", "").replaceAll("\r\n", "").replaceAll("^\\*+", ""), matcher.group(2).replaceAll("//", "").replaceAll(" +", "").replaceAll("\r\n", "").replaceAll("^\\*\\*", ""));
        }
        return mapRtn;
    }

    /**
     * 在指定字符串中查找正则表达式中的group
     *
     * @param ciqMsg
     * @param regFind
     * @return
     */
    public static String getFindMsg(String ciqMsg, String regFind) {
        String findMsg = "";
        Pattern pattern;
        pattern = Pattern.compile(regFind, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(ciqMsg);
        if (matcher.find() && matcher.groupCount() > 0) {
            findMsg = matcher.group(1);
        }
        return findMsg.trim();
    }

    /**
     * map2String
     *
     * @param map
     * @return
     */
    public static <K, V> String map2String(Map<K, V> map) {
        if (null == map) {
            return "null";
        }
        Iterator<Map.Entry<K, V>> i = map.entrySet().iterator();
        if (!i.hasNext()) {
            return "{}";
        }

        StringBuilder sb = new StringBuilder();
        sb.append('{');
        for (; ; ) {
            Map.Entry<K, V> e = i.next();
            K key = e.getKey();
            V value = e.getValue();
            sb.append(key);
            sb.append('=');
            sb.append(value.getClass().isArray() ? Arrays.toString((Object[]) value) : value);
            if (!i.hasNext()) {
                return sb.append('}').toString();
            }
            sb.append(',').append(' ');
        }
    }


    /**
     * sql日期格式转化为java日期格式
     *
     * @param sqlDateFormat
     * @return
     */
    public static String dateSqlFormat2javaFormat(String sqlDateFormat) {
        String javaDateFormat = sqlDateFormat.toUpperCase();
        javaDateFormat = javaDateFormat.replaceAll("Y", "y").replaceAll("H", "h").replaceAll("hh24", "HH").replaceAll("MI", "mm").replaceAll("SS", "ss").replaceAll("DD", "dd");

        return javaDateFormat;
    }

    public static List<String> generateDayHours(Date date) {
        String[] hours = {"000000", "010000", "020000", "030000", "040000", "050000", "060000", "070000", "080000",
                "090000", "100000", "110000", "120000", "130000", "140000", "150000", "160000", "170000", "180000",
                "190000", "200000", "210000", "220000", "230000"};
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String day = df.format(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, -1);
        String beforeDay = df.format(cal.getTime());
        List<String> rtnList = new ArrayList<>();
        int th = 0;
        for (int i = 0; i < 25; i++) {
            th = cal.get(Calendar.HOUR_OF_DAY) + i + 1;
            rtnList.add((th >= 24 ? day : beforeDay) + hours[Math.abs(th % 24)]);
        }
        return rtnList;
    }

    public static List<String> generateTodayHours() {
        return generateDayHours(new Date());
    }

    public static List<String> generateHours() {
        List<String> rtnList = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        for (int i = 0; i < 25; i++) {
            rtnList.add((cal.get(Calendar.HOUR_OF_DAY) + i + 1) % 24 + ":00");
        }
        return rtnList;
    }

    public static String transAngelValue(int nowValue) {
        String rtn = "";
        int[] unit = new int[]{3600, 60, 1};
        String[] units = new String[]{"°", "′", "″"};

        for (int i = 0; i < 3; i++) {
            int cur = nowValue / unit[i];
            nowValue = nowValue % unit[i];
            if (cur > 0) {
                rtn += ("" + cur + units[i]);
            }
        }
        if (StringUtils.isBlank(rtn)) {
            rtn = "0″";
        }
        return rtn;
    }

    public static String transAngelValue(BigDecimal nowValue) {
        String rtn = "";

        if (null != nowValue) {
            String[] nums = nowValue.toString().split("\\.");
            String intValue = transAngelValue(Integer.valueOf(nums[0]));
            String suffixValue = "";
            if (nums.length > 1 && !StringUtils.isBlank(nums[1])) {
                if (intValue.endsWith("″")) {
                    intValue = intValue.replaceAll("″", "");
                } else {
                    intValue += "0";
                }
                suffixValue = "." + nums[1] + "″";
            }

            rtn = intValue + suffixValue;
        }
        return rtn;
    }

    /**
     * 转换为查找字符串
     *
     * @param result
     * @return
     */
    public static String transSearchText(String result) {
        if (StringUtils.isBlank(result)) {
            return "";
        }
        char[] content = result.toUpperCase().toCharArray();
        for (int i = 0; i < content.length; i++) {
            content[i] = transSearchChar(content[i]);
        }
        return new String(content);
    }

    /**
     * 转换为查询字符
     *
     * @param c
     * @return
     */
    public static char transSearchChar(char c) {
        String withOutTrans = "0123456789ABCDEFGHIGKLMNOPQRSTUVWXYZ-";
        String directTransKey = "零一二三四五六七八九";
        String directTransValue = "0123456789";
        String[] pinyinTransKey = new String[]{"ling2", "yi1", "er4", "san1", "si4", "wu3", "liu4", "qi1", "ba1", "jiu3", "bi4", "di4", "yi4", "ji4", "ai4", "en1", "en2", "en3", "en4", "ou1", "pi4", "a1", "you4", "wei4", "wai4", "yi4", "gang4"};
        String pinyinTransValue = "0123456789BEEEEDGINOPRUVYE-";
        if (withOutTrans.indexOf(c) < 0) {
            if (directTransKey.indexOf(c) >= 0) {
                c = directTransValue.charAt(directTransKey.indexOf(c));
            } else {
                String pinyinKey = PinyinHelper.convertToPinyinString(c + "", "", PinyinFormat.WITH_TONE_NUMBER);
                for (int i = 0; i < pinyinTransKey.length; i++) {
                    if (pinyinKey.equals(pinyinTransKey[i])) {
                        c = pinyinTransValue.charAt(i);
                        break;
                    }
                }
            }
        }
        return c;
    }

    public static void main(String[] args) {
        PinyinFormat pinyinFormat = PinyinFormat.WITHOUT_TONE;
        System.out.println(PinyinHelper.convertToPinyinString("零一二三四五六七八九币地亿剂爱嗯欧屁啊又为外易杠", "", PinyinFormat.WITH_TONE_NUMBER));
    }
}