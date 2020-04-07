package bg.leetcode.exercises.itenev.linked_list;

import bg.leetcode.exercises.itenev.common.ListNode;

public class ReverseLinkedListPairWise {

    public static void reverse(ListNode node) {
        while (node != null && node.next != null) {
            int temp = node.val;
            node.val = node.next.val;
            node.next.val = temp;
            node = node.next.next;
        }
    }

    public static void reverseRecursive(ListNode head) {
        if (head != null && head.next != null) {
            int tmp = head.val;
            head.val = head.next.val;
            head.next.val = tmp;
            reverseRecursive(head.next.next);
        }
    }

}
