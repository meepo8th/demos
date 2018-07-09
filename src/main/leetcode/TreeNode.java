package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
     * @param levels
     */
    public static void traversals(TreeNodeWithParentLevel parent, TreeNode node, int level, TreeNodeWithParentLevel[] treeNodes, Map<Integer, List<TreeNodeWithParentLevel>> levels) {
        if (null != node) {
            treeNodes[node.val] = new TreeNodeWithParentLevel(node, parent, level);
            if (!levels.containsKey(level)) {
                levels.put(level, new ArrayList<>());
            }
            levels.get(level).add(treeNodes[node.val]);
            traversals(treeNodes[node.val], node.left, level + 1, treeNodes, levels);
            traversals(treeNodes[node.val], node.right, level + 1, treeNodes, levels);
        }
    }
}