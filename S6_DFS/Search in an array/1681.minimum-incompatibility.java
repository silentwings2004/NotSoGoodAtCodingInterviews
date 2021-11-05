import java.util.List;

/*
 * @lc app=leetcode id=1681 lang=java
 *
 * [1681] Minimum Incompatibility
 */

// @lc code=start
class Solution {
    // S1: dfs
    // time = O(3^n), space = O(n)
    private int res = Integer.MAX_VALUE;
    public int minimumIncompatibility(int[] nums, int k) {
        int n = nums.length;
        int[] count = new int[17];
        for (int x : nums) {
            count[x]++;
            if (count[x] > k) return -1;
        }

        Arrays.sort(nums);
        boolean[] visited = new boolean[n];
        visited[0] = true;
        dfs(nums, k, 0, 1, nums[0], nums[0], 0, visited);
        return res;
    }

    private void dfs(int[] nums, int k, int idx, int count, int low, int high, int sum, boolean[] visited) {
        int n = nums.length;
        if (count == n / k) {
            // find the next element to start searching for the next subset
            int j = 0;
            while (j < n && visited[j]) j++;
            if (j == n) {
                res = Math.min(res, sum + high - low);
                return;
            } else {
                visited[j] = true;
                dfs(nums, k, j, 1, nums[j], nums[j], sum + high - low, visited);
                visited[j] = false;
            }
        } else {
            int last = -1;
            for (int i = idx + 1; i < n; i++) {
                if (visited[i]) continue;
                if (nums[i] == nums[idx]) continue;
                if (nums[i] == last) continue;
                visited[i] = true;
                dfs(nums, k, i, count + 1, low, nums[i], sum, visited);
                last = nums[i];
                visited[i] = false;
            }
        }
    }

    // S2: DP + state compression
    // time = O(3^n), space = O(2^n)
    public int minimumIncompatibility2(int[] nums, int k) {
        int n = nums.length;
        int[] count = new int[17];
        for (int x : nums) {
            count[x]++;
            if (count[x] > k) return -1;
        }

        List<Integer> states = new ArrayList<>();
        List<Integer> values = new ArrayList<>();

        int state = (1 << n / k) - 1;
        while (state < (1 << n)) {
            int val = 0;
            int[] vals = new int[]{state, val};

            if (!containsDup(nums, vals)) {
                states.add(vals[0]);
                values.add(vals[1]);
            }

            int c = state & -state;
            int r = state + c;
            state = (((r ^ state) >> 2) / c) | r;
        }

        List<Integer> dpstates = new ArrayList<>();
        for (int dpstate = 0; dpstate < (1 << n); dpstate++) {
            if (Integer.bitCount(dpstate) % (n / k) == 0) dpstates.add(dpstate);
        }

        Collections.sort(dpstates, (o1, o2) -> o2 - o1);

        int[] dp = new int[1 << n];
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[0] = 0;

        for (int i = 0; i < states.size(); i++) {
            for (int dpstate : dpstates) {
                if ((dpstate & states.get(i)) == states.get(i)) {
                    dp[dpstate] = Math.min(dp[dpstate], dp[dpstate - states.get(i)] + values.get(i));
                }
            }
        }
        return dp[(1 << n) - 1];
    }

    private boolean containsDup(int[] nums, int[] vals) {
        int n = nums.length;
        List<Integer> p = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (((vals[0] >> i) & 1) == 1) p.add(nums[i]);
        }

        Collections.sort(p);

        for (int i = 1; i < p.size(); i++) {
            if (p.get(i) == p.get(i - 1)) return true;
        }
        vals[1] = p.get(p.size() - 1) - p.get(0);
        return false;
    }
}
// @lc code=end

