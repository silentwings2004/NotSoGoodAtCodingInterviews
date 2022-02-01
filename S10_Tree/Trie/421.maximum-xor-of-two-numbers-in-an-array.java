/*
 * @lc app=leetcode id=421 lang=java
 *
 * [421] Maximum XOR of Two Numbers in an Array
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(1)
    public int findMaximumXOR(int[] nums) {
        TrieNode root = new TrieNode();
        // build trie
        for (int num : nums) {
            TrieNode node = root;
            for (int k = 31; k >= 0; k--) {
                if (node.next[(num >> k) & 1] == null) {
                    node.next[(num >> k) & 1] = new TrieNode();
                }
                node = node.next[(num >> k) & 1];
            }
        }

        // find the best match for each num
        int res = 0;
        for (int num : nums) {
            TrieNode node = root;
            int ans = 0;
            for (int k = 31; k >= 0; k--) {
                if (node.next[1 - (num >> k) & 1] != null) {
                    node = node.next[1 - (num >> k) & 1];
                    ans = ans * 2 + 1;
                } else {
                    node = node.next[(num >> k) & 1];
                    ans = ans * 2;
                }
            }
            res = Math.max(res, ans);
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

