// Noah Park
/*

Problem: Write a function that takes in an array of integers and returns an array of length 2
representing the largest range of integers contained in that array.

The first number in the output array should be the first number in the range, while the second number
should be the last number in the range.

A range of numbers is defined as a set of numbers that come right after each other in the set of real
integers. For instance, the output array [2, 6] represents the range {2, 3, 4, 5, 6}, which is a range
of length 5. Note that number don't need to be sorted or adjacent in the input array in order to form a
range.

You can assume that there will only be one largest range.

*/

import java.util.Arrays;
import java.util.HashMap;

public class Largest_Range {
    // Optimal Solution
    // Time: O(n) | Space: O(n)
    public static int[] largestRange(int[] array) {
        // Store all the values from the array in a hash map tied to true boolean values that
        // represent if we have 'visited' them or not
        int[] ans = new int[2];
        HashMap<Integer, Boolean> h = new HashMap<>();
        for(int i = 0; i < array.length; i++){
            h.put(array[i], true);
        }
        // Iterate through the values in array once more
        for(int i = 0; i < array.length; i++){
            // Get the current value and check if it has not been visited (true)
            int cur = array[i];
            if(h.get(array[i])){
                // Update its visited value
                // Find the min/max range attainable from that number
                // Update the ans array if we find a larger range
                // Include <= so that 0,0 gets over-ridden to something like 1,1
                h.put(array[i], false);
                int min = findMin(h, cur);
                int max = findMax(h, cur);
                if(ans[1] - ans[0] <= max - min){
                    ans[0] = min;
                    ans[1] = max;
                }
            }
        }

        return ans;
    }

    // Finds the min value included in the range attainable from i
    public static int findMin(HashMap<Integer, Boolean> h, int i){
        while(h.containsKey(i)){
            h.replace(i, false);
            i--;
        }
        return i + 1;
    }

    // Finds the max value included in the range attainable from i
    public static int findMax(HashMap<Integer, Boolean> h, int i){
        while(h.containsKey(i)){
            h.replace(i, false);
            i++;
        }
        return i - 1;
    }

    // Naive Solution
    // Time: O(n*log(n)) | Space: O(n)
    public static int[] largestRange2(int[] array) {
        // Sort the array
        Arrays.sort(array);
        // p stands for permanent
        // min and max are just temporary to find the permanent values
        int pmin = 0;
        int pmax = 0;
        int min = 0;
        int max;
        boolean found = false;
        // Lets us know if we find a broken range
        // If found is false, the range is the entire array
        for(int i = 1; i < array.length; i++){
            // Iterate through the array
            // Compare neighboring values
            // If the value difference is larger than 1 then we have a broken range
            if(array[i] - array[i - 1] > 1){
                // Update found and max
                found = true;
                max = i - 1;
                // Compare to the perma values and update them if needed
                if(array[pmax] - array[pmin] < array[max] - array[min]){
                    pmax = max;
                    pmin = min;
                }
                // Update min to be a new start
                min = i;
            }
            // If we are at the end of the array
            else if(i == array.length - 1){
                // Update found and max
                found = true;
                max = i;
                // Compare to the perma values and update them if needed
                if(array[pmax] - array[pmin] < array[max] - array[min]){
                    pmax = max;
                    pmin = min;
                }
            }
        }
        // If we did not have a broken range, return the entire range of the array,
        // Otherwise, we must return the proper range using the perma values
        return !found ? new int[] {array[0], array[array.length - 1]} : new int[] {array[pmin], array[pmax]};
    }
}
