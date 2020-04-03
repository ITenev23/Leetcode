package bg.leetcode.exercises.itenev.linked_list;

import bg.leetcode.exercises.itenev.common.ListNode;

/**
 * Given a singly linked list, group all odd nodes together followed by the even nodes.
 * Please note here we are talking about the node number and not the value in the nodes.
 *
 * You should try to do it in place.
 * The program should run in O(1) space complexity and O(nodes) time complexity.
 */
public class OddEvenLinkedList {

    /**
     * A well-formed LinkedList need two pointers head and tail to support operations at both ends.
     * The variables head and odd are the head pointer and tail pointer of one LinkedList we call oddList;
     * the variables evenHead and even are the head pointer and tail pointer of another LinkedList we call evenList.
     * The algorithm traverses the original LinkedList and put the odd nodes into the oddList
     * and the even nodes into the evenList. To traverse a LinkedList we need at least one pointer
     * as an iterator for the current node. But here the pointers odd and even not only serve as the tail pointers
     * but also act as the iterators of the original list.
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null)
            return null;

        ListNode slow = head;
        ListNode fast = head.next;
        ListNode even = fast;

        while (fast != null && fast.next != null) {
            slow.next = fast.next;
            slow = slow.next;
            fast.next = slow.next;
            fast = fast.next;
        }

        slow.next = even;
        return head;
    }

    /**************************************************/

    public ListNode oddEvenListXOR(ListNode head) {
        ListNode odd = new ListNode(0);
        ListNode even = new ListNode(0);
        ListNode[] tail = {odd, even};
        int i = 0;

        while(head != null) {
            tail[i] = tail[i].next;
            tail[i].next = head;
            head = head.next;
            i ^= 1;
        }

        tail[0].next = even.next;
        tail[1].next = null;
        return odd.next;
    }

    /**************************************************/

    public ListNode oddEvenList2(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null) {
            ListNode slowParent = slow;
            slow = slow.next;
            if ((fast = fast.next) == null)
                break;
            ListNode fastParent = fast;
            if ((fast = fast.next) == null)
                break;
            /** Two lessons:
                1. always a good habbit to cache next_pointer in linked list manipulation.
                2. try thinking conceptually about what you did if you are stuck trying
                to see patterns simply from trace. */
            ListNode nextSlow = fast;
            fastParent.next = fast.next;
            fast.next = slow;
            slowParent.next = fast;

            slow = nextSlow;
            fast = fastParent;
        }
        return head;
    }
}
