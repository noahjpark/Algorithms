/* Noah Park

Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

*/

class Solution {
    
    // Intuition: Greedy approach -> If there is no overlap, nothing is removed and the previous interval moves forward. Otherwise, if we have an overlap the first interval either overtakes the entirety of the second or the second ends after the first. In the former, we clearly want to remove the first to maximize space for other intervals. In the latter situation, we want to keep the first as to minimize the ending times.
    // Time: O(nlogn) to sort intervals.
    // Space: O(1) constant.
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        
        int min = 0, n = intervals.length;
        Arrays.sort(intervals, (a, b) -> (a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]));
        
        int[] last = intervals[0];
        for (int i = 1; i < n; i++) {
            if (last[1] > intervals[i][0]) {
                if (last[1] > intervals[i][1]) last = intervals[i];
                min++;
            } else last = intervals[i];
        }
        
        return min;
    }
}
