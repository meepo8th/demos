package leetcode.advantageCount;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution {
    public int[] advantageCount(int[] A, int[] B) {
        int[] rtn = new int[A.length];
        List<ValuePos> valuePos = new ArrayList<>(A.length);
        for (int i = 0; i < A.length; i++) {
            valuePos.add(new ValuePos(A[i], i));
        }
        Collections.sort(valuePos);
        for (int i = 0; i < B.length; i++) {
            rtn[i] = A[getMinLast(valuePos, B[i])];
        }
        return rtn;
    }

    private int getMinLast(List<ValuePos> valuePos, int i) {
        int rtn = 0;
        for (int j = 0; j < valuePos.size(); j++) {
            if (valuePos.get(j).value > i) {
                rtn = valuePos.get(j).pos;
                valuePos.remove(j);
                return rtn;
            }
        }

        rtn = valuePos.get(0).pos;
        valuePos.remove(0);

        return rtn;
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().advantageCount(new int[]{12, 24, 8, 32}, new int[]{13, 25, 32, 11})));
    }
}

class ValuePos implements Comparable<ValuePos> {
    Integer value;
    Integer pos;

    public ValuePos(int value, int pos) {
        this.value = value;
        this.pos = pos;
    }

    @Override
    public int compareTo(ValuePos o) {
        return value.compareTo(o.value);
    }
}