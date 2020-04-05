package bg.leetcode.exercises.itenev.linked_list;

import bg.leetcode.exercises.itenev.common.ListNode;

/**
 * Given a linked list and a value x, partition it such that all nodes
 * less than x come before nodes greater than or equal to x.
 * You should preserve the original relative order of the nodes in each of the two partitions.
 * <p>
 * Input: head = 1->4->3->2->5->2, x = 3
 * Output: 1->2->2->4->3->5
 */
public class PartitionLinkedList {

    /**
    https://leetcode.com/problems/partition-list/discuss/349660/Simple-recursive-solution-runtime-and-memory-both-100-with-detailed-explanations
    */
     ListNode before_tail = null;

    public ListNode partitionRec(ListNode head, int x) {
        if (head == null)
            return null;

        head.next = partitionRec(head.next, x);

        if (before_tail == null) {
            if (head.val < x)
                before_tail = head;
            return head;
        } else if (head.val >= x) {
            ListNode next = head.next;
            head.next = before_tail.next;
            before_tail.next = head;
            return next;
        } else
            return head;

    }

    /*************************************************************/

    public ListNode partition(ListNode head, int x) {
        ListNode beforeHead = new ListNode(0);
        ListNode afterHead = new ListNode(0);
        ListNode before = beforeHead;
        ListNode after = afterHead;

        while (head != null) {
            // If the original list node is lesser than the given x,
            // assign it to the before list.
            if (head.val < x) {
                before.next = head;
                before = before.next;
            } else {
                // If the original list node is greater or equal to the given x,
                // assign it to the after list.
                after.next = head;
                after = after.next;
            }
            // move ahead in the original list
            head = head.next;
        }
        // Last node of "after" list would also be ending node of the reformed list
        after.next = null;

        // Once all the nodes are correctly assigned to the two lists,
        // combine them to form a single list which would be returned.
        before.next = afterHead.next;

        return beforeHead.next;
    }
}
