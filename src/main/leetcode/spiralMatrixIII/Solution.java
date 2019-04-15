package leetcode.spiralMatrixIII;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().spiralMatrixIII(1, 4, 0, 0));
        System.out.println(new Solution().spiralMatrixIII(5, 6, 1, 4));
    }

    public int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
        List<int[]> rtn = new ArrayList<>();
        int max = R * C;
        int[][] visit = new int[C * 3][R * 3];
        for (int i = C; i < 2 * C; i++) {
            for (int j = R; j < 2 * R; j++) {
                visit[i][j] = 1;
            }
        }
        int[] pos = new int[]{C + r0, R + c0};
        visit[pos[0]][pos[1]] = -1;
        int direct = 0;
        while (max > 0) {
            System.out.println(Arrays.toString(pos));

            switch (direct % 4) {
                case 0:
                    pos = new int[]{pos[0] + 1, pos[1]};
                    if (visit[pos[0] + 1][pos[1]] != -1) {
                        if (visit[pos[0] + 1][pos[1]] == 1) {
                            rtn.add(new int[]{pos[0] - C, pos[1] - R});
                            max--;
                        }
                        direct += 1;
                    }
                    visit[pos[0] + 1][pos[1]] = -1;
                    break;
                case 1:
                    pos = new int[]{pos[0], pos[1] + 1};
                    if (visit[pos[0]][pos[1] + 1] != -1) {
                        if (visit[pos[0]][pos[1] + 1] == 1) {

                            max--;
                        }
                        direct += 1;
                    }
                    visit[pos[0]][pos[1] + 1] = -1;

                    break;
                case 2:
                    if (visit[pos[0] - 1][pos[1]] != -1) {
                        if (visit[pos[0] - 1][pos[1]] == 1) {
                            max--;
                        }
                        direct += 1;
                    }
                    visit[pos[0] - 1][pos[1]] = -1;
                    pos = new int[]{pos[0] - 1, pos[1]};
                    break;
                case 3:
                    if (visit[pos[0]][pos[1] - 1] != -1) {
                        if (visit[pos[0]][pos[1] - 1] == 1) {
                            max--;
                        }
                        direct += 1;
                    }
                    visit[pos[0]][pos[1] - 1] = -1;
                    pos = new int[]{pos[0], pos[1] + 1};
                    break;
            }
        }
        return rtn.toArray(new int[0][]);
    }
}