/* Noah Park

A web developer needs to know how to design a web page's size. So, given a specific rectangular web page’s area, your job by now is to design a rectangular web page, whose length L and width W satisfy the following requirements:

The area of the rectangular web page you designed must equal to the given target area.
The width W should not be larger than the length L, which means L >= W.
The difference between length L and width W should be as small as possible.
Return an array [L, W] where L and W are the length and width of the web page you designed in sequence.

*/

class Solution {
    
    // Intuition: Optimize the below solution to start at the midpoint and work outwards to the first divisible number by area. This will minimize the difference between the l and r.
    // Time and Space: O(1) constant.
    public int[] constructRectangle(int area) {
        int w = (int) Math.sqrt(area);
        while (area % w != 0)
            w--;
        
        return new int[]{ area / w, w };
    }
    
    // Intuition: Brute force all possible combinations until l becomes too small.
    // Time: O(sqrt(n)) to iterate over all values of the square root of area.
    // Space: O(1) constant.
    public int[] constructRectangle2(int area) {
        int[] res = new int[]{ area, 1 };
        
        for (int l = area; l > 0; l--) {
            if (area % l != 0) continue;
            
            int w = area / l;
            if (l >= w) { res[0] = l; res[1] = w; }
            else break;
        }
        
        return res;
    }
}
