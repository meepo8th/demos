package ast;

public class RewriteTest {
    public static void main(String[] args){

        Parser parser = new OracleJdkParser();
        String a = "class A {{ B.foo(0); }}";
        String b = "class B { static void foo(int n) {} }";

        Tr.CompilationUnit cu = parser.parse(a, /* which depends on */ b);

        Refactor refactor = cu.refactor()
                .changeMethodName(cu.findMethodCalls("B foo(int)"), "bar");

        Tr.CompilationUnit fixed = refactor.fix();

        assertEquals(fixed.print(), "class A {{ B.bar(0); }}");
    }
}
