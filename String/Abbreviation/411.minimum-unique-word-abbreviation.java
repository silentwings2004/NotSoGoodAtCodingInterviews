/*
 * @lc app=leetcode id=411 lang=java
 *
 * [411] Minimum Unique Word Abbreviation
 */

// @lc code=start
class Solution {
    // time = O(2^m * n), space = O(2^m * n)
    public String minAbbreviation(String target, String[] dictionary) {
        int n = target.length();
        HashSet<String> set = new HashSet<>();
        for (String word : dictionary) {
            if (word.length() == n) set.add(word);
        }

        List<int[]> masks = new ArrayList<>();
        for (int state = 0; state < (1 << n); state++) {
            masks.add(new int[]{getLen(state, n), state});
        }
        Collections.sort(masks, (o1, o2) -> o1[0] - o2[0]);

        for (int[] x : masks) {
            int state = x[1];
            String t = getAbbr(target, state, n);
            boolean flag = true;
            for (String word : set) {
                String s = getAbbr(word, state, n);
                if (s.equals(t)) {
                    flag = false;
                    break;
                }
            }
            if (flag) return t;
        }
        return "";
    }

    private int getLen(int state, int n) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (((state >> i) & 1) == 1) count++; // not abbr
            else {
                int j = i;
                while (j < n && ((state >> j) & 1) == 0) j++; // abbr
                count += String.valueOf(j - i).length();
                i = j - 1;
            }
        }
        return count;
    }

    private String getAbbr(String word, int state, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (((state >> i)& 1) == 1) sb.append(word.charAt(i));
            else {
                int j = i;
                while (j < n && ((state >> j) & 1) == 0) j++;
                sb.append(j - i);
                i = j - 1;
            }
        }
        return sb.toString();
    }
}
// @lc code=end

