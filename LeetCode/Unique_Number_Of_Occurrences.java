/* Noah Park

Given an array of integers arr, write a function that returns true if and only if the number of occurrences of each value in the array is unique.

*/

class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> freq = new HashMap<>();
        for(int i : arr)
            freq.put(i, freq.getOrDefault(i, 0) + 1);
        
        Set<Integer> un = new HashSet<>();
        for(int i : freq.keySet()) {
            if(un.contains(freq.get(i))) return false;
            un.add(freq.get(i));
        }
        
        
        return true;
    }
}
