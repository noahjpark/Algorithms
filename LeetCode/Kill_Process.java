/* Noah Park

You have n processes forming a rooted tree structure. You are given two integer arrays pid and ppid, where pid[i] is the ID of the ith process and ppid[i] is the ID of the ith process's parent process.

Each process has only one parent process but may have multiple children processes. Only one process has ppid[i] = 0, which means this process has no parent process (the root of the tree).

When a process is killed, all of its children processes will also be killed.

Given an integer kill representing the ID of a process you want to kill, return a list of the IDs of the processes that will be killed. You may return the answer in any order.

*/

class Solution {
    
    // Intuition: Map all parent to child processes. Then use a queue (bfs approach) to add all reachable child processes into a returning list
    // Time: O(n) to iterate through the processes then a pass for the queue
    // Space: O(n) for the map and queue
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        for (int i = 0; i < ppid.size(); i++) {
            if (!map.containsKey(ppid.get(i))) map.put(ppid.get(i), new ArrayList<>());
            map.get(ppid.get(i)).add(pid.get(i));
        }
        
        if (map.get(0).get(0) == kill) return pid;
        
        Queue<Integer> q = new LinkedList<>();
        q.offer(kill);
        List<Integer> res = new ArrayList<>();
        
        while (!q.isEmpty()) {
            int cur = q.poll();
            res.add(cur);
            
            List<Integer> children = map.getOrDefault(cur, new ArrayList<>());
            q.addAll(children);
        }
        
        return res;
    }
}
