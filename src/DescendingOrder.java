public class DescendingOrder {
    public static int sortDesc(final int num) {
        //Your code
        int[] cache = new int[10];
        for (int tmp = num; tmp > 0; ) {
            cache[tmp % 10]++;
            tmp = tmp / 10;
        }
        int rtn = 0;
        for (int i = 9; i >= 0; i--) {
            for (int j = 0; j < cache[i]; j++) {
                rtn = (10 * rtn) + i;
            }
        }
        return rtn;

    }

    public static void main(String args[]) {
        System.out.println(sortDesc(123456554));
    }
}