import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {
    private String parseOption(String pathWay) {
        File file = new File(pathWay);
        BufferedReader br = null;
        StringBuffer sb=new StringBuffer();
        try {
            br = new BufferedReader(new FileReader(file));
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            String contentHtml = sb.toString();
            String pattern = "(var +option +=.+)var +bag +=";

            // 创建 Pattern 对象
            Pattern r = Pattern.compile(pattern);

            // 现在创建 matcher 对象
            Matcher m = r.matcher(contentHtml);
            if (m.find( )) {
                String content=m.group(1).trim().replaceAll(";$","");
                content=content.substring(content.indexOf("=")+1);
                JSONObject object = JSON.parseObject(content);
                System.out.println(object.get("result") );
            } else {
                System.out.println("no match");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    public static void main(String[] args) {
        System.out.println(new Solution().parseOption("f:/123.txt"));
    }
}