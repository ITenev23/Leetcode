package bg.leetcode.exercises.itenev.tree;

import bg.leetcode.exercises.itenev.common.TreeNode;

import java.util.*;

/**
 * Given a binary tree,
 * imagine yourself standing on the right side of it,
 * return the values of the nodes you can see ordered from top to bottom.
 *
 * Input: [1,2,3,null,5,null,4]
 * Output: [1, 3, 4]
 * Explanation:
 *
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 */
public class BinaryTreeRightSideView {

    public List<Integer> rightSideView(TreeNode root) {
        if(root==null)
            return new ArrayList<>();

        List<Integer> left = rightSideView(root.left);
        List<Integer> right = rightSideView(root.right);
        List<Integer> result = new ArrayList<>();
        result.add(root.val);

        for(int i=0;i < Math.max(left.size(), right.size()); i++){
            if(i >= right.size())
                result.add(left.get(i));
            else
                result.add(right.get(i));
        }
        return result;
    }

    /************************************************************************************/


    public List<Integer> rightSideViewDFS(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        rightView(root, result, 0);
        return result;
    }

    public void rightView(TreeNode curr, List<Integer> result, int currDepth){
        if(curr == null){
            return;
        }
        if(currDepth == result.size()){
            result.add(curr.val);
        }

        rightView(curr.right, result, currDepth + 1);
        rightView(curr.left, result, currDepth + 1);

    }

    /************************************************************************************/


    public List<Integer> rightSideViewBFS(TreeNode root) {
        List<Integer> visibleValues = new ArrayList<>();
        if (root == null)
            return visibleValues;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.remove();

                if (i == size - 1)
                    visibleValues.add(current.val);
                if (current.left != null)
                    queue.add(current.left);
                if (current.right != null)
                    queue.add(current.right);
            }
        }

        return visibleValues;
    }

    /************************************************************************************/


    public List<Integer> rightSideViewBFS2(TreeNode root) {
        Queue<TreeNode> q= new ArrayDeque<>();
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        q.offer(root);
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i< size; i++){
                TreeNode node = q.poll();
                if(i == size-1) res.add(node.val);
                if(node.left != null) q.offer(node.left);
                if(node.right != null) q.offer(node.right);
            }
        }
        return res;
    }

}
