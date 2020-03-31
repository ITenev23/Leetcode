package bg.leetcode.exercises.itenev.tree;

import bg.leetcode.exercises.itenev.common.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 *
 * Note: You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 *
 * Input: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * Output: 1
 *
 * Input: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * Output: 3
 */
public class KthSmallestElementBST {

    public int kthSmallest(TreeNode root, int k) {
        int[] nums = new int[2];
        inorder(root, k , nums);
        return nums[1];
    }

    private void inorder(TreeNode root, int k, int[] nums) {
        if (root == null)
            return;

        inorder(root.left, k, nums);
        if (++nums[0] == k) {
            nums[1] = root.val;
            return;
        }
        inorder(root.right, k ,nums);
    }

/*****************************************************************************/

    public int kthSmallest2(TreeNode root, int k) {
        LinkedList<TreeNode> stack = new LinkedList<>();

        while (true) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }

            root = stack.removeLast();

            if (--k == 0)
                return root.val;

            root = root.right;
        }
    }


    /*****************************************************************************/

    /**
     * Binary Search (dfs): this is NOT preferrable as in performance but
     * since the quesiton is categorized with Binary Search tag
     *
     * time complexity: O(N) best, O(N^2) worst
     */
    public int kthSmallest3(TreeNode root, int k) {
        int count = countNodes(root.left);

        if (k <= count)
            return kthSmallest3(root.left, k);
         else if (k > count + 1)
            return kthSmallest3(root.right, k - 1 - count); // 1 is counted as current node


        return root.val;
    }

    public int countNodes(TreeNode n) {
        if (n == null)
            return 0;

        return 1 + countNodes(n.left) + countNodes(n.right);
    }

    /*****************************************************************************/

    private static int number = 0;
    private static int count = 0;

    public int kthSmallest4(TreeNode root, int k) {
        count = k;
        inorder(root);
        return number;
    }

    public void inorder(TreeNode n) {
        if (n.left != null) inorder(n.left);
        count--;
        if (count == 0) {
            number = n.val;
            return;
        }
        if (n.right != null) inorder(n.right);
    }

}
