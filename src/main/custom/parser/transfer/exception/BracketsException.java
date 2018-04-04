package custom.parser.transfer.exception;

public class BracketsException extends TransferException {
    public static final String BRACKET_NOT_MATCH = "括号数量不匹配";
    public static final String BRACKET_NOT_SET = "调用必须指明括号类型";

    public BracketsException() {
    }

    public BracketsException(String var1) {
        super(var1);
    }

    public BracketsException(String var1, Throwable var2) {
        super(var1, var2);
    }

    public BracketsException(Throwable var1) {
        super(var1);
    }
}
