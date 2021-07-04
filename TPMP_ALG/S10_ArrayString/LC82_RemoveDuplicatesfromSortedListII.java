package S10_ArrayString;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: RemoveDuplicatesfromSortedListII
 * Creater: Silentwings
 * Date: May, 2020
 * Description: 82. Remove Duplicates from Sorted List II
 */
public class LC82_RemoveDuplicatesfromSortedListII {
    /**
     * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the
     * original list.
     *
     * Return the linked list sorted as well.
     *
     * Example 1:
     *
     * Input: 1->2->3->3->4->4->5
     * Output: 1->2->5
     * Example 2:
     *
     * Input: 1->1->1->2->3
     * Output: 2->3
     * @param head
     * @return
     */
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    // S1: Two Pointers
    // time = O(n), space = O(1)
    public ListNode deleteDuplicates(ListNode head) {
        // corner case
        if (head == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;
        while (slow.next != null && slow.next.next != null) {
            if (slow.next.val == slow.next.next.val) {
                ListNode fast = slow.next; // 探路结点
                while (fast != null && fast.next != null && fast.val == fast.next.val) {
                    fast = fast.next;
                }
                slow.next = fast.next;
            } else {
                slow = slow.next;
            }
        }
        return dummy.next;
    }

    // S2: HashMap
    // time = O(n), space = O(k) k is number of distinct nodes
    public ListNode deleteDuplicates2(ListNode head) {
        // corner case
        if (head == null) {
            return head;
        }

        ListNode cur = head;
        HashMap<Integer, Integer> map = new HashMap<>();
        while (cur != null) {
            if (map.containsKey(cur.val)) {
                map.put(cur.val, map.get(cur.val) + 1);
            } else {
                map.put(cur.val, 1);
            }
            cur = cur.next;
        }
        ListNode dummy = new ListNode(0);
        ListNode pointer = dummy;
        cur = head;
        while (cur != null) {
            if (map.get(cur.val) == 1) {
                pointer.next = cur;
                pointer = pointer.next;
            }
            cur = cur.next;
        }
        pointer.next = null;
        return dummy.next;
    }
}
