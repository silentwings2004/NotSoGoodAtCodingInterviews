/*
 * @lc app=leetcode id=1707 lang=java
 *
 * [1707] Maximum XOR With an Element From Array
 */

// @lc code=start
class Solution {
    // time = O(mlogm + nlogn + m * n), space = O(m)  m: length of queries, n: length of nums
    public int[] maximizeXor(int[] nums, int[][] queries) {
        int m = nums.length, n = queries.length;
        int[][] que = new int[n][3];
        for (int i = 0; i < n; i++) {
            que[i][0] = queries[i][0];
            que[i][1] = queries[i][1];
            que[i][2] = i;
        }

        Arrays.sort(nums);
        Arrays.sort(que, (o1, o2) -> o1[1] - o2[1]);

        int[] res = new int[n];
        TrieNode root = new TrieNode();
        int i = 0;
        for (int[] q : que) {
            while (i < m && nums[i] <= q[1]) {
                TrieNode node = root;
                for (int k = 31; k >= 0; k--) {
                    if (node.next[(nums[i] >> k) & 1] == null) {
                        node.next[(nums[i] >> k) & 1] = new TrieNode();
                    }
                    node = node.next[(nums[i] >> k) & 1];
                }
                i++;
            }

            if (i == 0) {
                res[q[2]] = -1;
                continue;
            }

            TrieNode node = root;
            int ans = 0;
            for (int k = 31; k >= 0; k--) {
                if (node.next[1 - (q[0] >> k) & 1] != null) {
                    node = node.next[1 - (q[0] >> k) & 1];
                    ans = ans * 2 + 1;
                } else {
                    node = node.next[(q[0] >> k) & 1];
                    ans = ans * 2;
                }
            }
            res[q[2]] = ans;
        }
        return res;
    }

    private class TrieNode {
        private TrieNode[] next;
        public TrieNode() {
            next = new TrieNode[2];
        }
    }
}
// @lc code=end

