package S3_LinkedList;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: ReverseNodesinkGroup
 * Creater: Silentwings
 * Date: Aug, 2020
 * Description: 25. Reverse Nodes in k-Group
 */
public class LC25_ReverseNodesinkGroup {
    /**
     * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
     *
     * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is
     * not a multiple of k then left-out nodes in the end should remain as it is.
     *
     * Example:
     *
     * Given this linked list: 1->2->3->4->5
     *
     * For k = 2, you should return: 2->1->4->3->5
     *
     * For k = 3, you should return: 3->2->1->4->5
     *
     * Note:
     *
     * Only constant extra memory is allowed.
     * You may not alter the values in the list's nodes, only nodes itself may be changed.
     * @param head
     * @param k
     * @return
     */
    // time = O(n), space = O(n / k)
    public ListNode reverseKGroup(ListNode head, int k) {
        // corner case
        if (head == null || head.next == null || k <= 0) return head;

        ListNode cur = head;
        for (int i = 1; i < k; i++) {
            cur = cur.next;
            if (cur == null) return head;
        }
        ListNode next = cur.next; // 找到recursion group list的head
        cur.next = null; // 与前k个断开，分别reverse
        ListNode newHead = reverse(head); // reverse前k个
        head.next = reverseKGroup(next, k); // recursion后面n个k group，并与前k个接上
        return newHead;
    }

    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
