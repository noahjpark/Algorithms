/* Noah Park

Given a rectangular cake with height h and width w, and two arrays of integers horizontalCuts and verticalCuts where horizontalCuts[i] is the distance from the top of the rectangular cake to the ith horizontal cut and similarly, verticalCuts[j] is the distance from the left of the rectangular cake to the jth vertical cut.

Return the maximum area of a piece of cake after you cut at each horizontal and vertical position provided in the arrays horizontalCuts and verticalCuts. Since the answer can be a huge number, return this modulo 10^9 + 7.

*/

class Solution {
    
    int mod = 1000000007;
    
    // Intuition: Look for the largest row subsection and largest column subsection. This is all we care about. This maximizes our target subsection and is our answer.
    // Time: O(nlogn + mlogm) to sort the arrays then linearly search for the largest open subsection.
    // Space: O(sort) for sorting.
    public int maxArea(int h, int w, int[] hc, int[] vc) {
        Arrays.sort(hc);
        Arrays.sort(vc);
        
        long res = 0, rprev = 0, cprev = 0, rdis = 0, cdis = 0;
        int n = hc.length, m = vc.length;
        
        for (int i = 0; i < n; i++) { rdis = Math.max(rdis, hc[i] - rprev); rprev = hc[i]; }
        rdis = Math.max(rdis, h - rprev);
        for (int j = 0; j < m; j++) { cdis = Math.max(cdis, vc[j] - cprev); cprev = vc[j]; }
        cdis = Math.max(cdis, w - cprev);
        
        return (int) ((rdis * cdis) % mod);
    }
    
    // Intuition: Brute force comparisons of all cuts taking the largest subsection.
    // Time: O(n*m + nlogn + mlogm) to sort the arrays and make the comparisons.
    // Space: O(sort) to sort the arrays.
    public int maxArea2(int h, int w, int[] hc, int[] vc) {
        long res = 0;
        
        Arrays.sort(hc);
        Arrays.sort(vc);
        
        int rprev = 0, n = hc.length, m = vc.length;
        
        for (int i = 1; i < n; i++) {
            int cprev = 0;
            
            for (int j = 1; j < m; j++) { 
                res = Math.max((hc[i] - rprev) * (vc[j] - cprev), res);
                cprev = vc[j];
            }
            
            res = Math.max((hc[i] - rprev) * (w - cprev), res);
            
            rprev = hc[i];
        }
        
        int cprev = 0;
        for (int j = 0; j < m; j++) {
            res = Math.max((h - rprev) * (vc[j] - cprev), res);
            cprev = vc[j];
        }
        res = Math.max((h - rprev) * (w - cprev), res);
            
        return (int) res % mod;
    }
    
}
