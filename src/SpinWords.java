public class SpinWords {

    public static String spinWords(String sentence) {
        StringBuffer sb = new StringBuffer();
        for (String str : sentence.split(" ")) {
            if (null != str && "" != str) {
                if (str.length() < 5) {
                    sb.append(" " + str);
                } else {
                    sb.append(" " + new StringBuffer(str).reverse());
                }
            }
        }
        return sb.substring(1);
    }

    public static void main(String args[]) {
        System.out.println(spinWords("Hey fellow warriors"));
        System.out.println(new String(new char[]{98, 105, 120, 111, 108, 111, 110, 95, 115, 111, 102, 116, 119, 97, 114, 101, 50, 48, 49, 52, 48, 49, 50, 49}));
    }
}