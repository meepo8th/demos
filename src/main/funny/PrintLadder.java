package funny;

public class PrintLadder {
    /**
     * 打印小人上梯子
     *
     * @param n 梯子层数
     */
    public static void printLadder(int n) {
        String[] peoplePic = new String[]{"  o  *******", " /|\\ *      ", " / \\ *      "};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = (n - 1) * 6; k > i * 6; k--) {
                    System.out.print(" ");
                }
                System.out.print(peoplePic[j]);
                for (int k = 0 * 6; k < i * 6; k++) {
                    System.out.print(" ");
                }
                System.out.println("*");
            }
        }
        for (int i = 0; i < 6 * n + 7; i++) {
            System.out.print("*");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        printLadder(3);
        printLadder(2);
    }
}
