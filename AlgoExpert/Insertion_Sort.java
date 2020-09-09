// Noah Park
/*

Problem: Write a function that takes in an array of integers and returns a sorted version of that
array. Use the Insertion Sort algorithm to sort the array.

Insertion Sort:

*/

public class Insertion_Sort {
    // Time: O(n^2) | Space: O(1)
    public static int[] insertionSort(int[] array) {
        // At most we loop through the entire array
        for(int i = 0; i < array.length - 1; i++){
            // Get i to the correct position in the array
            // This will be the index preceding a value smaller than the current one, meaning we need to swap them
            while(i < array.length - 1 && array[i] < array[i + 1]){
                i++;
            }
            // If i is at the end we can break without checking for swaps
            if(i == array.length - 1){
                break;
            }

            // Start a temp index at i and work our way back to the start or wherever it is supposed
            // to be inserted. We break once we hit this point or the start swapping it back one all along
            int curIndex = i;
            while(curIndex >= 0){
                if(array[curIndex] > array[curIndex + 1]){
                    int temp = array[curIndex];
                    array[curIndex] = array[curIndex + 1];
                    array[curIndex + 1] = temp;
                }
                else{
                    break;
                }
                curIndex--;
            }
        }

        // Return the sorted array
        return array;
    }

    // A cleaner version
    // Identical to above but with less code
    public static int[] insertionSortClean(int[] array){
        for(int i = 1; i < array.length; i++){ // Iterate through the entire array, O(n) if already sorted
            int j = i; // Start j at i. Since i starts at 1, we will be working backwards inserting the index from j into its correct position
            while(j > 0 && array[j] < array[j - 1]){ // Stop when either j is at the beginning or at its proper position swapping along the way
                int temp = array[j];
                array[j] = array[j - 1];
                array[j - 1] = temp;
                j--;
            }
        }

        // Return the sorted array
        return array;
    }
}
