/* Noah Park

There are several cards arranged in a row, and each card has an associated number of points The points are given in the integer array cardPoints.

In one step, you can take one card from the beginning or from the end of the row. You have to take exactly k cards.

Your score is the sum of the points of the cards you have taken.

Given the integer array cardPoints and the integer k, return the maximum score you can obtain.

*/

class Solution {
    
    // Intuition: Maintain the best score with the front and back spaces. Essentially a wrapped sliding window around the outside. Move this towards the front and wrap around the back and maximize the score.
    // Time: O(k) two passes over k.
    // Space: O(1) constant.
    public int maxScore(int[] cards, int k) {
        if (cards == null || cards.length == 0) return 0;
        
        int front = 0, back = 0;
        
        for (int i = 0; i < k; i++) 
            front += cards[i];
        
        int max = front;
        
        for (int i = k - 1; i >= 0; i--) {
            front -= cards[i];
            back += cards[cards.length - k + i];
            max = Math.max(max, front + back);
        }
        
        return max;
    }
    
    // Intuition: Sliding window prefix sum to minimize the unpicked cards (cards.length - k). 
    // Time: O(n) single pass through cards.
    // Space: O(1) constant.
    public int maxScore2(int[] cards, int k) {
        if (cards == null || cards.length == 0) return 0;
        
        int unchosen = cards.length - k, prefix = 0, start = 0, end = 0, window = 0, min = Integer.MAX_VALUE;
        
        for (int i = 0; i < cards.length; i++) {
            prefix += cards[i];
            if (end - start == unchosen) {
                min = Math.min(min, window);
                window -= cards[start++];
            } 
            window += cards[end++];
        }
        
        min = Math.min(min, window);
        
        return prefix - min;
    }
 
}
