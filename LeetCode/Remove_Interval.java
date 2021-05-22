/* Noah Park

A set of real numbers can be represented as the union of several disjoint intervals, where each interval is in the form [a, b). A real number x is in the set if one of its intervals [a, b) contains x (i.e. a <= x < b).

You are given a sorted list of disjoint intervals intervals representing a set of real numbers as described above, where intervals[i] = [ai, bi] represents the interval [ai, bi). You are also given another interval toBeRemoved.

Return the set of real numbers with the interval toBeRemoved removed from intervals. In other words, return the set of real numbers such that every x in the set is in intervals but not in toBeRemoved. Your answer should be a sorted list of disjoint intervals as described above.

*/

class Solution {
    
    // Intuition: 4 cases -> First is if the interval is compelely removed. Second is if the interval is unaffected. Third is if the interval begins before the removed part and is intersected. Fourth is if the interval is follows after the removed part and is intersected.
    // Time: O(n) to iterate over intervals
    // Space: O(n) for the resulting list.
    public List<List<Integer>> removeInterval(int[][] intervals, int[] r) {
        List<List<Integer>> res = new ArrayList<>();
        
        for (int[] interval : intervals) {
            if (interval[0] >= r[0] && interval[1] <= r[1]) continue; // this interval is completely subsumed.
            else if (interval[1] <= r[0] || interval[0] >= r[1]) res.add(Arrays.asList(interval[0], interval[1])); // these intervals are unaffected
            else if (interval[1] > r[0] && interval[0] < r[0]) {
                res.add(Arrays.asList(interval[0], r[0]));
                if (r[1] < interval[1]) res.add(Arrays.asList(r[1], interval[1]));
            } else if (interval[0] < r[1]) {
                if (interval[0] < r[0]) res.add(Arrays.asList(interval[0], r[0]));
                res.add(Arrays.asList(r[1], interval[1]));
            }
        }
        
        return res;
    }
}
