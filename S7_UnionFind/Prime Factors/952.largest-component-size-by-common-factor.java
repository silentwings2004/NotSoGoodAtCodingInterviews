import java.util.HashMap;

/*
 * @lc app=leetcode id=952 lang=java
 *
 * [952] Largest Component Size by Common Factor
 */

// @lc code=start
class Solution {
    int[] parent;
    int L = 100005;
    public int largestComponentSize(int[] nums) {
        parent = new int[L];
        for (int i = 0; i < L; i++) parent[i] = i;

        int[] primes = eratosthenes((int)Math.sqrt(L));
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            for (int p : primes) {
                if (x < p) break;
                if (x % p == 0) {
                    if (findParent(nums[i]) != findParent(p)) {
                        union(nums[i], p);
                    }
                    while (x % p == 0) x /= p;
                }
            }
            if (x > 1) {
                if (findParent(nums[i]) != findParent(x)) union(nums[i], x);
            }
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int p = findParent(nums[i]);
            map.put(p, map.getOrDefault(p, 0) + 1);
        }

        int res = 0;
        for (int x : map.keySet()) {
            res = Math.max(res, map.get(x));
        }
        return res;
    }

    private int findParent(int x) {
        if (x != parent[x]) parent[x] = findParent(parent[x]);
        return parent[x];
    }

    private void union(int x, int y) {
        x = parent[x];
        y = parent[y];
        if (x < y) parent[y] = x;
        else parent[x] = y;
    }

    private int[] eratosthenes(int n) {
        int[] q = new int[n + 1];
        for (int i = 2; i <= (int)Math.sqrt(n); i++) {
            if (q[i] == 0) {
                int j = i * 2;
                while (j < n) {
                    q[j] = 1;
                    j += i;
                }
            }
        }

        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (q[i] == 0) primes.add(i);
        }

        int[] res = new int[primes.size()];
        for (int i = 0; i < primes.size(); i++) res[i] = primes.get(i);
        return res;
    }
}
// @lc code=end

