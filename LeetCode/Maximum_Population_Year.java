/* Noah Park

You are given a 2D integer array logs where each logs[i] = [birthi, deathi] indicates the birth and death years of the ith person.

The population of some year x is the number of people alive during that year. The ith person is counted in year x's population if x is in the inclusive range [birthi, deathi - 1]. Note that the person is not counted in the year that they die.

Return the earliest year with the maximum population.

*/

class Solution {
    
    // Intuition: Sort the logs by birth date then death if equal. Utilize a minHeap to keep track of people at the same time. 
    // Time: O(n log n) for sorting and traversing the logs while pulling from the min heap.
    // Space: O(n) for the min heap.
    public int maximumPopulation(int[][] logs) {
        int max = -1, year = -1;
        
        Arrays.sort(logs, (a, b) -> (a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]));
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
        
        int end = 0;
        while (end < logs.length) {
            int temp = end;
            while (minHeap.size() == 0 || (end < logs.length && logs[end][0] < minHeap.peek()[1])) minHeap.offer(logs[end++]);
            if (minHeap.size() > max) {
                max = minHeap.size();
                year = logs[end - 1][0];
            }
            while (end < logs.length && minHeap.size() > 0 && logs[end][0] >= minHeap.peek()[1]) minHeap.poll();
            if (temp == end) end++;
        }
        
        if (minHeap.size() > max) {
            max = minHeap.size();
            year = logs[end - 1][0];
        }
        
        return year;
    }
}
