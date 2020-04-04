package bg.leetcode.exercises.itenev.linked_list;

import bg.leetcode.exercises.itenev.common.ListNode;

/**
 * Given a linked list, remove the n-th node from the end of list and return its head.
 *
 * Input: 1->2->3->4->5, and n = 2.
 * After removing the second node from the end:
 * Output: 1->2->3->5.
 */
public class RemoveNthNodeFromEnd {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null) {
            if (n-- < 0)
                slow = slow.next;

            fast = fast.next;
        }

        if (n < 0)
            slow.next = slow.next.next;
        else
            head = head.next;

        return head;
    }

    /********************************************************/

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;

        while (n-- > 0)
            fast = fast.next;

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

}
