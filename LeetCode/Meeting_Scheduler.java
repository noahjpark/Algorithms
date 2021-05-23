/* Noah Park

Given the availability time slots arrays slots1 and slots2 of two people and a meeting duration duration, return the earliest time slot that works for both of them and is of duration duration.

If there is no common time slot that satisfies the requirements, return an empty array.

The format of a time slot is an array of two elements [start, end] representing an inclusive time range from start to end.

It is guaranteed that no two availability slots of the same person intersect with each other. That is, for any two time slots [start1, end1] and [start2, end2] of the same person, either start1 > end2 or start2 > end1.

*/

class Solution {
    
    // Intuition: Maintain a heap of all the possible start/end times. We initialize it with any valid time that could hold the duration (anything larger than or equal to the duration). Any overlap in the heap is considered as this would be two different people. We don't have to worry about considering two times of the same person since the end of the current person would be before the start of that same person and this wouldn't allow a return.
    // Time: O((n+m)*log(n+m)) where n is the length of s1 and m is the length of s2.
    // Space: O(n+m) for the heap.
    public List<Integer> minAvailableDuration(int[][] s1, int[][] s2, int d) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        
        for(int[] s : s1) 
            if (s[1] - s[0] >= d) minHeap.offer(s);
        for (int[] s : s2)
            if (s[1] - s[0] >= d) minHeap.offer(s);
        
        while (minHeap.size() > 1) {
            int[] cur = minHeap.poll();
            
            int start = minHeap.peek()[0], end = Math.min(cur[1], minHeap.peek()[1]);
            if (start + d <= end) return Arrays.asList(new Integer[]{ start, start + d });
        }
        
        return new ArrayList<>();
    }
    
    // Intuition: Once the slots are sorted in order by earliest start, we can take the overlap at each point and move each pointer inwards when necessary.
    // Time: O(nlogn + mlogm) to sort the two slots.
    // Space: O(sort) for sorting.
    public List<Integer> minAvailableDuration2(int[][] slots1, int[][] slots2, int duration) {
        int p1 = 0, p2 = 0;
        Arrays.sort(slots1, (a, b) -> (a[0] - b[0]));
        Arrays.sort(slots2, (a, b) -> (a[0] - b[0]));
        
        while (p1 < slots1.length && p2 < slots2.length) {
            int start = Math.max(slots1[p1][0], slots2[p2][0]), end = Math.min(slots1[p1][1], slots2[p2][1]);
            if (start + duration <= end) return Arrays.asList(new Integer[]{ start, start + duration });
            
            if (slots1[p1][1] < slots2[p2][1]) p1++;
            else p2++;
        }
        
        return new ArrayList<>();
    }
}
