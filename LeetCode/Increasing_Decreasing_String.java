/* Noah Park

Given a string s. You should re-order the string using the following algorithm:

Pick the smallest character from s and append it to the result.
Pick the smallest character from s which is greater than the last appended character to the result and append it.
Repeat step 2 until you cannot pick more characters.
Pick the largest character from s and append it to the result.
Pick the largest character from s which is smaller than the last appended character to the result and append it.
Repeat step 5 until you cannot pick more characters.
Repeat the steps from 1 to 6 until you pick all characters from s.
In each step, If the smallest or the largest character appears more than once you can choose any occurrence and append it to the result.

Return the result string after sorting s with this algorithm.

*/

class Solution {
    public String sortString(String s) {
        int[] freq = new int[26]; // store frequencies of each letter in s
        StringBuilder res = new StringBuilder(); // return string
        int n = s.length(); // length of s
        int prev = -1; // prev index in freq that tells what the most recent letter was
        boolean increasing = true; // directional boolean
        
        for(char c : s.toCharArray()) freq[c - 'a']++; // update frequencies of each letter from s
        
        while (res.length() < n) { // build the string until the lengths are the same
            boolean found = false; // if we don't find a valid letter, go from the other direction
            if (increasing) { // smallest to largest
                for (int j = prev + 1; j < 26; j++) { // iterate over freq starting from one in front of the previous choice character
                    if (freq[j] > 0) { // found a larger character
                        found = true; prev = j; freq[j]--; // update prev pointer, frequency of the letter and mark as found
                        res.append((char) (j + 'a')); // append to the string
                    }
                }
            } else { // largest to smallest
                for (int j = prev - 1; j >= 0; j--) { // iterate over freq starting from one behind the previous choice character
                    if (freq[j] > 0) { // found a smaller character
                        found = true; prev = j; freq[j]--; // update prev pointer, frequency of the letter and mark as found
                        res.append((char) (j + 'a')); // append to the string
                    }
                }
            }
            if(!found) { increasing = !increasing; prev = increasing ? -1 : 26; } // update direction if we can't find a valid letter
        }
        
        return res.toString();
    }
}
