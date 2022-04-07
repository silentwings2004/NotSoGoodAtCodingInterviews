/*
 * @lc app=leetcode id=745 lang=java
 *
 * [745] Prefix and Suffix Search
 */

// @lc code=start
class WordFilter {
    // time = O(nk^2), space = O(nk^2)
    TrieNode root;
    public WordFilter(String[] words) {
        root = new TrieNode();
        int n = words.length;
        for (int i = 0; i < n; i++) {
            String word = words[i] + "{";
            int m = word.length();
            for (int j = 0; j < m; j++) {
                TrieNode node = root;
                node.weight = i;
                // 把suffix放在前面去做处理，prefix则通过在原来的单词后加上分隔符，再连上一个完整单词来进行prefix的查询！
                // add "apple{apple", "pple{apple", "ple{apple", "le{apple", "e{apple", "{apple" into the Trie Tree
                for (int k = j; k < 2 * m - 1; k++) {
                    char c = word.charAt(k % m);
                    if (node.next[c - 'a'] == null) {
                        node.next[c - 'a'] = new TrieNode();
                    }
                    node = node.next[c - 'a'];
                    node.weight = i;
                }
            }
        }
    }
    // time = O(k), space = O(nk^2)
    public int f(String prefix, String suffix) {
        TrieNode node = root;
        for (char c : (suffix + "{" + prefix).toCharArray()) { // 注意：拼接的时候，这里是suffix在前！
            if (node.next[c - 'a'] == null) return -1;
            node = node.next[c - 'a'];
        }
        return node.weight;
    }

    private class TrieNode {
        private TrieNode[] next;
        private int weight;
        public TrieNode() {
            this.next = new TrieNode[27];
            this.weight = 0;
        }
    }
}

/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter obj = new WordFilter(words);
 * int param_1 = obj.f(prefix,suffix);
 */
// @lc code=end

