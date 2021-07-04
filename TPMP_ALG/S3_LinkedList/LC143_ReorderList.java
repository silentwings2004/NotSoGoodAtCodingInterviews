package S3_LinkedList;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: ReorderList
 * Creater: Silentwings
 * Date: Aug, 2020
 * Description: 143. Reorder List
 */
public class LC143_ReorderList {
    /**
     * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
     * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
     *
     * You may not modify the values in the list's nodes, only nodes itself may be changed.
     *
     * Example 1:
     *
     * Given 1->2->3->4, reorder it to 1->4->2->3.
     *
     * Example 2:
     *
     * Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
     * @param head
     */
    // time = O(n), space = O(1)
    public void reorderList(ListNode head) {
        // corner case
        if (head == null || head.next == null) return;

        ListNode midNode = findMid(head);
        ListNode leftHead = head, rightHead = midNode.next;
        midNode.next = null;
        merge(leftHead, reverse(rightHead));
    }

    private ListNode findMid(ListNode head) {
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode merge(ListNode h1, ListNode h2) {
        if (h1 == null) return h2;
        if (h2 == null) return h1;

        ListNode head = h1;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        while (h1 != null && h2 != null) {
            cur.next = h1;
            h1 = h1.next;
            cur = cur.next;
            cur.next = h2;
            h2 = h2.next;
            cur = cur.next;
        }
        if (h1 != null) cur.next = h1;
        return head;
    }

    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
