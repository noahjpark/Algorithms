/* Noah Park

Given an array of meeting time intervals where intervals[i] = [starti, endi], determine if a person could attend all meetings.

*/

class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        if(intervals.length == 0) return true; // edge case for no meetings
        // sort meetings by start time, if there are any end times that are larger than a start time in front of it, we know it is impossible to attend that meeting
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        
        int end = intervals[0][1]; // initial end time
        
        for (int i = 1; i < intervals.length; i++) { // iterate over all other intervals
            if(end > intervals[i][0]) return false; // if we have an overlap, we can't attend that meeting
            end = intervals[i][1]; // update end to the next end time
        }
        
        return true;
    }
}
