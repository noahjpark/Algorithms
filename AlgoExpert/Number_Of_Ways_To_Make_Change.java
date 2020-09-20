// Noah Park
/*

Problem: Given an array of distinct positive integers representing coin denominations and a
single non-negative integer n representing a target amount of money, write a function that
returns the number of ways to make change for that target amount using the given coin denominations.

Note that an unlimited amount of coins is at your disposal.

*/

public class Number_Of_Ways_To_Make_Change {
    /*
	Use an array of size n to track our current denomination(s) value
	It will store the number of ways to make each amount up through n
	Update each new index along the way using the previous ones
	.
	.
	.
	*/
    public static int numberOfWaysToMakeChange(int n, int[] denoms) {
        // Initialize an ans array with indices through n
        // Start the first index at 1 with the rest at 0
        int[] ans = new int[n + 1];
        ans[0] = 1;

        // Iterate through the denoms
        for(int i = 0; i < denoms.length; i++){
            // For each denominator, we iterate from 1 through n to check if the current
            // denominator can make our iterator value. With this, we can update each index
            // in the ans array.
            for(int j = 1; j <= n; j++){
                if(j >= denoms[i]){ // If the denominator can make j
                    ans[j] += ans[j - denoms[i]]; // Update ans to be the formula here
                }
            }
        }

        return ans[n]; // Return ans[n] since n is our value we want to know the ways of
    }
}
