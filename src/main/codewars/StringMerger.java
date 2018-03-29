package codewars;

public class StringMerger {

    public static boolean isMerge(String s, String part1, String part2) {
        if (s.length() != part1.length() + part2.length()) {
            return false;
        }
        boolean[][] visit = new boolean[part1.length()][part2.length()];
        int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int x = 0, y = 0;
        return dfs(visit, s, part1, part2, dir, 0, 0);
    }

    public static boolean dfs(boolean[][] visit, String s, String part1, String part2, int dir[][], int x, int y) {
        visit[x][y] = true;
        if (x == part1.length() - 1 && y == part2.length() - 1 && x + y == s.length() - 2) {
            return true;
        }
        for (int i = 0; i < 4; i++) {
            if (checkEdge(visit, s, part1, part2, x + dir[i][0], y + dir[i][1])) { // 按照规则生成下一个节点
                return dfs(visit, s, part1, part2, dir, x + dir[i][0], y + dir[i][1]);
            }
        }
        return false;
    }

    private static boolean checkEdge(boolean[][] visit, String s, String part1, String part2, int x, int y) {
        return x >= 0 && y >= 0 && x < part1.length() && y < part2.length() && visit[x][y] == false && (part1.charAt(x) == s.charAt(x + y) || part2.charAt(y) == s.charAt(x + y));
    }

    public static void main(String args[]) {
        System.out.println(isMerge("Can we merge it? Yes, we can!", "Can we merge ", "it? Yes, we can!"));
    }
}