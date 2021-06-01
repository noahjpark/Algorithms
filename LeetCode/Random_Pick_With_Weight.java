/* Noah Park

You are given an array of positive integers w where w[i] describes the weight of ith index (0-indexed).

We need to call the function pickIndex() which randomly returns an integer in the range [0, w.length - 1]. pickIndex() should return the integer proportional to its weight in the w array. For example, for w = [1, 3], the probability of picking the index 0 is 1 / (1 + 3) = 0.25 (i.e 25%) while the probability of picking the index 1 is 3 / (1 + 3) = 0.75 (i.e 75%).

More formally, the probability of picking index i is w[i] / sum(w).

*/

class Solution {

    // Intuition: Essentially make each number its weight in comparison to the total. Prefix sum is an optimization over expanding each number to its frequency and choosing then. A binary search on the prefix array works by choosing a random value among the whole of the prefix sum then finding the index in which it corresponds to.
    // Time: O(log n) to apply the binary search, O(n) to create the prefix array.
    // Space: O(n) to maintain the prefix array.
    int[] pre;
    int total;
    
    public Solution(int[] w) {
        pre = new int[w.length];
        pre[0] = w[0];
        for (int i = 1; i < w.length; i++)
            pre[i] = pre[i - 1] + w[i];
        total = pre[w.length - 1];
    }
    
    public int pickIndex() {
        double val = total * Math.random();
        int l = 0, r = pre.length - 1;
        
        while (l < r) {
            int mid = l + (r - l) / 2;
            
            if (pre[mid] > val) r = mid;
            else l = mid + 1;
        }
        
        return l;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */
