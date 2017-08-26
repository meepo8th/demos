package codewars;

public class Xbonacci {

    public static double[] tribonacci(double[] s, int n) {
        // hackonacci me
        double[] rtn = new double[n];
        for (int i = 0; i < n; i++) {
            if (i < s.length) {
                rtn[i] = s[i];
            } else {
                rtn[i] = (rtn[i - 1] + rtn[i - 2] + rtn[i - 3]);
            }
        }
        return rtn;
    }

    public static void main(String args[]) {
        " ".toCharArray()[0]='d';
        System.out.println(Math.pow(10,10));
        double[]a = new double[]{Math.pow(2,2),Math.pow(1,1)};
        System.out.println(Long.MAX_VALUE);
    }
}