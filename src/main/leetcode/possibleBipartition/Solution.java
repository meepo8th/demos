package leetcode.possibleBipartition;

import java.util.*;

class Solution {
    public boolean possibleBipartition(int N, int[][] dislikes) {
        if (N == 2) {
            return true;
        }
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int[] dislike : dislikes) {
            if (!graph.containsKey(dislike[0])) {
                graph.put(dislike[0], new HashSet<>());
            }
            if (!graph.containsKey(dislike[1])) {
                graph.put(dislike[1], new HashSet<>());
            }
            graph.get(dislike[0]).add(dislike[1]);
            graph.get(dislike[1]).add(dislike[0]);
        }
        Set<Integer> visit = new HashSet<>();
        dfs(graph, dislikes[0][0], visit);
        return true;
    }

    private void dfs(Map<Integer, Set<Integer>> graph, int i, Set<Integer> visit) {
        visit.add(i);
        System.out.print(i);
        for (Integer node : graph.get(i)) {
            if(node.equals(i)){
                continue;
            }
            dfs(graph, node, visit);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().possibleBipartition(3, new int[][]{{1, 2}, {1, 3}, {2, 4}, {6, 1}}));
        System.out.println(new Solution().possibleBipartition(3, new int[][]{{1, 2}, {1, 3}, {2, 3}}));
        System.out.println(new Solution().possibleBipartition(5, new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {1, 5}}));

    }
}