/* Noah Park

A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.

Implement the Trie class:

Trie() Initializes the trie object.
void insert(String word) Inserts the string word into the trie.
boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.

*/

// Node wrapper class for the Trie class.
class TrieNode {
    boolean end;
    TrieNode[] children;
    
    public TrieNode() {
        end = false;
        children = new TrieNode[26];
    }
}

class Trie {
    
    // Intuition: Trie contains a root like a tree then traverses from there.
    TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }
    
    // Time: O(n) length of the word.
    // Space: O(n) length of the word.
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode cur = root;
        
        for (char c : word.toCharArray()) {
            if (cur.children[c - 'a'] == null) cur.children[c - 'a'] = new TrieNode();
            cur = cur.children[c - 'a'];
        }
        
        cur.end = true;
    }
    
    // Time: O(n) length of the word.
    // Space: O(1) constant.
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode cur = root;
        
        for (char c : word.toCharArray()) {
            if (cur.children[c - 'a'] == null) return false;
            cur = cur.children[c - 'a'];
        }
        
        return cur.end;
    }
    
    // Time: O(n) length of the word.
    // Space: O(1) constant.
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        
        for (char c : prefix.toCharArray()) {
            if (cur.children[c - 'a'] == null) return false;
            cur = cur.children[c - 'a'];
        }
        
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
