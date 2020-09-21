// Noah Park
/*

Problem: Given an array of positive integers representing coin denominations and a single non-negative
integer n representing a target amount of money, write a function that returns the smallest number
of coins needed to make change for (to sum up to) that target amount using the given coin denominations.

Note that you have access to an unlimited amount of coins. In other words, if the denominations are
[1, 5, 10], you have access to an unlimited amount of 1s, 5s, and 10s.

If it's impossible to make change for the target amount, return -1.

*/

public class Min_Number_Of_Coins_For_Change {
    /*
	Similarly to the last dynamic programming problem. My thoughts are to create an array
	of size n + 1 so that we can store the number of coins required to create the value n.

	By initializing all values in the new array, except for the 0 index, to the maximum integer
	value, we can continuously find smaller "coin" amounts. Max value represents that we have not
	found a coin amount for that value yet.
	*/
    public static int minNumberOfCoinsForChange(int n, int[] denoms) {
        if(n == 0){ // If the n value is 0, return 0
            return 0;
        }

        // make a coin array and initialize the first position to 0 with the rest the maximum integer value
        int[] numCoins = new int[n + 1];
        numCoins[0] = 0;
        for(int i = 1; i < numCoins.length; i++){
            numCoins[i] = Integer.MAX_VALUE;
        }

        // Similarly to the other coin problem, iterate through the denominators then
        // the values 1 through n for each denominator
        for(int i = 0; i < denoms.length; i++){
            for(int j = 1; j <= n; j++){
                // If the denominator is small enough to make change we check a few things:
                /*
                1. Find the difference leftover (this is where Dynamic Programming comes in)
                2. Get the number of coins from the leftovers and the number of coins that can make that (DP). If the value there is the maximum integer,
                then we have not found a combination of coins to make it yet.
                3. If the number of leftover coins is not the maximum integer value, we have found a combination of coins and can make the value equal to our j value by
                adding 1 coin (our current denomination value to get us to j).
                4. Finally, we compare the value sitting at that position to the value of the coins to make that value and update the position to contain the smaller number of coins.
                */
                if(denoms[i] <= j){
                    int cur = j - denoms[i];
                    int totalCoins = numCoins[cur];
                    if(totalCoins != Integer.MAX_VALUE){
                        totalCoins++;
                    }
                    numCoins[j] = Math.min(numCoins[j], totalCoins);
                }
            }
        }

        // If the number of coins to make the value n is still the maximum integer value, we return -1 since we could not make that value.
        // Otherwise, we return the number of coins at that index which make the number n.
        return numCoins[n] == Integer.MAX_VALUE ? -1 : numCoins[n];
    }
}
