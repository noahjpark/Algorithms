/* Noah Park

You are given a string s and an array of strings words. You should add a closed pair of bold tag <b> and </b> to wrap the substrings in s that exist in words. If two such substrings overlap, you should wrap them together with only one pair of closed bold-tag. If two substrings wrapped by bold tags are consecutive, you should combine them.

Return s after adding the bold tags.

*/

class Solution {
    
    // Intuition: Maintain a heap of intervals where the words from words occur in the string s sorted by their start. Then simply merge and apply tags before and after.
    // Time: O(n*m*k + nlogn + k) where n is the number of words in words, m is the number of characters in each word, k is the length of s.
    // Space: O(n) to maintain the resulting string, merged list, and heap.
    public String addBoldTag(String s, String[] words) {
        if (s == null || s.length() == 0 || words == null || words.length == 0) return s;
        
        List<int[]> merged = new ArrayList<>();
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        StringBuilder res = new StringBuilder();
        
        for (String word : words) {
            int start = s.indexOf(word);
            while (start != -1) {
                minHeap.offer(new int[]{ start, start + word.length() });
                start = s.indexOf(word, start + 1);
            }
        }
        
        while (!minHeap.isEmpty()) {
            int start = minHeap.peek()[0], end = minHeap.poll()[1];
            while (!minHeap.isEmpty() && end >= minHeap.peek()[0]) end = Math.max(end, minHeap.poll()[1]);
            merged.add(new int[]{ start, end });
        }
        
        int i = 0;
        for (int p = 0; p < merged.size(); p++) {
            while (i < s.length() && p < merged.size() && i < merged.get(p)[0]) res.append(s.charAt(i++));
            res.append("<b>");
            while (i < s.length() && p < merged.size() && i < merged.get(p)[1]) res.append(s.charAt(i++));
            res.append("</b>");
        }
        
        while (i < s.length()) res.append(s.charAt(i++));
        
        return res.toString();
    }
}
