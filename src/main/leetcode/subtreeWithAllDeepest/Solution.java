package leetcode.subtreeWithAllDeepest;

import leetcode.TreeNode;
class TreeNodeWithParentLevel{
    TreeNode treeNode;
    TreeNode parent;
    int level;

}
class Solution {
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return root;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().subtreeWithAllDeepest(TreeNode.buildTree(new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4})));
    }
}