import java.util.TreeMap;

/*
 * @lc app=leetcode id=2071 lang=java
 *
 * [2071] Maximum Number of Tasks You Can Assign
 */

// @lc code=start
class Solution {
    // time = O(nlogn + (m + n) * logm * logn), space = O(m)
    public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
        Arrays.sort(tasks); // O(nlogn)

        int left = 0, right = tasks.length;
        while (left < right) {  // O(logn)
            int mid = right - (right - left) / 2;
            if (checkOK(tasks, workers, pills, strength, mid)) left = mid;
            else right = mid - 1;
        }
        return left;
    }

    private boolean checkOK(int[] tasks, int[] workers, int pills, int strength, int t) {
        if (t > workers.length) return false;

        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int worker : workers) map.put(worker, map.getOrDefault(worker, 0) + 1); // mlogm

        for (int i = t - 1; i >= 0; i--) { // O(n)
            if (map.lastKey() >= tasks[i]) {
                int x = map.lastKey(); // O(logm)
                map.put(x, map.get(x) - 1);
                if (map.get(x) == 0) map.remove(x);
            } else {
                if (pills == 0) return false;
                Integer ck = map.ceilingKey(tasks[i] - strength);
                if (ck == null) return false;
                map.put(ck, map.get(ck) - 1);
                if (map.get(ck) == 0) map.remove(ck);
                pills--;
            }
        }
        return true;
    }
}
// @lc code=end

