/* Noah Park

Implement the MapSum class:

MapSum() Initializes the MapSum object.
void insert(String key, int val) Inserts the key-val pair into the map. If the key already existed, the original key-value pair will be overridden to the new one.
int sum(string prefix) Returns the sum of all the pairs' value whose key starts with the prefix.

*/

// Intuition: TrieNode wrapper class for the Trie implementation.
class TrieNode {
    
    int val;
    TrieNode[] children;
    
    public TrieNode() {
        val = 0;
        children = new TrieNode[26];
    }
    
}

class MapSum {

    // Intuition: Utilize a trie to maintain the prefixes of each word. Instead of needing an ending point, we can just maintain a value with each word, if we would have been at the end, we can update the value to val otherwise leave it as 0.
    // Time: O(n) for insert, O(n) for sum where n is the length of the longest word in the trie.
    // Space: O(1) for insert, O(n) for sum since the recursion could grow to the length of the longest word in the trie.
    TrieNode root;
    int sum = 0;
    
    public MapSum() {
        root = new TrieNode();
    }
    
    public void insert(String key, int val) {
        TrieNode cur = root;
        
        for (char c : key.toCharArray()) {
            if (cur.children[c - 'a'] == null) cur.children[c - 'a'] = new TrieNode();
            cur = cur.children[c - 'a'];
        }
        
        cur.val = val;
    }
    
    public int sum(String prefix) {
        sum = 0;
        TrieNode cur = root;
        
        for (char c : prefix.toCharArray()) {
            if (cur.children[c - 'a'] == null) return 0;
            cur = cur.children[c - 'a'];
        }
        
        sum(cur);
        return sum;
    }
    
    public void sum(TrieNode cur) {
        sum += cur.val;
        
        for (int i = 0; i < 26; i++) 
            if (cur.children[i] != null) sum(cur.children[i]);
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */
