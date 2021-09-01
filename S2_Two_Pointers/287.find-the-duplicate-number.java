/*
 * @lc app=leetcode id=287 lang=java
 *
 * [287] Find the Duplicate Number
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(1)
    public int findDuplicate(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) return -1;
        
        int n = nums.length;
        int slow = nums[0], fast = nums[0];
        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]]; // fast = fast.next.next
            if (slow == fast) break;
        }
        fast = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
// @lc code=end

