// Noah Park
/*

Problem: Write a function that takes in a non-empty array of distinct integers and an integer
representing a target sum. The function should find all quadruplets in the array that sum up to
the target sum and return a two-dimensional array of all these quadruplets in no particular order.

If no four numbers sum up to the target sum, the function should return an empty array.

*/

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Four_Number_Sum {

    //O(n^2) for both space and time complexity
    public static List<Integer[]> fourNumberSum(int[] array, int targetSum) {
        List<Integer[]> ans = new ArrayList<>(); // return list initialized to an empty array list
        // Hash table stores the differences as the key so that when we check if our current sum is in the hash table,
        // we will see that it is there.
        // The values will be an array list of integer pairs that are the values that we obtained the difference from
        Hashtable<Integer, List<Integer[]>> sums = new Hashtable<>();

        // Use the difference technique from the 2 Number Sums problem
        // Start by looping through the array
        for(int i = 1; i < array.length - 1; i++){ // start at 1 since 0 iteration does nothing

            // Find matching pairs that have been inserted into the hash table
            // nothing will happen on the first iteration since sums is empty
            for(int j = i + 1; j < array.length; j++){
                int curSum = array[j] + array[i];
                if(sums.containsKey(curSum)){ // If we have curSum, we found our opposing pairs that match the ones we have here
                    for(Integer[] ints : sums.get(curSum)){ // Loop through all integer pairs that will allow us to reach the target sum and create integer arrays of size four to add to our ans array list
                        Integer[] quad = new Integer[]{array[i], array[j], ints[0], ints[1]};
                        ans.add(quad);
                    }
                }
            }

            // Insert pairs into the hash table
            // Inserting has to come after searching for the following reason:
            // We check for pairs above using array[i], so if we add values using array[i],
            // we end up with non distinct quadruplets, as we reuse the number to reach the goal.
            // For example, we may put [7,4] into the pairs list in the hashtable but then retrieve it
            // with [4,1] for a sum of 16. Notice how the 4 gets reused. Thus, by inserting after we check,
            // we avoid these gnarly duplicate issues.
            for(int k = 0; k < i; k++){ // Loop from
                int difference = targetSum - (array[k] + array[i]); // get the difference
                if(!sums.containsKey(difference)){ // Add it to the hash table if we don't have any occurrences
                    List<Integer[]> allPairs = new ArrayList<>(); // Since it stores array lists of pairs, we need to create an array list to add the pair to
                    allPairs.add(new Integer[]{array[k], array[i]});
                    sums.put(difference, allPairs);
                }
                else{
                    // If it is already in the hash table, there is an array list we can simply add to
                    sums.get(difference).add(new Integer[]{array[k], array[i]});
                }
            }
        }

        return ans;
    }

}
