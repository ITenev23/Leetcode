package bg.leetcode.exercises.itenev.linked_list;

public class ReverseLinkedListPairWise {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

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
