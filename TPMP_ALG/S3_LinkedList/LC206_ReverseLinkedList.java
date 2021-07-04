package S3_LinkedList;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: ReverseLinkedList
 * Creater: Silentwings
 * Date: Aug, 2020
 * Description: 206. Reverse Linked List
 */
public class LC206_ReverseLinkedList {
    /**
     * Reverse a singly linked list.
     *
     * Example:
     *
     * Input: 1->2->3->4->5->NULL
     * Output: 5->4->3->2->1->NULL
     * Follow up:
     *
     * A linked list can be reversed either iteratively or recursively. Could you implement both?
     * @param head
     * @return
     */
    // S1: Iteration
    // time = O(n), space = O(1)
    public ListNode reverseList(ListNode head) {
        // corner case
        if (head == null || head.next == null) return head;

        ListNode cur = head;
        ListNode prev = null, next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    // S2: Recursion
    // time = O(n), space = O(1)
    public ListNode reverseList2(ListNode head) {
        // corner case
        if (head == null || head.next == null) return head;

        ListNode newHead = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
