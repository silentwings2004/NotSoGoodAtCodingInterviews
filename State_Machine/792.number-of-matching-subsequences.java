/*
 * @lc app=leetcode id=792 lang=java
 *
 * [792] Number of Matching Subsequences
 */

// @lc code=start
class Solution {
    // S1: TreeSet
    // time = O(n * logm * k), space = O(m)  n: length of s, m: length of words, k: average length of word
    public int numMatchingSubseq(String s, String[] words) {
        TreeSet<Integer>[] pos = new TreeSet[26];
        for (int i = 0; i < 26; i++) pos[i] = new TreeSet<>();
        for (int i = 0; i < s.length(); i++) {
            pos[s.charAt(i) - 'a'].add(i);
        }

        int count = 0;
        for (String word : words) {
            if (word.length() > s.length()) continue;
            if (check(word, pos)) count++;
        }
        return count;
    }

    private boolean check(String word, TreeSet<Integer>[] pos) {
        int i = 0;
        for (char ch : word.toCharArray()) {
            Integer idx = pos[ch - 'a'].ceiling(i);
            if (idx == null) return false;
            i = idx + 1;
        }
        return true;
    }

    // S2: State Machine
    // time = O(26m + n * k), space = O(26m) n: length of s, m: length of words, k: average length of word
    public int numMatchingSubseq2(String s, String[] words) {
        int m = s.length();
        s = "#" + s; // s[1:m] 扩充一位！！！
        int[][] next = new int[m + 1][26];
        Arrays.fill(next[m], -1);

        // build next array
        for (int i = m; i >= 1; i--) {
            for (int k = 0; k < 26; k++) {
                next[i - 1][k] = next[i][k];
            }
            next[i - 1][s.charAt(i) - 'a'] = i;
        }

        int res = 0;
        for (String word : words) {
            int i = 0;
            boolean flag = true;
            for (char ch : word.toCharArray()) {
                i = next[i][ch - 'a'];
                if (i == -1) {
                    flag = false;
                    break;
                }
            }
            if (flag) res++;
        }
        return res;
    }   
}
// @lc code=end

