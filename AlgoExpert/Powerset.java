// Noah Park
/*

Problem: Write a function that takes in an array of unique integers and returns its powerset.

The powerset P(X) of a set X is the set of all subsets of X. For example, the powerset of [1,2]
is [[], [1], [2], [1,2]].

Note that the sets in the powerset do not need to be in any particular order.

*/

import java.util.ArrayList;
import java.util.List;

public class Powerset {
    // Recursive solution
    // Time: O(2^n) | Space: O(2^n)
    public static List<List<Integer>> powerset(List<Integer> array) {
        ArrayList<List<Integer>> p = new ArrayList<>();
        helper(array, new ArrayList<>(), p, 0);
        return p;
    }

    public static void helper(List<Integer> array, List<Integer> cur, List<List<Integer>> p, int index){
        // Add the currently built powerset to the powerset list
        p.add(cur);
        // If the index reaches out of bounds, we have built our maximum subset and can stop
        if(index == array.size()){
            return;
        }
        else{
            // Otherwise, iterate through the array from index to the end
            for(int i = index; i < array.size(); i++){
                // Each iteration, create a new subset initialized to cur
                // Add the value at index i
                // Then call the powerset helper function again with the subset and index + 1
                List<Integer> newp = new ArrayList<>(cur); // On the first iteration, n subsets will be made from the initial empty one with each subset containing a single of the n values
                newp.add(array.get(i));
                helper(array, newp, p, i + 1);
            }
        }
    }

    // Time: O(n*2^n) | Space: O(n*2^n)
    // Iterative solution
    // Build each subset using the last value
    public static List<List<Integer>> powersetIterative(List<Integer> array) {
        ArrayList<List<Integer>> p = new ArrayList<>();
        p.add(new ArrayList<>()); // add the empty set
        // Iterate through the array
        for(int i = 0; i < array.size(); i++){
            int currentSubsetLength = p.size(); // Get the length of the powerset array and iterate from 0 up to that point
            for(int j = 0; j < currentSubsetLength; j++){
                // Initialize a new array list subset with the subset at each j index
                // Add the index from array (i) to each subset
                // Then add the subset to the powerset
                ArrayList<Integer> subset = new ArrayList<>(p.get(j));
                subset.add(array.get(i));
                p.add(subset);
            }
        }

        return p;
    }
}
