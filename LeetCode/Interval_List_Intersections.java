/* Noah Park

Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.

Return the intersection of these two interval lists.

(Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b. 

The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented as a closed interval.  

For example, the intersection of [1, 3] and [2, 4] is [2, 3].)

*/

class Solution {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> intervals = new ArrayList<>();
        int pa = 0, pb = 0;
        
        while(pa < A.length && pb < B.length){
            // If there is an intersection
            if(A[pa][0] >= B[pb][0] && A[pa][0] <= B[pb][1] || B[pb][0] >= A[pa][0] && B[pb][0] <= A[pa][1])
                intervals.add(new int[]{Math.max(A[pa][0], B[pb][0]), Math.min(A[pa][1], B[pb][1])});
            
            // Move from the interval which is finishing first
            if(A[pa][1] < B[pb][1]) pa++;
            else pb++;
        }
        
        int[][] ans = new int[intervals.size()][2];
        for(int i = 0; i < intervals.size(); i++)
            ans[i] = intervals.get(i);
        
        return ans;
    }
}
