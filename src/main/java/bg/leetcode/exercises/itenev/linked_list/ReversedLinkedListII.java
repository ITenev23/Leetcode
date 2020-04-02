package bg.leetcode.exercises.itenev.linked_list;

import bg.leetcode.exercises.itenev.common.ListNode;

/**
 * Reverse a linked list from position m to n. Do it in one-pass.
 * <p>
 * Note: 1 ≤ m ≤ n ≤ length of list.
 * <p>
 * Input: 1->2->3->4->5->NULL, m = 2, n = 4
 * Output: 1->4->3->2->5->NULL
 */
public class ReversedLinkedListII {

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null)
            return null;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        for (int i = 0; i < m - 1; i++)
            prev = prev.next;

        // a pointer to the beginning of a sub-list that will be reversed
        ListNode start = prev.next;
        // a pointer to a node that will be reversed
        ListNode then = start.next;

        // 1 - 2 -3 - 4 - 5 ; m=2; n =4 ---> prev = 1, start = 2, then = 3
        // dummy-> 1 -> 2 -> 3 -> 4 -> 5

        for (int i = 0; i < n - m; i++) {
            start.next = then.next;
            then.next = prev.next;
            prev.next = then;
            then = start.next;
        }

        // first reversing : dummy->1 - 3 - 2 - 4 - 5; prev = 1, start = 2, then = 4
        // second reversing: dummy->1 - 4 - 3 - 2 - 5; prev = 1, start = 2, then = 5 (finish)
        return dummy.next;
    }

    public ListNode reverseBetween2(ListNode head, int m, int n) {
        if (head == null)
            return null;

        ListNode cur = head;
        ListNode prev = null;

        while (m > 1) {
            prev = cur;
            cur = cur.next;
            m--;
            n--;
        }

        // The two pointers that will fix the final connections.
        ListNode con = prev, tail = cur;

        // Iteratively reverse the nodes until n becomes 0.
        ListNode third = null;
        while (n > 0) {
            third = cur.next;
            cur.next = prev;
            prev = cur;
            cur = third;
            n--;
        }

        if (con != null)
            con.next = prev;
        else
            head = prev;

        tail.next = cur;
        return head;
    }


    // Object level variables since we need the changes
    // to persist across recursive calls and Java is pass by value.
    private boolean stop;
    private ListNode left;

    public ListNode reverseBetween3(ListNode head, int m, int n) {
        this.left = head;
        this.stop = false;
        this.recurseAndReverse(head, m, n);
        return head;
    }

    public void recurseAndReverse(ListNode right, int m, int n) {
        if (n == 1)
            return;

        // Keep moving the right pointer one step forward until (n == 1)
        right = right.next;

        // Keep moving left pointer to the right until we reach the proper node
        // from where the reversal is to start.
        if (m > 1)
            this.left = this.left.next;


        // Recurse with m and n reduced.
        this.recurseAndReverse(right, m - 1, n - 1);

        // In case both the pointers cross each other or become equal, we
        // stop i.e. don't swap data any further. We are done reversing at this
        // point.
        if (this.left == right || right.next == this.left) {
            this.stop = true;
        }

        // Until the boolean stop is false, swap data between the two pointers
        if (!this.stop) {
            int t = this.left.val;
            this.left.val = right.val;
            right.val = t;

            // Move left one step to the right.
            // The right pointer moves one step back via backtracking.
            this.left = this.left.next;
        }
    }

}
