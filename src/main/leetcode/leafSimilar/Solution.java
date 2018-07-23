package leetcode.leafSimilar;




import leetcode.TreeNode;

import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leaf1 = findAllLeaf(root1);
        List<Integer> leaf2 = findAllLeaf(root2);
        return leaf1.equals(leaf2);
    }

    private List<Integer> findAllLeaf(TreeNode root) {
        List<Integer> leaf = new ArrayList<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.push(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.pop();
            if (null != node.left) {
                queue.push(node.left);
            }
            if (null != node.right) {
                queue.push(node.right);
            }

            if (null == node.left && null == node.right) {
                leaf.add(node.val);
            }
        }
        return leaf;
    }


    public static void main(String[] args){
        System.out.println(new Solution().leafSimilar(TreeNode.buildTree(new Integer[]{1,2,3}),TreeNode.buildTree(new Integer[]{1,3,2})));
    }
}
