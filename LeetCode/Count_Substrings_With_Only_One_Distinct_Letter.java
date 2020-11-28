/* Noah Park

Given a string S, return the number of substrings that have only one distinct letter.

*/

class Solution {
    public int countLetters(String S) {
        // window start and end pointers
        // count value and current substring starting character
        int start = 0, end = 0, count = 0;
        char cur = S.charAt(start);
        
        // iterate over all characters
        // we miss all single character substrings except for the first when start and end are both 0
        while(end < S.length()){
            // get the matching character
            char c = S.charAt(end);
            
            // whenever we have a match, add the matching value substring length to the count
            if(c == cur) count += (end - start + 1);
            else{ // otherwise, update start pointer and start character. Again, we need to increment count, as we miss all single character strings due to end being 1 in front of start EXCEPT the first character
                cur = c;
                start = end;
                count++;
            }
            
            end++;
        }
        return count;
    }
}
