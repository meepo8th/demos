package leetcode.isUnivalTree;

import leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public boolean isUnivalTree(TreeNode root) {
        return isUnivalTree(root, root.val);
    }
    public static void rotateString(char[] str, int offset) {
        String src=new String(str);
        String dest=src.substring(str.length-offset-1)+src.substring(0,offset);
        for(int i=0;i<dest.length();i++){
            str[i]=dest.charAt(i);
        }
        str=(src.substring(str.length-offset-1)+src.substring(0,offset)).toCharArray();
    }

    public static List<Integer> getNarcissisticNumbers(int n) {
        // write your code here
        List<Integer> rtn = new ArrayList<>();
        int max= (int)Math.pow(10,n);
        int min=(int)Math.pow(10,n-1);
        for(int i=min;i<max;i++){
            if(isNarcissisticNumbers(i)){
                rtn.add(i);
            }
        }
        return rtn;
    }

    private static boolean isNarcissisticNumbers(int num) {
        String cache=String.valueOf(num);
        Integer sum = 0;
        for(int i=0;i<cache.length();i++){
            if(sum>num){
                break;
            }
            sum+=(int)Math.pow(cache.charAt(i)-48,cache.length());
        }
        return sum.equals(num);
    }

    private boolean isUnivalTree(TreeNode node, int val) {
        if(null==node){
            return true;
        }
        return node.val == val && isUnivalTree(node.left, node.val) && isUnivalTree(node.right, node.val);
    }

    public static void main(String[] args) {
        System.out.println(getNarcissisticNumbers(1));
        System.out.println(new Solution().isUnivalTree(TreeNode.buildTree(new Integer[]{1, 1, 1, 1, 1, null, 1})));
        System.out.println(new Solution().isUnivalTree(TreeNode.buildTree(new Integer[]{2, 2, 2, 5, 2})));
    }
}