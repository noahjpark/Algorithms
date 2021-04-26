/* Noah Park

You want to build n new buildings in a city. The new buildings will be built in a line and are labeled from 1 to n.

However, there are city restrictions on the heights of the new buildings:

The height of each building must be a non-negative integer.
The height of the first building must be 0.
The height difference between any two adjacent buildings cannot exceed 1.
Additionally, there are city restrictions on the maximum height of specific buildings. These restrictions are given as a 2D integer array restrictions where restrictions[i] = [idi, maxHeighti] indicates that building idi must have a height less than or equal to maxHeighti.

It is guaranteed that each building will appear at most once in restrictions, and building 1 will not be in restrictions.

Return the maximum possible height of the tallest building.

*/

class Solution {
    
    // Intuition explained above loop. After sorting we can check restrictions in order.
    // Time: O(n log n) for sorting.
    // Space: O(n) most likely considering we are sorting objects.
    public int maxBuilding(int n, int[][] restrictions) {
        if (restrictions.length == 0 || n == 2) return n - 1;
        
        List<int[]> restrict = new ArrayList<>();
        
        restrict.add(new int[]{ 1, 0 });
        for (int[] restriction : restrictions) restrict.add(restriction);
        restrict.add(new int[]{ n, n - 1 });
        
        Collections.sort(restrict, (a, b) -> (a[0] - b[0]));
            
        // adjust restrictions from right to left
        for (int i = restrict.size() - 2; i >= 0; i--) 
            restrict.get(i)[1] = Math.min(restrict.get(i)[1], restrict.get(i + 1)[1] + restrict.get(i + 1)[0] - restrict.get(i)[0]);
        
        int max = 0;
        
        // adjust restrictions from left to right then find the maximum building between each restriction
        // difference is the difference in length between the buildings then subtracting the difference in their heights. This results in how many increments we would need to do to ensure both buildings are on the same height.
        // from here, the half of difference is how high we can go between the two buildings. We simply add this value to the larger of the two buildings as we "increased" the smaller one to that size.
        for (int i = 1; i < restrict.size(); i++) {
            restrict.get(i)[1] = Math.min(restrict.get(i)[1], restrict.get(i - 1)[1] + restrict.get(i)[0] - restrict.get(i - 1)[0]);
            int difference = restrict.get(i)[0] - restrict.get(i - 1)[0] - Math.abs(restrict.get(i)[1] - restrict.get(i - 1)[1]);
            max = Math.max(max, difference / 2 + Math.max(restrict.get(i)[1], restrict.get(i - 1)[1]));
        }
        
        return max;
    }
}
