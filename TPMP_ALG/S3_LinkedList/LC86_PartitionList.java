package S3_LinkedList;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: PartitionList
 * Creater: Silentwings
 * Date: Aug, 2020
 * Description: 86. Partition List
 */
public class LC86_PartitionList {
    /**
     * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than
     * or equal to x.
     *
     * You should preserve the original relative order of the nodes in each of the two partitions.
     *
     * Example:
     *
     * Input: head = 1->4->3->2->5->2, x = 3
     * Output: 1->2->2->4->3->5
     * @param head
     * @param x
     * @return
     */
    // time = O(n), space = O(1)
    public ListNode partition(ListNode head, int x) {
        // corner case
        if (head == null || head.next == null) return head;

        ListNode dummy1 = new ListNode(0);
        ListNode dummy2 = new ListNode(0);
        ListNode c1 = dummy1, c2 = dummy2;
        ListNode cur = head;

        while (cur != null) {
            if (cur.val < x) {
                c1.next = cur;
                c1 = c1.next;
            } else {
                c2.next = cur;
                c2 = c2.next;
            }
            cur = cur.next;
        }
        c2.next = null;
        c1.next = dummy2.next;
        return dummy1.next;
    }
}
