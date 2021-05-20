/* Noah Park

You are given an integer n which indicates that we have n courses, labeled from 1 to n. You are also given an array relations where relations[i] = [a, b], representing a prerequisite relationship between course a and course b: course a has to be studied before course b.

In one semester, you can study any number of courses as long as you have studied all the prerequisites for the course you are studying.

Return the minimum number of semesters needed to study all courses. If there is no way to study all the courses, return -1.

*/

class Solution {
    
    // Intuition: Topological sort taking prerequisites (dependencies) before moving on to ones that require them. First step involves building the graph (used integer arrays since n is bound to us). Second step involves offering all source nodes (non dependent) to a queue for the traversal. Finally we execute the algorithm visiting all nodes through n while maintaining the number of semesters and courses taken throughout. Each level increases a semester since we can potentially take numerous courses at the same time. Each removal of a course increases the total courses taken. To keep track of if we were able to take all courses OR if there was a cyclic dependency, the coursesTaken must match n at the time of returning to return the number of semesters we took. Otherwise, it wasn't possible and we return -1.
    // Time: O(V + E) to iterate over E courses, build the graph, etc.
    // Space: O(V + E) to maintain the graph. V vertices (keys), E edges (values).
    public int minimumSemesters(int n, int[][] relations) {
        int[] inDegree = new int[n + 1];
        List<Integer>[] graph = new ArrayList[n + 1];
        
        // build the graph
        for (int[] relation : relations) {
            if (graph[relation[0]] == null) graph[relation[0]] = new ArrayList<>();
            
            graph[relation[0]].add(relation[1]);
            inDegree[relation[1]]++;
        }
        
        // offer up the source nodes
        Deque<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++)
            if (inDegree[i] == 0) q.addLast(i);
        
        // take the courses and count semesters
        int semesters = 0, coursesTaken = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            
            for (int i = 0; i < size; i++) {
                int cur = q.removeFirst();
                coursesTaken++;
                
                if (graph[cur] != null) {
                    for (int child : graph[cur]) {
                        inDegree[child]--;
                        if (inDegree[child] == 0) q.addLast(child);
                    }
                }
            }
            
            semesters++;
        }
        
        return coursesTaken < n ? -1 : semesters;
    }
}
