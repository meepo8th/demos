package leetcode.test;

/**
 * @author Administrator
 */
public class CalcTax {
    public static double calcTax(double salary) {
        double tax = 0d;
        /**
         * 定义税饥阶及税率
         */
        double[] salaryLevel = new double[]{0, 3500, 5000, 8000,13500};
        double[] taxLevel = new double[]{0, 0.05, 0.1, 0.2};
        for (int i = salaryLevel.length - 1; i >= 0; i--) {
            if (salary > salaryLevel[i]) {
                tax += (salary - salaryLevel[i]) * taxLevel[i];
                salary = salaryLevel[i];
            }
        }
        return tax;
    }

    public static void main(String[] args) {
        System.out.println(calcTax(8000));
    }
}
