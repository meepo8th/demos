package leetcode.leafSimilar;

import lintcode.BuildTree;
import lintcode.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

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
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (null != node.left) {
                queue.offer(node.left);
            }
            if (null != node.right) {
                queue.offer(node.right);
            }

            if (null == leaf && null == node.right) {
                leaf.add(node.val);
            }
        }
        return leaf;
    }

    public static void main(String[] args){
        System.out.println(new Solution().leafSimilar(BuildTree.class));
    }
}
