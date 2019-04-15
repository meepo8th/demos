package leetcode.loudandrich;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().loudAndRich(new int[][]{{1, 0}, {2, 1}, {3, 1}, {3, 7}, {4, 3}, {5, 3}, {6, 3}}, new int[]{3, 2, 5, 4, 6, 1, 7, 0})));
    }

    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int[] rtn = new int[quiet.length];
        Richer[] mapRicher = new Richer[quiet.length];
        Richer[] richerMin = new Richer[quiet.length];
        for (int i = 0; i < quiet.length; i++) {
            mapRicher[i] = new Richer(i, quiet[i]);
        }
        for (int[] compare : richer) {
            mapRicher[compare[1]].child.add(mapRicher[compare[0]]);
        }
        for (int i = 0; i < quiet.length; i++) {
            rtn[i] = minQuiet(mapRicher[i], richerMin).id;
        }
        return rtn;
    }

    /**
     * Richer
     *
     * @param richer
     * @param richerMin
     * @return
     */
    private Richer minQuiet(Richer richer, Richer[] richerMin) {
        if (null != richerMin[richer.id]) {
            return richerMin[richer.id];
        }
        Richer minRicher = richer;
        Richer childMin;
        for (Richer child : richer.child) {
            if (child.child.isEmpty()) {
                childMin = child;
            } else {
                childMin = minQuiet(child, richerMin);
            }
            if (minRicher.quiet > childMin.quiet) {
                minRicher = childMin;
            }
        }
        richerMin[richer.id] = minRicher;
        return minRicher;
    }

    class Richer {
        int id;
        int quiet;
        List<Richer> child = new ArrayList<>(10);

        public Richer(int id, int quiet) {
            this.id = id;
            this.quiet = quiet;
        }
    }
}