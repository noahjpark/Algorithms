/* Noah Park

There is a new alien language that uses the English alphabet. However, the order among the letters is unknown to you.

You are given a list of strings words from the alien language's dictionary, where the strings in words are sorted lexicographically by the rules of this new language.

Return a string of the unique letters in the new alien language sorted in lexicographically increasing order by the new language's rules. If there is no solution, return "". If there are multiple solutions, return any of them.

A string s is lexicographically smaller than a string t if at the first letter where they differ, the letter in s comes before the letter in t in the alien language. If the first min(s.length, t.length) letters are the same, then s is smaller if and only if s.length < t.length.

*/

class Solution {
    
    // Intuition: Apply a topological sort bfs to the letters in the words.
    // Time: O(n*m) where n is the number of words and m is the number of characters in each word.
    // Space: O(1) since there are only 26 lowercase letters.
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) return "";
        
        // Initialize the graph
        List<Character>[] map = new ArrayList[26];
        for (int i = 0; i < 26; i++) map[i] = new ArrayList<>();
        Map<Character, Integer> inDegree = new HashMap<>(); // initialize with all unique valid characters in all words
        for (String word : words)
            for (char c : word.toCharArray()) inDegree.put(c, 0);
        int n = words.length;
        
        // Populate the graph with every adjacent string
        for (int i = 0; i < n - 1; i++) {
            String s1 = words[i], s2 = words[i + 1];
            int n1 = s1.length(), n2 = s2.length();
            if (n1 > n2 && s1.startsWith(s2)) return "";

            for (int k = 0; k < Math.min(n1, n2); k++) { 
                char c1 = s1.charAt(k), c2 = s2.charAt(k);
                if (c1 != c2) { 
                    map[c1 - 'a'].add(c2); 
                    inDegree.put(c2, inDegree.get(c2) + 1);
                    break; // we only need to find the first non-matching corresponding character between the two words
                }
            }
        }
        
        // for (int i = 0; i < 26; i++) {
        //     System.out.print((char) (i + 'a') + " -> " + map[i].toString());
        //     System.out.println(" " + inDegree[i]);
        // }
        
        // Start bfs
        Deque<Character> q = new LinkedList<>();
        for (char c : inDegree.keySet())
            if (inDegree.get(c) == 0) q.addLast(c);
        
        // Apply the bfs
        StringBuilder res = new StringBuilder();
        while (!q.isEmpty()) {
            int size = q.size();
            List<Character> next = new ArrayList<>();
            
            for (int i = 0; i < size; i++) {
                char c = q.removeFirst();
                res.append(c);
                
                for (char child : map[c - 'a']) {
                    inDegree.put(child, inDegree.get(child) - 1);
                    if (inDegree.get(child) == 0) next.add(child);
                }
            }
            
            q.addAll(next);
        }
        
        //System.out.println(inDegree.size());
        
        // return the letters if all letters were added to the string.
        return res.length() == inDegree.size() ? res.toString() : "";
    }
}
