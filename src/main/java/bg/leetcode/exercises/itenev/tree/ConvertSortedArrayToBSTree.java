package bg.leetcode.exercises.itenev.tree;

import bg.leetcode.exercises.itenev.common.TreeNode;

import java.util.Arrays;

/**
 * Given an array where elements are sorted in ascending order,
 * convert it to a height balanced BST.
 * <p>
 * For this problem, a height-balanced binary tree is defined as a binary tree
 * in which the depth of the two subtrees of every node never differ by more than 1.
 * <p>
 * Given the sorted array: [-10,-3,0,5,9],
 * <p>
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced
 * BST:
 * 0
 * / \
 * -3   9
 * /   /
 * -10  5
 */
public class ConvertSortedArrayToBSTree {

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0)
            return null;

        return constructBSTRecursive(nums, 0, nums.length - 1);
    }

    private TreeNode constructBSTRecursive(int[] nums, int left, int right) {
        if (left > right)
            return null;

        int mid = left + (right - left) / 2;

        TreeNode current = new TreeNode(nums[mid]);
        current.left = constructBSTRecursive(nums, left, mid - 1);
        current.right = constructBSTRecursive(nums, mid + 1, right);

        return current;
    }

    /***************************************************************/

    public TreeNode sortedArrayToBST2(int[] nums) {
        if (nums.length == 0)
             return null;

        int mid = nums.length / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(Arrays.copyOfRange(nums, 0, mid));
        root.right = sortedArrayToBST(Arrays.copyOfRange(nums, mid + 1, nums.length));
        return root;
    }

}
