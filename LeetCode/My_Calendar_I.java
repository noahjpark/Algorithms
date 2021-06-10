/* Noah Park

Implement a MyCalendar class to store your events. A new event can be added if adding the event will not cause a double booking.

Your class will have the method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end), the range of real numbers x such that start <= x < end.

A double booking happens when two events have some non-empty intersection (ie., there is some time that is common to both events.)

For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully without causing a double booking. Otherwise, return false and do not add the event to the calendar.

Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)

*/

// Intuition: Maintain the intervals in a treemap for easy access to any particular interval.
// Time: O(nlogn) for accessing the map.
// Space: O(n) for the map.
class MyCalendar {
    
    TreeMap<Integer, Integer> map;
    
    public MyCalendar() {
        map = new TreeMap<>();
    }
    
    public boolean book(int start, int end) {
        Integer before = map.floorKey(start), after = map.ceilingKey(start);
        if ((before == null || map.get(before) <= start) && (after == null || after >= end)) {
            map.put(start, end);
            return true;
        }
        return false;
    }
    
}

// Intuition: Maintain all intervals and iterate over to compare.
// Time and Space: O(n).
class MyCalendar2 {
    
    List<int[]> intervals;

    public MyCalendar2() {
        intervals = new ArrayList<>();
    }
    
    public boolean book(int start, int end) {
        for (int[] interval : intervals) {
            if (interval[0] < end && interval[1] > start) return false;
        }
        intervals.add(new int[]{ start, end });
        return true;
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */
