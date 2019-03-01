package lintcode;

import java.lang.reflect.Field;
import java.util.*;

import static funny.OnlyFunny.quickSort;

/**
 * LintCode Cat
 */
public class CatSolution {

    /**
     * 水仙花数
     *
     * @param n
     * @return
     */
    public static List<Integer> getNarcissisticNumbers(int n) {
        // write your code here
        List<Integer> rtn = new ArrayList<>();
        int[] powMap = new int[10];
        for (int i = 0; i < powMap.length; i++) {
            powMap[i] = (int) Math.pow(i, n);
        }
        int start = Math.max(0, (int) Math.pow(10, n - 1) - 1);
        int end = (int) Math.pow(10, n);
        for (int i = start; i < end; i++) {
            int now = i;
            int sum = 0;
            while (now > 0) {
                sum += powMap[now % 10];
                now = now / 10;
            }
            if (i == sum) {
                rtn.add(i);
            }
        }
        return rtn;
    }

    /**
     * 旋转字符串（O(n)空间)
     *
     * @param str
     * @param offset
     * @return
     */
    public static String rotateString(char[] str, int offset) {
        if (str.length == 0) {
            return "";
        }
        offset = (offset + str.length) % str.length;
        String start = new String(str);
        String end = start.substring(start.length() - offset) + start.substring(0, start.length() - offset);
        for (int i = 0; i < start.length(); i++) {
            str[i] = end.charAt(i);
        }
        return end;
    }


    /**
     * 是否唯一字符串
     *
     * @param str
     * @return
     */
    public boolean isUnique(String str) {
        if (str.length() > Character.MAX_VALUE) {
            return false;
        }
        Set<Character> cache = new HashSet<>();
        for (int i = 0; i < str.length(); i++) {
            if (cache.contains(str.charAt(i))) {
                return false;
            }
            cache.add(str.charAt(i));
        }
        return true;
    }

    /**
     * 查找第一个不重复的字符
     *
     * @param str
     * @return
     */
    public char firstUniqChar(String str) {
        Map<Character, Integer> cache = new LinkedHashMap<>();
        for (int i = 0; i < str.length(); i++) {
            if (cache.containsKey(str.charAt(i))) {
                cache.put(str.charAt(i), cache.get(str.charAt(i)) + 1);
            } else {
                cache.put(str.charAt(i), 0);
            }
        }
        for (Map.Entry<Character, Integer> entry : cache.entrySet()) {
            if (entry.getValue() == 0) {
                return entry.getKey();
            }
        }
        return '0';
    }

    /**
     * 反转单词
     *
     * @param s
     * @return
     */
    public static String reverseWords(String s) {
        StringBuilder sb = new StringBuilder("");
        // write your code here
        String[] cache = s.replaceAll(" +", " ").split(" ");
        for (int i = cache.length - 1; i >= 0; i--) {
            sb.append(cache[i] + " ");
        }
        return sb.toString().trim();
    }

