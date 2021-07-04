package S10_ArrayString;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: RemoveDuplicatesfromSortedList
 * Creater: Silentwings
 * Date: May, 2020
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
     * Example 2:
     *
     * Input: 1->1->2->3->3
     * Output: 1->2->3
     * @param head
     * @return
     *//**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    public ListNode deleteDuplicates(ListNode head) {
        // time = O(n), space = O(1)
        public ListNode deleteDuplicates(ListNode head) {
            // corner case
            if (head == null) {
                return head;
            }
            ListNode slow = head;
            ListNode fast = head.next;
            while (fast != null) {
                if (fast.val != slow.val) {
                    slow = slow.next;
                    slow.val = fast.val;
                }
                fast = fast.next;
            }
            slow.next = null;
            return head;
        }
}
