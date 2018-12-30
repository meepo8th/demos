package leetcode.numsSameConsecDiff;

import java.util.*;

class Solution {
    static ArrayList<Map<Integer,ArrayList<Integer>>>[] cache = new ArrayList[10];

    static {
        for (int i = 0; i < 10; i++) {
            cache[i] = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                if (j + i < 10) {
                    cache[i].add(new StartEnd(j, j + i));
                    cache[i].add(new StartEnd(j + i, j));
                }
            }
        }
    }

    public int[] numsSameConsecDiff(int N, int K) {
        Set<Integer> rtn = new HashSet<>();
        if(N==1){
            return new int[]{0,1,2,3,4,5,6,7,8,9};
        }
        for (StartEnd startEnds : cache[K]) {
            int tmp = 0;
            for (int i = 0; i < N; i++) {
                if (i % 2 == 0) {
                    tmp = (tmp * 10) + startEnds.getStart();
                } else {
                    tmp = (tmp * 10) + startEnds.getEnd();
                }
            }
            if (String.valueOf(tmp).length() == N) {
                rtn.add(tmp);
            }
        }
        int[] rtnArray = new int[rtn.size()];
        int i = 0;
        for (Integer integer : rtn) {
            rtnArray[i++] = integer;
        }
        return rtnArray;
    }

    static class StartEnd {
        public StartEnd(int start, int end) {
            this.start = start;
            this.end = end;
        }

        int start;
        int end;

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        @Override
        public int hashCode() {
            return start * 31 + end;
        }

        @Override
        public boolean equals(Object o) {
            StartEnd startEnd = (StartEnd) o;
            return null != o && startEnd.getStart() == getStart() && startEnd.getEnd() == getEnd();
        }

        @Override
        public String toString() {
            return "StartEnd{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().numsSameConsecDiff(3, 7)));
        System.out.println(Arrays.toString(new Solution().numsSameConsecDiff(2, 1)));
        System.out.println(Arrays.toString(new Solution().numsSameConsecDiff(1, 6)));
        System.out.println(Arrays.toString(new Solution().numsSameConsecDiff(3, 1)));
    }
}