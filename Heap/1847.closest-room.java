import java.util.TreeSet;

/*
 * @lc app=leetcode id=1847 lang=java
 *
 * [1847] Closest Room
 */

// @lc code=start
class Solution {
    // time = O(mlogm + (m + n) * logn), space = O(m + n)
    public int[] closestRoom(int[][] rooms, int[][] queries) {
        int[][] que = new int[queries.length][3];
        for (int i = 0; i < queries.length; i++) { // O(m)
            que[i][0] = queries[i][0];
            que[i][1] = queries[i][1];
            que[i][2] = i;
        }

        Arrays.sort(que, (o1, o2) -> o2[1] - o1[1]); // O(mlogm)
        Arrays.sort(rooms, (o1, o2) -> o2[1] - o1[1]); // O(nlogn)

        TreeSet<Integer> set = new TreeSet<>();
        int i = 0;
        int[] res = new int[que.length];
        for (int[] q : que) { // O(m)
            while (i < rooms.length && rooms[i][1] >= q[1]) {
                set.add(rooms[i][0]); // O(logn)
                i++;
            }

            Integer fk = set.floor(q[0]);
            Integer ck = set.ceiling(q[0]);

            int ans = -1, diff = Integer.MAX_VALUE;
            if (ck != null) {
                if (Math.abs(ck - q[0]) < diff) {
                    diff = Math.abs(ck - q[0]);
                    ans = ck;
                }
            }

            if (fk != null) {
                if (Math.abs(fk - q[0]) <= diff) {
                    diff = Math.abs(fk - q[0]);
                    ans = fk;
                }
            }
            res[q[2]] = ans;
        }
        return res;
    }
}
// @lc code=end

