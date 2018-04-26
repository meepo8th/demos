package leetcode.generateparenthesis;

import java.util.ArrayList;
import java.util.List;

import java.lang.Math;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> rtn = new ArrayList<>();
        return rtn;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().generateParenthesis(1));
        List<Integer> arrays= new ArrayList<>();
        for(int i=0;i<100000;i++) {
            arrays.add((int) (10*Math.random()));
        }
        System.out.println(arrays);
    }
}