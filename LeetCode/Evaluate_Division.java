/* Noah Park

You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.

You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.

Return the answers to all queries. If a single answer cannot be determined, return -1.0.

Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.

*/

class Solution {
    
    // Intuition: Map each string to a reachable string and the weight to get there.
    // Time: O(m*(v + e)) to traverse the graph for each query.
    // Space: O(v + e) for the graph.
    Map<String, Map<String, Double>> map = new HashMap<>();
    
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        if (equations == null || values == null || queries == null || equations.size() == 0 || values.length == 0 || queries.size() == 0) return new double[]{};
        
        // build the graph for dfs
        for (int i = 0; i < equations.size(); i++) {
            String j = equations.get(i).get(0), k = equations.get(i).get(1);
            
            if (!map.containsKey(j)) map.put(j, new HashMap<>());
            if (!map.containsKey(k)) map.put(k, new HashMap<>());
            
            map.get(j).put(k, values[i]);
            map.get(k).put(j, 1 / values[i]);
        }
        
        double[] res = new double[queries.size()];
        
        // Appply dfs to query each query.
        for (int i = 0; i < queries.size(); i++)
            res[i] = dfs(queries.get(i).get(0), queries.get(i).get(1), new HashSet<>());
        
        return res;
    }
    
    public double dfs(String i, String j, Set<String> visited) {
        if (!map.containsKey(i)) return -1.0;
        if (map.get(i).containsKey(j)) return map.get(i).get(j);
        
        visited.add(i);
        for (String neighbor : map.get(i).keySet()) {
            if (!visited.contains(neighbor)) {
                double temp = dfs(neighbor, j, visited);
                if (temp != -1.0) return temp * map.get(i).get(neighbor);
                temp *= map.get(i).get(neighbor);
            }
        }
        
        return -1.0;
    }
    
}
