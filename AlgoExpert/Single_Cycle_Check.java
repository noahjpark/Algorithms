// Noah Park
/*

Problem: You're given an array of integers where each integer represents a jump of its value in the array.
For instance, the integer 2 represents a jump of two indices forward in the array; the integer -3 represents
a jump of three indices backward in the array.

If a jump spills past the array's bounds, it wraps over to the other side. For instance, a jump of -1 at index
0 brings us to the last index in the array. Similarly, a jump of 1 at the last index in the array brings us to
index 0.

Write a function that returns a boolean representing whether the jumps in the array form a single cycle. A single
cycle occurs if, starting at any index in the array and following the jumps, every element in the array is visited
exactly once before landing back on the starting index.

*/

public class Single_Cycle_Check {
    // Optimal solution | Time: O(n) | Space: O(1)
    public static boolean hasSingleCycle(int[] array) {
        // curIndex represents our current index into the array
        // visited represents the number of elements we have visited so far
        int curIndex = 0;
        int visited = 0;

        // Since we are only looking to visited each element in the array once,
        // we can iterate only over the number of elements in the array
        while(visited < array.length){
            // This is our cycle check: if the number of elements we have visited is greater
            // than 0 and we reach our start index again, we clearly do not have a single cycle
            if(visited > 0 && curIndex == 0){
                return false;
            }

            // Update curIndex and visited
            curIndex += array[curIndex];
            visited++;

            // If we have a curIndex that is either less than 0 or greater than the array length,
            // we need to adjust its value to be within the array indices.
            if(curIndex < 0){
                int abs = Math.abs(curIndex);
                int rem = (array.length + abs) % array.length;
                curIndex = array.length - rem;
            }
            if(curIndex >= array.length){
                curIndex = curIndex % array.length;
            }
        }

        // The curIndex should end on 0, otherwise, there cannot be a single cycle
        return curIndex == 0;
    }
}
