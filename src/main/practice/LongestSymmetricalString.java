package practice;

public class LongestSymmetricalString {

    public static void main(String[] args) {
        String[] strings = {"google", "applelppa", "bananaananab", "orange", "strawberry"};
        for (String each : strings) {
            int length = LongestSymmetricalLength(each);
            System.out.println(length);
        }
    }

    public static int LongestSymmetricalLength(String strings) {
        if (strings == null || strings.length() == 0) {
            return -1;
        }
        int SymmetricalLength = 1;
        char[] letter = strings.toCharArray();
        int stringLength = strings.length();
        int currentIndex = 0;
        //No.1
        //开始写代码，在此写入实现代码
        while (currentIndex >= 0 && currentIndex < stringLength - 2) {
            int tmp = 1;
            int left = currentIndex;
            int right = letter[currentIndex + 1] == letter[currentIndex] ? currentIndex + 1 : currentIndex;
            while (left >= 0 && right <= stringLength - 1) {
                if (letter[right] == letter[left]) {
                    tmp = right - left + 1;
                    right++;
                    left--;
                } else {
                    break;
                }
            }
            if (tmp > SymmetricalLength) {
                SymmetricalLength = tmp;
            }
            currentIndex++;

        }
        //end_code
        return SymmetricalLength;
    }
}