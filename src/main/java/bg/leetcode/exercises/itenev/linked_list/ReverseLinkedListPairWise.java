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

    public static void reverseRecursive(ListNode node) {
        if (node != null && node.next != null) {
            int tmp = node.val;
            node.val = node.next.val;
            node.next.val = tmp;
            reverseRecursive(node.next.next);
        }
    }

}
