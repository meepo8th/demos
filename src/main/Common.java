import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by admin on 2017/4/17.
 */
public class Common {
    static class Stack {
        char[] elements;
        int size = 0;

        Stack() {
            elements = new char[5001];
        }

        public void push(char ele) {
            elements[size] = ele;
            size += 1;
        }

        public void clear() {
            size = 0;
        }

        public boolean empty() {
            return 0 == size;
        }

        public char pop() {
            if (!empty()) {
                size -= 1;
                return elements[size];
            }
            return 'h';
        }
    }

    /**
     * 数字转汉字
     *
     * @param numStr
     * @return
     */
    public static String num2ch(String numStr) {
        String chStr = "";
        String[] nums = new String[]{"零", "一", "二", "三", "四", "五", "六", "七", "八", "九", "点"};
//        if (StringUtils.isNumeric(numStr)) {
        for (char ch : numStr.toCharArray()) {
            int pos = ch - '0';
            if (pos >= 0 && pos <= 9) {
                chStr += nums[pos];
            } else {
                chStr += nums[10];
            }
        }
//        }
        return chStr;
    }

    /**
     * 数字转汉字带单位
     *
     * @param numStr
     * @return
     */
    public static String num2chWithUnit(String numStr) {
        String chStr = "";
        String[] units = new String[]{"千", "百", "十", ""};
        String[] unitGroups = new String[]{"京", "兆", "亿", "万", ""};
        chStr = num2ch(numStr);
        String afterStr = "";
        String beforeStr = "";
        if (chStr.contains("点")) {
            afterStr = chStr.substring(chStr.indexOf("点"));
            beforeStr = chStr.substring(0, chStr.indexOf("点"));
        } else {
            beforeStr = chStr;
        }
        if (null != beforeStr && !"".equals(beforeStr)) {
            List<String> listUnitStr = new ArrayList<>();
            //4位拆开
            for (int i = beforeStr.length() % 4 - 4; i < beforeStr.length(); i += 4) {
                listUnitStr.add(addUnit(beforeStr.substring((i < 0 ? 0 : i), i + 4).split(""), units));
            }
            System.out.println(listUnitStr);
            beforeStr = "";
            beforeStr += addUnit(listUnitStr.toArray(new String[]{}), unitGroups);
            chStr = beforeStr + afterStr;
        }
        return chStr;
    }

    /**
     * 数组截取
     *
     * @param split
     * @param start
     * @return
     */
    public static String[] subArray(String[] split, int start) {
        return subArray(split, start, split.length);
    }

    /**
     * 数组截取
     *
     * @param split
     * @param start
     * @return
     */
    public static String[] subArray(String[] split, int start, int end) {
        String[] rtn = new String[end - start];
        System.arraycopy(split, start, rtn, 0, end - start);
        return rtn;
    }

    /**
     * 添加单位
     *
     * @param split
     * @param units
     * @return
     */
    public static String addUnit(String[] split, String[] units) {
        String rtnStr = "";
        if (null != split && null != units) {
            if (split.length > units.length) {
                split = subArray(split, 1);
            }
            for (int i = 0; i < split.length; i++) {
                if ("零".equals(split[i])) {
                    if (i == split.length - 1 || "零".equals(split[i + 1])) {
                        continue;
                    }
                }
                rtnStr += split[i];
                if (!(null == split[i] || "".equals(split[i]) || "零".equals(split[i]))) {
                    rtnStr += units[i + units.length - split.length];
                }
            }
        }
        return rtnStr;
    }

    /**
     * 计算拼音串的相似度( Levenshtein distance最小编辑距离)
     *
     * @return
     */
    public static int calcPinyinSimilarity(String strA, String strB) {

        int lengthA = strA.length() + 1;
        int lengthB = strB.length() + 1;
        int[][] dis = new int[lengthA][lengthB];
        for (int i = 0; i < lengthA; i++) {
            for (int j = 0; j < lengthB; j++)
                dis[i][j] = Integer.MAX_VALUE;
        }
        dis[0][0] = 0;
        for (int i = 0; i < lengthA; i++) {
            for (int j = 0; j < lengthB; j++) {
                if (i > 0) dis[i][j] = Math.min(dis[i][j], dis[i - 1][j] + 1); //delete
                if (j > 0) dis[i][j] = Math.min(dis[i][j], dis[i][j - 1] + 1);//insert

                //substitute
                if (i > 0 && j > 0) {
                    if (strA.charAt(i - 1) != strB.charAt(j - 1)) {
                        dis[i][j] = Math.min(dis[i][j], dis[i - 1][j - 1] + 2);
                    } else {
                        dis[i][j] = Math.min(dis[i][j], dis[i - 1][j - 1]);
                    }
                }
            }
        }
        return dis[lengthA - 1][lengthB - 1];

    }

    private static final void swap(int[] array, int posA, int posB) {
        int tmp = array[posA];
        array[posA] = array[posB];
        array[posB] = tmp;
    }

    public static void rerange(int[] A) {
        if (A.length > 1) {
            int start = 0;
            int end = A.length - 1;
            while (start < end) {
                if (A[start] < 0) {
                    swap(A, start, end);
                    end--;
                } else {
                    start++;
                }
            }
            if (A.length / 2 == 0) {
                start = 0;
                end = A.length - 1;
            } else {
                if (A[(A.length + 1) / 2-1] > 0) {
                    start = 1;
                    end = A.length - 1;
                } else {
                    start=0;
                    end=A.length-2;
                }
            }
            while(start<end){
                swap(A,start,start+(end-start+1)/2);
                start+=2;
            }
        }
    }

    public static void main(String args[]) {
        int[] num = new int[]{-2, 1, 2, 3, 5, -3, -4,-5};
        System.out.println(num);
        rerange(num);
        System.out.println(Arrays.toString(num));
    }
}
