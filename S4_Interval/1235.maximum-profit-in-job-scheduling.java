/*
 * @lc app=leetcode id=1235 lang=java
 *
 * [1235] Maximum Profit in Job Scheduling
 */

// @lc code=start
class Solution {
    // S1: TreeMap
    // time = O(nlogn), space = O(n)
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] jobs = new int[n][3];

        for (int i = 0; i < n; i++) {
            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }

        Arrays.sort(jobs, (o1, o2) -> o1[1] - o2[1]);

        TreeMap<Integer, Integer> map = new TreeMap<>(); // {endTime -> profit}
        int res = 0;
        for (int i = 0; i < n; i++) {
            Integer fk = map.floorKey(jobs[i][0]);
            res = Math.max(res, (fk == null ? 0 : map.get(fk)) + jobs[i][2]);
            map.put(jobs[i][1], res);
        }
        return res;
    }

    // S2: BS
    // time = O(nlogn), space = O(n)
    public int jobScheduling2(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] jobs = new int[n][3];
        for (int i = 0; i < n; i++) {
            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }

        Arrays.sort(jobs, (o1, o2) -> o1[1] - o2[1]);

        List<int[]> dp = new ArrayList<>(); // {endPoint, profit}
        dp.add(new int[]{-1, 0});
        int res = 0;
        for (int i = 0; i < n; i++) {
            int idx = lowerBound(dp, jobs[i][0]);
            res = Math.max(res, dp.get(idx)[1] + jobs[i][2]);
            dp.add(new int[]{jobs[i][1], res});
        }
        return res;
    }

    private int lowerBound(List<int[]> dp, int target) {
        int left = 0, right = dp.size() - 1;
        while (left < right) {
            int mid = right - (right - left) / 2;
            if (dp.get(mid)[0] <= target) left = mid;
            else right = mid - 1;
        }
        return dp.get(left)[0] <= target ? left : left - 1;
    } 
}
// @lc code=end

