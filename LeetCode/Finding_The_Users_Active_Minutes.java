/* Noah Park

You are given the logs for users' actions on LeetCode, and an integer k. The logs are represented by a 2D integer array logs where each logs[i] = [IDi, timei] indicates that the user with IDi performed an action at the minute timei.

Multiple users can perform actions simultaneously, and a single user can perform multiple actions in the same minute.

The user active minutes (UAM) for a given user is defined as the number of unique minutes in which the user performed an action on LeetCode. A minute can only be counted once, even if multiple actions occur during it.

You are to calculate a 1-indexed array answer of size k such that, for each j (1 <= j <= k), answer[j] is the number of users whose UAM equals j.

Return the array answer as described above.

*/

class Solution {
    
    // Intuition: map each user to all of their actions. Since we are using a set, there can only be unique times. We can go over this map and get the size - 1 of the set which will be the UAM.
    // Time: O(n) to iterate over the logs then the map.
    // Space: O(n) to store all of the logs.
    Map<Integer, Set<Integer>> map = new HashMap<>();
    
    public int[] findingUsersActiveMinutes(int[][] logs, int k) {
        for (int[] log : logs) {
            int user = log[0], time = log[1];
            
            if (!map.containsKey(user)) map.put(user, new HashSet<>());
            
            map.get(user).add(time);
        }
        
        int[] res = new int[k];
        
        for (Integer key : map.keySet()) 
            res[map.get(key).size() - 1]++;
        
        return res;
    }
}
