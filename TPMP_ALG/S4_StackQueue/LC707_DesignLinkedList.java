package S4_StackQueue;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: DesignLinkedList
 * Creater: Silentwings
 * Date: Aug, 2020
 * Description: 707. Design Linked List
 */
public class LC707_DesignLinkedList {
    /**
     * Design your implementation of the linked list. You can choose to use the singly linked list or the doubly
     * linked list. A node in a singly linked list should have two attributes: val and next. val is the value of the
     * current node, and next is a pointer/reference to the next node. If you want to use the doubly linked list, you
     * will need one more attribute prev to indicate the previous node in the linked list. Assume all nodes in the
     * linked list are 0-indexed.
     *
     * Implement these functions in your linked list class:
     *
     * get(index) : Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     * addAtHead(val) : Add a node of value val before the first element of the linked list. After the insertion, the
     * new node will be the first node of the linked list.
     * addAtTail(val) : Append a node of value val to the last element of the linked list.
     * addAtIndex(index, val) : Add a node of value val before the index-th node in the linked list. If index equals
     * to the length of linked list, the node will be appended to the end of linked list. If index is greater than the
     * length, the node will not be inserted.
     * deleteAtIndex(index) : Delete the index-th node in the linked list, if the index is valid.
     *
     *
     * Example:
     *
     * Input:
     * ["MyLinkedList","addAtHead","addAtTail","addAtIndex","get","deleteAtIndex","get"]
     * [[],[1],[3],[1,2],[1],[1],[1]]
     * Output:
     * [null,null,null,null,2,null,3]
     *
     * Explanation:
     * MyLinkedList linkedList = new MyLinkedList(); // Initialize empty LinkedList
     * linkedList.addAtHead(1);
     * linkedList.addAtTail(3);
     * linkedList.addAtIndex(1, 2);  // linked list becomes 1->2->3
     * linkedList.get(1);            // returns 2
     * linkedList.deleteAtIndex(1);  // now the linked list is 1->3
     * linkedList.get(1);            // returns 3
     */

    /** Initialize your data structure here. */
    private int size;
    private ListNode head;
    private ListNode tail;

    public LC707_DesignLinkedList() {
        size = 0;
        head = null;
        tail = null;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        // corner case
        if (head == null || index < 0 || index >= size) return -1;

        ListNode cur = head;
        while (index-- > 0) {
            cur = cur.next;
        }
        return cur.val;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        ListNode node = new ListNode(val);
        // corner case
        if (head == null) tail = node;
        else {
            node.next = head;
            head.prev = node;
        }
        head = node;
        size++;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        ListNode node = new ListNode(val);
        // corner case
        if (tail == null) head = node;
        else {
            tail.next = node;
            node.prev = tail;
        }
        tail = node;
        size++;
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked
     *  list, the node will be appended to the end of linked list. If index is greater than the length, the node will
     *  not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size) return;

        if (index == 0) {
            addAtHead(val);
            return;
        }
        if (index == size) {
            addAtTail(val);
            return;
        }

        ListNode prev = head;
        while (prev != null && index-- > 1) {
            prev = prev.next;
        }
        ListNode node = new ListNode(val);
        node.next = prev.next;
        if (prev.next != null) prev.next.prev = node;
        prev.next = node;
        node.prev = prev;
        size++;
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) return;

        ListNode prev = head;
        if (index == 0) {
            head = head.next;
            prev.next = null;
            size--;
            return;
        }

        while (prev != null && index-- > 1) {
            prev = prev.next;
        }

        ListNode delNode = prev.next;
        prev.next = prev.next.next;
        if (delNode.next != null) delNode.next.prev = prev;
        delNode.prev = null;
        delNode.next = null;
        if (delNode == tail) tail = prev;
        size--;
    }

    class ListNode {
        private int val;
        private ListNode prev;
        private ListNode next;
        public ListNode(int val) {
            this.val = val;
        }
    }
}
