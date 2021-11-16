/*
 * @lc app=leetcode id=761 lang=java
 *
 * [761] Special Binary String
 */

// @lc code=start
class Solution {
    // time = O(n^2), space = O(n)
    public String makeLargestSpecial(String s) {
        int n = s.length();
        if (n == 2) return s;

        List<String> list = new ArrayList<>();
        for (int j = 0; j < n; j++) {
            int i = j, count = 0;
            while (j < n) {
                if (s.charAt(j) == '1') count++;
                else count--;
                if (count == 0) break;
                j++;
            }
            list.add("1" + makeLargestSpecial(s.substring(i + 1, j)) + "0"); // 注意这里是从 i + 1开始！！！
        }
        Collections.sort(list, (o1, o2) -> o2.compareTo(o1));
        StringBuilder sb = new StringBuilder();
        for (String x : list) sb.append(x);
        return sb.toString();
    }
}
// @lc code=end

