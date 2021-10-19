/*
 * @lc app=leetcode id=850 lang=java
 *
 * [850] Rectangle Area II
 */

// @lc code=start
class Solution {
    public int rectangleArea(int[][] rectangles) {
        TreeSet<Integer> setX = new TreeSet<>();
        TreeSet<Integer> setY = new TreeSet<>();

        for (int[] r : rectangles) {
            setX.add(r[0]);
            setX.add(r[2]);
            setY.add(r[1]);
            setY.add(r[3]);
        }

        List<Integer> xList = new ArrayList<>();
        List<Integer> yList = new ArrayList<>();
        for (int x : setX) xList.add(x);
        for (int x : setY) yList.add(x);

        HashMap<Integer, Integer> xMap = new HashMap<>();
        HashMap<Integer, Integer> yMap = new HashMap<>();
        for (int i = 0; i < xList.size(); i++) xMap.put(xList.get(i), i);
        for (int i = 0; i < yList.size(); i++) yMap.put(yList.get(i), i);
        
        int m = xList.size(), n = yList.size();
        long res = 0;
        long[][] diff = new long[m][n];
        long[][] sum = new long[m][n];
        long M = (long)(1e9 + 7);

        for (int[] r : rectangles) {
            int x1 = r[0], y1 = r[1], x2 = r[2], y2 = r[3];
            diff[xMap.get(x1)][yMap.get(y1)]++;
            diff[xMap.get(x1)][yMap.get(y2)]--;
            diff[xMap.get(x2)][yMap.get(y1)]--;
            diff[xMap.get(x2)][yMap.get(y2)]++;
        }

        sum[0][0] = diff[0][0];
        if (sum[0][0] > 0) {
            res = (long)(xList.get(1) - xList.get(0)) * (long)(yList.get(1) - yList.get(0)) % M;
        }

        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (i == 0 && j == 0) continue;
                long val1 = i >= 1 ? sum[i - 1][j] : 0;
                long val2 = j >= 1 ? sum[i][j - 1] : 0;
                long val3 = i >= 1 && j >= 1 ? sum[i - 1][j - 1] : 0;
                sum[i][j] = val1 + val2 - val3 + diff[i][j];
                if (sum[i][j] > 0) {
                    res = (res + (long)(xList.get(i + 1) - xList.get(i)) * (long)(yList.get(j + 1) - yList.get(j))) % M;
                }
            }
        }
        return (int) res;
    }
}
// @lc code=end

