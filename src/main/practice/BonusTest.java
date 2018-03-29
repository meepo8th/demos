package practice;

public class BonusTest {
    public static void main(String[] args) {
        double x = 0, y = 0;
        x = 30;
        //No.1
        //开始写代码,在此写入实现代码
        double[] level = new double[]{100, 60, 40, 20, 0};
        double[] levelFee = new double[]{0.01, 0.015, 0.03, 0.05, 0.075};
        for (int i = 0; i < level.length; i++) {
            if (x >= level[i]) {
                y += (x - level[i]) * levelFee[i];
                x = level[i];
            }
        }
        //end_code
        System.out.println("应该提取的奖金是 " + y + "万");
    }
}