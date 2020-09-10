// Noah Park
/*

Problem: Write a function that takes in an array of integers and returns a sorted version of
that array.Use the Selection Sort algorithm to sort the array.

Selection Sort: Double iteration. Find the minimum element of the current sub-array and move it to
the beginning of that sub-array.

*/

public class Selection_Sort {
    // Time: O(n^2) | Space: O(1)
    public static int[] selectionSort(int[] array) {
        // Iterate through the array. i will be the start of each sub-array we are looking at
        for(int i = 0; i < array.length - 1; i++){
            int minIdx = i; // Start the minimum value at index i. We will update this by looking through the rest of the sub-array
            for(int j = i + 1; j < array.length; j++){
                if(array[j] < array[minIdx]){ // If we find a new minimum value, update minIdx
                    minIdx = j;
                }
            }
            // Swap the index at the start of the sub-array (i) and the value at minIdx
            int temp = array[i];
            array[i] = array[minIdx];
            array[minIdx] = temp;
        }

        // Return the sorted array
        return array;
    }
}
