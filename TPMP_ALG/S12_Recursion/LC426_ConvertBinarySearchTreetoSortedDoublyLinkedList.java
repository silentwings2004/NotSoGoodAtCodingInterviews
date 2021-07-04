package S12_Recursion;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: ConvertBinarySearchTreetoSortedDoublyLinkedList
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 426. Convert Binary Search Tree to Sorted Doubly Linked List
 */
public class LC426_ConvertBinarySearchTreetoSortedDoublyLinkedList {
    /**
     * Convert a Binary Search Tree to a sorted Circular Doubly-Linked List in place.
     *
     * You can think of the left and right pointers as synonymous to the predecessor and successor pointers in a
     * doubly-linked list. For a circular doubly linked list, the predecessor of the first element is the last element,
     * and the successor of the last element is the first element.
     *
     * We want to do the transformation in place. After the transformation, the left pointer of the tree node should
     * point to its predecessor, and the right pointer should point to its successor. You should return the pointer to
     * the smallest element of the linked list.
     *
     *
     *
     * Example 1:
     *
     *
     *
     * Input: root = [4,2,5,1,3]
     *
     *
     * Output: [1,2,3,4,5]
     *
     * Explanation: The figure below shows the transformed BST. The solid line indicates the successor relationship,
     * while the dashed line means the predecessor relationship.
     *
     * Example 2:
     *
     * Input: root = [2,1,3]
     * Output: [1,2,3]
     * Example 3:
     *
     * Input: root = []
     * Output: []
     * Explanation: Input is an empty tree. Output is also an empty Linked List.
     * Example 4:
     *
     * Input: root = [1]
     * Output: [1]
     */
    /*
    // Definition for a Node.
    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    };
    */
    // S1: dummy node
    Node pre = null;
    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        Node dummy = new Node(0, null, null); // dummy.right --> head
        pre = dummy;
        // 把nodes全部连起来
        helper(root);
        // helper的功能就是连起所有的node，让prev指向tail，而dummy不动，依然指向head
        // 头尾相连
        pre.right = dummy.right; //  ==> tail.next = head;
        dummy.right.left = pre; // ==> head.pre = tail;
        return dummy.right; // ==> dummy.right --> head
    }

    private void helper(Node root) {
        if (root == null) return;
        helper(root.left);
        pre.right = root;
        root.left = pre;
        pre = root;
        helper(root.right);
    }

    // S2: In-place recursion
    Node prev = null;
    Node head = null;
    public Node treeToDoublyList2(Node root) {
        if (root == null) return null;
        inOrder(root);
        prev.right = head;
        head.left = prev;
        return head;
    }

    private void inOrder(Node root) {
        if (root == null) return;
        inOrder(root.left);
        if (prev != null) prev.right = root;
        else head = root;
        root.left = prev;
        prev = root;
        inOrder(root.right);
    }

    // S3: Binary reduction --> divide and conquer
    public Node treeToDoublyList3(Node root) {
        if (root == null) return null;
        Node lchild = treeToDoublyList3(root.left);
        Node rchild = treeToDoublyList3(root.right);
        root.left = root;
        root.right = root;
        return helper3(helper3(lchild, root), rchild); // 分两步把左根右接起来
    }

    private Node helper3(Node lchild, Node rchild) {
        if (lchild == null) return rchild;
        if (rchild == null) return lchild;
        Node ltail = lchild.left;
        Node rtail = rchild.left;
        ltail.right = rchild;
        rchild.left = ltail;
        rtail.right = lchild;
        lchild.left = rtail;
        return lchild;
    }
}
