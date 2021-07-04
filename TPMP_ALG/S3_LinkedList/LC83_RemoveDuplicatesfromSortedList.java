package S3_LinkedList;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: RemoveDuplicatesfromSortedList
 * Creater: Silentwings
 * Date: Aug, 2020
 * Description: 83. Remove Duplicates from Sorted List
 */
public class LC83_RemoveDuplicatesfromSortedList {
    /**
     * Given a sorted linked list, delete all duplicates such that each element appear only once.
     *
     * Example 1:
     *
     * Input: 1->1->2
     * Output: 1->2
     *
     * Example 2:
     *
     * Input: 1->1->2->3->3
     * Output: 1->2->3
     * @param head
     * @return
     */
    // time = O(n), space = O(1)
    public ListNode deleteDuplicates(ListNode head) {
        // corner case
        if (head == null || head.next == null) return head;

        ListNode prev = head, cur = head.next;
        while (cur != null) {
            if (prev.val == cur.val) {
                prev.next = cur.next;
                cur.next = null;
                cur = prev.next;
            } else {
                prev = cur;
                cur = cur.next;
            }
        }
        return head;
    }
}
