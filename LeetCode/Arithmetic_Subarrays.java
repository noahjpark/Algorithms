/* Noah Park

A sequence of numbers is called arithmetic if it consists of at least two elements, and the difference between every two consecutive elements is the same. More formally, a sequence s is arithmetic if and only if s[i+1] - s[i] == s[1] - s[0] for all valid i.

For example, these are arithmetic sequences:

1, 3, 5, 7, 9
7, 7, 7, 7
3, -1, -5, -9
The following sequence is not arithmetic:

1, 1, 2, 5, 7
You are given an array of n integers, nums, and two arrays of m integers each, l and r, representing the m range queries, where the ith query is the range [l[i], r[i]]. All the arrays are 0-indexed.

Return a list of boolean elements answer, where answer[i] is true if the subarray nums[l[i]], nums[l[i]+1], ... , nums[r[i]] can be rearranged to form an arithmetic sequence, and false otherwise.

*/

class Solution {

    // Intuition: Utilize that a pattern must exist between all numbers based on dividing the range by the number of values in the range.
    // Time: O(l*n) iterating over all of nums for each index in l.
    // Space: O(n) for the set
    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Boolean> res = new ArrayList<>();
        
        for (int i = 0; i < l.length; i++) {
            Set<Integer> set = new HashSet<>();
            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, start = l[i], end = r[i];
            boolean val = true;
            
            for (int j = start; j <= end; j++) { 
                min = Math.min(min, nums[j]);
                max = Math.max(max, nums[j]);
                set.add(nums[j]);
            }
            
            if (min == max) val = true; // if all values in the subarray are the same
            else if ((max - min) % (end - start) != 0) val = false; // if it is not possible for all values to follow an arithmetic pattern
            else {
                int interval = (max - min) / (end - start);

                for (int j = 0; j <= (end - start); j++)
                    if (!set.contains(min + interval*j)) { val = false; break; }
            }
            
            res.add(val);
        }
        
        return res;
    }
    
    // Intuition: Utilize a priority queue to maintain an ordered subarray.
    // Time: O(l*(n + logn)) iterating over l then over potentially all of nums then polling from the max heap.
    // Space: O(n) if the priority queue has all of nums.
    public List<Boolean> checkArithmeticSubarrays2(int[] nums, int[] l, int[] r) {
        List<Boolean> res = new ArrayList<>();
        
        for (int it = 0; it < l.length; it++) {
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> (b - a));
            
            for (int i = l[it]; i <= r[it]; i++)
                maxHeap.offer(nums[i]);
            
            int difference = -1;
            boolean val = true;
            
            while (maxHeap.size() > 1) {
                if (difference == -1) difference = maxHeap.poll() - maxHeap.peek();
                else if (difference != maxHeap.poll() - maxHeap.peek()) { val = false; break; }
            }
            
            res.add(val);
        }
        
        return res;
    }
}
