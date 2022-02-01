/*
 * @lc app=leetcode id=2003 lang=java
 *
 * [2003] Smallest Missing Genetic Value in Each Subtree
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(n)
    List<Integer>[] children;
    int[] res;
    HashSet<Integer> set;
    int q = 1;
    public int[] smallestMissingValueSubtree(int[] parents, int[] nums) {
        int n = parents.length;
        children = new List[n];
        for (int i = 0; i < n; i++) children[i] = new ArrayList<>();

        res = new int[n];
        set = new HashSet<>();
        int node = -1;
        for (int i = 0; i < n; i++) {
            if (parents[i] != -1) children[parents[i]].add(i);
            if (nums[i] == 1) node = i;
        }
        if (node == -1) {
            Arrays.fill(res, 1);
            return res;
        }

        for (int child : children[node]) {
            dfs1(child);
        }

        int temp = node;
        while (node != 0) {
            int p = parents[node];
            for (int child : children[p]) {
                if (child == node) continue;
                dfs1(child);
            }
            node = p;
        }

        node = temp;
        while (node != -1) {
            dfs2(node, nums);
            while (set.contains(q)) q++;
            res[node] = q;
            node = parents[node];
        }
        return res;
    }

    private void dfs1(int node) {
        res[node] = 1;
        for (int child : children[node]) {
            dfs1(child);
        }
    }

    private void dfs2(int node, int[] nums) {
        if (set.contains(nums[node])) return;

        set.add(nums[node]);
        for (int child : children[node]) {
            dfs2(child, nums);
        }
    }
}
// @lc code=end

