/* Noah Park

You are given two integer arrays nums1 and nums2 both of unique elements, where nums1 is a subset of nums2.

Find all the next greater numbers for nums1's elements in the corresponding places of nums2.

The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. If it does not exist, return -1 for this number.

*/

class Solution {
    
    // Intuition: Maintain a monostack of decreasing elements. When we reach a larger element, we can update the map for all elements in the stack to this element. This will maintain the next largest element in better time.
    // Time: O(m + n) to iterate over each array.
    // Space: O(m) for the map and stack.
    public int[] nextGreaterElement(int[] n1, int[] n2) {
        int n = n1.length, m = n2.length;
        Map<Integer, Integer> map = new HashMap<>();
        Deque<Integer> stack = new LinkedList<>();
        
        for (int i = 0; i < m; i++) {
            while (!stack.isEmpty() && stack.getLast() < n2[i]) 
                map.put(stack.removeLast(), n2[i]);
            stack.addLast(n2[i]);
        }
        
        while (!stack.isEmpty()) map.put(stack.removeLast(), -1);
        
        int[] res = new int[n];
        
        for (int i = 0; i < n; i++)
            res[i] = map.get(n1[i]);
        
        return res;
    }
    
    // Intuition: Map values from num2 to their next indices for easy access from nums1.
    // Time: O(n*m) to iterate over each value of nums2 for each value of nums1.
    // Space: O(m) to maintain the map.
    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums1.length, m = nums2.length;
        int[] res = new int[n];
        
        for (int i = 0; i < m; i++)
            map.put(nums2[i], i + 1);
        
        for (int i = 0; i < n; i++) {
            int val = -1;
            for (int j = map.get(nums1[i]); j < m; j++)
                if (nums2[j] > nums1[i]) { val = nums2[j]; break; }
            res[i] = val;
        }
        
        return res;
    }
}
