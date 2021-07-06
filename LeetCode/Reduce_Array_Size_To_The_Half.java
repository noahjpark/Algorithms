/* Noah Park

Given an array arr.  You can choose a set of integers and remove all the occurrences of these integers in the array.

Return the minimum size of the set so that at least half of the integers of the array are removed.

*/

class Solution {
    
    // Intuition: Bucket sort. Sort by the counts, then by the frequencies to take away the largest each time.
    // Time: O(n) to iterate over all nums in the arr. The loop for buckets will be smaller than or equal to n.
    // Space: O(1) in this case, but O(n) normally.
    public int minSetSize(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        
        int[] map = new int[100001];
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, n = arr.length, res = 0, maxFreq = 0;
        
        for (int num : arr) { 
            map[num]++;
            maxFreq = Math.max(maxFreq, map[num]);
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        
        int[] buckets = new int[maxFreq + 1];
        for (int i = min; i <= max; i++) 
            buckets[map[i]]++;
        
        double half = arr.length / 2.0;
        for (int i = maxFreq; i > 0; i--) {
            while (buckets[i] > 0) {
                n -= i;
                res++;
                buckets[i]--;
                if (n <= half) return res;
            }
        }
        
        return res;
    }
    
    // Intuition: Same as the 3rd solution but uses a counting sort map since we have an upper bound of values. We would need a map otherwise.
    // Time: O(nlogn) for the priority queue.
    // Space: O(n) for the priority queue.
    public int minSetSize2(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        
        int[] map = new int[100001];
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> (b - a));
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, n = arr.length, res = 0;
        
        for (int num : arr) { 
            map[num]++;
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        
        for (int i = min; i <= max; i++)
            maxHeap.offer(map[i]);
        
        double half = arr.length / 2.0;
        while (n > half) {
            n -= maxHeap.poll();
            res++;
        }
        
        return res;
    }
    
    public int minSetSize3(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = new PriorityQueue<>((a, b) -> (b.getValue() - a.getValue()));
        
        for (int num : arr)
            map.put(num, map.getOrDefault(num, 0) + 1);
        maxHeap.addAll(map.entrySet());
        
        int n = arr.length, res = 0;
        double half = arr.length / 2.0;
        while (n > half) {
            n -= maxHeap.poll().getValue();
            res++;
        }
        
        return res;
    }
}
