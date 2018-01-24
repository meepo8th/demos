enum Test {
    ONE(1),
    TWO(2);
    private int code;

    Test(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

public class Demo {

    public static void main(String args[]) {
        System.out.println(Test.TWO.getCode());
        System.out.println(Test.TWO);
    }
}