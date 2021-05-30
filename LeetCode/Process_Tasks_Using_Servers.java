/* Noah Park

You are given two 0-indexed integer arrays servers and tasks of lengths n​​​​​​ and m​​​​​​ respectively. servers[i] is the weight of the i​​​​​​th​​​​ server, and tasks[j] is the time needed to process the j​​​​​​th​​​​ task in seconds.

You are running a simulation system that will shut down after all tasks are processed. Each server can only process one task at a time. You will be able to process the jth task starting from the jth second beginning with the 0th task at second 0. To process task j, you assign it to the server with the smallest weight that is free, and in case of a tie, choose the server with the smallest index. If a free server gets assigned task j at second t,​​​​​​ it will be free again at the second t + tasks[j].

If there are no free servers, you must wait until one is free and execute the free tasks as soon as possible. If multiple tasks need to be assigned, assign them in order of increasing index.

You may assign multiple tasks at the same second if there are multiple free servers.

Build an array ans​​​​ of length m, where ans[j] is the index of the server the j​​​​​​th task will be assigned to.

Return the array ans​​​​.

*/

class Solution {
    
    // Intuition: Maintain a heap of the available servers and a heap of the servers in use. I maintain a seconds pointer to the current time we are at. If we have a situation where all servers are in use, we can jump forward in time by polling the soonest finishing server.
    // Time: O((m + n) * log(n)) for the heaps.
    // Space: O(m + n) for the heaps and resulting array.
    public int[] assignTasks(int[] servers, int[] tasks) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> (a[1] != b[1] ? a[1] - b[1] : a[0] - b[0]));
        PriorityQueue<int[]> todo = new PriorityQueue<>((a, b) -> (a[2] != b[2] ? a[2] - b[2] : a[1] != b[1] ? a[1] - b[1] : a[0] - b[0]));
        int n = servers.length, m = tasks.length;
        int[] res = new int[m];
        
        // { server number, server weight }
        for (int i = 0; i < n; i++) minHeap.offer(new int[]{ i, servers[i] });
        
        // Execute all tasks
        int sec = 0;
        for (int i = 0; i < m; i++, sec++) {  
            while (!todo.isEmpty() && todo.peek()[2] <= i) minHeap.offer(todo.poll());
            if (minHeap.isEmpty()) {
                int[] temp = todo.poll();
                minHeap.offer(temp);
                sec = temp[2];
            }
            
            int[] server = minHeap.poll();
            res[i] = server[0];
            todo.offer(new int[]{ server[0], server[1], sec + tasks[i] });
        }
        
        
        
        return res;
    }
}
