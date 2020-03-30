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
        ListNode startNode = node;

        while (startNode != null && startNode.next != null) {
            int temp = startNode.val;
            startNode.val = startNode.next.val;
            startNode.next.val = temp;
            startNode = startNode.next.next;
        }
    }

    public static void reverseRecursive(ListNode startNode) {
        if (startNode != null && startNode.next != null) {

            int tmp = startNode.val;
            startNode.val = startNode.next.val;
            startNode.next.val = tmp;

            reverseRecursive(startNode.next.next);
        }
    }

}
