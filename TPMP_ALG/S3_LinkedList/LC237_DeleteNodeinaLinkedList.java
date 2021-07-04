package S3_LinkedList;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: DeleteNodeinaLinkedList
 * Creater: Silentwings
 * Date: Aug, 2020
 * Description: 237. Delete Node in a Linked List
 */
public class LC237_DeleteNodeinaLinkedList {
    /**
     * Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.
     *
     * Given linked list -- head = [4,5,1,9], which looks like following:
     *
     *
     *
     *
     *
     * Example 1:
     *
     * Input: head = [4,5,1,9], node = 5
     * Output: [4,1,9]
     * Explanation: You are given the second node with value 5, the linked list should become 4 -> 1 -> 9 after
     * calling your function.
     *
     * Example 2:
     *
     * Input: head = [4,5,1,9], node = 1
     * Output: [4,5,9]
     * Explanation: You are given the third node with value 1, the linked list should become 4 -> 5 -> 9 after
     * calling your function.
     *
     *
     * Note:
     *
     * The linked list will have at least two elements.
     * All of the nodes' values will be unique.
     * The given node will not be the tail and it will always be a valid node of the linked list.
     * Do not return anything from your function.
     * @param node
     */
    // time = O(1), space = O(1)
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        ListNode next = node.next;
        node.next = next.next;
        next.next = null;
    }
}
