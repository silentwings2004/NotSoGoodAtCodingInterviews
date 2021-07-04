package S3_LinkedList;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: MiddleoftheLinkedList
 * Creater: Silentwings
 * Date: Aug, 2020
 * Description: 876. Middle of the Linked List
 */
public class LC876_MiddleoftheLinkedList {
    /**
     * Given a non-empty, singly linked list with head node head, return a middle node of linked list.
     *
     * If there are two middle nodes, return the second middle node.
     *
     *
     *
     * Example 1:
     *
     * Input: [1,2,3,4,5]
     * Output: Node 3 from this list (Serialization: [3,4,5])
     * The returned node has value 3.  (The judge's serialization of this node is [3,4,5]).
     * Note that we returned a ListNode object ans, such that:
     * ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, and ans.next.next.next = NULL.
     *
     * Example 2:
     *
     * Input: [1,2,3,4,5,6]
     * Output: Node 4 from this list (Serialization: [4,5,6])
     * Since the list has two middle nodes with values 3 and 4, we return the second one.
     *
     *
     * Note:
     *
     * The number of nodes in the given list will be between 1 and 100.
     * @param head
     * @return
     */
    // time = O(n), space = O(1)
    public ListNode middleNode(ListNode head) {
        // corner case
        if (head == null || head.next == null) return head;

        ListNode slow = head;
        ListNode fast = head; // for even case, return the 2nd middle one
        // for even case, if return the 1st middle one, the code should be:
        // ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
