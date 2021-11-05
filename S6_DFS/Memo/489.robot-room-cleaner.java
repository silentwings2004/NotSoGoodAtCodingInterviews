/*
 * @lc app=leetcode id=489 lang=java
 *
 * [489] Robot Room Cleaner
 */

// @lc code=start
/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * interface Robot {
 *     // Returns true if the cell in front is open and robot moves into the cell.
 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
 *     public boolean move();
 *
 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
 *     // Each turn will be 90 degrees.
 *     public void turnLeft();
 *     public void turnRight();
 *
 *     // Clean the current cell.
 *     public void clean();
 * }
 */

class Solution {
    // time = O(n - m), space = O(n - m)
    // n : number of cells, m: number of obstacles
    private int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    HashSet<String> visited;
    public void cleanRoom(Robot robot) {
        visited = new HashSet<>();
        visited.add(0 + "#" + 0);
        dfs(robot, 0, 0, 0);
    }

    private void dfs(Robot robot, int x, int y, int dir) {
        robot.clean();
        for (int k = 1; k <= 4; k++) {
            robot.turnRight();
            int nextDir = (dir + k) % 4;
            int i = x + directions[nextDir][0];
            int j = y + directions[nextDir][1];

            String hash = i + "#" + j;
            if (!visited.contains(hash) && robot.move()) {
                visited.add(hash);
                dfs(robot, i, j, nextDir);
                robot.turnRight();
                robot.turnRight();
                robot.move();
                robot.turnRight();
                robot.turnRight();
            }
        }
    }
}
// @lc code=end

