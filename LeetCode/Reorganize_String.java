/* Noah Park

Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.

If possible, output any possible result.  If not possible, return the empty string.

*/

class Element { // custom class for efficiency over the hash map
    char letter;
    int val;
    
    public Element(char l, int v) {
        letter = l;
        val = v;
    }
}

class Solution {
    public String reorganizeString(String S) {
        int[] map = new int[26]; // counting sort
        
        for (char c : S.toCharArray()) map[c - 'a']++;
        
        PriorityQueue<Element> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b.val, a.val)); // priority queue for character choice
        
        for (int i = 0; i < 26; i++) {
            if (map[i] > (S.length() + 1) / 2) return ""; // optimization if there are too many of one character we don't need to try to build a string
            if (map[i] > 0) maxHeap.offer(new Element((char) (i + 'a'), map[i]));
        }
        
        StringBuilder res = new StringBuilder();
        
        while (!maxHeap.isEmpty()) { // always try to choose the character with the largest frequency
            char current = res.length() > 0 ? res.charAt(res.length() - 1) : '*';
            Element e = maxHeap.poll();
            
            if (current != e.letter) {
                res.append(e.letter);
                e.val--;
            } else { // if the character with the largest frequency still has the largest, we need to choose a different one
                if (maxHeap.isEmpty()) return "";
                
                Element entryTwo = maxHeap.poll();
                res.append(entryTwo.letter);
                entryTwo.val--;
                if (entryTwo.val > 0) maxHeap.offer(entryTwo);
            }
            
            if (e.val > 0) maxHeap.offer(e); // add back if frequency still exists
        }
        
        return res.toString();
    }
    
    // same idea as above but using a hash map
    public String reorganizeStringMap(String S) {
        Map<Character, Integer> map = new HashMap<>();
        
        for (char c : S.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
            if (map.get(c) > (S.length() + 1) / 2) return "";
        }
        
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b.getValue(), a.getValue()));
        
        maxHeap.addAll(map.entrySet());
        
        StringBuilder res = new StringBuilder();
        
        while (!maxHeap.isEmpty()) {
            char current = res.length() > 0 ? res.charAt(res.length() - 1) : '*';
            Map.Entry<Character, Integer> entry = maxHeap.poll();
            
            if (current != entry.getKey()) {
                res.append(entry.getKey());
                entry.setValue(entry.getValue() - 1);
            } else {
                if (maxHeap.isEmpty()) return "";
                
                Map.Entry<Character, Integer> secondEntry = maxHeap.poll();
                res.append(secondEntry.getKey());
                secondEntry.setValue(secondEntry.getValue() - 1);
                if (secondEntry.getValue() > 0) maxHeap.offer(secondEntry);
            }
            
            if (entry.getValue() > 0) maxHeap.offer(entry);
        }
        
        return res.toString();
    }
}
