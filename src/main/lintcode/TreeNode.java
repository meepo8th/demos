package lintcode;

public class TreeNode {
    public int val;
    public TreeNode left, right;

    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }

    public TreeNode(String nodeArray) {
        String[] nodes = nodeArray.split(",");

    }
}