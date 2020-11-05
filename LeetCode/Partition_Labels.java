/* Noah Park

A string S of lowercase English letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, 
and return a list of integers representing the size of these parts.

*/

class Solution {
    public List<Integer> partitionLabels(String S) {
        List<Integer> partitions = new ArrayList<>();
        int start = 0, end = 0;
        Map<Character, Integer> freq = new HashMap<>();
        for(char c : S.toCharArray())
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        
        Map<Character, Integer> substring = new HashMap<>();
        
        while(end < S.length()){
            char c = S.charAt(end);
            substring.put(c, substring.getOrDefault(c, 0) + 1);
            
            int match = 0;
            for(Character ch : substring.keySet())
                if(substring.get(ch) == freq.get(ch)) match++;
            
            if(match == substring.size()){
                partitions.add(end - start + 1);
                start = end + 1;
                substring = new HashMap<>();
            }
            
            end++;
        }
        
        return partitions;
    }
}
