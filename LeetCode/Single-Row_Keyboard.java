/* Noah Park

There is a special keyboard with all keys in a single row.

Given a string keyboard of length 26 indicating the layout of the keyboard (indexed from 0 to 25). Initially, your finger is at index 0. To type a character, you have to move your finger to the index of the desired character. The time taken to move your finger from index i to index j is |i - j|.

You want to type a string word. Write a function to calculate how much time it takes to type it with one finger.

*/

class Solution {
    
    // Intuition: Counting sort to maintain indices and avoid having to recall the indexOf method in a loop.
    // Time: O(n) to iterate over the word.
    // Space: O(1) constant.
    public int calculateTime(String keyboard, String word) {
        int[] freq = new int[26];
        
        for (int i = 0; i < 26; i++)
            freq[keyboard.charAt(i) - 'a'] = i;
        
        int time = 0, prev = 0;
        
        for (char c : word.toCharArray()) {
            time += Math.abs(freq[c - 'a'] - prev);
            prev = freq[c - 'a'];
        }
        
        return time;
    }
    
    public int calculateTime2(String keyboard, String word) {
        int time = 0, prev = 0;
        
        for (char c : word.toCharArray()) {
            int cur = keyboard.indexOf(c);
            
            time += Math.abs(cur - prev);
            prev = cur;
        }
            
        return time;
    }
}
