package codewars;

public class EncodeResistorColors {
    public static String encodeResistorColors(String ohmsString) {
        // your code here
        String[] codes = {"black", "brown", "red", "orange", "yellow", "green", "blue", "violet", "gray", "white"};
        String numStr = ohmsString.split(" ")[0];
        String unit = "";
        int num = 0;
        if (numStr.endsWith("k") || numStr.endsWith("M")) {
            unit = numStr.substring(numStr.length() - 1, numStr.length());
            numStr = numStr.substring(0, numStr.length() - 1);
        }
        switch (unit) {
            case "k":
                num = (int) (Double.valueOf(numStr) * 1000);
                break;
            case "M":
                num = (int) (Double.valueOf(numStr) * 1000 * 1000);
                break;
            case "":
                num = Integer.valueOf(numStr);
                break;
            default:
                break;
        }
        int expr = 0;
        while (num >= 100) {
            num = num / 10;
            expr++;
        }
        numStr = "" + num;
        while (numStr.length() < 2) {
            numStr = "0" + numStr;
        }
        String rtn = codes[numStr.charAt(0) - '0'] + " " + codes[numStr.charAt(1) - '0'] + " " + codes[expr] + " gold";
        return rtn;
    }
}
