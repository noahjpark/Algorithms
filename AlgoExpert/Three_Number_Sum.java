// Noah Park
/*

Problem: Write a function that takes in a non-empty array of distinct integers and an integer
representing a target sum. The function should find all triplets in the array that sum up to the
target sum and return a two-dimensional array of all these triplets. The numbers in each triplet should
be ordered in ascending order, and the triplets themselves should be ordered in ascending order with respect
to the numbers they hold.

If no three numbers sum up to the target sum, the function should return an empty array.

*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Three_Number_Sum {

    // O(n^2) time and O(n) space
    public static List<Integer[]> threeNumberSum(int[] array, int targetSum) {
        boolean foundMatch = false; // Tells us if we found some triplets or not
        List<Integer[]> ans = new ArrayList<Integer[]>(); // return array
        Arrays.sort(array); // so we can use the end to end technique

        // Use the same end to end technique from the first problem like this
        // To avoid getting repeats, don't recount some elements
        for(int i = 0; i < array.length - 1; i++){
            int left = i + 1; // Avoid repeats by starting left in front of i
            int right = array.length - 1; // Start right at the rightmost index
            while(left < right){ // Use the end to end technique
                if(array[i] + array[left] + array[right] == targetSum){
                    foundMatch = true;
                    Integer[] triplet = new Integer[3];
                    triplet[0] = array[i];
                    triplet[1] = array[left];
                    triplet[2] = array[right];
                    Arrays.sort(triplet); // Make sure the triplet is sorted
                    ans.add(triplet);
                    left++;
                }
                // Move left up if the sum is too small
                else if(array[i] + array[left] + array[right] < targetSum){
                    left++;
                }
                // Move right down if the sum is too large
                else{
                    right--;
                }
            }
        }

        // If there are no matches return an empty arraylist, otherwise return the ans array list
        return !foundMatch ? new ArrayList<Integer[]>() : ans;
    }

}
