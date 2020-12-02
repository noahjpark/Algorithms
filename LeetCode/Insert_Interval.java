/* Noah Park

Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

*/

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>(); // return list
        boolean added = false; // boolean tell if we need to still add the interval after iterating over intervals
        
        int i = 0;
        
        while (i < intervals.length) { // iterate over intervals
            int start = intervals[i][0], end = intervals[i][1]; // store start and end of the current interval
            if (end < newInterval[0]) res.add(intervals[i]); // if the new interval is in front of the current one, we can add the current one
            else if (start > newInterval[1]) { // if the current interval is in front of the new interval, we can add the new interval, mark as added, then add the rest of the elements from the intervals list
                res.add(newInterval);
                added = true;
                while (i < intervals.length) res.add(intervals[i++]);
            }
            else { // there is an overlap and we need to merge the two intervals, I opted to simply update the newInterval array to reflect this merge
                int newStart = Math.min(start, newInterval[0]), newEnd = Math.max(end, newInterval[1]);
                newInterval = new int[] { newStart, newEnd };
            }
            
            i++;
        }
        
        if(!added) res.add(newInterval); // add the interval if the array was empty, or we merged through the end of the array
        
        int[][] ret = new int[res.size()][2]; // copy over the list into an array
        for (i = 0; i < res.size(); i++) ret[i] = res.get(i);
        
        return ret;
    }
}
