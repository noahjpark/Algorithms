/* Noah Park

You are given two arrays of positive integers, boxes and warehouse, representing the heights of some boxes of unit width and the heights of n rooms in a warehouse respectively. The warehouse's rooms are labelled from 0 to n - 1 from left to right where warehouse[i] (0-indexed) is the height of the ith room.

Boxes are put into the warehouse by the following rules:

Boxes cannot be stacked.
You can rearrange the insertion order of the boxes.
Boxes can only be pushed into the warehouse from left to right only.
If the height of some room in the warehouse is less than the height of a box, then that box and all other boxes behind it will be stopped before that room.
Return the maximum number of boxes you can put into the warehouse.

*/

class Solution {
    
    // Intuition: If we can't modify warehouse, this is an approach around it. Rather than looking from the smallest to the largest boxes, do the opposite. When we reach a point in the warehouse that is too small, we move on to a smaller box.
    // Time: O(nlogn + m) one pass over warehouse, sorted boxes.
    // Space: O(sort) constant without counting sort.
    public int maxBoxesInWarehouse(int[] boxes, int[] warehouse) {
        int b = boxes.length, w = warehouse.length, p1 = boxes.length - 1, p2 = 0, count = 0;
        
        Arrays.sort(boxes);
        
        while (p1 >= 0 && p2 < w) {
            if (boxes[p1] <= warehouse[p2]) { p1--; p2++; count++; }
            else if (boxes[p1] > warehouse[p2]) { p1--; }
        }
        
        return count;
    }
    
    // Intuition: Greedy approach -> adjust warehouse so it makes sense from left to right (non increasing) then sort boxes and try to put them smallest to largest.
    // Time: O(nlogn + m) two passes over warehouse, sorted boxes.
    // Space: O(sort) constant without counting sorting.
    public int maxBoxesInWarehouse2(int[] boxes, int[] warehouse) {
        int b = boxes.length, w = warehouse.length;
        
        for (int i = 1; i < w; i++) 
            if (warehouse[i] > warehouse[i - 1]) warehouse[i] = warehouse[i - 1];
        
        Arrays.sort(boxes);
        
        int p1 = 0, p2 = w - 1, count = 0;
        
        while (p1 < b && p2 >= 0) {
            if (boxes[p1] <= warehouse[p2]) { p1++; p2--; count++; }
            else if (boxes[p1] > warehouse[p2]) p2--;
        }
     
        return count;
    }
}
