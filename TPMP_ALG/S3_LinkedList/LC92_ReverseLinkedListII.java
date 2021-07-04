package S3_LinkedList;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: ReverseLinkedListII
 * Creater: Silentwings
 * Date: Aug, 2020
 * Description: 92. Reverse Linked List II
 */
public class LC92_ReverseLinkedListII {
    /**
     * Reverse a linked list from position m to n. Do it in one-pass.
     *
     * Note: 1 ≤ m ≤ n ≤ length of list.
     *
     * Example:
     *
     * Input: 1->2->3->4->5->NULL, m = 2, n = 4
     * Output: 1->4->3->2->5->NULL
     * @param head
     * @param m
     * @param n
     * @return
     */
    // time = O(n), space = O(1)
    public ListNode reverseBetween(ListNode head, int m, int n) {
        // corner case
        if (head == null || head.next == null) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = head, prev = dummy;
        for (int i = 1; i < m; i++) {
            cur = cur.next;
            prev = prev.next;
        }

        for (int i = 1; i <= n - m; i++) {
            ListNode temp = cur.next;
            cur.next = temp.next;
            temp.next = prev.next;
            prev.next = temp;
        }
        return dummy.next;
    }
}
