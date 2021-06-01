/* Noah Park

Our hero Teemo is attacking an enemy Ashe with poison attacks! When Teemo attacks Ashe, Ashe gets poisoned for a exactly duration seconds. More formally, an attack at second t will mean Ashe is poisoned during the inclusive time interval [t, t + duration - 1]. If Teemo attacks again before the poison effect ends, the timer for it is reset, and the poison effect will end duration seconds after the new attack.

You are given a non-decreasing integer array timeSeries, where timeSeries[i] denotes that Teemo attacks Ashe at second timeSeries[i], and an integer duration.

Return the total number of seconds that Ashe is poisoned.

*/

class Solution {
    
    // Intuition: Given a gap of 2 times (i and i + 1) we check if the duration overlaps, if it does we take the difference between these two gaps, otherwise the duration.
    // Time: O(n) to iterate over the times.
    // Space: O(1) constant.
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        if (timeSeries == null || timeSeries.length == 0) return 0;
        
        int res = 0, n = timeSeries.length;
        
        for (int i = 0; i < n - 1; i++)
            res += (timeSeries[i] + duration - 1 >= timeSeries[i + 1]) ? timeSeries[i + 1] - timeSeries[i] : duration;
        
        return res + duration;
    }
}
