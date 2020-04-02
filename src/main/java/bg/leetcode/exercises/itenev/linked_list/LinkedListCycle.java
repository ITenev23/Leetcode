package bg.leetcode.exercises.itenev.linked_list;

import bg.leetcode.exercises.itenev.common.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a linked list, determine if it has a cycle in it.
 * To represent a cycle in the given linked list, we use an integer pos
 * which represents the position (0-indexed) in the linked list where tail connects to.
 * If pos is -1, then there is no cycle in the linked list.
 * <p>
 * Input: head = [3,2,0,-4], pos = 1
 * Output: true
 * Explanation: There is a cycle in the linked list, where tail connects to the second node.
 * <p>
 * Input: head = [1,2], pos = 0
 * Output: true
 * Explanation: There is a cycle in the linked list, where tail connects to the first node.
 * <p>
 * Input: head = [1], pos = -1
 * Output: false
 * Explanation: There is no cycle in the linked list.
 */
public class LinkedListCycle {

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null)
            return false;

        ListNode slow = head;
        ListNode fast = head.next;

        while (slow != fast) {
            if (fast == null || fast.next == null)
                return false;

            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    /***********************************************************/

    /**
     * We go through each node one by one and record each node's reference (or memory address) in a hash table.
     * If the current node is null, we have reached the end of the list and it must not be cyclic.
     * If current node’s reference is in the hash table, then return true.
     */
    public boolean hasCycle2(ListNode head) {
        Set<ListNode> seen = new HashSet<>();

        while (head != null) {
            if (seen.contains(head))
                return true;
            else
                seen.add(head);

            head = head.next;
        }
        return false;
    }

    /***********************************************************/

    public boolean hasCycleRecursion(ListNode head) {
        if (head == null || head.next == null)
            return false;
        if (head.next == head)
            return true;

        ListNode nextNode = head.next;
        head.next = head;

        return hasCycleRecursion(nextNode);
    }

    /***********************************************************/

    public boolean hasCycleRec(ListNode head) {
        if (head == null)
            return false;
        if (head.val == 0xcafebabe)
            return true;

        /** Mark this node as visited */
        head.val = 0xcafebabe;
        return hasCycleRec(head.next);
    }

}
