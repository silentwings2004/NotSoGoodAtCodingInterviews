package S3_LinkedList;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: SortList
 * Creater: Silentwings
 * Date: Aug, 2020
 * Description: 148. Sort List
 */
public class LC148_SortList {
    /**
     * Sort a linked list in O(n log n) time using constant space complexity.
     *
     * Example 1:
     *
     * Input: 4->2->1->3
     * Output: 1->2->3->4
     *
     * Example 2:
     *
     * Input: -1->5->3->4->0
     * Output: -1->0->3->4->5
     * @param head
     * @return
     */
    // time = O(nlogn), space = O(1)
    public ListNode sortList(ListNode head) {
        // corner case
        if (head == null || head.next == null) return head;

        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {  // --> O(n)
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode h1 = head, h2 = slow.next;
        slow.next = null;
        h1 = sortList(h1);
        h2 = sortList(h2);
        head = merge(h1, h2);
        return head;
    }

    private ListNode merge(ListNode h1, ListNode h2) {
        // corner case
        if (h1 == null) return h2;
        if (h2 == null) return h1;

        ListNode head, cur;
        if (h1.val < h2.val) {
            head = h1;
            h1 = h1.next;
        } else {
            head = h2;
            h2 = h2.next;
        }
        cur = head;
        while (h1 != null && h2 != null) {
            if (h1.val < h2.val) {
                cur.next = h1;
                h1 = h1.next;
            } else {
                cur.next = h2;
                h2 = h2.next;
            }
            cur = cur.next;
        }
        if (h1 != null) cur.next = h1;
        if (h2 != null) cur.next = h2;
        return head;
    }
}
