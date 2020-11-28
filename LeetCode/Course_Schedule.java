/* Noah Park

There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

 

*/

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int courses = 0; // number of courses
        Map<Integer, Integer> inDegree = new HashMap<>(); // number of incoming edges
        Map<Integer, List<Integer>> graph = new HashMap<>(); // children adjacency list
        
        // Initialize graph
        for(int i = 0; i < numCourses; i++){
            inDegree.put(i, 0);
            graph.put(i, new ArrayList<>());
        }
        
        // Populate graph
        for(int[] pre : prerequisites){
            int parent = pre[0], child = pre[1];
            inDegree.put(child, inDegree.get(child) + 1);
            graph.get(parent).add(child);
        }
        
        // Find all sources
        Queue<Integer> sources = new LinkedList<>();
        for(Map.Entry<Integer, Integer> entry : inDegree.entrySet())
            if(entry.getValue() == 0) sources.offer(entry.getKey());
        
        // Go through and traverse the graph
        while(!sources.isEmpty()){
            int cur = sources.poll();
            courses++;
            List<Integer> children = graph.get(cur);
            for(int child : children){
                inDegree.put(child, inDegree.get(child) - 1);
                if(inDegree.get(child) == 0) sources.offer(child);
            }
        }
        
        return courses == numCourses;
    }
}
