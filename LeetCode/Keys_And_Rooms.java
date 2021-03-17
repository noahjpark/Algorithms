/* Noah Park

There are N rooms and you start in room 0.  Each room has a distinct number in 0, 1, 2, ..., N-1, and each room may have some keys to access the next room. 

Formally, each room i has a list of keys rooms[i], and each key rooms[i][j] is an integer in [0, 1, ..., N-1] where N = rooms.length.  A key rooms[i][j] = v opens the room with number v.

Initially, all the rooms start locked (except for room 0). 

You can walk back and forth between rooms freely.

Return true if and only if you can enter every room.

*/

class Solution {
    // Time: O(r + e) where r is the number of rooms and e is the number of keys | Space: O(n) for the queue
    public boolean canVisitAllRoomsa(List<List<Integer>> rooms) {
        if (rooms == null || rooms.size() == 0) return true;
        
        int n = rooms.size(), unlocked = 0;
        boolean[] inDegree = new boolean[n];
        Queue<Integer> keys = new LinkedList<>();
        
        // store indegree and offer the first room
        inDegree[0] = true;
        keys.offer(0);
        
        while (!keys.isEmpty()) {
            int key = keys.poll();
            
            unlocked++; // unlock the room

            for (Integer k : rooms.get(key)) {
                if (!inDegree[k]) { // if we find a new room, mark it so we don't have duplicates and add it to the queue
                    inDegree[k] = true;
                    keys.offer(k);
                }
            }
        }
        
        return unlocked == n;
    }
}
