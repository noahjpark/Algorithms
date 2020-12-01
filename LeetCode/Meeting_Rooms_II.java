/* Noah Park

Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

*/

class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals.length < 2) return intervals.length; // edge case
        
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0])); // sort the array
        int rooms = 0; // num rooms
        PriorityQueue<int[]> pq = new PriorityQueue<>(intervals.length, (a, b) -> Integer.compare(a[1], b[1])); // minHeap will allow us to store all rooms in use at a given time sorted by end time (front will be the earliest end time)
        
        for(int[] interval : intervals) { // iterate through the intervals
            while (!pq.isEmpty() && interval[0] >= pq.peek()[1]) pq.poll(); // remove an interval while the current interval start is larger than or equal to the earliest end time
            
            pq.offer(interval); // add a new interval to the min heap
            
            rooms = Math.max(rooms, pq.size()); // update the required rooms based on how many intervals are in the heap at a given time
        }
        
        return rooms;
    }
    
    public int minMeetingRoomsOptimal(int[][] intervals) {
        // starts and ends are the starts and ends of each individual interval
        int n = intervals.length;
        int[] starts = new int[n], ends = new int[n];
        
        // populate starts and ends
        for (int i = 0; i < n; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        
        // sort them for the two pointer technique
        Arrays.sort(starts); Arrays.sort(ends);
        
        // whenever a start time is less than an end time, we have an "overlapping" interval
        int ptr = 0, rooms = 0;
        for (int i = 0; i < n; i++) {
            if (starts[i] < ends[ptr]) rooms++; // overlap
            else ptr++; // need to move to the next end index, as the start has become too large
        }
        
        return rooms;
    }
}
