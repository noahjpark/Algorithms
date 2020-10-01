// Noah Park
/*

Problem: Write a function that takes in an array of unique integers and returns an array of all
permutations of those integers in no particular order.

If the input array is empty, the function should return an empty array.

*/

import java.util.ArrayList;
import java.util.List;

public class Permutations {
    // Time: O(n^2*n!) | Space: O(n*n!)
    // There are n*n! calls to the helper method (in the tree of calls)
    // Space on stack is blown out of the water by this space
    public static List<List<Integer>> getPermutations(List<Integer> array) {
        // If the array is empty, return an empty array list
        if(array.size() == 0){
            return new ArrayList<List<Integer>>();
        }
        // Initialize a permutations array list of array lists
        // cur will be our currently built array list
        ArrayList<List<Integer>> permutations = new ArrayList<>();
        ArrayList<Integer> cur = new ArrayList<>();

        // Call the helper function to build the permutations array list
        permHelper(array, cur, permutations);

        // Return the created permutations array list
        return permutations;
    }

    public static void permHelper(List<Integer> array, List<Integer> cur, ArrayList<List<Integer>> permutations){
        // If the array list 'array' we pass in is empty, we can add the current array list
        // to the permutations array list if the current array list is not empty
        // This is the same as having built one permutation
        if(array.size() == 0 && cur.size() != 0){
            permutations.add(cur);
        }
        // Otherwise, iterate through the array indices
        /*
        For each index, create a temporary array to pass into the recursive call.
        Note that if we do not make a temporary array, we will end up removing all the
        values in 'array' originally and only add one permutation to the permutations array list
        The temporary array is a copy of the array list 'array' with the index i removed. This will
        serve as the sub array of values to choose the next value for the permutation from.
        Then we need a new current permutation array to pass into the recursive call. We can simply add
        the value of the index we removed from the temporary array from 'array'.
        We then call the helper function with these new objects.
        */
        else{
            for(int i = 0; i < array.size(); i++){
                List<Integer> tempArray = new ArrayList<>(array);
                tempArray.remove(i);
                List<Integer> newPermutation = new ArrayList<>(cur);
                newPermutation.add(array.get(i));
                permHelper(tempArray, newPermutation, permutations);
            }
        }
    }

    // Computationally better than the other solution
    public static List<List<Integer>> getPermutations2(List<Integer> array) {
        // Similar to above but we don't need a current built array list
        // Instead, we will swap in place
        List<List<Integer>> permutations = new ArrayList<>();
        permHelper(0, array, permutations);
        return permutations;
    }

    public static void permHelper(int i, List<Integer> array, List<List<Integer>> permutations){
        if(i == array.size() - 1){
            // This MUST be a copy
            // The call after the function call will reset array back to its original self
            // This is kind of like how the temporary array needs to be created above
            // i == the last element is what is happening here. When we reach here,
            // the recursive chain will end here
            permutations.add(new ArrayList<>(array));
        }
        else{
            // Iterate through the array starting from i
            /*
            For each element, swap i and j then call the helper function recursively with
            an incremented i, then swap the values of i and j back so we don't destroy the array.
            */
            for(int j = i; j < array.size(); j++){
                swap(i, j, array);
                permHelper(i + 1, array, permutations);
                swap(i, j, array);
            }
        }
    }

    // Basic swap helper function
    public static void swap(int i, int j, List<Integer> array){
        int temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }
}
