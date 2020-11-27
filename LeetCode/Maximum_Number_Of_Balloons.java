/* Noah Park

Given a string text, you want to use the characters of text to form as many instances of the word "balloon" as possible.

You can use each character in text at most once. Return the maximum number of instances that can be formed.

*/

class Solution {
    public int maxNumberOfBalloons(String text) {
        int[] freq = new int[26]; // frequency array
        for(char c : text.toCharArray()) // populate frequencies
            freq[c - 'a']++;
        
        int single = 0, dbl = 0; // count minimum occurrences of single and double letters (b,a,n) and (l,o) respectively
        
        // we only want the minimum, because we can only make that many balloons then. Say we have 1 'b', then we obviously can only make 1 'balloon' even if we have many 'a's and 'n's
        single = Math.min(freq['b' - 'a'], Math.min(freq['a' - 'a'], freq['n' - 'a']));
        dbl = Math.min(freq['l' - 'a'], freq['o' - 'a']);
        
        // the total 'balloon's we can make is the minimum of the single letters and 1/2 the double letters, as that is the requirement for that number of 'balloon' creations.
        return Math.min(single, dbl / 2);
    }
}
