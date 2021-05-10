/* Noah Park

In English, we have a concept called root, which can be followed by some other word to form another longer word - let's call this word successor. For example, when the root "an" is followed by the successor word "other", we can form a new word "another".

Given a dictionary consisting of many roots and a sentence consisting of words separated by spaces, replace all the successors in the sentence with the root forming it. If a successor can be replaced by more than one root, replace it with the root that has the shortest length.

Return the sentence after the replacement.

*/

class Solution {
    
    // Intuition: Utilize a trie to maintain the roots from the dictionary. Then just go through each word in the sentence to test if a root exists in the trie.
    // Time: O(n*m) where n is the number of words and m is the number of letters.
    // Space: O(n*m) where n is the number of words in our dictionary and m is the number of letters in each word.
    public String replaceWords(List<String> dictionary, String sentence) {
        TrieNode root = new TrieNode();
        
        // build the trie
        for (String word : dictionary) 
            insert(word, root);
        
        StringBuilder res = new StringBuilder();
        String[] split = sentence.split(" ");
        
        // find roots
        for (String word : split) 
            res.append(find(word, root) + " ");
        
        return res.toString().trim();
    }
    
    // Typical insert into a trie.
    public void insert(String word, TrieNode root) {
        if (word == null || word.length() == 0) return;
        
        TrieNode cur = root;
        
        for (char c : word.toCharArray()) {
            if (cur.children[c - 'a'] == null) cur.children[c - 'a'] = new TrieNode();
            cur = cur.children[c - 'a'];
        }
        
        cur.end = true;
    }
    
    // Looks to find the first occurrence of a root of the word 'word', otherwise returns the word to be added to the resulting string.
    public String find(String word, TrieNode root) {
        if (word == null || word.length() == 0) return word;
        
        TrieNode cur = root;
        StringBuilder res = new StringBuilder();
        
        for (char c : word.toCharArray()) {
            if (cur.end) return res.toString();
            else if (cur.children[c - 'a'] == null) return word;
            
            res.append(c);
            cur = cur.children[c - 'a'];
        }
        
        return res.toString();
    }
}

class TrieNode {
    
    boolean end;
    TrieNode[] children;
    
    public TrieNode() {
        end = false;
        children = new TrieNode[26];
    }
    
}
