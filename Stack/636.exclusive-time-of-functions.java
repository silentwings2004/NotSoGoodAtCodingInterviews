/*
 * @lc app=leetcode id=636 lang=java
 *
 * [636] Exclusive Time of Functions
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(n)
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];
        Stack<int[]> stack = new Stack<>();

        for (String s : logs) {
            String[] strs = s.split(":");
            int id = Integer.parseInt(strs[0]);
            boolean isStart = strs[1].equals("start") ? true : false;
            int timestamp = Integer.parseInt(strs[2]);

            if (isStart) stack.push(new int[]{id, timestamp});
            else {
                int start = stack.pop()[1];
                int duration = timestamp - start + 1;
                res[id] += duration;

                if (!stack.isEmpty()) {
                    int prevId = stack.peek()[0];
                    res[prevId] -= duration;
                }
            }
        }
        return res;
    }
}
// @lc code=end

