/* Noah Park

You are given a 2D integer array intervals, where intervals[i] = [lefti, righti] describes the ith interval starting at lefti and ending at righti (inclusive). The size of an interval is defined as the number of integers it contains, or more formally righti - lefti + 1.

You are also given an integer array queries. The answer to the jth query is the size of the smallest interval i such that lefti <= queries[j] <= righti. If no such interval exists, the answer is -1.

Return an array containing the answers to the queries.

*/

class Solution {
    
    // Intuition: Utilize a treemap as a priority queue to order the minimum sized intervals as keys and the right bound as values in case the query is out of the bounds of that interval. The smallest interval will then be mapped with the query for later.
    // Time: O(n log n + k log k) for sorting each array.
    // Space: O(n + k) where n is length of intervals and k is the length of the queries.
    public int[] minInterval(int[][] intervals, int[] queries) {
        int[] res = new int[queries.length], sortedQueries = queries.clone();
        Map<Integer, Integer> map = new HashMap<>();
        TreeMap<Integer, Integer> minHeap = new TreeMap<>();  
        int i = 0;
        
        // sort queries and intervals to go in order.
        Arrays.sort(sortedQueries);
        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
        
        for (int query : sortedQueries) {
            while (i < intervals.length && intervals[i][0] <= query) { // add any valid starting intervals to the priority queue
                int left = intervals[i][0], right = intervals[i++][1];
                minHeap.put(right - left + 1, right);
            }
            
            while (!minHeap.isEmpty() && minHeap.firstEntry().getValue() < query) minHeap.pollFirstEntry(); // if the query isn't in the interval, get it out of the map
            
            // first key will be the smallest interval
            map.put(query, minHeap.isEmpty() ? -1 : minHeap.firstKey());
        }
        
        for (i = 0; i < queries.length; i++)
            res[i] = map.get(queries[i]);
        
        return res;
    }
}
