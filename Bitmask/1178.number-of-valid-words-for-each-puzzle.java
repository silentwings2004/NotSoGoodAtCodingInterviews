import java.util.HashMap;

/*
 * @lc app=leetcode id=1178 lang=java
 *
 * [1178] Number of Valid Words for Each Puzzle
 */

// @lc code=start
class Solution {
    // time = O(n * k + m * 2^l), space = O(n)
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        List<Integer> res = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (String word : words) {
            int num = 0;
            for (char c : word.toCharArray()) {
                num |= (1 << (c - 'a'));
            }
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (String puzzle : puzzles) {
            char first = puzzle.charAt(0);
            int[] sub = new int[1 << 6];
            for (int state = 0; state < (1 << 6); state++) {
                int num = 0;
                num |= (1 << (first - 'a'));
                for (int j = 1; j < puzzle.length(); j++) {
                    if (((state >> (j - 1)) & 1) == 1) {
                        char c = puzzle.charAt(j);
                        num |= (1 << (c - 'a'));
                    }
                }
                sub[state] = num;
            }
            
            int count = 0;
            for (int x : sub) {
                count += map.getOrDefault(x, 0);
            }
            res.add(count);
        }
        return res;
    }
}
// @lc code=end

