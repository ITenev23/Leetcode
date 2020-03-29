package bg.leetcode.exercises.itenev.linked_list;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a singly linked list, determine if it is a palindrome.
 * <p>
 * Input: 1->2
 * Output: false
 * <p>
 * Input: 1->2->2->1
 * Output: true
 */
public class PalindromeLinkedList {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode reverseLinkedList(ListNode head) {
        ListNode prev = null;

        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }

        return prev;
    }

    private boolean checkNodesForPalindrome(ListNode slow, ListNode fast) {
        while (slow != null) {
            if (slow.val != fast.val)
                return true;

            slow = slow.next;
            fast = fast.next;
        }
        return false;
    }

    public boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        slow = reverseLinkedList(slow);
        fast = head;

        return checkNodesForPalindrome(slow, fast);

    }

    public boolean isPalindrome2(ListNode head) {
        /**
         Approach: Use dual pointer method to get the middle of the linked list. The idea is, have a slow pointer and a fast
         pointer, and every time move the slow pointer by 1 step, and the fast pointer by 2 steps. By the time the
         fast pointer reaches the end of the list, the slow pointer will reach the middle of the list.
         Also, while we're finding the middle, reverse the slow pointer nodes, essentially reversing the first half
         of the linked list.
         Now have two pointers at the middle of the linked list pointing to each half. For linked list with even
         length like [2,4,5,7] these will start at 4 and 5. For odd length lists like [2,4,5,7,8], these will start
         at 4 and 7, skipping the middle element.
         Now traverse both the halves and compare the values.
         P.S. Here we are modifying the list (next nodes) by reversing the first half to have O(1) space complexity.
         We could restore the list to its original state by again reversing the first half while comparing with second
         half, if the problem statement asked us to do so.

         Complexity analysis: Time: O(n), Space: O(1)
         */

        if (head == null || head.next == null)
            return true;

        //  Reverse the first half
        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            //Move fast pointer
            fast = fast.next.next;

            //Reverse
            ListNode nextSlowNode = slow.next;                              //Store next node in a variable
            slow.next = prev;                                       //Point current node to prev node
            prev = slow;                                            //Move prev node to next (curr) node
            slow = nextSlowNode;                                            //Move curr node to next node
        }

        //  Find the pointers to the two halves

        ListNode firstHalfPointer = prev;                               //Point to prev, like like 4 in
        //[2,4,5,7] or [2,4,5,7,8]
        ListNode secondHalfPointer = null;
        if (fast == null) {                                                     //List is of even length, like [2,4,5,7]
            secondHalfPointer = slow;                                       //Point to slow, like 5 in [2,4,5,7]
        } else {                                           //List is of odd length, like [2,4,5,7,8]
            secondHalfPointer = slow.next;                                  //Point to slow.next, like 7 in [2,4,5,7,8]
        }

        return checkNodesForPalindrome(firstHalfPointer, secondHalfPointer);
    }

    public boolean isPalindrome3(ListNode head) {
        if (head == null)
            return true;

        List<ListNode> list = new ArrayList<>();
        ListNode current = head;
        for (int i = 0; ; i++) {
            if (current == null)
                break;
            list.add(current);
            current = current.next;
        }

        for (int i = 0; i < list.size() / 2; i++)
            if (list.get(i).val != list.get(list.size() - 1 - i).val)
                return false;

        return true;
    }

}
