package leetcode.buddyStrings;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public boolean buddyStrings(String A, String B) {
        if (A.length() == B.length()) {

            List<Integer> diffrents = new ArrayList<Integer>();
            for (int i = 0; i < A.length(); i++) {
                if (A.charAt(i) != B.charAt(i)) {
                    diffrents.add(i);
                    if (diffrents.size() > 2) {
                        break;
                    }
                }
            }
            if (diffrents.size() == 2) {
                return A.charAt(diffrents.get(0)) == B.charAt(diffrents.get(1)) && A.charAt(diffrents.get(1)) == B.charAt(diffrents.get(0));
            } else if (diffrents.size() == 0) {
                for (int i = 0; i < A.length(); i++) {
                    for (int j = i + 1; j < B.length(); j++) {
                        if (A.charAt(i) == B.charAt(j)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(JSONObject.parse("{\"diseaseResult\":[{\"name\":\"小儿发育性髋脱位\",\"score\":\"43.7500%\"},{\"name\":\"小儿发育性髋脱位-双侧\",\"score\":\"28.0000%\"},{\"name\":\"小儿发育性髋脱位-单侧\",\"score\":\"22.5806%\"}],\"type\":7}"));
        System.out.println(new Solution().buddyStrings("ab", "ab"));
    }
}