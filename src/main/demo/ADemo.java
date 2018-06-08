package demo;

import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;
import java.util.Map;

public class ADemo {

    public static void main(String[] args) {
        String[] array = new String[]{"a", "b", "c"};
        System.out.println(array[Integer.valueOf(null)]);
        String jsonContent = "{\"a\":{\"count\":1,\"result\":\"success\"},\"b\":{\"count\":2,\"result\":\"error\"},\"c\":{\"count\":3,\"result\":\"success\"},\"d\":{\"count\":4,\"result\":\"exception\"}}";
        JSONObject jsonObject = JSONObject.parseObject(jsonContent);
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }

}