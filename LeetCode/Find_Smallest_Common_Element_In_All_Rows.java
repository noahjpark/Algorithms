/* Noah Park

Given an m x n matrix mat where every row is sorted in strictly increasing order, return the smallest common element in all rows.

If there is no common element, return -1.

*/

class Solution {
    
    // Intuition: Use counting sort since we have an upper bound on the largest integer in the mat. Also, we know there aren't duplicates so we know that if the frequency matches the row count, we have the occurring element.
    // Time: O(m*n) where n is the rows and m is the columns.
    // Space: O(1)
    public int smallestCommonElement(int[][] mat) {
        int[] freq = new int[10001];
        
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++)
                freq[mat[i][j]]++;
        }
        
        for (int i = 0; i < 10001; i++) 
            if (freq[i] == mat.length) return i;
        
        return -1;
    }
    
    // Intuition: Map each row to the smallest index in the given row. Keep track of the smallest common elements among the rows. Move each row pointer forward as needed updating the smallest value and breaking from the loop if needed.
    // Time: O(m*n) where n is the rows and m is the columns.
    // Space: O(n) where n is the rows since each row is mapped to a pointer.
    public int smallestCommonElement2(int[][] mat) {
        Map<Integer, Integer> map = new HashMap<>();
        int smallest = 0;
        
        for (int i = 0; i < mat.length; i++) {
            map.put(i, 0);
            smallest = Math.max(smallest, mat[i][0]);
        }
        
        while (true) {
            int count = 0;
            
            for (int i = 0; i < mat.length; i++) {
                int idx = map.get(i);
                
                while (idx < mat[0].length && mat[i][idx] < smallest) idx++;
                
                if (idx == mat[0].length) return -1;
                
                if (mat[i][idx] > smallest) smallest = mat[i][idx];
                else count++;
                
                map.put(i, idx);
            }
            
            if (count == mat.length) break;
        }
        
        return smallest;
    }
}
