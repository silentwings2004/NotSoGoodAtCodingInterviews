package S3_LinkedList;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: SwapNodesinPairs
 * Creater: Silentwings
 * Date: Aug, 2020
 * Description: 24. Swap Nodes in Pairs
 */
public class LC24_SwapNodesinPairs {
    /**
     * Given a linked list, swap every two adjacent nodes and return its head.
     *
     * You may not modify the values in the list's nodes, only nodes itself may be changed.
     *
     *
     *
     * Example:
     *
     * Given 1->2->3->4, you should return the list as 2->1->4->3.
     * @param head
     * @return
     */
    // time = O(n), space = O(1)
    public ListNode swapPairs(ListNode head) {
        // corner case
        if (head == null || head.next == null) return head;

        ListNode subHead = swapPairs(head.next.next);
        head.next.next = head;
        ListNode newHead = head.next;
        head.next = subHead;
        return newHead;
    }
}
