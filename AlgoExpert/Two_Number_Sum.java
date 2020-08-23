// Noah Park
/*

Problem: Write a function that takes in a non-empty array of distinct
integers and an integer representing a target sum. If any two numbers
in the input array sum up to the target sum, the function should return
them in an array, in any order. If no two numbers sum up to the target
sum, the function should return an empty array.

Note that the target sum has to be obtained by summing two different integers
in the array; you can't add a single integer to itself in order to obtain the
target sum.

You can assume that there will be at most one pair of numbers summing up to the
target sum.

*/

import java.util.Hashtable;

public class Two_Number_Sum {

    // Naive solution in O(n^2)
    public static int[] twoNumberSum1(int[] array, int targetSum) {
        int[] ans = new int[2]; // return array
        boolean foundMatch = false; // for returning an empty array or ans

        // Loop through all possible combinations of elements
        for(int i = 0; i < array.length; i++){
            // firstNum will be i and we will traverse the rest of
            // the array each iteration to find a matching secondNum
            int firstNum = array[i];
            int secondNum = -1; // initialize to -1 to know if we can break from the outer loop early
            for(int j = 0; j < array.length; j++){
                if(i != j && firstNum + array[j] == targetSum){ // We found a match, update secondNum and break;
                    foundMatch = true;
                    secondNum = array[j];
                    break;
                }
            }
            if(secondNum != -1){ // We must have found a match if secondNum is not -1, update ans array and break;
                ans[0] = firstNum;
                ans[1] = secondNum;
                break;
            }
        }
        return !foundMatch ? new int[0] : ans; // return empty array with no size if we didn't find a match
    }

    // More time optimal solution O(n) but O(n) space by using a hash table
    @SuppressWarnings("unchecked")
    public static int[] twoNumberSum2(int[] array, int targetSum) {
        int[] ans = new int[2];
        boolean foundMatch = false;

        Hashtable h = new Hashtable<>();

        // We can do this in one pass
		/*
		Since we know the current value at our index and the target sum, we can add the difference
		at each index (targetSum - array[i]). By doing this, if we ever come across that value down
		the way in the array, we can simply return it and its complement to reach targetSum.
		*/
        for(int i = 0; i < array.length; i++){
            if(h.contains(array[i])){ // we found the matching complement
                foundMatch = true;
                ans[0] = array[i];
                ans[1] = targetSum - array[i];
                break;
            }
            h.put(i, targetSum - array[i]);
        }

        return !foundMatch ? new int[0] : ans; // return an empty array of no size if we don't find a match
    }

    /* O(n * log(n)) time and O(1) space
    A final solution to this problem is done by Clement using the end to end technique. Sort the array, I think
    Arrays.sort uses quicksort, then you'll have a sorted array. From there start two indices at the ends of the array
    and move them based on the current sum compared to the target sum. If the target sum is larger than our current sum,
    then we move the left up one. Otherwise, we will move the right down one. We will either end up finding a sum that we can
    return the two indices of in a new integer array, or we can return an empty array after breaking from the loop: while(left < right).
    */
}
