/* Noah Park

Design a search autocomplete system for a search engine. Users may input a sentence (at least one word and end with a special character '#').

You are given a string array sentences and an integer array times both of length n where sentences[i] is a previously typed sentence and times[i] is the corresponding number of times the sentence was typed. For each input character except '#', return the top 3 historical hot sentences that have the same prefix as the part of the sentence already typed.

Here are the specific rules:

The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). If several sentences have the same hot degree, use ASCII-code order (smaller one appears first).
If less than 3 hot sentences exist, return as many as you can.
When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.
Implement the AutocompleteSystem class:

AutocompleteSystem(String[] sentences, int[] times) Initializes the object with the sentences and times arrays.
List<String> input(char c) This indicates that the user typed the character c.
Returns an empty array [] if c == '#' and stores the inputted sentence in the system.
Returns the top 3 historical hot sentences that have the same prefix as the part of the sentence already typed. If there are fewer than 3 matches, return them all.

*/

// TrieNode class representing a Trie
class TrieNode {
    boolean end;
    int times;
    Map<Character, TrieNode> children = new HashMap<>();
    
    public TrieNode() {
        end = false;
        times = 0;
        children = new HashMap<>();
    }
}

// Wrapper class to maintain a string with the number of times it has appeared
class Element {
    String s;
    int freq;
    
    public Element(String s, int freq) {
        this.s = s;
        this.freq = freq;
    }
}

class AutocompleteSystem {
    
    // Intuition: Maintain the root of the trie and the currently built new string to potentially add when a user enters a '#'. Insert is typical, however there is the addition of a frequency value that is incremented by the number of times we have seen a sentence. For the input method, we maintain an ordered priority queue based on the given ordering, then we start our current string with the system string. From here, we get a traversal pointer to the trie node in which the last character of the system string is at. If we can't get that far, we return an empty list. Otherwise, we utilize a dfs find with backtracking to build the different sentence possibilities and add them to the priority queue whenever we reach the end of the sentence denoted by cur.end.
    // Time: Insert -> O(n) to iterate over the word, input -> O(m log m) for the priority queue.
    // Space: O(n) to maintain all sentence possibilities.
    TrieNode root;
    StringBuilder system;

    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        system = new StringBuilder();
        
        for (int i = 0; i < sentences.length; i++)
            insert(sentences[i], times[i]);
    }
    
    public List<String> input(char c) {
        if (c == '#') {
            insert(system.toString(), 1);
            system = new StringBuilder();
            return new ArrayList<>();
        }
        
        List<String> res = new ArrayList<>();
        PriorityQueue<Element> pq = new PriorityQueue<>((a, b) -> (a.freq == b.freq ? a.s.compareTo(b.s) : b.freq - a.freq));
        TrieNode cur = root;
        system.append(c);
        StringBuilder s = new StringBuilder();
        s.append(system.toString());
        
        for (int i = 0; i < system.length() - 1; i++) {
            if (!cur.children.containsKey(system.charAt(i))) return res;
            cur = cur.children.get(system.charAt(i));
        }
        
        if (cur.children.containsKey(system.charAt(system.length() - 1))) 
            find(pq, s, cur.children.get(system.charAt(system.length() - 1)));
        
        
        for (int i = 0; i < 3 && !pq.isEmpty(); i++) 
            res.add(pq.poll().s);
        
        return res;
    }
    
    public void find(PriorityQueue<Element> pq, StringBuilder s, TrieNode cur) { 
        if (cur.end) pq.offer(new Element(s.toString(), cur.times));
        
        for (Character key : cur.children.keySet()) {
            s.append(key);
            find(pq, s, cur.children.get(key));
            s.deleteCharAt(s.length() - 1);
        }
    }
    
    public void insert(String sentence, int freq) {
        TrieNode cur = root;
        
        for (char c : sentence.toCharArray()) {
            if (!cur.children.containsKey(c)) cur.children.put(c, new TrieNode());
            cur = cur.children.get(c);
        }
        
        cur.end = true;
        cur.times += freq;
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
