/* Noah Park

You are playing a Flip Game with your friend.

You are given a string currentState that contains only '+' and '-'. You and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move, and therefore the other person will be the winner.

Return all possible states of the string currentState after one valid move. You may return the answer in any order. If there is no valid move, return an empty list [].

*/

class Solution {
    
    // Intuition: Maintain a stringbuilder where we attempt to add everything before the current two characters and everything after the two characters with '--' in the middle assuming it is currently '++'.
    // Time: O(n) to iterate over the string.
    // Space: O(n) for the result.
    public List<String> generatePossibleNextMoves(String currentState) {
        StringBuilder s = new StringBuilder();
        List<String> res = new ArrayList<>();
        int n = currentState.length();
        
        for (int i = 0; i + 1 < n; i++) {
            if (currentState.charAt(i) == '+' && currentState.charAt(i + 1) == '+') {
                if (i > 0) s.append(currentState.substring(0, i));
                s.append("--");
                if (i < n - 2) s.append(currentState.substring(i + 2));
            }
            
            if (s.length() == n) res.add(s.toString());
            s = new StringBuilder();
        }
        
        return res;
    }
}
