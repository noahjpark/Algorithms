// Noah Park
/*

Problem: Write a function that takes in an array of integers and returns a boolean representing
whether the array is monotonic.

An array is said to be monotonic if its elements, from left to right, are entirely non-increasing or
entirely non-decreasing.

Note that empty arrays and arrays of one element are monotonic.

*/

public class Monotonic_Array {
    // Time: O(n) | Space: O(1)
    public static boolean isMonotonic(int[] array) {
        // The array is monotonic if its length is 0 or 1
        if(array.length == 0 || array.length == 1){
            return true;
        }

        // Initialize decreasing (represents whether the array is decreasing or not) to -1
        // so that we can find the trend first then check whether the trend holds
        int decreasing = -1;

        // Initialize ans to true and find a point where the monotonicity does not hold and change to false
        // Otherwise, the whole array must be monotonic
        boolean ans = true;
        for(int i = 0; i < array.length - 1; i++){ // Go to the second to last element since we compare elements in front of the one we are currently on
            if(decreasing == -1){ // The trend (downwards or upwards) has yet to be found
                // Find the trend by comparing and seeing if the current element is either smaller or larger than the subsequent element. Mark decreasing to 0 if smaller and 1 if larger
                if(array[i] < array[i + 1]){
                    decreasing = 0;
                }
                else if(array[i] > array[i + 1]){
                    decreasing = 1;
                }
            }

            // If increasing and find a decreasing trend, update ans and break
            if(decreasing == 0 && array[i] > array[i + 1]){
                ans = false;
                break;
            }
            // If decreasing and find an increasing trend, update ans and break
            else if(decreasing == 1 && array[i] < array[i + 1]){
                ans = false;
                break;
            }
        }

        // ans will have been updated by this point in time
        return ans;
    }
}
