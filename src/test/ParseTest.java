package test;

import cn.dx.diagnosis.parser.bean.ParseContent;
import cn.dx.diagnosis.parser.bean.RelationShip;
import codewars.Mixing;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ParseTest {
    @Test
    public void test() {
        ParseContent parseContent = new ParseContent("123",1.0, RelationShip.AND,new ArrayList<ParseContent>());
        parseContent.getChildren().add(new ParseContent("123",1.0, RelationShip.AND,new ArrayList<ParseContent>()));
        parseContent.getChildren().add(new ParseContent("123",1.0, RelationShip.AND,new ArrayList<ParseContent>()));
        parseContent.getChildren().get(0).getChildren().add(new ParseContent("123",1.0, RelationShip.AND,new ArrayList<ParseContent>()));
        parseContent.getChildren().get(0).getChildren().add(new ParseContent("123",1.0, RelationShip.AND,new ArrayList<ParseContent>()));
        System.out.println(parseContent);
    }
}
