package bg.leetcode.exercises.itenev.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an n-ary tree, return the postorder traversal of its nodes' values.
 * <p>
 * Nary-Tree input serialization is represented in their level order traversal,
 * each group of children is separated by the null value.
 * <p>
 * Input: root = [1,null,3,2,4,null,5,6]
 * Output: [5,6,3,2,4,1]
 * <p>
 * Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * Output: [2,6,14,11,7,3,12,8,4,13,9,10,5,1]
 * <p>
 * The height of the n-ary tree is less than or equal to 1000
 * The total number of nodes is between [0, 10^4]
 */
public class N_aryTreePostOrderTraversal {

    public List<Integer> postorder(Node root) {
        LinkedList<Integer> result = new LinkedList<>();
        if (root == null) {
            return result;
        }

        LinkedList<Node> stack = new LinkedList<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            Node node = stack.pollLast();
            result.addFirst(node.val);
            stack.addAll(node.children);
        }

        return result;
    }

    public List<Integer> postorderRecursive(Node root) {
        return postorder(root, new ArrayList<>());
    }

    private List<Integer> postorder(Node curr, List<Integer> nodes) {
        if (curr == null)
            return nodes;

        for (Node node : curr.children)
            postorder(node, nodes);

        nodes.add(curr.val);
        return nodes;
    }
    /**
     * Another way of recursion:
     *
     * private void postorder(Node node, List<Integer> list) {
     *         if (node != null) {
     *             for (Node n : node.children) {
     *                 postorder(n, list);
     *             }
     *             list.add(node.val);
     *         }
     *     }
     */

    public List<Integer> postorder3(Node root) {
        final LinkedList<Integer> list = new LinkedList<>();
        if (root == null) {
            return list;
        }

        final LinkedList<Node> stack = new LinkedList<>(Collections.singletonList(root));

        while (!stack.isEmpty()) {
            root = stack.pop();
            list.push(root.val);
            root.children.forEach(stack::push);
        }

        return list;
    }
}
