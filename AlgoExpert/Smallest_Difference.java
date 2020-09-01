// Noah Park
/*

Problem: Write a function that takes in two non-empty arrays of integers, finds the pair of
numbers (one from each array) whose absolute difference is closest to zero, and returns an
array containing these two numbers, with the number from the first array in the first position.

You can assume that there will only be one pair of numbers with the smallest difference.

*/

import java.util.Arrays;

public class Smallest_Difference {

    /*

    Basically, the trick to this problem, and many array search problems it seems, is to
    sort the array(s). Then we can use different techniques. In the past, the end to end technique
    has worked for minimizing time complexity.

    Here, by sorting the arrays, we can minimize the time complexity of searching the arrays
    since we know the current positive difference between our two integers. If we increment the
    larger value, we clearly will end up with an even larger difference which is bad. Rather, by
    incrementing the smaller value, we run the potential of finding a closer difference to 0 which is
    our goal.

    */

    // Naive solution O(n^2) comparisons for time complexity O(1) space
    public static int[] smallestDifference1(int[] arrayOne, int[] arrayTwo) {
        // If either array is empty or null/uninitialized, return an empty array
        if(arrayOne == null || arrayTwo == null || arrayOne.length == 0 || arrayTwo.length == 0){
            return new int[0];
        }
        // Declare answer array to return
        // Declare num1 and num2 to represent the numbers from arrayOne and arrayTwo respectively
        // Declare small as the maximum integer. This will be updated to a smaller difference as we find
        // two smaller integers (one from each array) that will achieve a value close to 0
        int[] ans = new int[2];
        int num1 = -1;
        int num2 = -1;
        int small = Integer.MAX_VALUE;

        // Iterate through arrayOne
        for(int i = 0; i < arrayOne.length; i++){
            int temp1 = arrayOne[i];
            // For each iteration/element through arrayOne, iterate through all of arrayTwo
            // Compare the difference between temp1 and temp2 and see if its less than our smallest difference
            // found so far. If so, we update the smallest difference and our num1/num2 values corresponding to
            // the new numbers found.
            for(int j = 0; j < arrayTwo.length; j++){
                int temp2 = arrayTwo[j];
                if(Math.abs(temp1 - temp2) < small){
                    small = Math.abs(temp1 - temp2);
                    num1 = temp1;
                    num2 = temp2;
                }
            }
        }

        // Populate and return ans
        ans[0] = num1;
        ans[1] = num2;
        return ans;
    }

    // Optimal solution in O(n*log(n) + m*log(m)) time complexity and O(1) space
    public static int[] smallestDifference(int[] arrayOne, int[] arrayTwo) {
        if(arrayOne.length == 0 || arrayTwo.length == 0 || arrayOne == null || arrayTwo == null){
            return new int[0];
        }
        // Uses quick sort
        Arrays.sort(arrayOne); // O(n*log(n))
        Arrays.sort(arrayTwo); // O(n*log(m)) since the arrays can be of different sizes

        // Initialize ans array for the two values
        // Initialize an i and j index for arrayOne and arrayTwo respectively
        // Initialize a difference value to the maximum integer to find smaller differences as we look through the array
        int[] ans = new int[2];
        int i = 0;
        int j = 0;
        int difference = Integer.MAX_VALUE; // start a large difference so we can only go down
        // Loop until we reach the end of either array
        while(i < arrayOne.length && j < arrayTwo.length){
            // Get the absolute difference between our two numbers
            int curDifference = Math.abs(arrayOne[i] - arrayTwo[j]);

            // If the current difference is 0 we have found our minimum difference, as there is only one minimum difference
            if(curDifference == 0){
                // Update ans and break;
                ans[0] = arrayOne[i];
                ans[1] = arrayTwo[j];
                break;
            }

            // We have found a new minimum difference:
            // Update 'difference' and ans
            else if(curDifference < difference){
                difference = curDifference;
                ans[0] = arrayOne[i];
                ans[1] = arrayTwo[j];
            }

            // Move the i and j indices/pointers based on whichever is smaller
            // Ideally, we will find a value closer together which will result in a possible
            // smaller difference
            if(arrayOne[i] > arrayTwo[j]){ // The value at the j index is smaller than the value at i
                j++;
            }
            else{ // The value at the i index is smaller than the value at the j index
                i++;
            }
        }

        return ans;
    }
}
