// Noah Park
/*

Problem: Given two non-empty arrays of integers, write a function that determines whether the second
array is a subsequence of the first one.

A subsequence of an array is a set of numbers that aren't necessarily adjacent in the array but that are
in the same order as they appear in the array. For instance, the numbers [1, 3, 4] form a subsequence of the
array [1, 2, 3, 4], and so do the numbers [2, 4]. Note that a single number in an array and the array itself are
both valid subsequences of the array.

*/

import java.util.List;

public class Validate_Subsequence {

    // First solution: O(n) time and O(1) space
    public static boolean isValidSubsequence1(List<Integer> array, List<Integer> sequence) {
        if(sequence.size() > array.size()){ // the main array can't contain sequence if sequence is larger
            return false;
        }
        int secondIndex = 0; // index for the sequence array
        for(int i = 0; i < array.size(); i++){ // iterate through the main array
            if(secondIndex == sequence.size()){ // If the second index reaches the size of sequence, all the elements are contained in the main array
                break;
            }
            if(sequence.get(secondIndex).equals(array.get(i))){
                secondIndex++;
            }
        }

        return secondIndex == sequence.size(); // Similar to above, if secondIndex reaches the sequence's size, the main array contains it
    }
}
