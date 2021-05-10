/* Noah Park

Given a list of sorted characters letters containing only lowercase letters, and given a target letter target, find the smallest element in the list that is larger than the given target.

Letters also wrap around. For example, if the target is target = 'z' and letters = ['a', 'b'], the answer is 'a'.

*/

class Solution {
    
    // Intuition: Basic binary search to push left to the closest value that is larger than target in the array. If there is no value larger then left will reach the last value in the array. In the end we return the letter at left if it is larger otherwise the first.
    // Time: O(log n) for binary search.
    // Space: O(1) constant.
    public char nextGreatestLetter(char[] letters, char target) {
        int left = 0, right = letters.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (letters[mid] <= target) left = mid + 1;
            else right = mid;
        }
        
        return letters[left] > target ? letters[left] : letters[0];
    }
}
