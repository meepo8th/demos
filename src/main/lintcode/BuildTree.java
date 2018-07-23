package lintcode;

/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */
 
 
public class BuildTree {
    /**
     *@param preorder : A list of integers that preorder traversal of a tree
     *@param inorder : A list of integers that inorder traversal of a tree
     *@return : Root of a tree
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length==inorder.length&&preorder.length>0){
            return null;
        }else{
            return null;
        }
        
    }
}