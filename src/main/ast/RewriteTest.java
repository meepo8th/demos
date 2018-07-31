package ast;

import com.netflix.rewrite.ast.Tr;
import com.netflix.rewrite.ast.Type;
import com.netflix.rewrite.parse.OracleJdkParser;
import com.netflix.rewrite.parse.Parser;

public class RewriteTest {
    public static void main(String[] args){

        Parser parser = new OracleJdkParser();
        String a = "class A {int a;int b;public void main(String[] args){};public getA(){}}";

        Tr.CompilationUnit cu = parser.parse(a);
        System.out.println(((Type.Class) cu.getClasses().get(0).getType()).getMembers());
        System.out.println(( (cu.getClasses().get(0).getBody())));

    }
}
