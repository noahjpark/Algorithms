/* Noah Park

Design a data structure that supports adding new words and finding if a string matches any previously added string.

Implement the WordDictionary class:

WordDictionary() Initializes the object.
void addWord(word) Adds word to the data structure, it can be matched later.
bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.

*/

// trie/prefix tree to store the word in more efficiency
class TrieNode {

    char val;
	TrieNode[] children;
	boolean end;

    public TrieNode(char c){
		end = false;
        val = c;
		children = new TrieNode[26];
    }

    // search for a word in the trie
	public boolean search(String word, int idx) {
		TrieNode cur = this;
		for (int j = idx; j < word.length(); j++) { // loop through the word
			char c = word.charAt(j);
			if (c == '.') { // if there is a wildcard
				boolean check = false;

				for (int i = 0; i < 26; i++) // check all children and return true if any of them end up returning true
					if(cur.children[i] != null) check |= cur.children[i].search(word, j + 1);
				
				return check;
			} 
			else if (cur.children[c - 'a'] == null && c != '.') return false; // if we have a regular character and no more letters, we have a mismatch
			else if (cur.children[c - 'a'] != null) cur = cur.children[c - 'a']; // otherwise, we can proceed forward in the trie
		}
		
		return cur.end;
	}
}

class WordDictionary {
    
    TrieNode root;

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode('*');
    }
    
    // add a word to the trie
    public void addWord(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            if (cur.children[c - 'a'] == null) cur.children[c - 'a'] = new TrieNode(c);
            cur = cur.children[c - 'a'];
        }
        cur.end = true;
    }
    
    public boolean search(String word) {
        return root.search(word, 0);
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
