/* Noah Park

There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.

*/

class Solution {
    
    // Intuition: Typical topological sort to order the courses.
    // Time: O(V + E) to iterate over the edges and create the graph then traverse the vertices.
    // Space: O(V + E) for the graph.
    public int[] findOrder(int n, int[][] pre) {
        if (pre == null) return new int[]{};
        
        List<Integer>[] map = new ArrayList[n];
        int[] inDegree = new int[n];
        
        for (int i = 0; i < n; i++)
            map[i] = new ArrayList<>();
        
        for (int[] c : pre) {
            map[c[1]].add(c[0]);
            inDegree[c[0]]++;
        }
        
        Deque<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++)
            if (inDegree[i] == 0) q.addLast(i);
        
        int courses = 0, i = 0;
        int[] res = new int[n];
        
        while (!q.isEmpty()) {
            int course = q.removeFirst();
            courses++;
            res[i++] = course;
            if (i == n) break;
            
            List<Integer> next = map[course];
            for (Integer nei : next) {
                if (inDegree[nei]-- == 1) q.addLast(nei);
            }
        }
        
        return courses == n ? res : new int[]{};
    }
}
