package lintcode;

import com.mysql.cj.util.StringUtils;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
    public int val;
    public TreeNode left, right;

    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }

    public TreeNode(String nodeArray) {
        String[] nodes = nodeArray.split(",");
        Queue<TreeNode> treeNodeQueue = new LinkedList();
        for (int i = 0; i < nodes.length; i++) {
            if (StringUtils.isEmptyOrWhitespaceOnly(nodes[i])) {
                continue;
            }
            if (treeNodeQueue.isEmpty()) {
                this.val = Integer.parseInt(nodes[i]);
                treeNodeQueue.add(this);
            } else {
                TreeNode treeNode = treeNodeQueue.poll();
                if (StringUtils.isStrictlyNumeric(nodes[i])) {
                    treeNode.left = new TreeNode(Integer.parseInt(nodes[i]));
                    treeNodeQueue.add(treeNode.left);
                }
                if (++i < nodes.length && StringUtils.isStrictlyNumeric(nodes[i])) {
                    treeNode.right = new TreeNode(Integer.parseInt(nodes[i]));
                    treeNodeQueue.add(treeNode.right);
                }
            }

        }
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode("1,2,#,3,4,5,#,7,8");
        System.out.println(node);
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}