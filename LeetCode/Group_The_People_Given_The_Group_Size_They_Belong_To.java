/* Noah Park

There are n people that are split into some unknown number of groups. Each person is labeled with a unique ID from 0 to n - 1.

You are given an integer array groupSizes, where groupSizes[i] is the size of the group that person i is in. For example, if groupSizes[1] = 3, then person 1 must be in a group of size 3.

Return a list of groups such that each person i is in a group of size groupSizes[i].

Each person should appear in exactly one group, and every person must be in a group. If there are multiple answers, return any of them. It is guaranteed that there will be at least one valid solution for the given input.

*/

class Solution {
    
    // Optimize first try to use list instead of map.
    public List<List<Integer>> groupThePeople3(int[] groupSizes) {
        List<List<Integer>> res = new ArrayList<>(), build = new ArrayList<>();
        
        for (int i = 0; i < groupSizes.length + 1; i++) build.add(new ArrayList<>());
        
        for (int i = 0; i < groupSizes.length; i++) {
            build.get(groupSizes[i]).add(i);
            if (build.get(groupSizes[i]).size() == groupSizes[i]) { res.add(new ArrayList<>(build.get(groupSizes[i]))); build.get(groupSizes[i]).clear(); }
        }
        
        return res;
    }
    
    // Intuition: Greedy solution using map. Map groupSizes to a list of members in the group. If the list of members reaches the groupSize that maps to it, add it to the result list and remove it from the mapping.
    // Time: O(n) to iterate over groupSizes.
    // Space: O(n) for the resulting list and map.
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        for (int i = 0; i < groupSizes.length; i++) {
            if (!map.containsKey(groupSizes[i])) map.put(groupSizes[i], new ArrayList<>());
            List<Integer> temp = map.get(groupSizes[i]);
            temp.add(i);
            if (temp.size() == groupSizes[i]) { res.add(temp); map.put(groupSizes[i], new ArrayList<>()); }
        }
        
        return res;
    }
}
