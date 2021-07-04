package S3_LinkedList;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: MergekSortedLists
 * Creater: Silentwings
 * Date: Aug, 2020
 * Description: 23. Merge k Sorted Lists
 */
public class LC23_MergekSortedLists {
    /**
     * Given an array of linked-lists lists, each linked list is sorted in ascending order.
     *
     * Merge all the linked-lists into one sort linked-list and return it.
     *
     *
     *
     * Example 1:
     *
     * Input: lists = [[1,4,5],[1,3,4],[2,6]]
     * Output: [1,1,2,3,4,4,5,6]
     * Explanation: The linked-lists are:
     * [
     *   1->4->5,
     *   1->3->4,
     *   2->6
     * ]
     * merging them into one sorted list:
     * 1->1->2->3->4->4->5->6
     *
     * Example 2:
     *
     * Input: lists = []
     * Output: []
     *
     * Example 3:
     *
     * Input: lists = [[]]
     * Output: []
     *
     *
     * Constraints:
     *
     * k == lists.length
     * 0 <= k <= 10^4
     * 0 <= lists[i].length <= 500
     * -10^4 <= lists[i][j] <= 10^4
     * lists[i] is sorted in ascending order.
     * The sum of lists[i].length won't exceed 10^4.
     * @param lists
     * @return
     */
    // S1: minHeap
    // time = O(nlogk), space = O(k)  n: # of nodes in the final LinkedList, k: # of LinkedList
    public ListNode mergeKLists(ListNode[] lists) {
        // corner case
        if (lists == null || lists.length == 0) return null;

        PriorityQueue<ListNode> heap = new PriorityQueue<>((o1, o2) -> (o1.val - o2.val));

        for (ListNode node : lists) {
                if (node != null) heap.offer(node);
        }

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        while (!heap.isEmpty()) {
            cur.next = heap.poll();
            cur = cur.next;
            if (cur.next != null) heap.offer(cur.next);
        }
        return dummy.next;
    }

    // S2: Merge Two
    // time = O(nlogk), space = O(1)
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return sort(lists, 0, lists.length - 1);
    }

    public ListNode sort(ListNode[] lists, int lo, int hi) {
        if (lo >= hi) return lists[lo];
        int mid = (hi - lo) / 2 + lo;
        ListNode l1 = sort(lists, lo, mid);
        ListNode l2 = sort(lists, mid + 1, hi);
        return merge(l1, l2);
    }

    public ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        }
        l2.next = merge(l1, l2.next);
        return l2;
    }
}
