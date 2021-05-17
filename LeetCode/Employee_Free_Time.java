/* Noah Park

We are given a list schedule of employees, which represents the working time for each employee.

Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.

Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.

(Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined).  Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.

*/

/*
// Definition for an Interval.
class Interval {
    public int start;
    public int end;

    public Interval() {}

    public Interval(int _start, int _end) {
        start = _start;
        end = _end;
    }
};
*/

class Solution {
    
    // Intuition: We don't need to care about the fact that each list of times is associated with a specific employee. This actually threw me off at the start. Instead, we know that one employee can't work two different shifts at the same time. Thus, we can treat each time as its own chunk of "used" time and reduce the problem to simply finding the ranges of open time. This screams a minHeap implementation similar to that of merge intervals. The intuition behind my solution involves adding all of the times to the heap which is sorted by start time then end if they are equal, then polling the first of a new employee working and "merging" ends (i.e. updating the maximum ending overlap time). If there are employees whose intervals overlapped with the current, we only care about their end time since they are still working when the overlap happens. We maintain this max end time until there is a break (i.e. the next employee doesn't overlap) or there are no more employees. If there are no more employees, we can simply break from the loop as the intervals are done. Otherwise, we have found an interval of open time before the next employee works and can add this to the resulting list.
    // Time: O(n log n) to maintain all intervals in the min heap.
    // Space: O(n) to maintain all intervals from the schedule and the resulting list.
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        if (schedule == null || schedule.size() == 0) return new ArrayList<>();
        
        List<Interval> res = new ArrayList<>();
        Comparator<Interval> c = new Comparator<>() { // sorts by start times then end times if they are equal
            public int compare(Interval e1, Interval e2) {
                int s1 = e1.start, s2 = e2.start;
                return s1 == s2 ? e1.end - e2.end : s1 - s2;
            }
        };
        
        PriorityQueue<Interval> minHeap = new PriorityQueue<>(c);
        for (List<Interval> times : schedule)
            minHeap.addAll(times);
        
        while (!minHeap.isEmpty()) {
            Interval cur = minHeap.poll();
            int maxEnd = cur.end;
            
            while (!minHeap.isEmpty() && minHeap.peek().start <= maxEnd)
                maxEnd = Math.max(maxEnd, minHeap.poll().end);
            
            if (minHeap.isEmpty()) break; // edge case, no more free times afterwards since they aren't finite.
            
            if (minHeap.peek().start > maxEnd) res.add(new Interval(maxEnd, minHeap.peek().start));
        }
        
        return res;
    }
}
