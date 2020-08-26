import java.util.ArrayList;
import java.util.List;

// Noah Park - Following the example of KnowledgeCenterYouTube

/*

Problem: Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cells, where "adjacent" cells
are those horizontally or vertically neighboring. The same letter cell may not be used more than once
in a word.

*/

public class Word_Search_II {
    class Trie{

        public Trie[]children;
        public boolean end;

        // Basic initialization of the trie class variables
        public Trie(){
            end = false;
            children = new Trie[26];
            for(int i = 0; i < 26; i++){
                children[i] = null;
            }
        }

        public void insert(String word){
            Trie cur = this; // Initialize the trie from 'this'
            for(int i = 0; i < word.length(); i++){ // Iterate through the characters in the word
                char c = word.charAt(i);
                if(cur.children[c - 'a'] == null){ // Normalize the position by subtracting 'a'
                    cur.children[c - 'a'] = new Trie(); // Create a new trie there since it corresponds to the letter's index
                }
                cur = cur.children[c - 'a']; // Go to the next letter's index. If it was null, we created a new trie, otherwise we continue down the line since we have another word that has the same prefix
            }
            cur.end = true; // last letter will have its end as true
        }
    }

    class Solution {
        public void dfs(char[][] board, int i, int j, ArrayList<String> ans, String s, Trie trie){

            char c = board[i][j]; // Get our current character
            if(c == '-') return;  // Stop if we reach an already visited character
            board[i][j] = '-';    // "visit" the character by marking it

            Trie t = trie.children[c - 'a']; // Go the corresponding child from our character by normalizing its value into a 26 sized array

            if(t != null){ // If t is not null, the letter matches and is in the trie

                String word = s + c; // Build our new word
                if(t.end && !ans.contains(word)) ans.add(word); // If we haven't already found the word and reached the 'end' of a word in the trie, add it to our ans array list

                // call dfs on the four cardinal directions we can go in assuming there is a valid cell there
                if(i < board.length - 1) dfs(board, i + 1, j, ans, word, t);
                if(i > 0) dfs(board, i - 1, j, ans, word, t);
                if(j < board[0].length - 1) dfs(board, i, j + 1, ans, word, t);
                if(j > 0) dfs(board, i, j - 1, ans, word, t);

            }

            board[i][j] = c; // Reset the character for future dfs calls starting from different cells

        }

        public List<String> findWords(char[][] board, String[] words) {
            if(words.length == 0){
                return new ArrayList<String>();
            }
            Trie trie = new Trie(); // Populate the trie with all of the words in 'words'
            for(String s : words){
                trie.insert(s);
            }

            ArrayList<String> ans = new ArrayList<>(); // Return list

            for(int i = 0; i < board.length; i++){ // Iterate through each cell in the matrix and call a dfs on each one to check if we can find words in the trie
                for(int j = 0; j < board[0].length; j++){
                    dfs(board, i, j, ans, "", trie);
                }
            }

            // Return the populated array list
            return new ArrayList<>(ans);
        }
    }
}
