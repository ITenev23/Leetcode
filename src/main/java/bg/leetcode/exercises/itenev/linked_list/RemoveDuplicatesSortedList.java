package bg.leetcode.exercises.itenev.linked_list;

import bg.leetcode.exercises.itenev.common.ListNode;

/**
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 * <p>
 * Input: 1->1->2
 * Output: 1->2
 * <p>
 * Input: 1->1->2->3->3
 * Output: 1->2->3
 */
public class RemoveDuplicatesSortedList {

    /**
     * This is a simple problem that merely tests your ability to manipulate list node pointers.
     * Because the input list is sorted, we can determine if a node is a duplicate
     * by comparing its value to the node after it in the list. If it is a duplicate,
     * we change the next pointer of the current node so that it skips the next node
     * and points directly to the one after the next node.
     */
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode current = head;
        while (current != null && current.next != null) {
            if (current.next.val == current.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }

    /*************************************************/

    public ListNode deleteDuplicatesRec(ListNode head) {
        if (head == null || head.next == null)
            return head;
        head.next = deleteDuplicatesRec(head.next);
        return head.val == head.next.val ? head.next : head;
    }

    /*************************************************/

    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode current = head;

        while (current.next != null) {
            if (current.val == current.next.val)
                current.next = current.next.next;
            else
                current = current.next;
        }

        return head;
    }

}
