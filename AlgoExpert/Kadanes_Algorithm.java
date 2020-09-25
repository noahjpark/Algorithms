// Noah Park
/*

Problem: Write a function that takes in a non-empty array of integers and returns the maximum
sum that can be obtained by summing up all of the integers in the non-empty sub-array of the
input array. A sub-array must only contain adjacent numbers.

*/

public class Kadanes_Algorithm {
    // Optimal Solution | Time: O(n) | Space: O(1)
    public static int kadanesAlgorithm(int[] array) {
        // Start the answer at the smallest possible integer value so that any value, negative or positive,
        // will be able to increase the sum.
        int ans = Integer.MIN_VALUE;

        // Initialize the current sum to 0 then iterate through the array for a single pass
        int curSum = 0;
        for(int i = 0; i < array.length; i++){
            // Take the larger of the following:
            /*
            - curSum + array[i] and array[i]
            If curSum + array[i] is smaller than array[i], we are coming from a negative value
            into a positive value (negative sum into a positive sum) so we are losing a good starting point.
            To combat this, we simply take the larger of the two and update our current sum based on this.
            Finally, we simply update our ans value based on the curSum.
            */
            if(curSum + array[i] < array[i]) curSum = array[i];
            else{
                curSum += array[i];
            }
            if(curSum > ans){
                ans = curSum;
            }
        }

        return ans;
    }

    // Naive solution | Time: O(N^2) | Space: O(1)
    /*
    Pretty much the same implementation as above with a significantly worse time complexity.
    Rather than keeping track of the running sum we have, we are doing extra work by calculating
    all possible sums for each possible sub-array which we definitely do not need to do.
    */
    public static int kadanesAlgorithmNaive(int[] array) {
        int ans = Integer.MIN_VALUE;

        for(int i = 0; i < array.length; i++){
            int curSum = 0;
            for(int j = i; j < array.length; j++){
                curSum += array[j];
                if(curSum > ans){
                    ans = curSum;
                }
            }
        }

        return ans;
    }
}
