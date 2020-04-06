package bg.leetcode.exercises.itenev.linked_list;

import bg.leetcode.exercises.itenev.common.ListNode;

/**
 * Remove all elements from a linked list of integers that have value val.
 * <p>
 * Input:  1->2->6->3->4->5->6, val = 6
 * Output: 1->2->3->4->5
 */
public class RemoveLinkedListElements {

    public ListNode removeElements(ListNode head, int val) {
        if (head == null)
            return null;

        head.next = removeElements(head.next, val);

        return head.val == val ? head.next : head;
    }

    /**************************************************************/

    public ListNode removeElements2(ListNode head, int val) {
        if (head == null)
            return null;

        ListNode next = removeElements2(head.next, val);

        if (head.val == val)
            return next;

        head.next = next;
        return head;
    }

    /**************************************************************/

    public ListNode removeElements3(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;

        while (current.next != null) {
            if (current.next.val == val)
                current.next = current.next.next;
            else
                current = current.next;
        }
        return dummy.next;
    }

    /**************************************************************/

    public ListNode removeElements4(ListNode head, int val) {
        while (head != null && head.val == val)
            head = head.next;

        ListNode current = head;
        while (current != null && current.next != null)
            if (current.next.val == val)
                current.next = current.next.next;
            else
                current = current.next;

        return head;
    }
}