    /**
     * 语法错误计数
     *
     * @param s
     * @return
     */
    public static int count(String s) {
        // Write your code here.
        int count = 0;
        String[] sentences = s.split("\\.");
        for (String sentence : sentences) {
            sentence = sentence.trim();
            if (sentence.length() == 0) {
                continue;
            }
            if (Character.isLowerCase(sentence.charAt(0))) {
                count++;
            }
            String[] words = sentence.split(",| +");
            for (String word : words) {
                for (int i = 0; i < word.length(); i++) {
                    if (Character.isUpperCase(word.charAt(i)) && i > 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    /**
     * 出现最多的字符数
     *
     * @param str
     * @return
     */
    public int mostFrequentlyAppearingLetters(String str) {
        Map<Character, Integer> cache = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            if (cache.containsKey(str.charAt(i))) {
                cache.put(str.charAt(i), cache.get(str.charAt(i)) + 1);
            } else {
                cache.put(str.charAt(i), 1);
            }
        }
        int max = 0;
        for (Map.Entry<Character, Integer> entry : cache.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
            }
        }
        return max;
    }

    /**
     * 合法表达式
     *
     * @param str
     * @return
     */
    public static boolean isLegalIdentifier(String str) {
        return str.length() > 0 && (str.charAt(0) < '0' || str.charAt(0) > '9') && str.replaceAll("[a-z|A-Z|0-9|_]", "").length() == 0;
    }

    /**
     * 数字排序
     *
     * @param A
     */
    public void sortIntegers(int[] A) {
        quickSort(A, 0, A.length);
    }

    /**
     * 多关键词排序
     *
     * @param array: the input array
     * @return: the sorted array
     */
    public int[][] multiSort(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (compare(array[i], array[j]) < 0) {
                    int[] tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;
                }
            }
        }
        return array;
    }

    private int compare(int[] array1, int[] array2) {
        if (array1[1] > array2[1]) {
            return 1;
        } else if (array1[1] < array2[1]) {
            return -1;
        } else {
            return array2[0] - array1[0];
        }
    }

    /**
     * 去除重复元素
     *
     * @param nums: an array of integers
     * @return: the number of unique integers
     */
    public static int deduplication(int[] nums) {
        Set<Integer> cache = new HashSet<>();
        for (int num : nums) {
            cache.add(num);
        }
        int i = 0;
        for (Integer integer : cache) {
            nums[i++] = integer;
        }
        return cache.size();
    }

    /**
     * 搜素插入的位置,需改为二分查找
     *
     * @param A
     * @param target
     * @return
     */
    public static int searchInsert(int[] A, int target) {
        // write your code here
        int index = -1;
        for (int i = 0; i < A.length; i++) {
            if (target <= A[i]) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            index = A.length;
        }
        return index;
    }

    /**
     * 时间区间是否冲突(O(n^2))
     *
     * @param intervals: an array of meeting time intervals
     * @return: if a person could attend all meetings
     */
    public boolean canAttendMeetings(List<Interval> intervals) {
        // Write your code here
        for (int i = 0; i < intervals.size(); i++) {
            for (int j = i + 1; j < intervals.size(); j++) {
                if (intervals.get(i).start <= intervals.get(j).start && intervals.get(i).end > intervals.get(j).start) {
                    return false;
                }
                if (intervals.get(i).start >= intervals.get(j).start && intervals.get(j).end > intervals.get(i).start) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 时间区间
     * Definition of Interval:
     */
    class Interval {
        int start;
        int end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    /**
     * 是否能找到n个数的和小于limit
     *
     * @param m:   the limit
     * @param k:   the sum of choose
     * @param arr: the array
     * @return: yes or no
     */
    public String depress(int m, int k, int[] arr) {
        // Write your code here.
        Arrays.sort(arr);
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += arr[i];
            if (sum > m) {
                return "no";
            }
        }
        return "yes";
    }

    /**
     * 链表倒序
     *
     * @param head: the given linked list
     * @return: the array that store the values in reverse order
     */
    public List<Integer> reverseStore(ListNode head) {
        // write your code here
        List<Integer> rtn = new ArrayList<>();
        for (ListNode now = head; now != null; now = now.next) {
            rtn.add(now.val);
        }
        for (int i = 0; i < rtn.size() / 2; i++) {
            int tmp = rtn.get(i);
            rtn.set(i, rtn.get(rtn.size() - 1 - i));
            rtn.set(rtn.size() - i - 1, tmp);
        }
        return rtn;
    }

    /**
     * 找链表的中心节点
     *
     * @param head: the head of linked list.
     * @return: a middle node of the linked list
     */
    public ListNode middleNode(ListNode head) {
        // write your code here
        ListNode fast = head;
        ListNode slow = head;
        while (null != fast && null != fast.next) {
            fast = fast.next;
            fast = fast.next;
            if (null != fast) {
                slow = slow.next;
            }
        }
        return slow;
    }

    /**
     * 在链表中插入一个节点
     *
     * @param head: The head of linked list.
     * @param val:  An integer.
     * @return: The head of new linked list.
     */
    public ListNode insertNode(ListNode head, int val) {
        // write your code here
        ListNode node = new ListNode(val);
        ListNode pre = head;
        if (null == pre || val < pre.val) {
            node.next = pre;
            return node;
        }
        ListNode now = pre.next;
        while (null != now) {
            if (val <= now.val) {
                pre.next = node;
                node.next = now;
                return head;
            }
            pre = pre.next;
            now = now.next;
        }
        pre.next = node;
        return head;
    }

    /**
     * 计算链表的长度
     *
     * @param head: the first node of linked list.
     * @return: An integer
     */
    public int countNodes(ListNode head) {
        int count = 0;
        while (null != head) {
            count++;
            head = head.next;
        }
        return count;
        // write your code here
    }

    /**
     * 删除链表中的n->m个节点
     *
     * @param head: The first node of linked list
     * @param n:    the start index
     * @param m:    the end node
     * @return: A ListNode
     */
    public static ListNode deleteNode(ListNode head, int n, int m) {
        // Write your code here
        ListNode now = head;
        ListNode pre = null;
        for (int i = 0; i <= m && null != now; i++) {
            if (i >= n) {
                if (null == pre) {
                    head = head.next;
                } else {
                    pre.next = now.next;
                    now = pre.next;
                }
            } else {
                pre = now;
                now = now.next;
            }
        }
        return head;
    }

    /**
     * 统计非负奇数节点
     *
     * @param head:
     * @return: nothing
     */
    public int countNodesII(ListNode head) {
        int count = 0;
        while (null != head) {
            if (head.val > 0 && head.val % 2 == 0) {
                count++;
            }
            head = head.next;
        }
        return count;
    }

    /**
     * 二叉树的最大深度
     *
     * @param root: The root of binary tree.
     * @return: An integer
     */
    public int maxDepth(TreeNode root) {
        if (null == root) {
            return 0;
        }
        return Math.max(maxDepth(root.left, 1), maxDepth(root.right, 1));
    }

    /**
     * 二叉树的最大深度
     *
     * @param root
     * @param level 当前level
     * @return
     */
    public int maxDepth(TreeNode root, int level) {
        if (null == root) {
            return level;
        }
        return Math.max(maxDepth(root.left, level + 1), maxDepth(root.right, level + 1));
    }

    /**
     * 反转二叉树
     *
     * @param root: a TreeNode, the root of the binary tree
     * @return: nothing
     */
    public void invertBinaryTree(TreeNode root) {
        // write your code here
        if (null != root) {
            TreeNode cache = root.left;
            root.left = root.right;
            root.right = cache;
            invertBinaryTree(root.left);
            invertBinaryTree(root.right);
        }
    }

    /**
     * 二叉树的路径和
     *
     * @param root:   the root of binary tree
     * @param target: An integer
     * @return: all valid paths
     */
    public List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {
        int nowSum = 0;
        List<List<Integer>> rtnList = new ArrayList<>();
        if (null != root) {
            binaryTreePathSum(root, target, nowSum, new ArrayList<>(), rtnList);
        }
        return rtnList;
    }

    private void binaryTreePathSum(TreeNode root, int target, int nowSum, List<Integer> cacheList, List<List<Integer>> rtnList) {
        List<Integer> nowList = new ArrayList<>();
        nowList.addAll(cacheList);
        nowList.add(root.val);
        nowSum = nowSum + root.val;
        if (null == root.left && null == root.right) {
            if (nowSum == target) {
                rtnList.add(nowList);
            }
            return;
        }
        if (null != root.left) {
            binaryTreePathSum(root.left, target, nowSum, nowList, rtnList);
        }
        if (null != root.right) {
            binaryTreePathSum(root.right, target, nowSum, nowList, rtnList);
        }
    }

    /**
     * 二叉树的所有路径
     *
     * @param root: the root of the binary tree
     * @return: all root-to-leaf paths
     */
    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> rtnList = new ArrayList<>();
        if (null != root) {
            binaryTreePaths(root, "", rtnList);
        }
        return rtnList;
    }

    private static void binaryTreePaths(TreeNode root, String s, List<String> rtnList) {
        String nowString = String.format("%s->%s", s, root.val);
        if (null == root.left && null == root.right) {
            rtnList.add(nowString.replaceFirst("->", ""));
            return;
        }
        if (null != root.left) {
            binaryTreePaths(root.left, nowString, rtnList);
        }
        if (null != root.right) {
            binaryTreePaths(root.right, nowString, rtnList);
        }
    }

    /**
     * 中序遍历
     *
     * @param root: A Tree
     * @return: Inorder in ArrayList which contains node values.
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        // write your code here
        List<Integer> rtnList = new ArrayList<>();
        inorderTraversal(root, rtnList);
        return rtnList;
    }

    private void inorderTraversal(TreeNode root, List<Integer> rtnList) {
        if (null != root) {
            inorderTraversal(root.left, rtnList);
            rtnList.add(root.val);
            inorderTraversal(root.right, rtnList);
        }
    }

    /**
     * 前序遍历
     *
     * @param root: A Tree
     * @return: Preorder in ArrayList which contains node values.
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> rtnList = new ArrayList<>();
        inorderTraversal(root, rtnList);
        return rtnList;
    }

    private void preorderTraversal(TreeNode root, List<Integer> rtnList) {
        if (null != root) {
            rtnList.add(root.val);
            preorderTraversal(root.left, rtnList);
            preorderTraversal(root.right, rtnList);
        }
    }

    /**
     * 组合加判断素数
     *
     * @param a: the n numbers
     * @param k: the number of integers you can choose
     * @return: how many ways that the sum of the k integers is a prime number
     */
    public int getWays(int[] a, int k) {
        // Write your code here
        int ways = 0;
        List<Integer> combine = new ArrayList<>();
        ArrayList select = new ArrayList(a.length);
        for (int i : a) {
            select.add(i);
        }
        combine(combine, select, 0, k);
        for (Integer i : combine) {
            if (isPrime(i)) {
                ways++;
            }
        }
        return combine.size();
    }

    private void combine(List<Integer> combine, ArrayList<Integer> unSelect, int sum, int last) {
        if (last == 0 || unSelect.isEmpty()) {
            combine.add(sum);
            return;
        }
        for (Integer i : unSelect) {
            ArrayList<Integer> select = new ArrayList<>(unSelect);
            select.remove(i);
            combine(combine, select, sum + i, last - 1);
        }
    }

    /**
     * 是否是素数
     *
     * @param n
     * @return
     */
    public boolean isPrime(int n) {
        if (n <= 2) {
            return n == 2;
        }
        if (n % 2 == 0) {
            return false;
        }
        for (int i = 3; i <= (int) Math.sqrt(n); i += 2) {
            if (n % i == 0 && n != i) {
                return false;
            }
        }
        return true;
    }

    /**
     * 返回节点数
     *
     * @param root: the root of the binary tree
     * @return: the number of nodes
     */
    public static int getAns(TreeNode root) {
        // Write your code here
        if (null == root) {
            return 0;
        }
        int count = 0;
        Deque<TreeNode> queue = new ArrayDeque();
        queue.push(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.pop();
            if (node != null) {
                count++;
            }
            if (null != node.left) {
                queue.push(node.left);
            }
            if (null != node.right) {
                queue.push(node.right);
            }
        }
        return count;
    }


    /**
     * 矩阵迷宫搜索，dfs
     *
     * @param maze: the maze
     * @return: Can they reunion?
     */
    public boolean findHer(String[] maze) {
        // Write your code here
        int[] startPosition = new int[2];
        char[][] mazeCache = findStartPosition(maze, startPosition);
        Stack<int[]> stack = new Stack<>();
        stack.add(startPosition);
        while (!stack.isEmpty()) {
            int[] nowPos = stack.pop();
            if (mazeCache[nowPos[0]][nowPos[1]] == 'T') {
                return true;
            }
            flagVisited(mazeCache, '*', nowPos);
            List<int[]> paths = findNextPaths(mazeCache, nowPos, '*');
            for (int[] pos : paths) {
                stack.push(pos);
            }
        }
        return false;
    }


    public static Field charField;

    static {
        Field[] fs = String.class.getDeclaredFields();
        for (Field f : fs) {
            if ("value".equals(f.getName())) {
                charField = f;
                f.setAccessible(true);
            }
        }
    }

    /**
     * 标记已读
     *
     * @param maze
     * @param blockChar
     * @param nowPosition
     */
    private void flagVisited(char[][] maze, char blockChar, int[] nowPosition) {
        char[] value = maze[nowPosition[0]];
        value[nowPosition[1]] = blockChar;
    }

    /**
     * 通过反射获取字符串的char[]
     *
     * @param s
     * @return
     */
    public static char[] getStringValue(String s) {
        try {
            return (char[]) charField.get(s);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return new char[]{};
    }


    /**
     * 查找起始位置
     *
     * @param maze
     * @param startPosition
     * @return
     */
    private final char[][] findStartPosition(String[] maze, int[] startPosition) {
        char[][] rtn = new char[maze.length][];
        for (int i = 0; i < maze.length; i++) {
            String line = maze[i];
            for (int j = 0; j < line.length(); j++) {
                if ('S' == line.charAt(j)) {
                    startPosition[0] = i;
                    startPosition[1] = j;
                }
                rtn[i] = getStringValue(line);
            }
        }
        return rtn;
    }

    /**
     * 查找下一步路径
     *
     * @param maze
     * @param nowPosition
     * @param blockChar
     * @return
     */
    private final List<int[]> findNextPaths(char[][] maze, int[] nowPosition, char blockChar) {
        List<int[]> rtnList = new LinkedList<>();
        /**
         * 上
         */
        if (nowPosition[0] - 1 >= 0) {
            int[] upPosition = new int[]{nowPosition[0] - 1, nowPosition[1]};
            if (!positionBlock(maze, upPosition, blockChar)) {
                rtnList.add(upPosition);
            }
        }
        /**
         * 下
         */
        if (nowPosition[0] + 1 < maze.length) {
            int[] downPosition = new int[]{nowPosition[0] + 1, nowPosition[1]};
            if (!positionBlock(maze, downPosition, blockChar)) {
                rtnList.add(downPosition);
            }
        }
        /**
         * 左
         */
        if (nowPosition[1] - 1 >= 0) {
            int[] leftPosition = new int[]{nowPosition[0], nowPosition[1] - 1};
            if (!positionBlock(maze, leftPosition, blockChar)) {
                rtnList.add(leftPosition);
            }
        }
        /**
         * 右
         */
        if (nowPosition[1] + 1 < maze[nowPosition[0]].length) {
            int[] rightPosition = new int[]{nowPosition[0], nowPosition[1] + 1};
            if (!positionBlock(maze, rightPosition, blockChar)) {
                rtnList.add(rightPosition);
            }
        }
        return rtnList;
    }

    /**
     * 是否是阻塞的位置
     *
     * @param maze
     * @param position
     * @param blockChar
     * @return
     */
    private final boolean positionBlock(char[][] maze, int[] position, char blockChar) {
        return maze[position[0]][position[1]] == blockChar;
    }

    /**
     * 排序
     *
     * @param A: an integer array
     * @return: nothing
     */
    public void sortIntegers2(int[] A) {
        // write your code here
        quickSort(A);
    }

    /**
     * 三数之和为0
     *
     * @param numbers: Give an array numbers of n integer
     * @return: Find all unique triplets in the array which gives the sum of zero.
     */
    public List<List<Integer>> threeSum(int[] numbers) {
        // write your code here
        List<List<Integer>> rtn = new ArrayList<>();
        List<Integer> selected = new ArrayList<>();
        List<Integer> unSelect = new ArrayList<>();
        for (int n : numbers) {
            unSelect.add(n);
        }
        numSum(rtn, selected, unSelect, 0, 3, 0);
        return rtn;
    }

    private void numSum(List<List<Integer>> rtn, List<Integer> selected, List<Integer> unSelect, int sum, int num, int target) {
        if (selected.size() == num) {
            if (sum == target) {
                rtn.add(selected);
            }
            Iterator<Integer> iterator = unSelect.iterator();
            while (iterator.hasNext()) {
                List<Integer> nowSelect = new ArrayList<>(selected);
                List<Integer> nowUnSelect = new ArrayList<>(unSelect);
                Integer now = iterator.next();
                nowSelect.add(now);
                nowUnSelect.remove(now);
                numSum(rtn, nowSelect, nowUnSelect, sum + now, num, target);
            }
        }
    }

    /**
     * 数组划分
     *
     * @param nums: The integer array you should partition
     * @param k:    An integer
     * @return: The index after partition
     */
    public static int partitionArray(int[] nums, int k) {
        // write your code here
        boolean find = false;
        int start = 0;
        int end = nums.length - 1;
        int tmp;
        while (start != end) {
            while (nums[end] >= k && end > start) {
                end--;
            }
            while (nums[start] < k && end > start) {
                find = true;
                start++;
            }
            if (start < end) {
                tmp = nums[start];
                nums[start] = nums[end];
                nums[end] = tmp;
                find = true;
            }
        }
        return find ? start + 1 : 0;
    }

    /**
     * 第k小的数(O(nlogn))
     *
     * @param k:    An integer
     * @param nums: An integer array
     * @return: kth smallest element
     */
    public int kthSmallest(int k, int[] nums) {
        // write your code here
        return 0;
    }

    /**
     * 第k大的数
     *
     * @param n:    An integer
     * @param nums: An array
     * @return: the Kth largest element
     */
    public int kthLargestElement(int n, int[] nums) {
        // write your code here
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(Math.ceil(0.01));
        System.out.println(Math.floor(0.01));
    }
}