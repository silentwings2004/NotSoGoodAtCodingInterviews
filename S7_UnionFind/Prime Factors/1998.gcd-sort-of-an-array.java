/*
 * @lc app=leetcode id=1998 lang=java
 *
 * [1998] GCD Sort of an Array
 */

// @lc code=start
class Solution {
    // time = O(n(loglogM + M^(1/2)), space = O(n + M)
    int L = 100005;
    int[] parent; 
    public boolean gcdSort(int[] nums) {
        parent = new int[L];
        for (int i = 0; i < L; i++) parent[i] = i; // group by value instead of index!

        List<Integer> primes = helper((int)Math.sqrt(L));
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            for (int p : primes) {
                if (x < p) break;
                if (x % p == 0) {
                    if (findParent(nums[i]) != findParent(p)) { // path compression
                        union(nums[i], p);
                    }

                    while (x % p == 0) x /= p;
                }
            }
            if (x > 1) {
                if (findParent(nums[i]) != findParent(x)) {
                    union(nums[i], x);
                }
            }
        }

        int[] copy = nums.clone();
        Arrays.sort(copy);

        for (int i = 0; i < nums.length; i++) {
            if (findParent(nums[i]) != findParent(copy[i])) return false;
        }
        return true;
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

    List<Integer> helper(int n) { // sieve of eratosthenes
        int[] q = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            if (q[i] == 0) {
                int j = 2 * i;
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
        return primes;
    }
}


// @lc code=end

