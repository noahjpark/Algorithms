/* Noah Park

Suppose you have a long flowerbed in which some of the plots are planted and some are not. However, flowers cannot be planted in adjacent plots - they would compete for water and both would die.

Given a flowerbed (represented as an array containing 0 and 1, where 0 means empty and 1 means not empty), and a number n, return if n new flowers can be planted in it without violating the no-adjacent-flowers rule.

*/

class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if(n == 0) return true;
        int len = flowerbed.length, count = 0; // Length of the array and count of available spaces
        for(int i = 0; i < len; i++) { // greedy overriding the array
            int ones = 0; // count the number of adjacent ones
            if(flowerbed[i] == 0){ // if we have a zero, we count
                if(i - 1 > -1) ones += flowerbed[i - 1]; // one on the left
                if(i + 1 < len) ones += flowerbed[i + 1]; // one on the right
                if(ones == 0) { flowerbed[i] = 1; count++; } // if there aren't any ones around it, we have our closest available space, update it to a one and update count
            }
            if(count >= n) return true; // optimization to stop when we have enough spaces
        }
        
        return false;
    }
}
