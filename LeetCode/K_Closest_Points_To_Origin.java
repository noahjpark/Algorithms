/* Noah Park

We have a list of points on the plane.  Find the K closest points to the origin (0, 0).

(Here, the distance between two points on a plane is the Euclidean distance.)

You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)

*/

class Solution {
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(K, (a, b) -> Integer.compare((int) (Math.pow(b[1], 2)) + (int) (Math.pow(b[0], 2)), (int) (Math.pow(a[1], 2)) + (int) (Math.pow(a[0], 2)))); // max heap will store the closest k elements
        
        for (int[] point : points) { // iterate through all the points
            maxHeap.offer(point); // offer each new point
            if (maxHeap.size() > K) maxHeap.poll(); // remove the largest point (furthest) when the size is too large
        }
        
        int[][] res = new int[K][2]; // return array
        int i = 0;
        
        while (!maxHeap.isEmpty()) res[i++] = maxHeap.poll(); // put closest values into the return array
        
        return res;
    }
}
