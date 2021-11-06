class Solution {
    // time = O(26m + n), space = O(26m)
    public int shortestWay(String source, String target) {
        int m = source.length();
        source = "#" + source;
        
        int[][] next = new int[m + 1][26];
        
        // init next
        for (int i = 0; i < 26; i++) next[m][i] = -1;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < 26; j++) {
                next[i][j] = next[i + 1][j]; // copy from the right side
            }
            next[i][source.charAt(i + 1) - 'a'] = i + 1;
        }
        
        int j = 0, count = 1;
        for (int i = 0; i < target.length(); i++) {
            if (next[j][target.charAt(i) - 'a'] != -1) {
                j = next[j][target.charAt(i) - 'a'];
            } else {
                if (j == 0) return -1;
                j = 0;
                count++;
                i--;
            }
        }
        return count;
    }
}