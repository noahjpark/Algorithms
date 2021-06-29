/* Noah Park

You are given an integer array score of size n, where score[i] is the score of the ith athlete in a competition. All the scores are guaranteed to be unique.

The athletes are placed based on their scores, where the 1st place athlete has the highest score, the 2nd place athlete has the 2nd highest score, and so on. The placement of each athlete determines their rank:

The 1st place athlete's rank is "Gold Medal".
The 2nd place athlete's rank is "Silver Medal".
The 3rd place athlete's rank is "Bronze Medal".
For the 4th place to the nth place athlete, their rank is their placement number (i.e., the xth place athlete's rank is "x").
Return an array answer of size n where answer[i] is the rank of the ith athlete.

*/

class Solution {
    
    String[] medals = new String[]{ "Gold Medal", "Silver Medal", "Bronze Medal" };
    
    // Intuition: Sorting using an array instead of heap. Really no difference.
    // Time: O(nlogn) for sorting.
    // Space: O(n) for the intermediate array.
    public String[] findRelativeRanks(int[] score) {
        if (score == null || score.length == 0) return new String[]{};
        
        int n = score.length, place = 4;
        Integer[] sort = new Integer[n];
        for (int i = 0; i < n; i++)
            sort[i] = i;
        Arrays.sort(sort, (a, b) -> (score[b] - score[a]));
        
        String[] res = new String[n];
        for (int i = 0; i < 3 && i < n; i++)
            res[sort[i]] = medals[i];
        for (int i = 3; i < n; i++)
            res[sort[i]] = String.valueOf(place++);
        
        return res;
    }
    
    // Intuition: Sort the indices using a maxHeap.
    // Time: O(nlogn) for the heap.
    // Space: O(n) for the heap.
    public String[] findRelativeRanks2(int[] score) {
        if (score == null || score.length == 0) return new String[]{};
        
        int n = score.length, place = 4;
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> (b[0] - a[0]));
        for (int i = 0; i < n; i++)
            minHeap.offer(new int[]{ score[i], i });
        
        String[] res = new String[n];
        for (int i = 0; i < 3 && !minHeap.isEmpty(); i++)
            res[minHeap.poll()[1]] = medals[i];
        
        while (!minHeap.isEmpty())
            res[minHeap.poll()[1]] = String.valueOf(place++);
        
        return res;
    }
}
