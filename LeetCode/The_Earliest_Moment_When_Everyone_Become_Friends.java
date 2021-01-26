/* Noah Park

In a social group, there are N people, with unique integer ids from 0 to N-1.

We have a list of logs, where each logs[i] = [timestamp, id_A, id_B] contains a non-negative integer timestamp, and the ids of two different people.

Each log represents the time in which two different people became friends.  Friendship is symmetric: if A is friends with B, then B is friends with A.

Let's say that person A is acquainted with person B if A is friends with B, or A is a friend of someone acquainted with B.

Return the earliest time for which every person became acquainted with every other person. Return -1 if there is no such earliest time.

In a social group, there are N people, with unique integer ids from 0 to N-1.

We have a list of logs, where each logs[i] = [timestamp, id_A, id_B] contains a non-negative integer timestamp, and the ids of two different people.

Each log represents the time in which two different people became friends.  Friendship is symmetric: if A is friends with B, then B is friends with A.

Let's say that person A is acquainted with person B if A is friends with B, or A is a friend of someone acquainted with B.

Return the earliest time for which every person became acquainted with every other person. Return -1 if there is no such earliest time.

*/

class Solution {
    // Time: O(n log n)
    // Space: O(n)
    public int earliestAcq(int[][] logs, int N) {
        Arrays.sort(logs, (a, b) -> Integer.compare(a[0], b[0])); // sort by increasing log times
        
        // union find ds
        int[] graph = new int[N];
        
        // initialize each parent to themselves
        for (int i = 0; i < N; i++) graph[i] = i;
        
        // iterate through the logs
        for (int i = 0; i < logs.length; i++) {
            int p1 = find(logs[i][1], graph), p2 = find(logs[i][2], graph); // get the current parents of each
            
            // if they aren't connected, connect them and decrease the number of disconnected components
            if (p1 != p2) {
                graph[p1] = p2;
                N--;
            }
            
            // all components are connected
            if (N == 1) return logs[i][0];
        }
        
        // not all components are connected
        return -1;
    }
    
    // path compression to find the parent
    public int find(int edge, int[] g) {
        if (g[edge] != edge) g[edge] = find(g[edge], g);
        return g[edge];
    }
}
