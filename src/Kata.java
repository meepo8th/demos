import java.util.Stack;

public class Kata {
    public static String expandedForm(int num) {
        //your code here
        String rtn = "";
        int pos = 1;
        Stack<String> stack = new Stack<>();
        for (int i = num; i > 0; i /= 10) {
            if (i % 10 > 0) {
                stack.push("" + (i % 10) * pos);
            }
            pos *= 10;
        }
        while (!stack.isEmpty()) {
            rtn += (stack.pop() + " + ");
        }
        return rtn.replaceAll(" \\+ $", "");
    }

    public static void main(String args[]) {
        System.out.println(expandedForm(102));
    }
}