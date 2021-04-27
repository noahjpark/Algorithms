/* Noah Park

Given an array of strings strs, group the anagrams together. You can return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

*/

class Solution {
    
    // Intuition: Utilize the same idea from below but map each frequency array to a list by converting the array to a string. Each identical array will be the same string.
    // Time: O(n*m) where n is the number of strings and m is the length of each string.
    // Space: O(n*m) to maintain the information in the hash map.
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        List<List<String>> res = new ArrayList<>();
        
        for (String s : strs) {
            int[] freq = new int[26];
            
            for (char c : s.toCharArray())
                freq[c - 'a']++;
            
            String k = Arrays.toString(freq);
            
            if (!map.containsKey(k)) map.put(k, new ArrayList<>());
            map.get(k).add(s);
        }
        
        return new ArrayList<>(map.values());
    }
    
    // Intuition: Brute force store all frequencies of each string then check if each one matches. Some optimization to not use already used strings.
    // Time: O(n^2) number of strings.
    // Space: O(n*m) to maintain the return list.
    public List<List<String>> groupAnagrams2(String[] strs) {
        int[][] freqs = new int[strs.length][26];
        boolean[] used = new boolean[strs.length];
        
        for (int i = 0; i < strs.length; i++) 
            for (char c : strs[i].toCharArray())
                freqs[i][c - 'a']++;
        
        List<List<String>> res = new ArrayList<>();
        
        for (int i = 0; i < strs.length; i++) {
            if (used[i]) continue;
            
            List<String> temp = new ArrayList<>();
            temp.add(strs[i]);
            used[i] = true;
            
            for (int j = i + 1; j < strs.length; j++) {
                if (used[j]) continue;
                if (isAnagram(freqs[i], freqs[j])) { temp.add(strs[j]); used[j] = true; }
            }
            
            res.add(temp);
        }
        
        return res;
    }
    
    public boolean isAnagram(int[] f1, int[] f2) {
        for (int i = 0; i < 26; i++)
            if (f1[i] != f2[i]) return false;
        return true;
    }
}
