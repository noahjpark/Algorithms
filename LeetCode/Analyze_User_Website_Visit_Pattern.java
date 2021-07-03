/* Noah Park

We are given some website visits: the user with name username[i] visited the website website[i] at time timestamp[i].

A 3-sequence is a list of websites of length 3 sorted in ascending order by the time of their visits.  (The websites in a 3-sequence are not necessarily distinct.)

Find the 3-sequence visited by the largest number of users. If there is more than one solution, return the lexicographically smallest such 3-sequence.

*/

class Solution {
    
    // Intuition: Step one is to map all users to their respective website at a particular time. Then we can loop through those mappings and sort each list by the timestamps to then create all combinations of 3-sequence websites the user visited in order of timestamps. One small edge case is to ensure that a user can't add multiple of the same 3-sequence so we need a hashset to avoid this. From there, we look for the smallest string we obtained that has a frequency that matches the maximum which we then convert to a list.
    // Time: O(n + nlogn + n^3) -> O(n^3) bounded by nested loops.
    // Space: O(n) to maintain all of the information through maps, sets, lists, etc.
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        Map<String, List<Pair<String, Integer>>> map = new HashMap<>();
        Map<List<String>, Integer> freq = new HashMap<>();
        int n = username.length, max = 0;
        
        for (int i = 0; i < n; i++) {
            map.putIfAbsent(username[i], new ArrayList<>());
            map.get(username[i]).add(new Pair(website[i], timestamp[i]));
        }
        
        for (String key : map.keySet()) {
            List<Pair<String, Integer>> l = map.get(key);
            if (l.size() < 3) continue;
            Collections.sort(l, (a, b) -> (a.getValue() - b.getValue()));
            Set<List<String>> set = new HashSet<>();
            
            find3Sequence(set, l.size(), l);
            
            for (List<String> ll : set) {
                freq.put(ll, freq.getOrDefault(ll, 0) + 1);
                max = Math.max(max, freq.get(ll));
            }
        }
        
        List<String> res = new ArrayList<>();
        for (List<String> key : freq.keySet())
            if (freq.get(key) == max && (res.size() == 0 || res.toString().compareTo(key.toString()) > 0)) res = key;
        
        return res;
    }
    
    public void find3Sequence(Set<List<String>> set, int m, List<Pair<String, Integer>> l) {
        for (int i = 0; i < m - 2; i++) {
            for (int j = i + 1; j < m - 1; j++) {
                for (int k = j + 1; k < m; k++) {
                    List<String> list = new ArrayList<>();
                    list.add(l.get(i).getKey());
                    list.add(l.get(j).getKey());
                    list.add(l.get(k).getKey());
                    set.add(list);
                }
            }
        } 
    }

}
