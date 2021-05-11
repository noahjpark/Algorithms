/* Noah Park

Given an m x n board of characters and a list of strings words, return all words on the board.

Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

*/

// TrieNode class with insert method
class TrieNode {
    
    String word;
    TrieNode[] children;
    
    public TrieNode() {
        word = null;
        children = new TrieNode[26];
    }
    
    public void insert(String word) {
        TrieNode cur = this;
        
        for (char c : word.toCharArray()) {
            if (cur.children[c - 'a'] == null) cur.children[c - 'a'] = new TrieNode();
            cur = cur.children[c - 'a'];
        }
        
        cur.word = word;
    }
    
}

class Solution {

    // Intuition: Utilize a set to prevent duplicates. Build the trie before attempting to do a dfs on each position to attempt to find all words. In the dfs, we ensure the bounds are all safe, it is a valid character, and that the next trienode contains the character before going. 
    // Time: O(m*n*(4*3^(L-1))) m*n for the grid, 4*3^(L-1) for the dfs.
    // Space: O(n) for the words in the words array.
    int maxSize = 0, maxLength = 0;
    Set<String> set = new HashSet<>();
    int[] rows = new int[]{ -1, 0, 1, 0 }, cols = new int[]{ 0, -1, 0, 1 };
    
    public List<String> findWords(char[][] board, String[] words) {
        if (board == null || board.length == 0 || words == null || words.length == 0) return new ArrayList<>();
        
        TrieNode root = new TrieNode();
        int n = board.length, m = board[0].length;
        maxSize = words.length;
        
        for (String word : words) {
            root.insert(word);
            maxLength = Math.max(maxLength, word.length());
        }
        
        for (int i = 0; i < n; i++) 
            for (int j = 0; j < m; j++) 
                if (root.children[board[i][j] - 'a'] != null) dfs(board, i, j, root.children[board[i][j] - 'a']);
        
        return new ArrayList<>(set);
    }
    
    public void dfs(char[][] board, int i, int j, TrieNode node) {
        if (set.size() == maxSize) return;
        
        char ch = board[i][j];
        board[i][j] = '*';
        
        if (node.word != null) set.add(node.word);
        
        for (int idx = 0; idx < 4; idx++) {
            int r = i + rows[idx], c = j + cols[idx];
            if (r >= 0 && c >= 0 && r < board.length && c < board[0].length && board[r][c] != '*' && node.children[board[r][c] - 'a'] != null) 
                dfs(board, r, c, node.children[board[r][c] - 'a']);
        }
        
        board[i][j] = ch;
    }
    
}
