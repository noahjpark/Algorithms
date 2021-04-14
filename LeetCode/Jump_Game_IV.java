/* Noah Park

Given an array of integers arr, you are initially positioned at the first index of the array.

In one step you can jump from index i to index:

i + 1 where: i + 1 < arr.length.
i - 1 where: i - 1 >= 0.
j where: arr[i] == arr[j] and i != j.
Return the minimum number of steps to reach the last index of the array.

Notice that you can not jump outside of the array at any time.

*/

class Solution {
    
    // Intuition: Utilize a bfs to find the shortest path. We look at each level through all reachable positions then move to the next level. Need an optimization for space to only store the start and end indices of any consecutive numbers of the same type. If there are 1000 7s, we only need the first and last occurrence of them if they are all adjacent.
    // Time: O(n) to create the map and bfs over arr.
    // Space: O(n) for the queue and map.
    public int minJumps(int[] arr) {  
        if (arr.length == 1) return 0;
        if (arr[0] == arr.length - 1) return 1;
        
        int n = arr.length, jumps = 0, k = 0;
        boolean[] visited = new boolean[n];
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        while (k < n) {
            if (!map.containsKey(arr[k])) map.put(arr[k], new ArrayList<>());
            map.get(arr[k]).add(k);
            if (k < n - 1 && arr[k] == arr[k + 1]) { // necessary optimization for memory
                int i = k + 1;
                while (i < n && arr[k] == arr[i]) i++;
                map.get(arr[k]).add(i - 1);
                k = i - 1;
            }
            k++;
        }
    
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(0);
        
        while (!q.isEmpty()) {
            int size = q.size();
            
            for (int i = 0; i < size; i++) {
                int cur = q.poll();
                
                if (cur == n - 1) return jumps;
                visited[cur] = true;
                
                if (cur - 1 >= 0 && !visited[cur - 1]) q.offer(cur - 1);
                if (cur + 1 < n && !visited[cur + 1]) q.offer(cur + 1);
                
                List<Integer> reachableIndices = map.get(arr[cur]);
                for (int idx : reachableIndices) 
                    if (idx != cur && !visited[idx]) q.offer(idx);
            }
            
            jumps++;
        }
        
        return -1;
    }
    
}
