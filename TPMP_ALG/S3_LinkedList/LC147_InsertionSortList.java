package S3_LinkedList;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: InsertionSortList
 * Creater: Silentwings
 * Date: Aug, 2020
 * Description: 147. Insertion Sort List
 */
public class LC147_InsertionSortList {
    /**
     * Sort a linked list using insertion sort.
     *
     *
     * A graphical example of insertion sort. The partial sorted list (black) initially contains only the first
     * element in the list.
     * With each iteration one element (red) is removed from the input data and inserted in-place into the sorted
     * list
     *
     *
     * Algorithm of Insertion Sort:
     *
     * Insertion sort iterates, consuming one input element each repetition, and growing a sorted output list.
     * At each iteration, insertion sort removes one element from the input data, finds the location it belongs
     * within the sorted list, and inserts it there.
     * It repeats until no input elements remain.
     *
     * Example 1:
     *
     * Input: 4->2->1->3
     * Output: 1->2->3->4
     * Example 2:
     *
     * Input: -1->5->3->4->0
     * Output: -1->0->3->4->5
     * @param head
     * @return
     */
    // time = O(n^2), space = O(1)
    public ListNode insertionSortList(ListNode head) {
        // corner case
        if (head == null || head.next == null) return head; // 只有以一个node --> 不需要排序

        ListNode dummy = new ListNode(0);
        ListNode prev = dummy, cur = head, next = null;

        while (cur != null) {
            next = cur.next;
            while (prev.next != null && prev.next.val < cur.val) {
                prev = prev.next;
            } // 出while loop --> 插入位置在prev与prev.next之间
            cur.next = prev.next; // 将cur插入到prev与prev.next之间
            prev.next = cur;
            prev = dummy; // prev回到dummy处
            cur = next; // cur继续向下从刚才位置的下一个继续遍历
        }
        return dummy.next;
    }
}
