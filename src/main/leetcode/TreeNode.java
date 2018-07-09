package leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * 树节点
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    /**
     * 建立树
     *
     * @param array
     * @return
     */
    public static TreeNode buildTree(Integer[] array) {
        List<TreeNode> queue = new LinkedList<>();
        TreeNode node = new TreeNode(array[0]);
        queue.add(node);
        int i = 1;
        while (!queue.isEmpty() && i < array.length) {
            TreeNode nowNode = queue.get(0);
            queue.remove(0);
            if (null != nowNode) {
                nowNode.left = addTreeNode(array, i++);
                nowNode.right = addTreeNode(array, i++);
                queue.add(nowNode.left);
                queue.add(nowNode.right);
            }
        }

        return node;
    }

    /**
     * 增加树节点
     *
     * @param array
     * @param i
     * @return
     */
    private static TreeNode addTreeNode(Integer[] array, int i) {
        if (i < array.length && i >= 0 && null != array[i]) {
            return new TreeNode(array[i]);
        }
        return null;
    }

    /**
     * 遍历
     *
     * @param parent
     * @param node
     * @param treeNodes
     */
    public static void traversals(TreeNode parent, TreeNode node, int level, TreeNodeWithParentLevel[] treeNodes) {
        if (null != node) {
            treeNodes[node.val] = new TreeNodeWithParentLevel(node, parent, level);
            traversals(node, node.left, level + 1, treeNodes);
            traversals(node, node.right, level + 1, treeNodes);
        }
    }
}