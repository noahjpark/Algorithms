/* Noah Park

On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M. Each worker and bike is a 2D coordinate on this grid.

Our goal is to assign a bike to each worker. Among the available bikes and workers, we choose the (worker, bike) pair with the shortest Manhattan distance between each other, and assign the bike to that worker. (If there are multiple (worker, bike) pairs with the same shortest Manhattan distance, we choose the pair with the smallest worker index; if there are multiple ways to do that, we choose the pair with the smallest bike index). We repeat this process until there are no available workers.

The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.

Return a vector ans of length N, where ans[i] is the index (0-indexed) of the bike that the i-th worker is assigned to.

*/

class Solution {
    
    // Intuition: Utilize a bucket sort since we can create buckets where each distance would be. We iterate from the start to ensure smallest distances. If we iterate over the list in order, the bike and worker indices will be minimized as well.
    // Time: O(M*N) for creating the buckets.
    // Space: O(M*n) for the buckets.
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        List<int[]>[] buckets = new ArrayList[2001];
        
        for (int i = 0; i < workers.length; i++) {
            for (int j = 0; j < bikes.length; j++) {
                int distance = Math.abs(workers[i][0] - bikes[j][0]) + Math.abs(workers[i][1] - bikes[j][1]);
                if (buckets[distance] == null) buckets[distance] = new ArrayList<>();
                buckets[distance].add(new int[]{ i, j });
            }
        }
        
        int[] res = new int[workers.length];
        boolean[] used = new boolean[bikes.length];
        Arrays.fill(res, -1);
        int assigned = 0;
        
        for (int i = 0; i < 2001; i++) {
            if (buckets[i] == null) continue;
            for (int[] pair : buckets[i]) {
                int widx = pair[0], bidx = pair[1];
                
                if (!used[bidx] && res[widx] == -1) {
                    res[widx] = bidx;
                    used[bidx] = true;
                    assigned++;
                }
            }
            
            if (assigned == workers.length) break;
        }
        
        return res;
    }
    
    // Intuition: Utilize a min heap to store the information in the following prioritiy order: minimum distance, minimum worker idx, minimum bike idx.
    // Time: O(m*n*log(m*n)) for the heap.
    // Space: O(m*n) for the heap.
    public int[] assignBikes2(int[][] workers, int[][] bikes) {
        int n = workers.length, m = bikes.length, assigned = 0;
        int[] res = new int[n];
        boolean[] used = new boolean[m];
        Arrays.fill(res, -1);
        
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> {
            return a[0] != b[0] ? a[0] - b[0] : a[1] != b[1] ? a[1] - b[1] : a[2] - b[2];
        });
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int distance = Math.abs(workers[i][0] - bikes[j][0]) + Math.abs(workers[i][1] - bikes[j][1]);
                minHeap.offer(new int[]{ distance, i, j });
            }
        }
        
        while (assigned < workers.length) {
            int[] cur = minHeap.poll();
            int workerIdx = cur[1], bikeIdx = cur[2];
            
            if (!used[bikeIdx] && res[workerIdx] == -1) {
                res[workerIdx] = bikeIdx;
                used[bikeIdx] = true;
                assigned++;
            }
        }
        
        return res;
    }
}
