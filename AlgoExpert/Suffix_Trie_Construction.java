// Noah Park
/*

Problem: Write a SuffixTrie class for a Suffix-Trie-like data structure. The class should have a root
property set to be the root node of the trie and should support:

    - Creating the trie from a string; this will be done by calling the populateSuffixTrieFrom method
    upon class instantiation, which should populate the root of the class.

    - Searching for strings in the trie.

Note that every string added to the trie should end with the special endSymbol character "*".

*/

import java.util.HashMap;
import java.util.Map;

public class Suffix_Trie_Construction {
    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    }

    static class SuffixTrie {
        TrieNode root = new TrieNode();
        char endSymbol = '*';

        public SuffixTrie(String str) {
            populateSuffixTrieFrom(str);
        }

        // Call helper function for each character in the string
        public void populateSuffixTrieFrom(String str) {
            for(int i = 0; i < str.length(); i++){
                helper(i, str);
            }
        }

        // Helper method to build the suffix trie
        public void helper(int i, String str){
            // Start t at the root and build downwards
            TrieNode t = root;
            // Iterate through the characters from i through the end of the string
            for(int j = i; j < str.length(); j++){
                // Get the current character
                /*
                If the current trie node does not contain the character c then we must create
                a new trie node and put it in the children map with the character c
                After this, we just move t to the child mapped to that character
                */
                char c = str.charAt(j);
                if(!t.children.containsKey(c)){
                    TrieNode n = new TrieNode();
                    t.children.put(c, n);
                }
                t = t.children.get(c);
            }
            // Add the end symbol into the children when we finish going through all characters
            t.children.put(endSymbol, null);
        }

        // Check if the trie contains a specific word 'str'
        public boolean contains(String str) {
            // Start from the root
            TrieNode t = root;
            // Iterate through the entire string
            for(int i = 0; i < str.length(); i++){
                // Get the current character
                /*
                If the character is not in the children map, clearly we don't contain the entire
                word and must return false.
                Then we must move t to the trie node mapped to the character c
                */
                char c = str.charAt(i);
                if(!t.children.containsKey(c)) return false;
                t = t.children.get(c);
            }
            // If the end symbol is contained in the children map, we can return true, otherwise false
            return t.children.containsKey(endSymbol);
        }
    }
}
