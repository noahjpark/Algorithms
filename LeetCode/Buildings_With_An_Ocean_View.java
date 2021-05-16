/* Noah Park

There are n buildings in a line. You are given an integer array heights of size n that represents the heights of the buildings in the line.

The ocean is to the right of the buildings. A building has an ocean view if the building can see the ocean without obstructions. Formally, a building has an ocean view if all the buildings to its right have a smaller height.

Return a list of indices (0-indexed) of buildings that have an ocean view, sorted in increasing order.

*/

class Solution {
    
    // Intuition: Start at the ocean view and maintain the tallest building so far. If the current building is taller than the tallest building to the right, we can see the ocean. Each iteration, we maximize the tallest building.
    // Time: O(n) one pass over heights, one to convert to an array from a list.
    // Space: O(1)/O(n) constant without resulting list, otherwise maintains the indices from heights.
    public int[] findBuildings(int[] heights) {
        int curMax = 0, n = heights.length;
        List<Integer> l = new ArrayList<>();
        
        for (int i = n - 1; i >= 0; i--) {
            if (heights[i] > curMax) l.add(i);
            curMax = Math.max(curMax, heights[i]);
        }
        
        int s = l.size();
        int[] res = new int[s];
        for (int i = 0; i < s; i++)
            res[i] = l.get(s - i - 1);
        
        return res;
    }
}
