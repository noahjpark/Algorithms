/* Noah Park

We are given two sentences A and B.  (A sentence is a string of space separated words.  Each word consists only of lowercase letters.)

A word is uncommon if it appears exactly once in one of the sentences, and does not appear in the other sentence.

Return a list of all uncommon words. 

You may return the list in any order.

*/

class Solution {
    
    // Intuition: Map frequency of words in two maps. Iterate over the maps and apply the uncommon idea to store in a return list.
    // Time: O(n + m) where n is the length of A and m is the length of B
    // Space: O(n + m) where n is the length of A and m is the length of B
    public String[] uncommonFromSentences(String A, String B) {
        String[] a = A.split(" "), b = B.split(" ");
        Map<String, Integer> acount = new HashMap<>(), bcount = new HashMap<>();
        
        for (String s : a) acount.put(s, acount.getOrDefault(s, 0) + 1);
        for (String s : b) bcount.put(s, bcount.getOrDefault(s, 0) + 1);
        
        List<String> res = new ArrayList<>();
        
        for (String key : acount.keySet()) 
            if (acount.get(key) == 1 && !bcount.containsKey(key)) res.add(key);
        
        for (String key : bcount.keySet())
            if (bcount.get(key) == 1 && !acount.containsKey(key)) res.add(key);
        
        return res.toArray(new String[res.size()]);
    }
}
