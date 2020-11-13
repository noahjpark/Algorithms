/* Noah Park

Given a m * n matrix mat of ones (representing soldiers) and zeros (representing civilians), return the indexes of the k weakest rows in the matrix ordered from the weakest to the strongest.

A row i is weaker than row j, if the number of soldiers in row i is less than the number of soldiers in row j, or they have the same number of soldiers but i is less than j. Soldiers are always stand in the frontier of a row, that is, always ones may appear first and then zeros.

*/

class Solution {
    public int[] kWeakestRows(int[][] mat, int k) {
        int[] weakest = new int[k];
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> a[0] != b[0] ? Integer.compare(b[0], a[0]) : Integer.compare(b[1], a[1]));
        
        for(int i = 0; i < mat.length; i++){
            int numOnes = binarySearch(mat[i]);
            maxHeap.offer(new int[]{ numOnes, i });
            if(maxHeap.size() > k) maxHeap.poll(); // Remove the largest value currently there as there are k smaller ones there already
        }
        
        // The maxHeap will remove from the largest values to the smallest, so we populate the array backwards
        for(int i = k - 1; i > -1; i--)
            weakest[i] = maxHeap.poll()[1];
        
        return weakest;
    }
    
    public int binarySearch(int[] arr){
        int left = 0, right = arr.length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(arr[mid] == 1) left = mid + 1;
            else right = mid - 1;
        }
        return left; // Left will be the index of the first non 1 value which is also the total number of 1s in the row
    }
}
