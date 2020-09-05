// Noah Park
/*

Problem: Write a function that takes in a sorted array of integers as well as a target integer.
The function should use the Binary Search algorithm to determine if the target integer is contained
in the array and should return its index if it is, otherwise -1.

*/

public class Binary_Search {
    // Time: O(log(n)) | Space: O(1)
    // Iterative version
    public static int binarySearch(int[] array, int target) {
        // If the array is empty to begin with, target is not within it
        if(array.length == 0){
            return -1;
        }

        // Define a start index at the start of the array, end index at the end of the array, and an ans to represent the index of target (if it is present in the array)
        int start = 0;
        int end = array.length - 1;
        int ans = -1;

        // Since we know the array is sorted, any value we compare to will allow us to cut the array in half
        // if the value does not match the target. We either know to look into the bottom or top portion of our array partition
        // Loop until start is larger than end, at which point there cannot be a value that matches target
        while(start <= end){
            int curIndex = (start + end) / 2; // Get our middle index
            if(array[curIndex] == target){ // If the middle index matches target, update ans and break
                ans = curIndex;
                break;
            }
            else if(array[curIndex] > target){ // If our value is too large, cut the current portion of the array in half by moving the end point to one less than the middle
                end = curIndex - 1;
            }
            else{ // Similarly: if our value is too small, cut the current portion of the array in half by moving our start to one larger than the middle since we have already compared the middle
                start = curIndex + 1;
            }
        }

        return ans;
    }

    // Recursive version of above
    public static int binarySearchRec(int[] array, int target) {
        return bsrec(array, target, 0, array.length - 1); // Return the value obtained from the recursive helper function
    }

    // Recursive helper function
    public static int bsrec(int[] array, int target, int start, int end){
        // If the start index is larger than the end index, target is not present in the array
        if(start > end){
            return -1;
        }

        /*
        Get the middle index. Compare it to target and cut it in half either going to the top or bottom
        portion based on the comparison. The explanation is already above in the iterative version. This does
        the exact same thing but using recursion.
        */
        int middle = (end + start) / 2;
        if(array[middle] == target){
            return middle;
        }
        else if(array[middle] > target){
            return bsrec(array, target, start, middle - 1);
        }
        else{
            return bsrec(array, target, middle + 1, end);
        }
    }
}
