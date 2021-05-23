/* Noah Park

You are given a floating-point number hour, representing the amount of time you have to reach the office. To commute to the office, you must take n trains in sequential order. You are also given an integer array dist of length n, where dist[i] describes the distance (in kilometers) of the ith train ride.

Each train can only depart at an integer hour, so you may need to wait in between each train ride.

For example, if the 1st train ride takes 1.5 hours, you must wait for an additional 0.5 hours before you can depart on the 2nd train ride at the 2 hour mark.
Return the minimum positive integer speed (in kilometers per hour) that all the trains must travel at for you to reach the office on time, or -1 if it is impossible to be on time.

Tests are generated such that the answer will not exceed 107 and hour will have at most two digits after the decimal point.

*/

class Solution {
    
    // Intuition: Same problem as koko eating bananas. We start with our left and right values as the smallest and largest allowed values then binary search until left ends up on the smallest valid speed. Note that doing anything quadratically ran into a TLE.
    // Time: O(nlogm) for binary searching over m values and iterating over n length distances.
    // Space: O(1) constant.
    public int minSpeedOnTime(int[] dist, double hour) {
        if (hour < dist.length - 1) return -1;
        
        int left = 1, right = 10000000, n = dist.length;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            double sum = 0;
            
            for (int i = 0; i < n - 1; i++) 
                sum += Math.ceil(dist[i] / (double) mid);
            
            sum += (dist[n - 1] / (double) mid);
            
            if (sum > hour) left = mid + 1;
            else right = mid;
            
        }
        
        return left;
    }
}
