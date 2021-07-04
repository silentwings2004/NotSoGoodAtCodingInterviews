package S3_LinkedList;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: RemoveNthNodeFromEndofList
 * Creater: Silentwings
 * Date: Aug, 2020
 * Description: 19. Remove Nth Node From End of List
 */
public class LC19_RemoveNthNodeFromEndofList {
    /**
     * Given a linked list, remove the n-th node from the end of list and return its head.
     *
     * Example:
     *
     * Given linked list: 1->2->3->4->5, and n = 2.
     *
     * After removing the second node from the end, the linked list becomes 1->2->3->5.
     * Note:
     *
     * Given n will always be valid.
     *
     * Follow up:
     *
     * Could you do this in one pass?
     * @param head
     * @param n
     * @return
     */
    // time = O(n), space = O(1)
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // corner case
        if (head == null || n <= 0) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy, fast = dummy;

        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        ListNode next = slow.next;
        slow.next = next.next;
        next.next = null;
        return dummy.next;
    }
}
