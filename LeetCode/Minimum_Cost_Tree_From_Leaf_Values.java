/* Noah Park

Given an array arr of positive integers, consider all binary trees such that:

Each node has either 0 or 2 children;
The values of arr correspond to the values of each leaf in an in-order traversal of the tree.  (Recall that a node is a leaf if and only if it has 0 children.)
The value of each non-leaf node is equal to the product of the largest leaf value in its left and right subtree respectively.
Among all possible binary trees considered, return the smallest possible sum of the values of each non-leaf node.  It is guaranteed this sum fits into a 32-bit integer.

*/

class Solution {
    
    // Intuition: Monotonic stack to mitigate the recompution from the greedy solution. Stack maintains the array elements in order, when we have a smaller value at the top of the stack than our current number, we have the found a candidate for adding to the tree.
    // Time: O(n) for looping over the array
    // Space: O(n) stack
    public int mctFromLeafValues(int[] arr) {
        int sum = 0;
        Stack<Integer> stack = new Stack<>();
        
        for (int num : arr) {
            while (!stack.isEmpty() && stack.peek() <= num) {
                int cur = stack.pop();
                sum += cur * Math.min((stack.isEmpty() ? Integer.MAX_VALUE : stack.peek()), num);
            }
            stack.push(num);
        }
        
        while (stack.size() > 1) 
            sum += stack.pop() * stack.peek();
        
        return sum;
    }
    
    // Intuition: Greedy approach by taking the smallest index and multiplying it with its smallest neighbor at each point to create small sums.
    // Time: O(n^2) 
    // Space: O(n) to copy over arr.
    public int mctFromLeafValues2(int[] arr) {
        List<Integer> copy = new ArrayList<>();
        for (int num : arr) copy.add(num);
        int sum = 0;
        
        while (copy.size() > 1) {
            int min = -1;
            
            for (int j = 0; j < copy.size(); j++)
                if (min == -1 || copy.get(min) > copy.get(j)) min = j;
            
            if (min == 0 || min == copy.size() - 1) sum += min == 0 ? copy.get(min) * copy.get(min + 1) : copy.get(min) * copy.get(min - 1);
            else sum += copy.get(min) * Math.min(copy.get(min - 1), copy.get(min + 1));
            
            copy.remove(min);
        }
        
        return sum;
    }
    
    // Intuition: Top Down dynamic programming with memoization. Coming up with each possibility involves splitting the array at a pivot and taking the product of the maximum values in between the pivot.
    // Time: O(n^3) since we loop n^2 to come up with all possiblities within a n loop.
    // Space: O(n^2) for the memoized array
    Integer[][] mem;
    
    public int mctFromLeafValuesBUDP(int[] arr) {
        if (arr.length == 2) return arr[0]*arr[1];
        mem = new Integer[arr.length + 1][arr.length + 1];
        return dp(arr, 0, arr.length - 1);
    }
    
    public int dp(int[] arr, int start, int end) {
        if (start >= end) return 0;
        if (mem[start][end] != null) return mem[start][end];
        
        int min = Integer.MAX_VALUE;
        
        for (int i = start; i < end; i++) {
            int max1 = 0, max2 = 0;
            
            for (int j = start; j <= i; j++) max1 = Math.max(arr[j], max1);
            for (int j = i + 1; j <= end; j++) max2 = Math.max(arr[j], max2);

            min = Math.min(min, dp(arr, start, i) + dp(arr, i + 1, end) + max1*max2);
        }
        
        mem[start][end] = min;
        return min;
    }
}
