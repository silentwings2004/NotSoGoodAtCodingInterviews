import java.util.HashMap;
import java.util.List;

/*
 * @lc app=leetcode id=815 lang=java
 *
 * [815] Bus Routes
 */

// @lc code=start
class Solution {
    // time = O(n^2), space = O(n^2)
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) return 0;
        HashMap<Integer, List<Integer>> stop2bus = new HashMap<>();
        int n = routes.length;
        for (int i = 0; i < n; i++) {
            for (int j : routes[i]) {
                stop2bus.putIfAbsent(j, new ArrayList<>());
                stop2bus.get(j).add(i);
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);
        HashSet<Integer> visitedStop = new HashSet<>();
        visitedStop.add(source);
        HashSet<Integer> visitedBus = new HashSet<>();

        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int cur = queue.poll();
                for (int bus : stop2bus.getOrDefault(cur, null)) {
                    if (!visitedBus.add(bus)) continue;
                    for (int next : routes[bus]) {
                        if (!visitedStop.add(next)) continue;
                        if (next == target) return step + 1;
                        queue.offer(next);
                    }
                } 
            }
            step++;
        }
        return -1;
    }
}
// @lc code=end

