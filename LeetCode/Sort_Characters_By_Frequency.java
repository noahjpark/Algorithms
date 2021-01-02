/* Noah Park

Given a string, sort it in decreasing order based on the frequency of characters.

*/

class Solution {
    
    // utilize bucket sort
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>(); // frequencies mapped from characters
        
        for (char c : s.toCharArray()) map.put(c, map.getOrDefault(c, 0) + 1);
        
        List<Character>[] freq = new List[s.length() + 1]; // store each set of characters from a particular frequency in the same buckets since no ordering is required
        
        for (Character c : map.keySet()) {
            if (freq[map.get(c)] == null) freq[map.get(c)] = new ArrayList<>();
            freq[map.get(c)].add(c);
        }
        
        StringBuilder res = new StringBuilder();
        
        // start from the largest frequencies and populate the resulting string
        for (int i = freq.length - 1; i > -1; i--) {
            if (freq[i] != null) {
                for (Character ch : freq[i]) {
                    for (int j = 0; j < i; j++) res.append(ch);
                }
            }
        }
        
        return res.toString();
    }
    
    public String frequencySortMaxHeap(String s) {
        Map<Character, Integer> map = new HashMap<>(); // frequencies mapped from characters
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b.getValue(), a.getValue())); // max heap for frequencies
        
        for (char c : s.toCharArray()) map.put(c, map.getOrDefault(c, 0) + 1);
        
        maxHeap.addAll(map.entrySet());
        
        StringBuilder res = new StringBuilder();
        
        while (!maxHeap.isEmpty()) {
            Map.Entry<Character, Integer> entry = maxHeap.poll();
            
            for (int i = 0; i < entry.getValue(); i++) res.append(entry.getKey());
        }
        
        return res.toString();
    }
    
    public String frequencySortSubOptimal(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int distinct = 0;
        
        for (char c : s.toCharArray()) {
            if (!map.containsKey(c)) distinct++;
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        StringBuilder res = new StringBuilder();
        
        for (int i = 0; i < distinct; i++) {
            Character max = null;
            
            for (Character c : map.keySet()) {
                if (max == null) max = c;
                else if (map.get(c) > map.get(max)) max = c;
            }
            
            for (int j = 0; j < map.get(max); j++)
                res.append(max);
            
            map.remove(max);
        }
        
        return res.toString();
    }
}
