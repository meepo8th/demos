package leetcode.distancek;


import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

}

class Solution {
    public static void main(String[] args) {
        TreeNode node = new Solution().buildTree(new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4});
        System.out.println(new Solution().distanceK(node, new TreeNode(5), 0));
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        TreeNodeWithParent[] treeNodes = new TreeNodeWithParent[500];
        traversals(null, root, treeNodes);
        List<List<Node>> dp = new ArrayList<>();
        dp.add(new ArrayList<>());
        Node firstNode = new Node(target.val);
        firstNode.travedNodes.add(target.val);
        dp.get(0).add(firstNode);
        for (int i = 0; i < K; i++) {
            dp.add(new ArrayList<>());
            for (Node node : dp.get(i)) {
                addNode(dp.get(i + 1), node, treeNodes);
            }
        }
        List<Integer> rtn = new ArrayList<>();
        for (Node node : dp.get(K)) {
            rtn.add(node.val);
        }
        return rtn;
    }

    public TreeNode buildTree(Integer[] array) {
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

    private TreeNode addTreeNode(Integer[] array, int i) {
        if (i < array.length && i >= 0 && null != array[i]) {
            return new TreeNode(array[i]);
        }
        return null;
    }

    /**
     * 增加节点,禁止反向增加
     *
     * @param nodes
     * @param node
     * @param treeNodes
     */
    private void addNode(List<Node> nodes, Node node, TreeNodeWithParent[] treeNodes) {

        if (null != treeNodes[node.val].parent) {
            if (!node.travedNodes.contains(treeNodes[node.val].parent.val)) {
                Node addNode = new Node(treeNodes[node.val].parent.val);
                addNode.travedNodes.addAll(node.travedNodes);
                addNode.travedNodes.add(node.val);
                nodes.add(addNode);
            }
        }
        TreeNode treeNode = treeNodes[node.val].node;
        if (null != treeNode.left) {
            if (!node.travedNodes.contains(treeNode.left.val)) {
                Node addNode = new Node(treeNode.left.val);
                addNode.travedNodes.addAll(node.travedNodes);
                addNode.travedNodes.add(node.val);
                nodes.add(addNode);
            }
        }
        if (null != treeNode.right) {
            if (!node.travedNodes.contains(treeNode.right.val)) {
                Node addNode = new Node(treeNode.right.val);
                addNode.travedNodes.addAll(node.travedNodes);
                addNode.travedNodes.add(node.val);
                nodes.add(addNode);
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
            treeNodes[node.val] = new TreeNodeWithParent(node, parent);
            traversals(node, node.left, treeNodes);
            traversals(node, node.right, treeNodes);
        }
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

class Node {
    Integer val;
    Set<Integer> travedNodes = new HashSet<>();

    public Node(Integer val) {
        this.val = val;
    }

    @Override
    public int hashCode() {
        return null != val ? val.hashCode() : -1;
    }

    @Override
    public boolean equals(Object o) {
        return o != null && o instanceof Node && (((Node) o).val.equals(val));
    }
}