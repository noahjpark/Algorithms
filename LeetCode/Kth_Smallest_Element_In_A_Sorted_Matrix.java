/* Noah Park

Given an n x n matrix where each of the rows and columns are sorted in ascending order, return the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

*/

class Solution {
    
    // Intuition: With the help of the article, I learned how to binary search this problem. We essentially look at the total range of values we have and find the midpoint. Then we are able to count the number of smaller or equal elements to this value in the matrix using the sorted intuition in better time. From here, we decide we have found the range that contains the value we are looking for as the largest or we must shorten our search space.
    // Time: O((n+m)*log(range)) where n is the rows, m is the cols, and range is the range of numbers in the matrix.
    // Space: O(1) constant.
    public int kthSmallest(int[][] matrix, int k) {
        int rows = matrix.length, cols = matrix[0].length, left = matrix[0][0], right = matrix[rows - 1][cols - 1];
        
        while (left < right) {
            int mid = left + (right - left) / 2, r = 0, c = cols - 1, min = right, max = left, count = 0;
            
            while (c >= 0 && r < rows) {
                if (matrix[r][c] > mid) { 
                    min = Math.min(min, matrix[r][c]);
                    c--;
                } else {
                    max = Math.max(max, matrix[r][c]);
                    count += c + 1;
                    r++;
                }
            }
            
            if (count == k) return max;
            else if (count < k) left = min;
            else right = max;
        }
        
        return left;
    }
    
    // Intuition: Maintain all values in the matrix in sorted order (maxHeap) with k elements. Then take top.
    // Time: O(n*m*log(k))) for the heap.
    // Space: O(k) for the heap.
    public int kthSmallest2(int[][] matrix, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> (b - a));
        int size = matrix.length * matrix[0].length;
        
        for (int[] row : matrix) {
            for (int col : row) {
                maxHeap.offer(col);
                if (maxHeap.size() > k) maxHeap.poll();
            }
        }
        
        return maxHeap.peek();
    }
}
