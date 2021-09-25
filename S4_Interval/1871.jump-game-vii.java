/*
 * @lc app=leetcode id=1871 lang=java
 *
 * [1871] Jump Game VII
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(n)
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        if (s.charAt(0) == '1' || s.charAt(n - 1) == '1') return false;

        int[] diff = new int[n + 1]; // 必须+1位
        diff[0 + minJump] = 1;
        diff[0 + maxJump + 1] = -1;


        int reach = 0;
        for (int i = 1; i < n; i++) {
            reach += diff[i];
            if (reach == 0) continue; // 无法走到，越过！
            if (s.charAt(i) == '1') continue;
            if (i + minJump <= n) diff[i + minJump]++;
            if (i + maxJump + 1 <= n ) diff[i + maxJump + 1]--;
        }

        return reach > 0;
    }
}
// @lc code=end

