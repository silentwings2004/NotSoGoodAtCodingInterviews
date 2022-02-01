/*
 * @lc app=leetcode id=212 lang=java
 *
 * [212] Word Search II
 */

// @lc code=start
class Solution {
    TrieNode root;
    HashSet<String> res;
    boolean[][] visited;
    int m, n;
    private int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public List<String> findWords(char[][] board, String[] words) {
        root = new TrieNode();
        res = new HashSet<>();

        // step 1: build trie
        for (String word : words) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (node.next[c - 'a'] == null) {
                    node.next[c - 'a'] = new TrieNode();
                }
                node = node.next[c - 'a'];
                node.count++;
            }
            node.isEnd = true;
        }

        // dfs
        m = board.length;
        n = board[0].length;
        visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, i, j, new StringBuilder(), root);
            }
        }
        return new ArrayList<>(res);
    }

    private void dfs(char[][] board, int i, int j, StringBuilder path, TrieNode node) {
        if (node.next[board[i][j] - 'a'] == null) return;
        if (node.next[board[i][j] - 'a'].count == 0) return;

        node = node.next[board[i][j] - 'a'];
        path.append(board[i][j]);
        visited[i][j] = true;

        if (node.isEnd) {
            res.add(path.toString());
            remove(path.toString());
            node.isEnd = false;
        }

        for (int[] dir : directions) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x < 0 || x >= m || y < 0 || y >= n) continue;
            if (visited[x][y]) continue;
            dfs(board, x, y, path, node);
        }
        // setback
        visited[i][j] = false;
        path.setLength(path.length() - 1);
    }

    private void remove(String s) {
        TrieNode node = root;
        for (char c : s.toCharArray()) {
            node = node.next[c - 'a'];
            node.count--;
        }
    }

    private class TrieNode {
        private TrieNode[] next;
        private boolean isEnd;
        private int count;
        public TrieNode() {
            this.next = new TrieNode[26];
            this.isEnd = false;
            this.count = 0;
        }
    }
}
// @lc code=end

