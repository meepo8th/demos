package leetcode;

public class TreeNodeWithParentLevel {
    public TreeNode node;
    public TreeNodeWithParentLevel parent;
    int level;

    public TreeNodeWithParentLevel(TreeNode node, TreeNodeWithParentLevel parent, int level) {
        this.node = node;
        this.parent = parent;
        this.level = level;
    }

    @Override
    public int hashCode() {
        return node.val;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof TreeNodeWithParentLevel && this.node.val == ((TreeNodeWithParentLevel) o).node.val;
    }
}
