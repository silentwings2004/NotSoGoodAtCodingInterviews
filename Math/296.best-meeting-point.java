import java.util.List;

/*
 * @lc app=leetcode id=296 lang=java
 *
 * [296] Best Meeting Point
 */

// @lc code=start
class Solution {
    // S1: sort
    // time = O(m * n * log(m * n)), space = O(m * n)
    public int minTotalDistance(int[][] grid) {
        List<Integer> x = new ArrayList<>();
        List<Integer> y = new ArrayList<>();

        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    x.add(i);
                    y.add(j);
                }
            }
        }
        Collections.sort(x);
        Collections.sort(y);
        int r = x.get(x.size() / 2), c = y.get(y.size() / 2);

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    res += Math.abs(i - r) + Math.abs(j - c);
                }
            }
        }
        return res;
    }

    // S2: two pointers
    // time = O(m * n), space = O(m * n)
    public int minTotalDistance2(int[][] grid) {
        List<Integer> x = new ArrayList<>();
        List<Integer> y = new ArrayList<>();

        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    x.add(i);
                }
            }
        }

        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                if (grid[i][j] == 1) {
                    y.add(j);
                }
            }
        }
        return helper(x) + helper(y);
    }

    private int helper(List<Integer> list) {
        int left = 0, right = list.size() - 1, sum = 0;
        while (left < right) {
            sum += list.get(right--) - list.get(left++);
        }
        return sum;
    }
}
// @lc code=end

