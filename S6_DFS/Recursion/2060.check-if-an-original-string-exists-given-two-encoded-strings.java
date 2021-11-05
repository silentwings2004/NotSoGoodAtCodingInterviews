import java.util.List;

/*
 * @lc app=leetcode id=2060 lang=java
 *
 * [2060] Check if an Original String Exists Given Two Encoded Strings
 */

// @lc code=start
class Solution {
    public boolean possiblyEquals(String s1, String s2) {
        List<String> t1 = parse(s1);
        List<String> t2 = parse(s2);
        HashSet<String> visited = new HashSet<>();
        return dfs(t1, 0, 0, t2, 0, 0, visited);
    }

    private boolean dfs(List<String> t1, int i, int num1, List<String> t2, int j, int num2, HashSet<String> visited) {
        // base case
        if (i == t1.size() && j == t2.size()) return num1 == num2;
        if (i == t1.size() && num1 == 0 || j == t2.size() && num2 == 0) return false;

        String hash = i + "#" + num1 + "#" + j + "#" + num2;
        if (visited.contains(hash)) return false;

        if (i < t1.size() && Character.isDigit(t1.get(i).charAt(0))) {
            HashSet<Integer> set = getNum(t1.get(i));
            for (int x : set) {
                if (dfs(t1, i + 1, num1 + x, t2, j, num2, visited)) return true;
            }
            visited.add(hash);
            return false;
        }
        if (j < t2.size() && Character.isDigit(t2.get(j).charAt(0))) {
            HashSet<Integer> set = getNum(t2.get(j));
            for (int x : set) {
                if (dfs(t1, i, num1, t2, j + 1, num2 + x, visited)) return true;
            }
            visited.add(hash);
            return false;
        }

        if (num1 != 0 && num2 != 0) {
            int common = Math.min(num1, num2);
            return dfs(t1, i, num1 - common, t2, j, num2 - common, visited);
        } else if (num1 != 0 && num2 == 0) {
            return dfs(t1, i, num1 - 1, t2, j + 1, num2, visited);
        } else if (num1 == 0 && num2 != 0) {
            return dfs(t1, i + 1, num1, t2, j, num2 - 1, visited);
        } else {
            if (!t1.get(i).equals(t2.get(j))) {
                visited.add(hash);
                return false;
            }
            return dfs(t1, i + 1, 0, t2, j + 1, 0, visited);
        }
    }

    private List<String> parse(String s) {
        List<String> res = new ArrayList<>();
        int n = s.length();

        for (int i = 0; i < n; i++) {
            if (Character.isLetter(s.charAt(i))) res.add(s.substring(i, i + 1));
            else if (Character.isDigit(s.charAt(i))) {
                int j = i;
                while (j < n && Character.isDigit(s.charAt(j))) j++;
                res.add(s.substring(i, j));
                i = j - 1;
            }
        }
        return res;
    }

    private HashSet<Integer> getNum(String s) {
        HashSet<Integer> set = new HashSet<>();
        int d = Integer.parseInt(s);
        
        if (s.length() == 1) set.add(d);
        else if (s.length() == 2) {
            int a = d / 10, b = d % 10;
            set.addAll(Arrays.asList(a + b, d));
        } else {
            int a = d / 100, b = (d / 10) % 10, c = d % 10;
            set.addAll(Arrays.asList(a + b + c, a * 10 + b + c, a + b * 10 + c, d));
        }
        return set;
    }
}
// @lc code=end

