import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * @lc app=leetcode id=638 lang=java
 *
 * [638] Shopping Offers
 */

// @lc code=start
class Solution {
    // S1: dfs
    // time = O(n * k * m^n), space = O(n * m^n)
    HashMap<String, Integer> map;
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int n = price.size();
        map = new HashMap<>();
        // filter unncessary special deals
        List<List<Integer>> fs = new ArrayList<>();
        for (int i = 0; i < special.size(); i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                sum += special.get(i).get(j) * price.get(j);
            }
            if (sum > special.get(i).get(n)) fs.add(special.get(i));
        }

        return dfs(price, fs, needs);
    }

    private int dfs(List<Integer> price, List<List<Integer>> fs, List<Integer> needs) {
        int n = price.size();
        String key = getKey(needs);
        if (map.containsKey(key)) return map.get(key);

        int minPrice = 0;
        for (int i = 0; i < n; i++) {
            minPrice += needs.get(i) * price.get(i); // do not use any special deals
        }

        for (List<Integer> x : fs) {
            int speicalPrice = x.get(n);
            List<Integer> nxtNeeds = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (x.get(i) > needs.get(i)) break;
                nxtNeeds.add(needs.get(i) - x.get(i));
            }
            if (nxtNeeds.size() == n) {
                minPrice = Math.min(minPrice, dfs(price, fs, nxtNeeds) + speicalPrice);
            }
        }
        map.put(key, minPrice);
        return minPrice;
    }

    private String getKey(List<Integer> needs) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < needs.size(); i++) {
            sb.append(needs.get(i)).append('#');
        }
        return sb.toString();
    }
}
// @lc code=end

