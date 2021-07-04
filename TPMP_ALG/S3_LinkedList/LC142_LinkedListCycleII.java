package S3_LinkedList;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: LinkedListCycleII
 * Creater: Silentwings
 * Date: Aug, 2020
 * Description: 142. Linked List Cycle II
 */
public class LC142_LinkedListCycleII {
    /**
     * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
     *
     * To represent a cycle in the given linked list, we use an integer pos which represents the position
     * (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked
     * list.
     *
     * Note: Do not modify the linked list.
     *
     *
     *
     * Example 1:
     *
     * Input: head = [3,2,0,-4], pos = 1
     * Output: tail connects to node index 1
     * Explanation: There is a cycle in the linked list, where tail connects to the second node.
     *
     *
     * Example 2:
     *
     * Input: head = [1,2], pos = 0
     * Output: tail connects to node index 0
     * Explanation: There is a cycle in the linked list, where tail connects to the first node.
     *
     *
     * Example 3:
     *
     * Input: head = [1], pos = -1
     * Output: no cycle
     * Explanation: There is no cycle in the linked list.
     *
     *
     *
     *
     * Follow-up:
     * Can you solve it without using extra space?
     * @param head
     * @return
     */
    // time = O(n), space = O(1)
    public ListNode detectCycle(ListNode head) {
        // corner case
        if (head == null) return head;

        ListNode slow = head, fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) return null;
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = head;
        while (slow.next != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }
}
