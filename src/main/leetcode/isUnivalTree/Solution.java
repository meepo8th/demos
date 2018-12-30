package leetcode.isUnivalTree;

import leetcode.TreeNode;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public boolean isUnivalTree(TreeNode root) {
        return isUnivalTree(root, root.val);
    }

    private boolean isUnivalTree(TreeNode node, int val) {
        if(null==node){
            return true;
        }
        return node.val == val && isUnivalTree(node.left, node.val) && isUnivalTree(node.right, node.val);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isUnivalTree(TreeNode.buildTree(new Integer[]{1, 1, 1, 1, 1, null, 1})));
        System.out.println(new Solution().isUnivalTree(TreeNode.buildTree(new Integer[]{2, 2, 2, 5, 2})));
    }
}