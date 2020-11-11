// Noah Park
/*

Problem: Write a function that takes in an array of at least two integers and that returns an array
of the starting and ending indices of the smallest subarray in the input array that needs to be sorted
in place in order for the entire input array to be sorted (in ascending order).

If the input array is already sorted, the function should return [-1, -1].

*/

public class Subarray_Sort {
    // Optimal Solution
    // Time: O(n) | Space: O(1)
    public static int[] subarraySort(int[] array) {
        // Find the minimum value in the unsorted portion of the array OR Integer.MAX_VALUE
        // Find the maximum value in the unsorted portion of the array OR Integer.MIN_VALUE
        int min = findMin(array);
        int max = findMax(array);
        int startIndex = 0;
        int endIndex = 0;
        // Find where the positions they should be at are and store those in start and end
        for(int i = 0; i < array.length; i++){
            if(array[i] > min) {
                startIndex = i;
                break;
            }
        }
        for(int i = array.length - 1; i >= 0; i--){
            if(array[i] < max) {
                endIndex = i;
                break;
            }
        }
        // Ensure that the startIndex is less than the endIndex, otherwise the array is sorted and we can return [-1, -1]
        return startIndex < endIndex ? new int[] {startIndex, endIndex} : new int[] {-1, -1};
    }

    // Uses a boolean to dictate whether we are in the unsorted or sorted portion of the array
    // Finds the smallest value of the unsorted portion of the array OR Integer.MAX_VALUE
    public static int findMin(int[] array){
        boolean unsorted = false;
        int min = array[1] >= array[0] ? Integer.MAX_VALUE : array[0];
        for(int i = 1; i < array.length; i++){
            if(!unsorted && array[i] < array[i - 1]) unsorted = true;
            if(unsorted && array[i] < min) min = array[i];
        }
        return min;
    }

    // Uses a boolean to dictate whether we are in the unsorted or sorted portion of the array
    // Finds the largest value of the unsorted portion of the array OR Integer.MIN_VALUE
    public static int findMax(int[] array){
        boolean unsorted = false;
        int max = array[array.length - 2] <= array[array.length - 1] ? Integer.MIN_VALUE : array[array.length - 1];
        for(int i = array.length - 2; i >= 0; i--){
            if(!unsorted && array[i] > array[i + 1]) unsorted = true;
            if(unsorted && array[i] > max) max = array[i];
        }
        return max;
    }


    // Naive solution
    // Time: O(n^2) | Space: O(1)
    public static int[] subarraySortNaive(int[] array) {
        // Get the start and end index of the out of order numbers and put them in an integer array to return given that the start is less than the end. Otherwise, return [-1, -1]
        int startIndex = findStart(array);
        int endIndex = findEnd(array);
        return startIndex < endIndex ? new int[] {startIndex, endIndex} : new int[] {-1, -1};
    }

    // Makes n^2 comparisons to find the first value that is out of place and returns its index
    public static int findStart(int[] array){
        int index = 0;
        for(int i = 0; i < array.length - 1; i++){
            for(int j = i + 1; j < array.length; j++){
                if(array[i] > array[j]){
                    return i;
                }
            }
        }
        return index;
    }

    // Makes n^2 comparisons to find the first value that is out of place and returns its index
    public static int findEnd(int[] array){
        int index = 0;
        for(int i = array.length - 1; i > 0; i--){
            for(int j = i - 1; j >= 0; j--){
                if(array[i] < array[j]){
                    return i;
                }
            }
        }
        return index;
    }
}
