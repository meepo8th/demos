package lintcode;

import java.lang.reflect.Field;
import java.util.*;

import static funny.OnlyFunny.quickSort;

/**
 * LintCode Cat
 */
public class CatGoldSolution {

    /**
     * 带重复元素的排列
     * 中文English
     * 给出一个具有重复数字的列表，找出列表所有不同的排列。
     * <p>
     * 样例
     * Example 1:
     * <p>
     * Input: [1,1]
     * Output:
     * [
     * [1,1]
     * ]
     * Example 2:
     * <p>
     * Input: [1,2,2]
     * Output:
     * [
     * [1,2,2],
     * [2,1,2],
     * [2,2,1]
     * ]
     * 挑战
     * 使用递归和非递归分别完成该题。
     *
     * @param : A list of integers
     * @return: A list of unique permutations
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        // write your code here
        List<List<Integer>> rtn = new ArrayList<>();
        List<Integer> unSelectList = new ArrayList<>(nums.length);
        for (int n : nums) {
            unSelectList.add(n);
        }
        permuteUnique(rtn, unSelectList, new ArrayList<Integer>());
        return rtn;
    }

    private void permuteUnique(List<List<Integer>> rtn, List<Integer> unSelectList, List<Integer> selectList) {
        if (unSelectList.size() == 0) {
            rtn.add(selectList);
        }
        Set<Integer> addCache = new TreeSet<>();
        for (int i = 0; i < unSelectList.size(); i++) {
            if (!addCache.contains(unSelectList.get(i))) {
                permuteUnique(rtn, removeList(unSelectList, i), addList(selectList, unSelectList.get(i)));
                addCache.add(unSelectList.get(i));
            }
        }
    }

    private List<Integer> addList(List<Integer> selectList, Integer integer) {
        List<Integer> rtn = new ArrayList<>(selectList);
        rtn.add(integer);
        return rtn;
    }

    private List<Integer> removeList(List<Integer> unSelectList, int i) {
        List<Integer> rtn = new ArrayList<>(unSelectList);
        rtn.remove(i);
        return rtn;
    }

    /**
     * k数和 II
     * 中文English
     * 给定n个不同的正整数，整数k（1<= k <= n）以及一个目标数字。
     * <p>
     * 在这n个数里面找出K个数，使得这K个数的和等于目标数字，你需要找出所有满足要求的方案。
     * <p>
     * 样例
     * Example 1:
     * Input: [1,2,3,4], k = 2, target = 5
     * Output:  [[1,4],[2,3]]
     * <p>
     * <p>
     * Example 2:
     * Input: [1,3,4,6], k = 3, target = 8
     * Output:  [[1,3,4]]
     *
     * @param A:      an integer array
     * @param k:      a postive integer <= length(A)
     * @param target: an integer
     * @return: A list of lists of integer
     */
    public List<List<Integer>> kSumII(int[] A, int k, int target) {
        // write your code here
        List<List<Integer>> rtn = new ArrayList<>();
        List<Integer> unSelectList = new ArrayList<>(A.length);
        for (int i = 0; i < A.length; i++) {
            unSelectList.add(i);
        }
        kSumII(rtn, A, unSelectList, new ArrayList<Integer>(), 0, target, k);
        return rtn;
    }

    private void kSumII(List<List<Integer>> rtn, int[] A, List<Integer> unSelectList, List<Integer> selectList, int nowSum, int target, int maxLength) {
        if (nowSum == target && maxLength == selectList.size()) {
            List<Integer> list = new ArrayList<>();
            for (Integer i : selectList) {
                list.add(A[i]);
            }
            rtn.add(list);
        }
        if (selectList.size() == maxLength) {
            return;
        }
        for (int i = 0; i < unSelectList.size(); i++) {
            if (selectList.size() == 0 || unSelectList.get(i) > selectList.get(selectList.size() - 1)) {
                kSumII(rtn, A, removeList(unSelectList, i), addList(selectList, unSelectList.get(i)), nowSum + A[unSelectList.get(i)], target, maxLength);
            }
        }
    }

    /**
     * N皇后问题
     * 中文English
     * n皇后问题是将n个皇后放置在n*n的棋盘上，皇后彼此之间不能相互攻击。
     * <p>
     * 给定一个整数n，返回所有不同的n皇后问题的解决方案。
     * <p>
     * 每个解决方案包含一个明确的n皇后放置布局，其中“Q”和“.”分别表示一个女王和一个空位置。
     * <p>
     * 样例
     * 例1:
     * <p>
     * 输入:1
     * 输出:
     * [["Q"]]
     * <p>
     * <p>
     * 例2:
     * <p>
     * 输入:4
     * 输出:
     * [
     * // Solution 1
     * [".Q..",
     * "...Q",
     * "Q...",
     * "..Q."
     * ],
     * // Solution 2
     * ["..Q.",
     * "Q...",
     * "...Q",
     * ".Q.."
     * ]
     * ]
     * <p>
     * 挑战
     * 你能否不使用递归完成？
     *
     * @param n: The number of queens
     * @return: All distinct solutions
     */
    public List<List<String>> solveNQueens(int n) {
        // write your code here
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new CatGoldSolution().kSumII(new int[]{1, 2, 3, 4}, 2, 5));

    }

}