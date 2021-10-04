/*
 * @lc app=leetcode id=646 lang=java
 *
 * [646] Maximum Length of Pair Chain
 */

// @lc code=start
class Solution {
    // time = O(nlogn), space = O(1)
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (o1, o2) -> o1[1] - o2[1]);

        int n = pairs.length;
        int i = 0, count = 0;
        while (i < n) {
            count++;
            int j = i + 1;
            while (j < n && pairs[j][0] <= pairs[i][1]) j++;
            i = j;
        }
        return count;
    }
}
// @lc code=end

