import java.util.HashMap;

/*
 * @lc app=leetcode id=1542 lang=java
 *
 * [1542] Find Longest Awesome Substring
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(n)
    public int longestAwesome(String s) {
        // corner case
        if (s == null || s.length() == 0) return 0;

        int n = s.length(), res = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int[] count = new int[10];

        for (int j = 0; j < n; j++) {
            count[s.charAt(j) - '0']++;
            int key = count2Key(count);

            if (map.containsKey(key)) {
                res = Math.max(res, j - map.get(key)); // [i + 1, j]
            }

            for (int k = 0; k < 10; k++) {
                int newKey = key;
                if (((newKey >> k) & 1) == 0) newKey += (1 << k);
                else newKey -= (1 << k);
                if (map.containsKey(newKey)) {
                    res = Math.max(res, j - map.get(newKey));
                } 
            }
            if (!map.containsKey(key)) map.put(key, j);
        }
        return res;
    }

    private int count2Key(int[] count) {
        int key = 0;
        for (int i = 0; i < 10; i++) {
            key += ((count[i] % 2) << i);
        }
        return key;
    }
}
// @lc code=end

