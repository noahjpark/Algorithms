/* Noah Park

Given a string S of lowercase letters, a duplicate removal consists of choosing two adjacent and equal letters, and removing them.

We repeatedly make duplicate removals on S until we no longer can.

Return the final string after all such duplicate removals have been made.  It is guaranteed the answer is unique.

*/

class Solution {
    public String removeDuplicates(String S) {
        // Use StringBuilder to store the string
        StringBuilder res = new StringBuilder();
        for(char c : S.toCharArray()){
            if(res.length() > 0 && res.charAt(res.length() - 1) == c) res.deleteCharAt(res.length() - 1); // If there are characters and the most recent character matches our current, remove that character (pop off the stack)
            else res.append(c); // Else, append to the end
        }
        
        return res.toString(); // res will contain all unique occurrences of characters
    }
}
