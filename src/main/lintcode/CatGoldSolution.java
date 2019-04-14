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

    /**
     * 153. 数字组合 II
     * 中文English
     * 给出一组候选数字(C)和目标数字(T),找出C中所有的组合，使组合中数字的和为T。C中每个数字在每个组合中只能使用一次。
     * <p>
     * 样例
     * 给出一个例子，候选数字集合为[10,1,6,7,2,1,5] 和目标数字 8  ,
     * <p>
     * 解集为：[[1,7],[1,2,5],[2,6],[1,1,6]]
     * <p>
     * 注意事项
     * 所有的数字(包括目标数字)均为正整数。
     * 元素组合(a1, a2, … , ak)必须是非降序(ie, a1 ≤ a2 ≤ … ≤ ak)。
     * 解集不能包含重复的组合。
     *
     * @param num:    Given the candidate numbers
     * @param target: Given the target number
     * @return: All the combinations that sum to target
     */
    public List<List<Integer>> combinationSum2(int[] num, int target) {
        // write your code here
        List<List<Integer>> rtn = new ArrayList<>();
        Arrays.sort(num);
        List<Integer> unSelectList = new ArrayList<>(num.length);
        for (int i = 0; i < num.length; i++) {
            unSelectList.add(i);
        }
        combinationSum2(rtn, num, unSelectList, new ArrayList<Integer>(), 0, target);
        return rtn;
    }

    private void combinationSum2(List<List<Integer>> rtn, int[] A, List<Integer> unSelectList, List<Integer> selectList, int nowSum, int target) {
        if (nowSum == target) {
            List<Integer> list = new ArrayList<>();
            for (Integer i : selectList) {
                list.add(A[i]);
            }
            rtn.add(list);
        }
        if (nowSum > target) {
            return;
        }

        Set<Integer> addCache = new TreeSet<>();
        for (int i = 0; i < unSelectList.size(); i++) {
            if (!addCache.contains(A[unSelectList.get(i)]) && (selectList.size() == 0 || unSelectList.get(i) > selectList.get(selectList.size() - 1))) {
                combinationSum2(rtn, A, removeList(unSelectList, i), addList(selectList, unSelectList.get(i)), nowSum + A[unSelectList.get(i)], target);
                addCache.add(A[unSelectList.get(i)]);
            }
        }
    }

    /**
     * 535. 打劫房屋 III
     * 中文English
     * 在上次打劫完一条街道之后和一圈房屋之后，窃贼又发现了一个新的可以打劫的地方，但这次所有的房子组成的区域比较奇怪，聪明的窃贼考察地形之后，发现这次的地形是一颗二叉树。与前两次偷窃相似的是每个房子都存放着特定金额的钱。你面临的唯一约束条件是：相邻的房子装着相互联系的防盗系统，且当相邻的两个房子同一天被打劫时，该系统会自动报警。
     * <p>
     * 算一算，如果今晚去打劫，你最多可以得到多少钱，当然在不触动报警装置的情况下。
     * <p>
     * 样例
     * Example1
     * <p>
     * Input:  {3,2,3,#,3,#,1}
     * Output: 7
     * Explanation:
     * Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
     * 3
     * / \
     * 2   3
     * \   \
     * 3   1
     * Example2
     * <p>
     * Input:  {3,4,5,1,3,#,1}
     * Output: 9
     * Explanation:
     * Maximum amount of money the thief can rob = 4 + 5 = 9.
     * 3
     * / \
     * 4   5
     * / \   \
     * 1   3   1
     *
     * @param root: The root of binary tree.
     * @return: The maximum amount of money you can rob tonight
     */
    public int houseRobber3(TreeNode root) {
        // write your code here
        return houseRobber3(root, 0, 0);
    }

    private int houseRobber3(TreeNode node, int lSum, int rSum) {
        if (null == node) {
            return 0;
        }
        int ll = lSum + houseRobber3(node.left, 0, 0);
        int lr = rSum + houseRobber3(node.right, 0, 0);
        return Math.max(node.val + Math.max(houseRobber3(node.left.left, 0, 0), houseRobber3(node.left.right, 0, 0)) + Math.max(houseRobber3(node.right.left, 0, 0), houseRobber3(node.right.right, 0, 0)), ll + lr);
    }

    /**
     * 14. 二叉树的最长连续子序列 II
     * 中文English
     * 给定一棵二叉树，找到最长连续序列路径的长度(节点数)。
     * 路径起点跟终点可以为二叉树的任意节点。
     * <p>
     * 样例
     * 例1:
     * <p>
     * 输入:
     * {1,2,0,3}
     * 输出:
     * 4
     * 解释:
     * 1
     * / \
     * 2   0
     * /
     * 3
     * 0-1-2-3
     * 例2:
     * <p>
     * 输入:
     * {3,2,2}
     * 输出:
     * 2
     * 解释:
     * 3
     * / \
     * 2   2
     * 2-3
     *
     * @param root: the root of binary tree
     * @return: the length of the longest consecutive sequence path
     */
    public int longestConsecutive2(TreeNode root) {
        // write your code here
        Set<Integer> treeSet = new TreeSet<>();
        bfs(treeSet, root);
        int last=Integer.MIN_VALUE;
        int length = 1;
        int maxLength = 0;
        for (Integer i : treeSet) {
            if(i-last==1){
                length++;
            }else{
                maxLength = Math.max(length,maxLength);
                length=1;
            }
            last=i;
        }
        return Math.max(maxLength,length);
    }

    private void bfs(Set<Integer> treeSet, TreeNode node) {
        if (null == node) {
            return;
        }
        treeSet.add(node.val);
        bfs(treeSet, node.left);
        bfs(treeSet, node.right);
    }

    public static void main(String[] args) {
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        root.right=new TreeNode(3);
        root.left.left=new TreeNode(0);
        System.out.println(new CatGoldSolution().longestConsecutive2(root));

    }

}