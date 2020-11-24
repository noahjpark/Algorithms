/* Noah Park

Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

*/

class Solution {
    public int[][] merge(int[][] intervals) {
        if(intervals.length < 2) return intervals; // If we only have 1 or less intervals, there is nothing to merge
        
        // Make sure the intervals are sorted in ascending order in terms of start
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> res = new ArrayList<>();
        
        // Keep track of the start and end intervals
        int start = intervals[0][0], end = intervals[0][1];
        
        // Go through all intervals (excluding the first since we stored those in start and end) and compare to the start and end current intervals
        for(int i = 1; i < intervals.length; i++){
            if(end >= intervals[i][0]) end = Math.max(end, intervals[i][1]); // If the end is overlapping into the next interval, take the larger of end and the end of the next interval
            else { // else we no longer have an overlap and can add to the result list
                res.add(new int[]{start, end});
                start = intervals[i][0]; // update start and end to the current interval start and end, as we have not added it yet
                end = intervals[i][1];
            }
        }
        
        // We miss the last interval so we need to add it
        res.add(new int[]{start, end});
            
        return res.toArray(new int[res.size()][]); // return the resulting list as an array
    }
}
