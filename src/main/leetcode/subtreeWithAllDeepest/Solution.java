package leetcode.subtreeWithAllDeepest;

import leetcode.TreeNode;
import leetcode.TreeNodeWithParentLevel;

import java.util.*;

class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().subtreeWithAllDeepest(TreeNode.buildTree(new Integer[]{0, 3, 1, 4, null, 2, null, null, 6, null, 5})));
    }

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        TreeNodeWithParentLevel[] nodes = new TreeNodeWithParentLevel[500];
        Map<Integer, List<TreeNodeWithParentLevel>> levels = new HashMap<>();
        TreeNode.traversals(null, root, 0, nodes, levels);
        int maxLevel = -1;
        for (int level : levels.keySet()) {
            if (level >= maxLevel) {
                maxLevel = level;
            }
        }
        List<TreeNodeWithParentLevel> deepest = levels.get(maxLevel);
        List<TreeNodeWithParentLevel> parents = new ArrayList<>();
        parents.addAll(deepest);
        while (parents.size() != 1) {
            Set<TreeNodeWithParentLevel> tmpParents = new HashSet<>();
            for (TreeNodeWithParentLevel child : parents) {
                tmpParents.add(child.parent);
            }
            parents.clear();
            parents.addAll(tmpParents);
        }
        return parents.get(0).node;

    }
}