/* Noah Park

A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

Every adjacent pair of words differs by a single letter.
Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
sk == endWord
Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.

*/

class Solution {
    
    // Intuition: Bidirectional solution based off of the below solution of modifying each point in the string to add new values.
    // Time: O(n*m*26) number of words in the list * number of characters in a word * 26 for transforming all possible outcomes.
    // Space: O(n) words in the list.
    public int ladderLength(String begin, String endWord, List<String> wordList) {
        if (wordList == null || wordList.size() == 0) return 0;
        
        Set<String> words = new HashSet<>(wordList), start = new HashSet<>(), end = new HashSet<>();
        if (!words.contains(endWord)) return 0;
        start.add(begin);
        end.add(endWord);
        words.remove(begin);
        words.remove(endWord);
        
        for (int count = 1; !start.isEmpty(); count++) {
            Set<String> next = new HashSet<>();
            
            for (String word : start) {
                char[] ch = word.toCharArray();
            
                for (int i = 0; i < ch.length; i++) {
                    char prev = ch[i];
                    
                    for (char c = 'a'; c <= 'z'; c++) {
                        ch[i] = c;
                        String s = new String(ch);
                        if (end.contains(s)) return count + 1;
                        if (words.contains(s)) {
                            next.add(s);
                            words.remove(s);
                        }
                    }
                    
                    ch[i] = prev;
                }
            }
            
            // if (next.size() > end.size()) { start = end; end = next; }
            // else start = next;
            start = next.size() < end.size() ? next: end;
            end = start.size() < end.size() ? end : next;
        }
        
        return 0;
    }
    
    // Intuition: Run a bfs from the first word until we reach the target. We can modify each character in the current word to check all possibilities (26*10).
    // Time: O(n*m*26) number of words in the list * number of characters in a word * 26 for transforming all possible outcomes.
    // Space: O(n) words in the list.
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.size() == 0) return 0;
        
        Deque<String> q = new LinkedList<>();
        Set<String> list = new HashSet<>(wordList), visited = new HashSet<>();
        
        if (!list.contains(endWord)) return 0;
        
        q.addLast(beginWord);
        visited.add(beginWord);
        
        for (int count = 1; !q.isEmpty(); count++) {
            for (int k = q.size(); k > 0; k--) {
                String cur = q.removeFirst();

                if (cur.equals(endWord)) return count;

                for (int i = 0; i < cur.length(); i++) {
                    char[] ch = cur.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == cur.charAt(i)) continue;
                        ch[i] = c;
                        String next = new String(ch);
                        if (!visited.contains(next) && list.contains(next)) {
                            visited.add(next);
                            q.addLast(next);
                        }
                    }
                }
            }
        }
        
        return 0;
    }
    
}
