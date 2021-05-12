/* Noah Park

You have n  tiles, where each tile has one letter tiles[i] printed on it.

Return the number of possible non-empty sequences of letters you can make using the letters printed on those tiles.

*/

class Solution {
    
    // Intuition: Inspired by user: 'mo39-fmbh' to utilize counting sort as a means of choosing characters. When a character is chosen, the frequency array is updated, then dfs is called like in the subset version, then backtracking is applied after the recursion to reapply the increment to the frequency array. The frequency array storing all of a particular letter in the same spot prevents duplicate strings.
    // Time: O(n!) to traverse the dfs.
    // Space: O(1) constant since we don't maintain the strings.
    public int numTilePossibilities(String tiles) {
        int[] freq = new int[26];
        for (char c : tiles.toCharArray())
            freq[c - 'A']++;
        return dfs(freq);
    }
    
    public int dfs(int[] freq) {
        int sum = 0;
        
        for (int i = 0; i < 26; i++) {
            if (freq[i] != 0) {
                sum++;
                freq[i]--;
                sum += dfs(freq);
                freq[i]++;
            }
        }
        
        return sum;
    }
    
    // Intuition: Set prevents duplicate tile possibilities. Used prevents reusage of used characters. Otherwise, takes the subset approach to build the problem by applying backtracking dfs algorithm.
    // Time: O(n!) to build the possibilities.
    // Space: O(n!) to maintain the set.
    Set<String> set = new HashSet<>();
    boolean[] used;
    int n;
    
    public int numTilePossibilities2(String tiles) {
        n = tiles.length();
        used = new boolean[n];
        build(tiles, new StringBuilder(), 0);
        
        return set.size();
    }
    
    public void build(String tiles, StringBuilder s, int idx) {
        if (s.length() > 0) set.add(s.toString());
        
        if (s.length() == n) return; // finished longest possibility
        
        for (int i = 0; i < n; i++) {
            if (!used[i]) {
                s.append(tiles.charAt(i));
                used[i] = true;
                build(tiles, s, i);
                s.deleteCharAt(s.length() - 1);
                used[i] = false;
            }
        }
    }
    
}
