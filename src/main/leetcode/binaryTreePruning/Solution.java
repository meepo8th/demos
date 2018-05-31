package leetcode.binaryTreePruning;


class Solution {
    public TreeNode pruneTree(TreeNode root) {
        removeZeroNode(root);
        return root;
    }

    private void removeZeroNode(TreeNode root) {
        if (null != root) {
            if (root.right == null && root.left == null) {
                if (root.val == 0) {
                    root = null;
                }
            } else {
                removeZeroNode(root.left);
                removeZeroNode(root.right);
            }
        }
    }
}