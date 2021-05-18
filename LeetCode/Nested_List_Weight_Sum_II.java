/* Noah Park

You are given a nested list of integers nestedList. Each element is either an integer or a list whose elements may also be integers or other lists.

The depth of an integer is the number of lists that it is inside of. For example, the nested list [1,[2,2],[[3],2],1] has each integer's value set to its depth. Let maxDepth be the maximum depth of any integer.

The weight of an integer is maxDepth - (the depth of the integer) + 1.

Return the sum of each integer in nestedList multiplied by its weight.

*/

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
class Solution {
    
    // Intuition: Initial dfs to find the max depth. Second dfs to actually accumulate the result by each number's weight.
    // Time: O(n) to iterate over the numbers in the list.
    // Space: O(h) however nested a number is.
    int res = 0, max = 0;
    
    public int depthSumInverse(List<NestedInteger> nestedList) {
        max = depth(nestedList);
        dfs(nestedList, 1);
        return res;
    }
    
    public int depth(List<NestedInteger> nl) {
        int maxDepth = 1;
        
        for (NestedInteger n : nl)
            if (!n.isInteger()) maxDepth = Math.max(maxDepth, depth(n.getList()) + 1);
        
        return maxDepth;
    }
    
    public void dfs(List<NestedInteger> nl, int curDepth) {
        int sum = 0;
        
        for (NestedInteger n : nl) {
            if (!n.isInteger()) dfs(n.getList(), curDepth + 1);
            else res += n.getInteger()*(max - curDepth + 1);
        }
    }
}
