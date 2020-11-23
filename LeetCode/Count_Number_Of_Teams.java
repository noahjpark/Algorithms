/* Noah Park

There are n soldiers standing in a line. Each soldier is assigned a unique rating value.

You have to form a team of 3 soldiers amongst them under the following rules:

Choose 3 soldiers with index (i, j, k) with rating (rating[i], rating[j], rating[k]).
A team is valid if:  (rating[i] < rating[j] < rating[k]) or (rating[i] > rating[j] > rating[k]) where (0 <= i < j < k < n).
Return the number of teams you can form given the conditions. (soldiers can be part of multiple teams).

*/

class Solution {
    public int numTeams(int[] rating) {
        int teams = 0, n = rating.length; // team count and rating length
        
        // iterate through all values that have values on the left and right
        for(int i = 1; i < n - 1; i++){
            // counts for the smaller on the left, larger on the left, smaller on the right, larger on the right.
            // the total triplets is smaller on the left * larger on the right + smaller on the right * larger on the left.
            int lefts = 0, leftl = 0, rights = 0, rightl = 0;
            
            // Iterate through all values and compare to the ith soldier. Updates the appropriate side and small/large value
            for(int j = 0; j < n; j++){
                if(rating[j] < rating[i]) {
                    if(j > i) lefts++;
                    else leftl++;
                }
                else if(rating[j] > rating[i]) {
                    if(j > i) rights++;
                    else rightl++;
                }
            }
            
            // updates team count
            teams += lefts * rightl + leftl * rights;
        }
        
        return teams;
    }
}
