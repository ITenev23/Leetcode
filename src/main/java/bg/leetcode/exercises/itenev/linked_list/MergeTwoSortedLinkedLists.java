package bg.leetcode.exercises.itenev.linked_list;

/**
 * Merge two sorted linked lists and return it as a new list.
 * The new list should be made by splicing together the nodes of the first two lists.
 * <p>
 * Input: 1->2->4, 1->3->4
 * Output: 1->1->2->3->4->4
 */
public class MergeTwoSortedLinkedLists {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null)
            return l1 == null ? l2 : l1;

        ListNode dummy = l1.val <= l2.val ? l1 : l2;
        ListNode head = new ListNode(-1);

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                head.next = l1;
                l1 = l1.next;
            } else {
                head.next = l2;
                l2 = l2.next;
            }

            head = head.next;
        }

        head.next = l1 == null ? l2 : l1;

        return dummy;
    }

    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        head.next = l1;
        ListNode dummy = head;

        while (head.next != null && l2 != null) {
            if (head.next.val > l2.val) {
                ListNode change = l2;
                l2 = head.next;
                head.next = change;
            }
            head = head.next;
        }

        if (l2 == null)
            return dummy.next;

        head.next = l2;

        return dummy.next;
    }
}
