/* Noah Park

You are given an integer n, the number of teams in a tournament that has strange rules:

If the current number of teams is even, each team gets paired with another team. A total of n / 2 matches are played, and n / 2 teams advance to the next round.
If the current number of teams is odd, one team randomly advances in the tournament, and the rest gets paired. A total of (n - 1) / 2 matches are played, and (n - 1) / 2 + 1 teams advance to the next round.
Return the number of matches played in the tournament until a winner is decided.

*/

class Solution {
    
    // Constant solution returns n - 1 since there must be n - 1 matches played since the winner is a single team.
    
    // Intuition: Apply the rules of the strange tournament. Decided to use bit shifting for speed.
    // Time: O(log n) since we are cutting half the teams out at each point. 
    // Space: O(1)
    public int numberOfMatches(int n) {
        int matches = 0;
        
        while (n > 1) {
            if (n % 2 == 0) { matches += n >> 1; n >>= 1; }
            else { matches += (n - 1) >> 1; n = ((n - 1) >> 1) + 1; }
        }
        
        return matches;
    }
}
