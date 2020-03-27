package bg.leetcode.exercises.itenev.linked_list;

import bg.leetcode.exercises.LeetcodeApplication;

import java.util.Stack;

/**
 * Reverse a singly linked list.
 *
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 */
public class ReversedLinkedList {

    private  static class ListNode {
        int val;
        ListNode next;
        public ListNode(int x) { val = x; }
    }

    public static ListNode reverseList(ListNode head) {
        ListNode current = head;
        ListNode prev = null;

        while (current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }

    public static ListNode reverseListRecursive(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode p = reverseListRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    public static ListNode reverseList2(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }

        ListNode result = new ListNode(0);
        head = result;
        while (!stack.isEmpty()){
            ListNode node = stack.pop();
            head.next = new ListNode(node.val);
            head = head.next;
        }

        return result.next;
    }
}
