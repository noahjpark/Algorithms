/* Noah Park

Given a robot cleaner in a room modeled as a grid.

Each cell in the grid can be empty or blocked.

The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it made is 90 degrees.

When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays on the current cell.

Design an algorithm to clean the entire room using only the 4 given APIs shown below.

*/

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
    
    // Intuition: DFS in all possible directions. Since we don't know where the robot is at any given time, we start at some arbitrary (0,0) value we deem as the start. From here we don't actually have robot coordinates so a bfs is out of the question. Instead, we can dfs as far as possible then backtrack while maintaining all of the visited positions with respect to our source position.
    // Time: O(n*m) To check all cells in the event that all cells are reachable. It would be O(n*m - k) where k is the number of obstacles if we want to be specific.
    // Space: O(n*m) Same as above.
    Set<Pair<Integer, Integer>> visited = new HashSet<>();
    int[] curPos;
    
    public void cleanRoom(Robot robot) {
        curPos = new int[]{ 0, 0 };
        dfs(robot);
    }
    
    public void dfs(Robot robot) {
        robot.clean();
        visited.add(new Pair(curPos[0], curPos[1]));
        
        // Up
        if (!visited.contains(new Pair(curPos[0] - 1, curPos[1]))) {
            boolean up = moveUp(robot);
            if (up) {
                curPos[0]--;
                dfs(robot);
                curPos[0]++;
                moveDown(robot);
            }
        }
        
        // Left
        if (!visited.contains(new Pair(curPos[0], curPos[1] - 1))) {
            boolean left = moveLeft(robot);
            if (left) {
                curPos[1]--;
                dfs(robot);
                curPos[1]++;
                moveRight(robot);
            }
        }
        
        // Down
        if (!visited.contains(new Pair(curPos[0] + 1, curPos[1]))) {
            boolean down = moveDown(robot);
            if (down) {
                curPos[0]++;
                dfs(robot);
                curPos[0]--;
                moveUp(robot);
            }
        }
        
        // Right 
        if (!visited.contains(new Pair(curPos[0], curPos[1] + 1))) {
            boolean right = moveRight(robot);
            if (right) {
                curPos[1]++;
                dfs(robot);
                curPos[1]--;
                moveLeft(robot);
            }
        }
    }
    
    // The following four methods move the robot in the specified direction. After each call, the robot is always facing upwards.
    public boolean moveUp(Robot robot) {
        return robot.move();
    }
    
    public boolean moveLeft(Robot robot) {
        robot.turnLeft();
        boolean res = robot.move();
        robot.turnRight();
        return res;
    }
    
    public boolean moveRight(Robot robot) {
        robot.turnRight();
        boolean res = robot.move();
        robot.turnLeft();
        return res;
    }
    
    public boolean moveDown(Robot robot) {
        robot.turnLeft();
        robot.turnLeft();
        boolean res = robot.move();
        robot.turnLeft();
        robot.turnLeft();
        return res;
    }
}
