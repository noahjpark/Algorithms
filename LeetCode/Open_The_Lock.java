/* Noah Park

You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.

The lock initially starts at '0000', a string representing the state of the 4 wheels.

You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning and you will be unable to open it.

Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to open the lock, or -1 if it is impossible.

*/

class Solution {
    
    // Intuition: bfs approach from the start (0000) moving each number in both the upwards and downwards directions. Maintain a set of bad values (deadends and visited places) so we don't infinitely loop.
    // Time: O(a^n*n^2 + d) d is to create the set, a^n is the possible combinations, n^2 is because we iterate over n dials and recreate them in the moveLock function.
    // Space: O(a^n + v) since there are only a^n possible combinations and v visited places.
    public int openLock(String[] deadends, String target) {
        Set<String> dead = new HashSet<>();
        for (String d : deadends) dead.add(d);
        
        int moves = 0;
        Queue<char[]> q = new ArrayDeque<>();
        q.offer(new char[]{ '0', '0', '0', '0' });
        
        while (!q.isEmpty()) {
            int level = q.size();
            
            for (int i = 0; i < level; i++) {
                char[] cur = q.poll();
                
                if (target.equals(new String(cur))) return moves;
                if (dead.contains(new String(cur))) continue;
                dead.add(new String(cur));
                
                for (int j = 0; j < 4; j++) 
                    q.offer(moveLock(cur, j, true));
                for (int j = 0; j < 4; j++)
                    q.offer(moveLock(cur, j, false));
            }
            
            moves++;
        }
        
        return -1;
    }
    
    public char[] moveLock(char[] cur, int idx, boolean up) {
        char[] res = new char[4];
        for (int i = 0; i < 4; i++)
            if (i != idx) res[i] = cur[i];
        
        char c = cur[idx];
        
        if (up) {
            c = (char) (c + 1);
            if (c > '9') c = '9';
        } else {
            c = (char) (c - 1);
            if (c < '0') c = '9';
        }
        
        res[idx] = c;
        
        return res;
    }
}
