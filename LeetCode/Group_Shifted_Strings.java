/* Noah Park

We can shift a string by shifting each of its letters to its successive letter.

For example, "abc" can be shifted to be "bcd".
We can keep shifting the string to form a sequence.

For example, we can keep shifting "abc" to form the sequence: "abc" -> "bcd" -> ... -> "xyz".
Given an array of strings strings, group all strings[i] that belong to the same shifting sequence. You may return the answer in any order.

*/

class Solution {
    
    // Intuition: Maps strings with the same difference sequence to the same list.
    // Time: O(n*m) where n is the length of each string and m is the number of characters in the strings.
    // Space: O(n*m) to maintain the resulting list.
    public List<List<String>> groupStrings(String[] strings) {
        if (strings == null || strings.length == 0) return new ArrayList<>();
        
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        
        for (int i = 0; i < strings.length; i++) {
            String key = getDiff(strings[i]);
            if (!map.containsKey(key)) map.put(key, new ArrayList<>());
            map.get(key).add(strings[i]);
        }
        
        for (List<String> list : map.values())
            res.add(list);
        
        return res;
    }
    
    // Converts the string into the differences between each character. Example: 'abc' becomes '0,1,1,' that can then be mapped to all strings with this difference sequence. Improved time by utilizing a character array instead of string builder.
    public String getDiff(String s) {
        char[] res = new char[s.length() * 2];
        res[0] = '0'; res[1] = ',';
        for (int i = 1; i < s.length(); i++) {
            int val = (int) s.charAt(i) - (int) s.charAt(i - 1);
            if (val < 0) val += 26; 
            res[2*i] = (char) val; res[2*i+1] = ',';
        }
        return new String(res);
//         StringBuilder res = new StringBuilder();
//         res.append("0,");
        
//         for (int i = 1; i < s.length(); i++) {
//             int val1 = (int) s.charAt(i), val2 = (int) s.charAt(i - 1);
//             if (val2 > val1) val1 += 26; 
//             res.append((val1 - val2) + ",");
//         }
        
//         return res.toString();
    }
    
    // Intuition: Brute force calculation at each point with slight optimization to avoid recomputing ones we've already chosen.
    // Time: O((n*m)^2) where n is the number of words and m is the number of letters in each word.
    // Space: O(n*m) for the resulting list.
    public List<List<String>> groupStrings2(String[] strings) {
        if (strings == null || strings.length == 0) return new ArrayList<>();
        
        List<List<String>> res = new ArrayList<>();
        boolean[] used = new boolean[strings.length];
        
        for (int i = 0; i < strings.length; i++) {
            if (used[i]) continue;
            
            Set<String> set = new HashSet<>();
            List<String> temp = new ArrayList<>();
            
            temp.add(strings[i]);
            set.add(strings[i]);
            used[i] = true;
            
            String s = new String(strings[i]);
            for (int k = 0; k < 26; k++) {
                s = shift(s);
                set.add(s);
            }
            
            for (int j = i + 1; j < strings.length; j++) {
                if (used[j]) continue;
                
                if (set.contains(strings[j])) {
                    temp.add(strings[j]);
                    used[j] = true;
                }
            }
            
            res.add(temp);
        }
        
        return res;
    }
    
    public String shift(String s) {
        StringBuilder res = new StringBuilder();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int n = s.length();
        
        for (int i = 0; i < n; i++) {
            int idx = (alphabet.indexOf(s.charAt(i)) + 1) % 26;
            res.append(alphabet.charAt(idx));
        }
        
        return res.toString();
    }
}
