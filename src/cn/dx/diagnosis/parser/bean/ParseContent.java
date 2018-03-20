package cn.dx.diagnosis.parser.bean;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * 解析内容
 */
public class ParseContent {
    //概率
    Double probability;
    //子内容关系
    RelationShip relationship;
    //内容
    String content;
    //子内容
    List<ParseContent> children;

    public ParseContent(String content, double probability, RelationShip relationShip, List<ParseContent> children) {
        this.content = content;
        this.probability = probability;
        this.relationship = relationShip;
        this.children = children;
    }

    public ParseContent() {

    }

    /**
     * 获取所有没有子节点的节点,用堆栈实现递归防止递归层数过多导致系统堆栈不足
     *
     * @return
     */
    public List<ParseContent> getAllChildWithoutChild() {
        List<ParseContent> allChild = new ArrayList<>();
        ArrayDeque<ParseContent> stack = new ArrayDeque<>();
        if (isEmptyList(children)) {
            stack.push(this);
        }
        while (!stack.isEmpty()) {
            ParseContent nowContent = stack.pop();
            if (null != nowContent) {
                if (isEmptyList(nowContent.children)) {
                    for (ParseContent each : nowContent.children) {
                        stack.push(each);
                    }
                } else {
                    allChild.add(nowContent);
                }
            }
        }
        return allChild;
    }

    private boolean isEmptyList(List list) {
        return null != list && !list.isEmpty();
    }

    public Double getProbability() {
        return probability;
    }

    public void setProbability(Double probability) {
        this.probability = probability;
    }

    public RelationShip getRelationship() {
        return relationship;
    }

    public void setRelationship(RelationShip relationship) {
        this.relationship = relationship;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<ParseContent> getChildren() {
        return children;
    }

    public void setChildren(List<ParseContent> children) {
        this.children = children;
    }


    @Override
    public String toString() {
        return "ParseContent{" +
                "probability=" + probability +
                ", relationship=" + relationship +
                ", content='" + content + '\'' +
                ", children=" + children +
                '}';
    }
}
