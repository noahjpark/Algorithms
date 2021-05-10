/* Noah Park

You are given a 2D integer array logs where each logs[i] = [birthi, deathi] indicates the birth and death years of the ith person.

The population of some year x is the number of people alive during that year. The ith person is counted in year x's population if x is in the inclusive range [birthi, deathi - 1]. Note that the person is not counted in the year that they die.

Return the earliest year with the maximum population.

*/

class Solution {
    
    // Intuition: Apply the line sweep algorithm. Maintain a size 101 array for all birth/death years. Apply the counting sort technique to fill these with +1s for births and -1s for deaths. From here, we simply prefix and update the highest count for a year as we move through it in a single line sweep.
    // Time: O(n) one pass through logs then a 101 iteration over the years.
    // Space: O(1) since years is never changing in size.
    public int maximumPopulation(int[][] logs) {
        int[] years = new int[101];
        int year = 0;
        
        for (int[] log : logs) { years[log[0] - 1950]++; years[log[1] - 1950]--; }
        for (int i = 1; i < 101; i++) {
            years[i] += years[i - 1];
            if (years[i] > years[year]) year = i;
        }
        
        return 1950 + year;
    }
    
    // Intuition: Sort the logs by birth date then death if equal. Utilize a minHeap to keep track of people at the same time. 
    // Time: O(n log n) for sorting and traversing the logs while pulling from the min heap.
    // Space: O(n) for the min heap.
    public int maximumPopulation2(int[][] logs) {
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
