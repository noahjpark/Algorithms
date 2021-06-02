/* Noah Park

The Leetcode file system keeps a log each time some user performs a change folder operation.

The operations are described below:

"../" : Move to the parent folder of the current folder. (If you are already in the main folder, remain in the same folder).
"./" : Remain in the same folder.
"x/" : Move to the child folder named x (This folder is guaranteed to always exist).
You are given a list of strings logs where logs[i] is the operation performed by the user at the ith step.

The file system starts in the main folder, then the operations in logs are performed.

Return the minimum number of operations needed to go back to the main folder after the change folder operations.

*/

class Solution {
    
    // Intuition: If we stay on the same directory, we can skip this log. If we are going back, we subtract the directory we are on, only if we can go back (i.e. are deeper than main). Otherwise, we are going into a new directory.
    // Time: O(n) to iterate over logs.
    // Space: O(1) constant.
    public int minOperations(String[] logs) {
        if (logs == null || logs.length == 0) return 0;
        
        int res = 0;
        
        for (String log : logs) {
            if (log.equals("./")) continue;
            if (log.equals("../")) {
                if (res > 0) res--;
            }
            else res++;
        }
        
        return res;
    }
}
