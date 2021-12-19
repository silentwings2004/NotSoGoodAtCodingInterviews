import java.util.HashMap;
import java.util.List;

/*
 * @lc app=leetcode id=721 lang=java
 *
 * [721] Accounts Merge
 */

// @lc code=start
class Solution {
    // time = O(n * k * log(n * k)), space = O(n * k), n: num of accounts, k: max length of an account
    HashMap<String, String> parent;
    HashMap<String, TreeSet<String>> emails;
    HashMap<String, String> owner;
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> res = new ArrayList<>();
        parent = new HashMap<>();
        emails = new HashMap<>();
        owner = new HashMap<>();
        
        for (List<String> x : accounts) {
            String person = x.get(0);
            String root = x.get(1);
            owner.put(root, person);

            if (!parent.containsKey(root)) parent.put(root, root);
            for (int i = 2; i < x.size(); i++) {
                String email = x.get(i);
                owner.put(email, person);
                if (!parent.containsKey(email)) parent.put(email, email);
                if (findParent(root) != findParent(email)) union(root, email);
            }
        }

        for (String email : parent.keySet()) {
            String root = findParent(email);
            emails.putIfAbsent(root, new TreeSet<>());
            emails.get(root).add(email);
        }

        for (String root : emails.keySet()) {
            String person = owner.get(root);
            List<String> temp = new ArrayList<>();
            temp.add(person);
            for (String email : emails.get(root)) temp.add(email);
            res.add(temp);
        }
        return res;
    }

    private String findParent(String x) {
        if (!x.equals(parent.get(x))) {
            parent.put(x, findParent(parent.get(x)));
        }
        return parent.get(x);
    }

    private void union(String x, String y) {
        x = findParent(x);
        y = findParent(y);
        parent.put(x, y);
    }
}
// @lc code=end

