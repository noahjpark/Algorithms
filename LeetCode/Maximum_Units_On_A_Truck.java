/* Noah Park

You are assigned to put some amount of boxes onto one truck. You are given a 2D array boxTypes, where boxTypes[i] = [numberOfBoxesi, numberOfUnitsPerBoxi]:

numberOfBoxesi is the number of boxes of type i.
numberOfUnitsPerBoxi is the number of units in each box of the type i.
You are also given an integer truckSize, which is the maximum number of boxes that can be put on the truck. You can choose any boxes to put on the truck as long as the number of boxes does not exceed truckSize.

Return the maximum total number of units that can be put on the truck.

*/

class Solution {
    // Time: O(n log n) where n is the number of boxes (sorting)
    // Space: O(1)
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> Integer.compare(b[1], a[1])); // sort by units, largest come first
        
        int units = 0;
        
        // iterate over the boxes until the truck is full or we have added all boxes
        for (int i = 0; i < boxTypes.length && truckSize > 0; i++) {
            int[] box = boxTypes[i];
            
            if (box[0] > truckSize) { // if we can't add all boxes of this type, add what we can since the truck will be full
                units += truckSize * box[1];
                break;
            } else { // add all the boxes of this type and reflect this change in the truckSize as well
                units += box[0] * box[1];
                truckSize -= box[0];
            }
        }
        
        return units;
    }
    
    // Time: O(m log n) where m is the truckSize and n is the number of boxes
    // Space: O(n) where n is the number of boxes
    public int maximumUnitsHeap(int[][] boxTypes, int truckSize) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b[1], a[1])); // order using a max heap
        
        // offer all boxes ordered by units
        for (int[] box : boxTypes) 
            maxHeap.offer(box);
        
        int units = 0;
        
        // keep choosing a box and adding its units while the truck can still hold boxes
        while (!maxHeap.isEmpty() && truckSize > 0) {
            int[] box = maxHeap.poll();
            box[0]--;
            units += box[1];
            if (box[0] > 0) maxHeap.offer(box);
            truckSize--;
        }
        
        return units;
    }
}
