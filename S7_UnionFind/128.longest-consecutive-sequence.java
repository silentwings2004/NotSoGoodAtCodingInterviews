import java.util.HashMap;

/*
 * @lc app=leetcode id=128 lang=java
 *
 * [128] Longest Consecutive Sequence
 */

// @lc code=start
class Solution {
    // S1: HashSet
    // time = O(n), space = O(n)
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int x : nums) set.add(x);

        int res = 0;
        for (int x : set) {
            int count = 0;
            if (!set.contains(x - 1)) { // 不是最小值，而是中间值，以后到了最小值再数，现在不用管
                while (set.contains(x)) {
                    count++;
                    x++;
                }
                res = Math.max(res, count);
            }
        }
        return res;
    }

    // S2: Union Find
    // time = O(nlogn), space = O(n)
    HashMap<Integer, Integer> parent;
    public int longestConsecutive2(int[] nums) {
        parent = new HashMap<>();
        for (int x : nums) {
            if (!parent.containsKey(x)) parent.put(x, x);
            if (parent.containsKey(x - 1) && findParent(x - 1) != findParent(x)) union(x - 1, x);
            if (parent.containsKey(x + 1) && findParent(x + 1) != findParent(x)) union(x + 1, x);
        }

        for (int x : nums) parent.put(x, findParent(x)); // path compression
        HashMap<Integer, HashSet<Integer>> count = new HashMap<>();
        for (int x : nums) {
            int p = parent.get(x);
            count.putIfAbsent(p, new HashSet<>());
            count.get(p).add(x);
        }

        int res = 0;
        for (int key : count.keySet()) {
            res = Math.max(res, count.get(key).size());
        }
        return res;
    }

    private int findParent(int x) {
        if (x != parent.get(x)) parent.put(x, findParent(parent.get(x)));
        return parent.get(x);
    }

    private void union(int x, int y) {
        x = parent.get(x);
        y = parent.get(y);
        if (x < y) parent.put(y, x);
        else parent.put(x, y);
    }
}
// @lc code=end

