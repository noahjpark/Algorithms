/* Noah Park

Given a non-empty list of words, return the k most frequent elements.

Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.

*/

class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        if (words == null || words.length == 0) return new ArrayList<>(); // edge cases
        
        // custom comparator sorts based on directions: most frequent first then same frequencies are ordered lexicographically in ascending order
        Comparator<Map.Entry<String, Integer>> c = new Comparator<>() {
            public int compare(Map.Entry<String, Integer> m1, Map.Entry<String, Integer> m2) {
                if (m1.getValue() == m2.getValue()) return m1.getKey().compareTo(m2.getKey());
                
                return Integer.compare(m2.getValue(), m1.getValue());
            }
        };
        
        List<String> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>(); // frequency map
        PriorityQueue<Map.Entry<String, Integer>> maxHeap = new PriorityQueue<>(c); // maxHeap for the words
        
        for (String word : words) map.put(word, map.getOrDefault(word, 0) + 1); 
        
        maxHeap.addAll(map.entrySet());
        
        int count = 0;
    
        // add k words
        while (!maxHeap.isEmpty() && count < k) {
            count++;
            res.add(maxHeap.poll().getKey());
        }
        
        return res;
    }
}
