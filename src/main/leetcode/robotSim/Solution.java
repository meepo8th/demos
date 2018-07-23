package leetcode.robotSim;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        int[] pos = new int[]{0, 0};
        String directs = new String(new char[]{0, 1, 2, 3});
        Map<Integer,Set<Integer>> obs= new HashMap<>();
        int direct = 0;
        for (int command : commands) {
            switch (command) {
                case -1:
                    direct = directs.charAt((directs.indexOf(direct) + 1 + 4) % 4);
                    break;
                case -2:
                    direct = directs.charAt((directs.indexOf(direct) - 1 + 4) % 4);
                    break;
                default:
                    for (int i = 0; i < command; i++) {
                        switch (direct) {
                            case 0:
                                pos[1] += command;
                                break;
                            case 1:
                                pos[0] += command;
                                break;
                            case 2:
                                pos[1] -= command;
                                break;
                            case 3:
                                pos[0] -= command;
                                break;
                        }
                    }
                    break;

            }
        }
        return pos[0] * pos[0] + pos[1] * pos[1];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().robotSim(new int[]{4, -1, 3}, new int[][]{{}}));
        System.out.println(new Solution().robotSim(new int[]{4, -1, 4, -2, 4}, new int[][]{{2, 4}}));

    }
}