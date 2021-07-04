package S3_LinkedList;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: PlusOneLinkedList
 * Creater: Silentwings
 * Date: Aug, 2020
 * Description: 369. Plus One Linked List
 */
public class LC369_PlusOneLinkedList {
    /**
     * Given a non-negative integer represented as non-empty a singly linked list of digits, plus one to the integer.
     *
     * You may assume the integer do not contain any leading zero, except the number 0 itself.
     *
     * The digits are stored such that the most significant digit is at the head of the list.
     *
     * Example :
     *
     * Input: [1,2,3]
     * Output: [1,2,4]
     * @param head
     * @return
     */
    // time = O(n), space = O(1)
    public ListNode plusOne(ListNode head) {
        // corner case
        if (head == null) return head;

        head = reverse(head);
        ListNode cur = head;
        int carry = 0;
        while (cur != null && cur.val == 9) {
            cur.val = 0;
            cur = cur.next;
        }
        if (cur != null) cur.val += 1;
        else carry = 1;
        head = reverse(head);
        if (carry != 0) {
            ListNode newHead = new ListNode(carry);
            newHead.next = head;
            return newHead;
        }
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
