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
            if (s.length() > 1000) {
                return (char[]) charField.get(s);
            } else {
                return s.toCharArray();
            }
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
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(numbers);
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] > 0) {
                break;
            }
            if (i > 0 && numbers[i] == numbers[i - 1]) {
                continue;
            }
            int j = numbers.length - 1;
            int target = 0 - numbers[i];
            int k = i + 1;
            while (k < j) {
                if (numbers[k] + numbers[j] == target) {
                    List<Integer> item = Arrays.asList(numbers[i], numbers[k], numbers[j]);
                    result.add(item);
                    while (k < j && numbers[k] == numbers[k + 1]) {
                        k++;
                    }
                    while (k < j && numbers[j] == numbers[j - 1]) {
                        j--;
                    }
                    k++;
                    j--;
                } else if (numbers[k] + numbers[j] < target) {
                    k++;
                } else {
                    j--;
                }
            }
        }
        return result;
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
        Arrays.sort(nums);
        return nums[k];
    }

    /**
     * 第k大的数(O(n))
     *
     * @param n:    An integer
     * @param nums: An array
     * @return: the Kth largest element
     */
    public static int kthLargestElement(int n, int[] nums) {
        // write your code here
        int start = 0, end = nums.length - 1;
        while (start != n && start <= end) {
            int i = start, j = end;
            int base = nums[start];
            while (i != j) {
                if (nums[i] <= base && i < j) {
                    i++;
                }
                if (nums[j] >= base && i < j) {
                    j--;
                }
                if (i < j) {
                    int tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = tmp;
                }
            }
            nums[start] = nums[i];
            nums[i] = base;
            if (i > n) {
                end = i - 1;
            } else if (i < n) {
                start = i + 1;
            } else {
                break;
            }
        }
        return nums[start];
    }

    /**
     * 回合制游戏
     * QW 是一个回合制游戏的玩家，今天他决定去打怪。
     * QW 在一场战斗中会碰到 n 个怪物，每个怪物有攻击力 atk[i]，每回合结束时如果第 i 个怪物还活着，就会对 QW 造成 atk[i] 的伤害。QW 只能在每回合开始时击杀一个怪物，请帮 QW 出他打完所有怪物最少需要损失多少生命值。
     * <p>
     * 样例
     * 样例 1：
     * <p>
     * 输入：atk = [19,3]
     * 输出：3
     * 样例 2：
     * <p>
     * 输入：atk = [1,3,2,5]
     * 输出：10
     * 注意事项
     * n, atk[i] <= 100000
     * 答案可能超过 int 范围
     *
     * @param atk: the atk of monsters
     * @return: Output the minimal damage QW will suffer
     */
    public static long getAns(int[] atk) {
        // Write your code here
        long life = 0;
        Arrays.sort(atk);
        int length = atk.length - 1;
        int nowLength = length;
        for (int i = 0; i < length; i++) {
            life += (1L * atk[i] * nowLength--);
        }
        return life;
    }

    /**
     * 区间统计
     * 给定一个01数组和一个k。你需要统计有多少区间符合如下条件：
     * 1、区间的两个端点都为0（允许区间长度为1）。
     * 2、区间内的1的个数不多于k。
     * <p>
     * 样例
     * 给出arr=[0,0,1,0,1,1,0],k=1,返回7。
     * <p>
     * 1、l=1,r=1
     * 2、l=1,r=2
     * 3、l=1,r=4
     * 4、l=2,r=2
     * 5、l=2,r=4
     * 6、l=4,r=4
     * 7、l=7,r=7
     * <p>
     * 注意事项
     * 数组长度不超过100000
     *
     * @param arr: the 01 array
     * @param k:   the limit
     * @return: the sum of the interval
     */
    public static long intervalStatistics(int[] arr, int k) {
        // Write your code here.
        long count = 0L;
        for (int i = 0; i < arr.length; i++) {
            boolean started = false;
            boolean found1 = false;
            int count1 = 0;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] == 1) {
                    found1 = true;
                    if (!started) {
                        break;
                    } else {
                        count1++;
                    }
                } else {
                    if (!started) {
                        started = true;
                    }
                    if (count1 <= k) {
                        count++;
                    } else {
                        break;
                    }

                }
            }
            if (!found1) {
                return count + (1L * (arr.length - i) * (arr.length - i - 1)) / 2L;
            }
        }
        return count;
    }

    /**
     * 区间最小覆盖(贪心？）
     * 给定n个区间，输出最少需要取多少个点，使任意一个区间均包含至少一个点。
     * <p>
     * 样例
     * 给出a=[(1,5),(4,8),(10,12)]，返回2
     * <p>
     * 解释：
     * 选择两个点：5,10，
     * 第一个区间[1,5]包含了5，
     * 第二个区间[4,8]包含了5，
     * 第三个区间[10,12]包含了10.
     * 给出a=[(1,5),(4,8),(5,12)]，返回1
     * <p>
     * 解释：
     * 选择一个点：5，
     * 第一个区间[1,5]包含了5，
     * 第二个区间[4,8]包含了5，
     * 第三个区间[5,12]包含了5.
     * 注意事项
     * 1 \leq n \leq 1e41≤n≤1e4
     * 区间最大值不超过 1e51e5
     *
     * @param a: the array a
     * @return: return the minimal points number
     */
    public int getAns(List<Interval> a) {
        // write your code here
        a.sort(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if (o1 == o2) {
                    return 0;
                }
                if (o1.start > o2.start) {
                    return 1;
                } else if (o1.start < o2.start) {
                    return -1;
                } else {
                    return o1.start - o2.end;
                }
            }
        });
        int count = 0;
        Interval now = null;
        for (Interval interval : a) {
            if (null == now) {
                now = interval;
                count++;
            } else {
                if (now.end < interval.start) {
                    now.end = interval.end;
                    count++;
                } else if (now.start > interval.end) {
                    now.start = interval.start;
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 搜索旋转排序数组 II 二分(O(lg(n)))
     * 中文English
     * 跟进“搜索旋转排序数组”，假如有重复元素又将如何？
     * <p>
     * 是否会影响运行时间复杂度？
     * <p>
     * 如何影响？
     * <p>
     * 为何会影响？
     * <p>
     * 写出一个函数判断给定的目标值是否出现在数组中。
     * <p>
     * 样例
     * 例1:
     * <p>
     * 输入:
     * []
     * 1
     * 输出:
     * false
     * 例2:
     * <p>
     * 输入:
     * [3,4,4,5,7,0,1,2]
     * 4
     * 输出:
     * true
     *
     * @param A:      an integer ratated sorted array and duplicates are allowed
     * @param target: An integer
     * @return: a boolean
     */
    public static boolean searchRo(int[] A, int target) {
        // write your code here
        for (int a : A) {
            if (target == a) {
                return true;
            }
        }
        return false;
    }

    /**
     * 最慢搜索，O(n)
     *
     * @param A
     * @param target
     * @return
     */
    public static boolean slowSearch(int[] A, int target) {
        // write your code here
        for (int a : A) {
            if (target == a) {
                return true;
            }
        }
        return false;
    }

    /**
     * 第一个错误的代码版本,二分(O(lg(n)))
     * 中文English
     * 代码库的版本号是从 1 到 n 的整数。某一天，有人提交了错误版本的代码，因此造成自身及之后版本的代码在单元测试中均出错。请找出第一个错误的版本号。
     * <p>
     * 你可以通过 isBadVersion 的接口来判断版本号 version 是否在单元测试中出错，具体接口详情和调用方法请见代码的注释部分。
     * <p>
     * 样例
     * n = 5:
     * <p>
     * isBadVersion(3) -> false
     * isBadVersion(5) -> true
     * isBadVersion(4) -> true
     * <p>
     * 因此可以确定第四个版本是第一个错误版本。
     * 挑战
     * 调用 isBadVersion 的次数越少越好
     * <p>
     * 注意事项
     * 请阅读代码编辑框内注释代码，获取对于不同语言正确调用 isBadVersion 的方法，比如java的调用方式是SVNRepo.isBadVersion(v)
     * public class SVNRepo {
     * public static boolean isBadVersion(int k);
     * }
     * you can use SVNRepo.isBadVersion(k) to judge whether
     * the kth code version is bad or not.
     *
     * @param n: An integer
     * @return: An integer which is the first bad version.
     */
    public static int findFirstBadVersion(int n) {
        // write your code here
        int lastStart = 1, start = 1, end = n;
        while (start < end) {
            if (SVNRepo.isBadVersion(start)) {
                end = start;
                start = Math.min(end, lastStart + 1);
            } else {
                lastStart = start;
                start = start + Math.max((end - start) / 2, 1);
            }
        }
        return start;
    }

    static class SVNRepo {
        static boolean isBadVersion(int k) {
            System.out.println(k);
            return k >= 1;
        }
    }

    /**
     * 搜索旋转排序数组
     * 中文English
     * 假设有一个排序的按未知的旋转轴旋转的数组(比如，0 1 2 4 5 6 7 可能成为4 5 6 7 0 1 2)。给定一个目标值进行搜索，如果在数组中找到目标值返回数组中的索引位置，否则返回-1。
     * <p>
     * 你可以假设数组中不存在重复的元素。
     * <p>
     * 样例
     * 例1:
     * <p>
     * 输入: [4, 5, 1, 2, 3] and target=1,
     * 输出: 2.
     * 例2:
     * <p>
     * 输入: [4, 5, 1, 2, 3] and target=0,
     * 输出: -1.
     * 挑战
     * O(logN) 时间限制
     *
     * @param A:      an integer rotated sorted array
     * @param target: an integer to be searched
     * @return: an integer
     */
    public static int search(int[] A, int target) {
        // write your code here
        if (A.length == 0) {
            return -1;
        }
        int pos = findMinPos(A);
        int firstPos = binarySearch(A, target, 0, pos - 1);
        int secondPos = binarySearch(A, target, pos, A.length - 1);
        return firstPos >= 0 ? firstPos : (secondPos >= 0 ? secondPos : -1);
    }

    /**
     * 二分查找
     *
     * @param array
     * @param target
     * @param start
     * @param end
     * @return
     */
    public static int binarySearch(int[] array, int target, int start, int end) {

        int mid;
        while (start <= end) {
            mid = start + (end - start) / 2;
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 寻找旋转排序数组中的最小值
     * 中文English
     * 假设一个排好序的数组在其某一未知点发生了旋转（比如0 1 2 4 5 6 7 可能在第3和第4个元素间发生旋转变成4 5 6 7 0 1 2）。
     * <p>
     * 你需要找到其中最小的元素。
     * <p>
     * 你可以假设数组中不存在重复的元素。
     * <p>
     * 样例
     * 给出[4,5,6,7,0,1,2]  返回 0
     * <p>
     * 注意事项
     * 你可以假设数组中不存在重复元素。
     *
     * @param nums
     * @return: an integer
     */
    public static int findMin(int[] nums) {
        // write your code here
        return nums[findMinPos(nums)];
    }

    /**
     * 寻找旋转排序数组中的最小值位置
     * 中文English
     * 假设一个排好序的数组在其某一未知点发生了旋转（比如0 1 2 4 5 6 7 可能在第3和第4个元素间发生旋转变成4 5 6 7 0 1 2）。
     * <p>
     * 你需要找到其中最小的元素。
     * <p>
     * 你可以假设数组中不存在重复的元素。
     * <p>
     * 样例
     * 给出[4,5,6,7,0,1,2]  返回 0
     * <p>
     * 注意事项
     * 你可以假设数组中不存在重复元素。
     *
     * @param nums
     * @return: an integer
     */
    public static int findMinPos(int[] nums) {
        // write your code here
        if (null == nums || nums.length == 0) {
            return -1;
        }
        if (nums[0] <= nums[nums.length - 1]) {
            return 0;
        }
        int start = 0, end = nums.length - 1;
        int aL = nums[0];
        int mid = 0;
        while (start < end) {
            if (start == end - 1) {
                return nums[start] > nums[end] ? end : start;
            }
            mid = start + (end - start) / 2;
            if (nums[mid] <= nums[Math.max(mid - 1, 0)] && nums[mid] <= nums[Math.min(nums.length - 1, mid + 1)]) {
                break;
            } else if (nums[mid] >= aL && nums[mid] >= nums[Math.max(mid - 1, 0)]) {
                start = mid;
            } else {
                end = mid;
            }
        }
        return mid;
    }

    /**
     * 搜索区间
     * 给定一个包含 n 个整数的排序数组，找出给定目标值 target 的起始和结束位置。
     * <p>
     * 如果目标值不在数组中，则返回[-1, -1]
     * <p>
     * 样例
     * 例1:
     * <p>
     * 输入:
     * []
     * 9
     * 输出:
     * [-1,-1]
     * <p>
     * 例2:
     * <p>
     * 输入:
     * [5, 7, 7, 8, 8, 10]
     * 8
     * 输出:
     * [3, 4]
     * 挑战
     * 时间复杂度 O(log n)
     *
     * @param A:      an integer sorted array
     * @param target: an integer to be inserted
     * @return: a list of length 2, [index1, index2]
     */
    public static int[] searchRange(int[] A, int target) {
        int[] rtn = new int[]{-1, -1};
        // write your code here
        int start = 0, lastStart = 0, end = A.length - 1;
        while (start < end) {
            if (A[start] < target) {
                lastStart = start;
                start = start + Math.max((end - start) / 2, 1);
            } else if (A[start] == target) {
                break;
            } else {
                end = start;
                start = Math.min(end, lastStart + 1);
            }
        }
        if (A.length > 0 && A[start] == target) {
            rtn[0] = start;
            rtn[1] = start;
            for (int i = start; i >= 0; i--) {
                if (A[i] != target) {
                    break;
                }
                rtn[0] = i;
            }
            for (int i = start; i < A.length; i++) {
                if (A[i] != target) {
                    break;
                }
                rtn[1] = i;
            }
        }
        return rtn;
    }

    /**
     * 寻找峰值
     * 中文English
     * 你给出一个整数数组(size为n)，其具有以下特点：
     * <p>
     * 相邻位置的数字是不同的
     * A[0] < A[1] 并且 A[n - 2] > A[n - 1]
     * 假定P是峰值的位置则满足A[P] > A[P-1]且A[P] > A[P+1]，返回数组中任意一个峰值的位置。
     * <p>
     * 样例
     * 样例 1:
     * 输入:  [1, 2, 1, 3, 4, 5, 7, 6]
     * 输出:  1 or 6
     * <p>
     * 解释:
     * 返回峰顶元素的下标
     * <p>
     * <p>
     * 样例 2:
     * 输入: [1,2,3,4,1]
     * 输出:  3
     * <p>
     * 挑战
     * Time complexity O(logN)
     * <p>
     * 注意事项
     * 数组保证至少存在一个峰
     * 如果数组存在多个峰，返回其中任意一个就行
     * 数组至少包含 3 个数
     *
     * @param A: An integers array.
     * @return: return any of peek positions.
     */
    public int findPeak(int[] A) {
        // write your code here
        int size = A.length, low = 0, high = size - 1, mid = 0;
        while (low <= high) {
            mid = (high - low) / 2 + low;
            if (A[mid] > (mid - 1 < 0 ? Integer.MIN_VALUE : A[mid - 1]) && A[mid] > (mid + 1 > size - 1 ? Integer.MIN_VALUE : A[mid + 1])) {
                return mid;
            } else if (A[mid] < (mid - 1 < 0 ? Integer.MIN_VALUE : A[mid - 1])) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 跳石头
     * cat-only-icon
     * CAT 专属题目
     * 中文English
     * 有 n 个岩石（不包含起点和终点），下标为 i 的岩石到起点的距离为 d[i]，终点到起点的距离为 target。我们从起点开始，每次只能跳到相邻的岩石上，直至终点。现在最多可以移走m块岩石，移走不同数量和不同位置的岩石可以组成不同的路径，每条路径都有一个最短的跳跃距离，求所有路径中最短跳跃距离的最大值。
     * <p>
     * 样例
     * 给定 n=5, m=2, target=25, d=[2,11,14,17,21], 返回 4。
     * <p>
     * 移除第一个和第三个岩石。
     * 注意事项
     * 0 \leq m \leq n \leq 50,0000≤m≤n≤50,000
     * 1 \leq target \leq 1,000,000,0001≤target≤1,000,000,000
     * 这些岩石按与起点距离从小到大的顺序给出，且不会有两个岩石出现在同一个位置。
     *
     * @param n:      The total number of stones.
     * @param m:      The total number of stones you can remove.
     * @param target: The distance from the end to the starting point.
     * @param d:      The array that the distance from the i rock to the starting point is d[i].
     * @return: Return the maximum value of the shortest jump distance.
     */
    public static int getDistance(int n, int m, int target, List<Integer> d) {
        // Write your code here.
        int[] distances = new int[d.size() + 1];
        int i = 0, last = 0;
        for (Integer distance : d) {
            distances[i++] = distance - last;
            last = distance;
        }
        distances[distances.length - 1] = target - last;
        Arrays.sort(distances);
        return distances[m];
    }


    /**
     * 玩游戏
     * cat-only-icon
     * CAT 专属题目
     * 中文English
     * N个人在玩游戏，每局游戏有一个裁判和N-1个平民玩家。给出一个数组A, A[i]代表玩家i至少需要成为平民A[i]次，返回最少进行游戏的次数。
     * <p>
     * 样例
     * 样例 1
     * <p>
     * 输入：A = [2, 2, 2, 2]
     * 输出：3
     * 解析：A[0] = 2表示玩家0至少需要成为2次平民
     * 第一局：玩家0担任裁判，此时 A[0] = 0, A[1] = 1, A[2] = 1, A[3] = 1
     * 第二局：玩家1担任裁判，此时 A[0] = 1, A[1] = 1, A[2] = 2, A[3] = 2
     * 第三局：玩家2担任裁判，此时 A[0] = 2, A[1] = 2, A[2] = 2, A[3] = 3
     * 此时每个玩家都达到了要求，所以进行三局游戏即可
     * 注意事项
     * ∑Ai<=1e18
     * n>1
     *
     * @param A:
     * @return: nothing
     */
    public long playGames(int[] A) {
        // Write your code here
        int max = 0;
        for (int a : A) {
            max = Math.max(a, max);
        }
        long l = 0, r = max * 2;
        //cnt表式某一人完成时的游戏次数
        while (l < r) {
            long m = (l + r) / 2;
            long cnt = 0;
            for (int a : A) {
                cnt += Math.max(m - a, 0);
            }
            if (m > cnt) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return Math.max(l, max);

    }

    /**
     * 写作业
     * cat-only-icon
     * CAT 专属题目
     * 中文English
     * n个人，他们每个人需要独立做m份作业。
     * 第i份作业需要花费cost[i]的时间。由于每个人的空闲时间不同，第i个人有val[i]的时间，这代表他做作业的总时间不会超过val[i]。每个人都按照顺序，从1号作业开始，然后做2号，3号...... 现在，你需要计算出他们最多花了多少的时间。
     * <p>
     * 样例
     * Example 1:
     * <p>
     * Given `cost=[1,2,3,5]`,`val=[6,10,4]`, return `15`.
     * Input:
     * 1 2 3 5
     * 6 10 4
     * Output:
     * 15
     * <p>
     * Explanation:
     * The first person can complete the 1st job, the 2nd job, the 3rd job, 1+2+3<=6.
     * The second person cancomplete the 1st job, the 2nd job, the 3rd job, and cannot complete the 4th job, 1+2+3<=10, 1+2+3+5>10.
     * The third person can complete  the 1st job, the 2nd job, and cannot complete the 3rd job,  1+2<=4, 1+2+3>4.
     * 1+2+3+1+2+3+1+2=15, returning 15.
     * Example 2:
     * <p>
     * Given `cost=[3,7,3,2,5]`,`val=[10,20,12,8,17,25]`, return `78`.
     * Input:
     * 3 7 3 2 5
     * 10 20 12 8 17 25
     * Output:
     * 78
     *
     * @param cost: the cost
     * @param val:  the val
     * @return: the all cost
     */
    public static long doingHomework(int[] cost, int[] val) {
        // Write your code here.
        int[] costTimes = new int[cost.length];
        int sum = 0;
        for (int i = 0; i < cost.length; i++) {
            sum += cost[i];
            costTimes[i] = sum;
        }
        long allCost = 0;
        for (int nowVal : val) {
            allCost += findFloorVal(nowVal, costTimes);
        }
        return allCost;
    }

    /**
     * 查找小于target并且最接近target的值
     *
     * @param target
     * @param array
     * @return
     */
    public static int findFloorVal(int target, int[] array) {
        int start = 0, mid = 0, end = array.length - 1;
        while (start <= end) {
            mid = start + (end - start) / 2;
            if (array[mid] == target || (array[mid] < target && (mid == array.length - 1 || array[mid + 1] > target))) {
                return array[mid];
            } else if (array[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return 0;
    }

    /**
     * 任务计划
     * 中文English
     * 给定一个字符串，表示CPU需要执行的任务。 这个字符串由大写字母A到Z构成，不同的字母代表不同的任务。完成任务不需要按照给定的顺序。 每项任务都可以在一个单位时间内被完成。 在每个单位时间，CPU可以选择完成一个任务或是不工作。
     * <p>
     * 但是，题目会给定一个非负的冷却时间“n”，表示在执行两个“相同的任务”之间，必须至少有n个单位时间，此时CPU不能执行该任务，只能执行其他任务或者不工作。
     * <p>
     * 您需要返回CPU完成所有给定任务所需的最少单位时间数。
     * <p>
     * 样例
     * 样例1
     * <p>
     * 输入: tasks = ['A','A','A','B','B','B'], n = 2
     * 输出: 8
     * 解释:
     * A -> B -> idle -> A -> B -> idle -> A -> B.
     * 样例2
     * <p>
     * 输入: tasks = ['A','A','A','B','B','B'], n = 1
     * 输出: 6
     * 解释:
     * A -> B -> A -> B -> A -> B.
     * 注意事项
     * 任务数量的范围为 [1, 10000].
     * 整数 n 的范围为 [0, 100].
     *
     * @param tasks: the given char array representing tasks CPU need to do
     * @param n:     the non-negative cooling interval
     * @return: the least number of intervals the CPU will take to finish all the given tasks
     */
    public int leastInterval(char[] tasks, int n) {
        // write your code here
        int[] cache = new int[26];
        for (char task : tasks) {
            cache[task - 'A'] = cache[task - 'A'] + 1;
        }
        int i = 0;
        while (!taskEnd(cache)) {
            int[] visited = new int[n];
            for (int j = 0; j < n; j++) {
                i += findNextTask(cache, visited);
            }
        }
        return tasks.length + i;
    }

    private int findNextTask(int[] cache, int[] visited) {
        int maxPos = -1;
        for (int i = 0; i < 26; i++) {
            if (cache[i] == 0) {
                continue;
            }
            boolean finded=false;
            for (int visit : visited) {
                if (i == visit) {
                    finded=true;
                    break;
                }
            }
            if(finded){
                continue;
            }
            if (maxPos < 0) {
                maxPos = i;
            } else {
                maxPos = (cache[i] >= cache[maxPos] ? maxPos : i);
            }
        }
        if (maxPos >= 0) {
            cache[maxPos] = cache[maxPos] - 1;
            for (int i = 0; i < visited.length; i++) {
                if (visited[i] == 0) {
                    visited[i] = maxPos;
                    break;
                }
            }
        }
        return -1 == maxPos ? 1 : 0;
    }

    /**
     * 任务结束
     *
     * @param cache
     * @return
     */
    private boolean taskEnd(int[] cache) {
        for (int num : cache) {
            if (num > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 最长无重复字符的子串
     * 中文English
     * 给定一个字符串，请找出其中无重复字符的最长子字符串。
     * <p>
     * 样例
     * 样例 1:
     * <p>
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 最长子串是 "abc".
     * 样例 2:
     * <p>
     * 输入: "bbbbb"
     * 输出: 1
     * 解释: 最长子串是 "b".
     * 挑战
     * O(n) 时间复杂度
     *
     * @param s: a string
     * @return: an integer
     */
    public static int lengthOfLongestSubstring(String s) {
        // write your code here
        int max = 0;
        int[] flags = new int[255];
        int i = 0, j = 0;
        for (; i < s.length() && j < s.length(); ) {
            if (flags[s.charAt(j)] == 0) {
                flags[s.charAt(j++)] = 1;
            } else {
                if (j - i > max) {
                    max = j - i;
                }
                flags[s.charAt(i++)] = 0;
            }

        }
        return max > j - i ? max : j - i;
    }

    /**
     * 栈排序
     * 中文English
     * 请设计一种方法将一个栈进行升序排列 （最大的数在最上面）。
     * <p>
     * 你可以使用另外一个栈来辅助操作，但不可将这些数复制到另外一个数据结构中 （如，数组）。
     * <p>
     * 样例
     * 给一个栈：
     * <p>
     * | |
     * |3|
     * |1|
     * |2|
     * |4|
     * -
     * 排序之后：
     * <p>
     * | |
     * |4|
     * |3|
     * |2|
     * |1|
     * -
     * 栈会被序列化为[4,2,1,3]，也就是说最右边是栈顶。
     * <p>
     * 注意事项
     * 时间复杂度为O(n^2)的算法也可以通过测试
     *
     * @param stk: an integer stack
     * @return: void
     */
    public void stackSorting(Stack<Integer> stk) {
        // write your code here
        Stack<Integer> cacheStack = new Stack<>();
        for (int i = 0; i < stk.size(); i++) {
            for (int j = i; j < stk.size(); j++) {
                int pop = stk.pop();
                if (cacheStack.isEmpty()) {
                    cacheStack.push(pop);
                } else {
                    int min = cacheStack.pop();
                    if (min < pop) {
                        cacheStack.push(pop);
                        cacheStack.push(min);
                    } else {
                        cacheStack.push(min);
                        cacheStack.push(pop);
                    }
                }
            }
            while (!cacheStack.isEmpty()) {
                stk.push(cacheStack.pop());
            }
        }
    }

    /**
     * 两数和 II-输入已排序的数组
     * 中文English
     * 给定一个已经 按升序排列 的数组，找到两个数使他们加起来的和等于特定数。
     * 函数应该返回这两个数的下标，index1必须小于index2。注意返回的值不是 0-based。
     * <p>
     * 样例
     * Example 1:
     * <p>
     * Input: nums = [2, 7, 11, 15], target = 9
     * Output: [1, 2]
     * Example 2:
     * <p>
     * Input: nums = [2,3], target = 5
     * Output: [1, 2]
     * 注意事项
     * 你可以假设每个输入刚好只有一个答案
     *
     * @param nums:   an array of Integer
     * @param target: target = nums[index1] + nums[index2]
     * @return: [index1 + 1, index2 + 1] (index1 < index2)
     */
    public static int[] twoSum(int[] nums, int target) {
        // write your code here
        int[] rtn = new int[2];
        Map<Integer, List<Integer>> cacheMap = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if(cacheMap.containsKey(nums[i])){
                cacheMap.get(nums[i]).add(i+1);
            }else {
                cacheMap.put(nums[i], new ArrayList(Arrays.asList(new Integer[]{i + 1})));
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if(cacheMap.containsKey(target-nums[i])){
                rtn[0] = i + 1;
                if(cacheMap.get(target-nums[i]).size()==1) {
                    rtn[1] = cacheMap.get(target - nums[i]).get(0);
                }else{
                    for(Integer j:cacheMap.get(target-nums[i])){
                        if(j!=i+1){
                            rtn[1]=j;
                            break;
                        }
                    }
                }
            }
        }
        Arrays.sort(rtn);
        return rtn;
    }

    public static void main(String[] args) {
        System.out.println(new CatSolution().leastInterval("AAABBB".toCharArray(),2));
    }
}