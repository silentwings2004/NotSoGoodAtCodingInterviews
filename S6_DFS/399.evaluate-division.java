import java.util.HashMap;
import java.util.List;

/*
 * @lc app=leetcode id=399 lang=java
 *
 * [399] Evaluate Division
 */

// @lc code=start
class Solution {
    // time = O(m * n), space = O(n)  m: number of queries, n: number of input equations
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        HashMap<String, List<Pair>> map = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            String a = equations.get(i).get(0);
            String b = equations.get(i).get(1);
            double c = values[i];
            map.putIfAbsent(a, new ArrayList<>());
            map.putIfAbsent(b, new ArrayList<>());
            map.get(a).add(new Pair(b, c));
            map.get(b).add(new Pair(a, 1 / c));
        }

        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String a = queries.get(i).get(0);
            String b = queries.get(i).get(1);
            if (!map.containsKey(a) || !map.containsKey(b)) {
                res[i] = -1;
                continue;
            }
            HashSet<String> visited = new HashSet<>();
            res[i] = dfs(a, b, map, visited);
        }
        return res;
    }

    private double dfs(String a, String b, HashMap<String, List<Pair>> map, HashSet<String> visited) {
        // base case
        if (a.equals(b)) return 1.0;

        int size = map.getOrDefault(a, new ArrayList<>()).size();
        for (int i = 0; i < size; i++) {
            String c = map.get(a).get(i).str;
            if (visited.contains(c)) continue;
            visited.add(c);
            double val1 = map.get(a).get(i).val;
            double val2 = dfs(c, b, map, visited);
            if (val2 != -1) return val1 * val2;
        }
        return -1;
    }

    private class Pair {
        private String str;
        private double val;
        public Pair(String str, double val) {
            this.str = str;
            this.val = val;
        }
    }
}
// @lc code=end

