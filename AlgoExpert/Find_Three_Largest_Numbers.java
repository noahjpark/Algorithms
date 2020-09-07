// Noah Park
/*

Problem: Write a function that takes in an array of at least three integers and, without sorting the
input array, returns a sorted array of the three largest integers in the input array.

The function should return duplicate integers if necessary; for example, it should return
[10, 10, 12] for an input array of [10, 5, 9, 10, 12].

*/

import java.util.Arrays;

public class Find_Three_Largest_Numbers {
    public static int[] findThreeLargestNumbers(int[] array) {
        int[] ans = new int[3]; // ans array
        // populate ans with all min values of integer
        for(int i = 0; i < ans.length; i++){
            ans[i] = Integer.MIN_VALUE;
        }
        // Solved in O(n) - one pass
        for(int i = 0; i < array.length; i++){
            if(array[i] > ans[0]){ // If we find a new larger value than our smallest value, we can get rid of our smallest value and update it to this new value
                ans[0] = array[i];
            }
            // Maintain the ans array in sorted order so we can always find a new value to replace our smallest value
            Arrays.sort(ans); // O(1) since ans is always of length 3 otherwise can use sort() hardcoded for arrays of size 3
        }

        return ans;
    }

    // If we couldn't use Arrays.sort...
    public void sort(int[] arr){ // assumes array of size 3
        int first, second, third;
        first = arr[0];
        second = arr[1];
        third = arr[2];
        if(first > second){
            int temp = first;
            first = second;
            second = temp;
        }
        if(first > third){
            int temp = second;
            second = third;
            third = temp;
        }
        if(second > third){
            int temp = third;
            third = second;
            second = temp;
        }
    }
}
