import java.util.HashMap;
import java.util.List;

/*
 * @lc app=leetcode id=947 lang=java
 *
 * [947] Most Stones Removed with Same Row or Column
 */

// @lc code=start
class Solution {
    // time = O(nlogk), space = O(k)   n: num of stones; k: different (x,y) numbers of array stones
    HashMap<Integer, Integer> parent;
    HashMap<Integer, List<Integer>> xMap;
    HashMap<Integer, List<Integer>> yMap;
    int n = 10000;
    public int removeStones(int[][] stones) {
        parent = new HashMap<>();
        xMap = new HashMap<>();
        yMap = new HashMap<>();

        for (int[] stone : stones) {
            int x = stone[0], y = stone[1];
            int id = x * n + y;
            if (!parent.containsKey(id)) parent.put(id, id);
            xMap.putIfAbsent(x, new ArrayList<>());
            yMap.putIfAbsent(y, new ArrayList<>());
            xMap.get(x).add(id);
            yMap.get(y).add(id);
        }

        for (int x : xMap.keySet()) {
            int u = xMap.get(x).get(0);
            for (int i = 1; i < xMap.get(x).size(); i++) {
                int v = xMap.get(x).get(i);
                if (findParent(u) != findParent(v)) union(u, v);
            }
        }

        for (int y : yMap.keySet()) {
            int u = yMap.get(y).get(0);
            for (int i = 1; i < yMap.get(y).size(); i++) {
                int v = yMap.get(y).get(i);
                if (findParent(u) != findParent(v)) union(u, v);
            }
        }

        HashSet<Integer> set = new HashSet<>();
        for (int[] stone : stones) {
            int id = stone[0] * n + stone[1];
            set.add(findParent(id));
        }
        return stones.length - set.size();
    }

    private int findParent(int x) {
        if (x != parent.get(x)) parent.put(x, findParent(parent.get(x)));
        return parent.get(x);
    }

    private void union(int x, int y) {
        x = parent.get(x);
        y = parent.get(y);
        if (x < y) parent.put(x, y);
        else parent.put(y, x);
    }
}
// @lc code=end

