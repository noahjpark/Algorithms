/* Noah Park

Given an array of integers arr and an integer k. Find the least number of unique integers after removing exactly k elements.

*/

class Solution {
    
    // Intuition: Bucket sort after finding the counts of each integer. Then we just iterate over the buckets up to the maximum occurence (optimized) until we reach a point where the buckets[i] * i is larger than k. buckets[i] represents the number of integers that have an occurrence i. If we have a perfect match, we can count all unique integers in front of buckets[i], otherwise, we calculate how many unique integers at i we are keeping then count the ones after. Say we have 3 integers each with an occurrence of 2. Then buckets[2] will be 3. buckets[i] * i is then 6 total integers. If we have a k of 3 for example, we subtract the k to get 3. Since 3 is not divisible by the number of unique integers (i: 2) we need to round our value up (i - res % i). From here we divide by i and we have the total unique integers we are currently on.
    // Time: O(n) to iterate over the map, buckets, and result.
    // Space: O(n) for the map and buckets.
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        if (arr == null || arr.length == 0) return 0;
        
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) 
            map.put(num, map.getOrDefault(num, 0) + 1);
        int n = arr.length, max = 0, size = map.size();
        
        int[] buckets = new int[n + 1];
        for (Integer key : map.keySet()) {
            int val = map.get(key);
            buckets[val]++;
            max = Math.max(max, val);
        }
        
        for (int i = 1; i <= max; i++) {
            if (buckets[i] * i >= k) {
                int res = 0;
                if (buckets[i] * i > k) {
                    res = buckets[i] * i - k;
                    if (res % i != 0) res += (i - res % i);
                    res /= i;
                }
                
                return res + (size - buckets[i]);
            } else { k -= (buckets[i] * i); size -= buckets[i]; }
        }
        
        return 0;
    }
    
    // Intuition: Greedily try to remove the integer with the least number of occurrences using a min heap.
    // Time: O(n log n) for the heap since k < n.
    // Space: O(n) for the map and heap.
    public int findLeastNumOfUniqueInts2(int[] arr, int k) {
        if (arr == null || arr.length == 0) return 0;
        
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) 
            map.put(num, map.getOrDefault(num, 0) + 1);
        
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>((a, b) -> (a.getValue() - b.getValue()));
        minHeap.addAll(map.entrySet());
        
        for (int i = 0; i < k; i++) {
            Map.Entry<Integer, Integer> entry = minHeap.poll();
            entry.setValue(entry.getValue() - 1);
            if (entry.getValue() > 0) minHeap.offer(entry);
        }
        
        return minHeap.size();
    }
}
