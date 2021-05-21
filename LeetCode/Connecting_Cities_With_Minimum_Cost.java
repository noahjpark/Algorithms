/* Noah Park

There are n cities numbered from 1 to n.

You are given connections, where each connections[i] = [city1, city2, cost] represents the cost to connect city1 and city2 together.  (A connection is bidirectional: connecting city1 and city2 is the same as connecting city2 and city1.)

Return the minimum cost so that for every pair of cities, there exists a path of connections (possibly of length 1) that connects those two cities together.  The cost is the sum of the connection costs used. If the task is impossible, return -1.

*/

class Solution {
    
    // Intuition: Kruskal's algorithm -> greedily take the smallest available connection to union all unconnected nodes together.
    // Time: O(m*log^*N) ~ O(m) where m is the number of connections/edges and n is the number of cities.
    // Space: O(n) cities maintained by parents/weights.
    int[] weights, parents;
    
    public int minimumCost(int n, int[][] connections) {
        weights = new int[n + 1];
        parents = new int[n + 1];
        
        for (int i = 1; i < n + 1; i++) {
            weights[i] = 1;
            parents[i] = i;
        }
        
        Arrays.sort(connections, (a, b) -> (a[2] - b[2]));
        int cost = 0, edges = 0;
    
        for (int[] connection : connections) {
            if (find(connection[0]) == find(connection[1])) continue; // If there already exists a connection in the graph, we don't take another
            
            union(connection[0], connection[1]);
            cost += connection[2];
            edges++;
        }
        
        return edges == n - 1 ? cost : -1;
    }
    
    public void union(int a, int b) {
        int pa = find(a), pb = find(b);
        
        if (pa != pb) {
            if (weights[pa] > weights[pb]) {
                parents[pb] = pa;
                weights[pa] += weights[pb];
            } else {
                parents[pa] = pb;
                weights[pb] += weights[pa];
            }
        }
    }
    
    public int find(int a) {
        if (a == parents[a]) return a;
        parents[a] = find(parents[parents[a]]);
        return parents[a];
    }
    
}
