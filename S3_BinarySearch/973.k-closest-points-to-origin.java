/*
 * @lc app=leetcode id=973 lang=java
 *
 * [973] K Closest Points to Origin
 */

// @lc code=start
class Solution {
    public int[][] kClosest(int[][] points, int k) {
        List<long[]> arr = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            long d = points[i][0] * points[i][0] + points[i][1] * points[i][1];
            arr.add(new long[]{d, i});
        }

        long dist = quickselect(arr, 0, arr.size() - 1, k);

        int[][] res = new int[k][2];
        int idx = 0;
        for (long[] x : arr) {
            if (x[0] <= dist) res[idx++] = points[(int)x[1]];
        }
        return res;
    }

    private long quickselect(List<long[]> arr, int a, int b, int k) {
        long pivot = arr.get(a + (b - a) / 2)[0];

        int i = a, j = b, t = a;
        while (t <= j) {
            if (arr.get(t)[0] < pivot) swap(arr, t++, i++);
            else if (arr.get(t)[0] > pivot) swap(arr, t, j--);
            else t++;
        }

        if (i - a >= k) return quickselect(arr, a, i - 1, k);
        if (j - a + 1 >= k) return pivot;
        return quickselect(arr, j + 1, b, k - (j - a + 1));
    }

    private void swap(List<long[]> arr, int i, int j) {
        long[] temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }
}
// @lc code=end

