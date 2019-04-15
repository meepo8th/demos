package leetcode.binaryTreePruning;


import java.util.Stack;

class Solution {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = null;
        root.right = new TreeNode(0);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(1);
        System.out.println(new Solution().pruneTree(root));
    }

    public TreeNode pruneTree(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack.push(root);
        stack2.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (null != node) {
                stack.push(node.left);
                stack.push(node.right);
                stack2.push(node.left);
                stack2.push(node.right);
            }
        }
        while (!stack2.isEmpty()) {
            TreeNode node = stack2.pop();
            if (node != null) {
                if (null != node.left) {
                    if (node.left.val == 0 && node.left.left == null && node.left.right == null) {
                        node.left = null;
                    }
                }
                if (null != node.right) {
                    if (node.right.val == 0 && node.right.left == null && node.right.right == null) {
                        node.right = null;
                    }
                }
            }
        }
        return root;
    }

}