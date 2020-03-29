package bg.leetcode.exercises.itenev.linked_list;

import java.util.PriorityQueue;

/**
 * Merge k sorted linked lists and return it as one sorted list.
 * <p>
 * Input:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 */
public class MergeKSortedLists {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (ListNode head : lists) {
            while (head != null) {
                minHeap.add(head.val);
                head = head.next;
            }
        }

        ListNode dummy = new ListNode(-1);
        ListNode head = dummy;
        while (!minHeap.isEmpty()) {
            head.next = new ListNode(minHeap.remove());
            head = head.next;
        }

        return dummy.next;
    }

}
