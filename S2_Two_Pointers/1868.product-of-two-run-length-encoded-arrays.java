/*
 * @lc app=leetcode id=1868 lang=java
 *
 * [1868] Product of Two Run-Length Encoded Arrays
 */

// @lc code=start
class Solution {
    // time = O(Math.max(m, n)), space = O(1));
    public List<List<Integer>> findRLEArray(int[][] encoded1, int[][] encoded2) {
        List<List<Integer>> res = new ArrayList<>();

        int m = encoded1.length, n = encoded2.length;
        int i = 0, j = 0;
        while (i < m && j < n) {
            int match = Math.min(encoded1[i][1], encoded2[j][1]);
            encoded1[i][1] -= match;
            encoded2[j][1] -= match;
            int product = encoded1[i][0] * encoded2[j][0];
            if (!res.isEmpty() && res.get(res.size() - 1).get(0) == product) {
                List<Integer> list = res.get(res.size() - 1);
                list.set(1, list.get(1) + match);
            } else res.add(Arrays.asList(product, match));
            if (encoded1[i][1] == 0) i++;
            if (encoded2[j][1] == 0) j++;
        }
        return res;
    }
}
// @lc code=end

