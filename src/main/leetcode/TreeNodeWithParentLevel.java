package leetcode;

public class TreeNodeWithParentLevel {
    TreeNode node;
    TreeNode parent;
    int level;

    public TreeNodeWithParentLevel(TreeNode node, TreeNode parent, int level) {
        this.node = node;
        this.parent = parent;
        this.level = level;
    }
}
