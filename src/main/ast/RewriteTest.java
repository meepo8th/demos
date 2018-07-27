package ast;

import com.netflix.rewrite.ast.Tr;
import com.netflix.rewrite.parse.OracleJdkParser;
import com.netflix.rewrite.parse.Parser;
import com.netflix.rewrite.refactor.Refactor;

public class RewriteTest {
    public static void main(String[] args){

        Parser parser = new OracleJdkParser();
        String a = "class A {{ B.foo(0); }}";
        String b = "class B { static void foo(int n) {} }";

        Tr.CompilationUnit cu = parser.parse(a);

        Refactor refactor = cu.refactor()
                .changeMethodName(cu.findMethodCalls("B foo(int)"), "bar");

        Tr.CompilationUnit fixed = refactor.fix();

    }
}
