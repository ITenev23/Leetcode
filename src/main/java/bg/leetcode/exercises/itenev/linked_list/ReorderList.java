package bg.leetcode.exercises.itenev.linked_list;

import bg.leetcode.exercises.itenev.common.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

/**
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 * <p>
 * Given 1->2->3->4, reorder it to 1->4->2->3.
 * <p>
 * Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
 */
public class ReorderList {

    public void reorderList(ListNode head) {
        HashMap<Integer, ListNode> map = new HashMap<>();
        for (int i = 1; head != null; head = head.next, i++) {
            map.put(i, head);
        }
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int i = 1, j = map.size(); i <= j; i++, j--) {
            curr.next = map.get(i);
            if (i != j)
                map.get(i).next = map.get(j);

            map.get(j).next = null;
            curr = map.get(j);
        }
    }

    /**********************************************************************/

    public void reorderList2(ListNode head) {
        if (head == null || head.next == null)
            return;

        ListNode p1 = head;
        ListNode p2 = head;
        while (p2.next != null && p2.next.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
        }

        //Reverse the half after middle  1->2->3->4->5->6 to 1->2->3->6->5->4
        ListNode preMiddle = p1;
        ListNode preCurrent = p1.next;
        while (preCurrent.next != null) {
            ListNode current = preCurrent.next;
            preCurrent.next = current.next;
            current.next = preMiddle.next;
            preMiddle.next = current;
        }

        //Start reorder one by one  1->2->3->6->5->4 to 1->6->2->5->3->4
        p1 = head;
        p2 = preMiddle.next;
        while (p1 != preMiddle) {
            preMiddle.next = p2.next;
            p2.next = p1.next;
            p1.next = p2;
            p1 = p2.next;
            p2 = preMiddle.next;
        }
    }

    /**********************************************************************/

    public void reorderList3(ListNode head) {
        if (head == null || head.next == null)
            return;

        Deque<ListNode> stack = new ArrayDeque<>();
        ListNode ptr = head;
        while (ptr != null) {
            stack.push(ptr);
            ptr = ptr.next;
        }
        int cnt = (stack.size() - 1) / 2;
        ptr = head;
        while (cnt-- > 0) {
            ListNode top = stack.pop();
            ListNode tmp = ptr.next;
            ptr.next = top;
            top.next = tmp;
            ptr = tmp;
        }
        stack.pop().next = null;
    }

    /**********************************************************************/


    public void reorderList4(ListNode head) {
        if (head == null || head.next == null)
            return;

        ListNode firstHalf = head;
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = null;

        ListNode secondHalf = reverse(slow);
        merge(firstHalf, secondHalf);
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }

    private void merge(ListNode first, ListNode second) {
        while (first != null) {
            ListNode firstNext = first.next;
            ListNode secondNext = second.next;

            first.next = second;
            if (firstNext == null)
                break;

            second.next = firstNext;
            first = firstNext;
            second = secondNext;
        }
    }
}
