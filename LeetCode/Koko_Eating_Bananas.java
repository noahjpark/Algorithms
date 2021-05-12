/* Noah Park

Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.

Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.

Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.

Return the minimum integer k such that she can eat all the bananas within h hours.

*/

class Solution {
    
    // Intuition: Apply binary search on the possible eating speeds koko can have. The slowest would be 1 and the fastest would be the maximum in piles. From here, we just apply a normal binary search algorithm where we check if the midpoint is a valid eating speed to finish everything in h hours.
    // Time: O(nlogm) since we break the largest pile down but iterate through piles for each breakdown.
    // Space: O(1) constant.
    public int minEatingSpeed(int[] piles, int h) {
        int l = 1, r = max(piles);
        
        while (l < r) {
            int mid = l + (r - l) / 2;
            
            if (valid(piles, h, mid)) r = mid;
            else l = mid + 1;
        }
        
        return l;
    }
    
    public boolean valid(int[] piles, int h, int speed) {
        int count = 0;
        for (int pile : piles)
            count += pile % speed != 0 ? pile / speed + 1 : pile / speed;
        return count <= h;
    }
    
    public int max(int[] piles) {
        int max = 0;
        for (int pile : piles) max = Math.max(pile, max);
        return max;
    }
}
