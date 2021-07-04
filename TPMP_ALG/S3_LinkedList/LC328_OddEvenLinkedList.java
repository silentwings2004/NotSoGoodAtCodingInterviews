package S3_LinkedList;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: OddEvenLinkedList
 * Creater: Silentwings
 * Date: Aug, 2020
 * Description: 328. Odd Even Linked List
 */
public class LC328_OddEvenLinkedList {
    /**
     * Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are
     * talking about the node number and not the value in the nodes.
     *
     * You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time
     * complexity.
     *
     * Example 1:
     *
     * Input: 1->2->3->4->5->NULL
     * Output: 1->3->5->2->4->NULL
     *
     * Example 2:
     *
     * Input: 2->1->3->5->6->4->7->NULL
     * Output: 2->3->6->7->1->5->4->NULL
     *
     *
     * Constraints:
     *
     * The relative order inside both the even and odd groups should remain as it was in the input.
     * The first node is considered odd, the second node even and so on ...
     * The length of the linked list is between [0, 10^4].
     * @param head
     * @return
     */
    // time = O(n), space = O(1)
    public ListNode oddEvenList(ListNode head) {
        // corner case
        if (head == null || head.next == null) return head;

        ListNode cur = head;
        ListNode oddHead = head, evenHead = head.next;
        ListNode c1 = oddHead, c2 = evenHead;
        cur = c2.next;

        while (cur != null) {
            c1.next = cur;
            c1 = c1.next;
            cur = cur.next;
            if (cur != null) {
                c2.next = cur;
                c2 = c2.next;
                cur = cur.next;
            }
        }
        c2.next = null;
        c1.next = evenHead;
        return head;
    }
}
