package S3_LinkedList;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: AddTwoNumbers
 * Creater: Silentwings
 * Date: Aug, 2020
 * Description: 2. Add Two Numbers
 */
public class LC2_AddTwoNumbers {
    /**
     * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in
     * reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked
     * list.
     *
     * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
     *
     * Example:
     *
     * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
     * Output: 7 -> 0 -> 8
     * Explanation: 342 + 465 = 807.
     * @param l1
     * @param l2
     * @return
     */
    // time = O(max(m, n)), space = O(1)
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // corner case
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        int sum = 0;
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l1 != null || l2 != null) {
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            cur.next = new ListNode(sum % 10);
            sum /= 10; // sum 充当了sum与carry的作用
            cur = cur.next;
        }
        if (sum != 0) cur.next = new ListNode(sum);
        return dummy.next;
    }
}
