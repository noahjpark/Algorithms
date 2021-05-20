/* Noah Park

Given a text string and words (a list of strings), return all index pairs [i, j] so that the substring text[i]...text[j] is in the list of words.

*/

class Solution {
    
    // Intuition: For each word in words, we look at all substrings of the length of that particular word in text. Any matches get added to the index list. The index list is then sorted by first coordinate then second in case of ties and converted to the integer array result.
    // Time: O(n*m) where n is the length of words and m is the length of text.
    // Space: O(n*m) if there are m occurrences of each word.
    public int[][] indexPairs(String text, String[] words) {
        List<int[]> l = new ArrayList<>();
        
        for (String word : words) {
            int s = 0, e = word.length();
            for (; e <= text.length(); s++, e++) 
                if (text.substring(s, e).equals(word)) l.add(new int[]{ s, e - 1 });
        }
        
        Collections.sort(l, (a, b) -> (a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]));
        int[][] res = new int[l.size()][2];
        for (int i = 0; i < l.size(); i++)
            res[i] = l.get(i);
        
        return res;
    }
}
