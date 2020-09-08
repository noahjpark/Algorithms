// Noah Park
/*

Problem: Write a function that takes in an array of integers and returns a sorted version of
that array. Use the Bubble Sort algorithm to sort the array.

Bubble Sort: Here we are trying to sort in ascending order. We will perform swaps to place numbers
in their correct places. Continuously swap adjacent elements while looping through the array.

*/

public class Bubble_Sort {
    // Time: O(n^2) | Space: O(1)
    public static int[] bubbleSort(int[] array) {
        // We would iterate at most through every element, so i can be bounded by the length of the array
        for(int i = 0; i < array.length; i++){
            boolean isSorted = true; // Helps us check if we can break the algorithm early - means the array is now sorted
            for(int j = 0; j < array.length - i - 1; j++){ // Iterate through all elements except the last (length - i) but also subtract 1 because we are checking j + 1
                if(array[j] > array[j + 1]){ // Need to swap into the correct order
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    isSorted = false; // We clearly don't have a sorted array and update the isSorted variable
                }
            }
            // Break out of the algorithm early if we are finished or given a sorted array
            if(isSorted){
                break;
            }
        }

        // Return the sorted array
        return array;
    }
}
