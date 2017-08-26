public class StringMerger {

    public static boolean isMerge(String s, String part1, String part2) {
        int[][] matrix = new int[part1.length()][part2.length()];
        int x = 0, y = 0;
        while (true) {
            if (x < part1.length() && y < part2.length()) {
                return true;
            }else{
                return false;
            }
        }
    }

    public static void main(String args[]) {
        System.out.println(isMerge("Can we merge it? Yes, we can!", "w mee ", "Can erge it? Yes, wcan!"));
    }
}