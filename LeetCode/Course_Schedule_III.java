/* Noah Park

There are n different online courses numbered from 1 to n. You are given an array courses where courses[i] = [durationi, lastDayi] indicate that the ith course should be taken continuously for durationi days and must be finished before or on lastDayi.

You will start on the 1st day and you cannot take two or more courses simultaneously.

Return the maximum number of courses that you can take.

*/

class Solution {
    
    // Intuition: Take a greedy approach. Initially, my thought was to order based on increasing ending values. This worked, however it missed the problem where an early ending value had a very long course duration. To mediate this issue, when we can no longer take courses, remove the longest course we took, as that one is now taking up the most space. To maintain this information, a maxHeap proves quite useful.
    // Time: O(n log n) for sorting the courses and utilizing the maxHeap.
    // Space: O(n) to maintain all of the courses in the maxHeap.
    public int scheduleCourse(int[][] courses) {
        int curDay = 0;
        Arrays.sort(courses, (a, b) -> (a[1] - b[1]));
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> (b[0] - a[0]));
        
        for (int[] course : courses) {
            curDay += course[0];
            maxHeap.offer(course);
            
            if (curDay > course[1]) curDay -= maxHeap.poll()[0];
        }
        
        
        return maxHeap.size();
    }
}
