/* Noah Park

Given a non-empty array of integers, return the k most frequent elements.

*/

// custom class for sorting
class Element {
    int value;
    int frequency;
    
    public Element(int v, int f) {
        value = v;
        frequency = f;
    }
}

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[]{}; // edge cases
        
        Map<Integer, Integer> freq = new HashMap<>(); // frequency map
        
        for (int num : nums) freq.put(num, freq.getOrDefault(num, 0) + 1); // put into map
        
        PriorityQueue<Element> minHeap = new PriorityQueue<>(k, (a, b) -> Integer.compare(a.frequency, b.frequency)); // min heap by frequency
        
        // populate heap
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            minHeap.offer(new Element(entry.getKey(), entry.getValue()));
            if (minHeap.size() > k) minHeap.poll();
        }
        
        int[] res = new int[k];
        int i = 0;
        
        // remaining elements in the heap are the top k elements
        while (!minHeap.isEmpty()) res[i++] = minHeap.poll().value;
        
        return res;
    }
}
