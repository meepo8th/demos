package leetcode.distancek;


import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class TreeNodeWithParent {
    TreeNode node;
    TreeNode parent;

    public TreeNodeWithParent(TreeNode node, TreeNode parent) {
        this.node = node;
        this.parent = parent;
    }
}

class Solution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        TreeNodeWithParent[] treeNodes = new TreeNodeWithParent[500];
        boolean[] visit = new boolean[500];
        traversals(null, root, treeNodes);
        List<List<Integer>> dp = new ArrayList<>();
        dp.add(new ArrayList<>());
        dp.get(0).add(target.val);
        visit[target.val] = true;
        for (int i = 0; i < K; i++) {
            dp.add(new ArrayList<>());
            for (Integer nodeVal : dp.get(i)) {
                addNode(dp.get(i + 1), nodeVal, treeNodes, visit);
            }
        }
        return dp.get(K);
    }

    /**
     * 增加节点
     *
     * @param nodes
     * @param nodeVal
     * @param treeNodes
     * @param visit
     */
    private void addNode(List<Integer> nodes, Integer nodeVal, TreeNodeWithParent[] treeNodes, boolean[] visit) {
        if (null != treeNodes[nodeVal]) {
            if (null != treeNodes[nodeVal].parent) {
                if (!visit[treeNodes[nodeVal].parent.val]) {
                    visit[treeNodes[nodeVal].parent.val] = true;
                    nodes.add(treeNodes[nodeVal].parent.val);
                }
            }
            if (null != treeNodes[nodeVal].node) {
                if (null != treeNodes[nodeVal].node.left) {
                    if (!visit[treeNodes[nodeVal].node.left.val]) {
                        visit[treeNodes[nodeVal].node.left.val] = true;
                        nodes.add(treeNodes[nodeVal].node.left.val);
                    }
                }
                if (null != treeNodes[nodeVal].node.right) {
                    if (!visit[treeNodes[nodeVal].node.right.val]) {
                        visit[treeNodes[nodeVal].node.right.val] = true;
                        nodes.add(treeNodes[nodeVal].node.right.val);
                    }
                }
            }
        }
    }


    /**
     * 遍历
     *
     * @param parent
     * @param node
     * @param treeNodes
     */
    private void traversals(TreeNode parent, TreeNode node, TreeNodeWithParent[] treeNodes) {
        if (null != node) {
            treeNodes[node.val] = new TreeNodeWithParent(parent, node);
            traversals(node, node.left, treeNodes);
            traversals(node, node.right, treeNodes);
        }
    }

}