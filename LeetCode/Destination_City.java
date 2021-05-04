/* Noah Park

You are given the array paths, where paths[i] = [cityAi, cityBi] means there exists a direct path going from cityAi to cityBi. Return the destination city, that is, the city without any path outgoing to another city.

It is guaranteed that the graph of paths forms a line without any loop, therefore, there will be exactly one destination city.

*/

class Solution {
    
    // Intuition: Store each outgoing city in a set. The missing city is the destination.
    // Time: O(n) two pass over the paths.
    // Space: O(n) to store each city.
    public String destCity(List<List<String>> paths) {
        Set<String> cities = new HashSet<>();
        
        for (List<String> path : paths) 
            cities.add(path.get(0));
        
        for (List<String> path : paths)
            if (!cities.contains(path.get(1))) return path.get(1);
        
        return null;
    }
    
    // Intuition: Map each city to its outgoing edges. The one without an outgoing edge is the destination.
    // Time: O(n) two pass over the paths.
    // Space: O(n) to map all cities.
    public String destCity2(List<List<String>> paths) {
        Map<String, Integer> map = new HashMap<>();
        
        for (List<String> path : paths) {
            map.put(path.get(0), map.getOrDefault(path.get(0), 0) + 1);
            if (!map.containsKey(path.get(1))) map.put(path.get(1), 0);
        }
        
        for (String key : map.keySet())
            if (map.get(key) == 0) return key;
        
        return null;
    }
}
