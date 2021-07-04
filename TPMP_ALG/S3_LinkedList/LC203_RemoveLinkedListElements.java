package S3_LinkedList;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: RemoveLinkedListElements
 * Creater: Silentwings
 * Date: Aug, 2020
 * Description: 203. Remove Linked List Elements
 */
public class LC203_RemoveLinkedListElements {
    /**
     * Remove all elements from a linked list of integers that have value val.
     *
     * Example:
     *
     * Input:  1->2->6->3->4->5->6, val = 6
     * Output: 1->2->3->4->5
     * @param head
     * @param val
     * @return
     */
    // time = O(n), space = O(1)
    public ListNode removeElements(ListNode head, int val) {
        // corner case
        if (head == null) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy, cur = head;
        while (cur != null) {
            if (cur.val == val) {
                prev.next = cur.next;
                cur.next = null;
                cur = prev.next;
            } else {
                prev = cur;
                cur = cur.next;
            }
        }
        return dummy.next;
    }
}
