/* Noah Park

Given an array A of strings made only from lowercase letters, return a list of all characters that show up in all strings within the list (including duplicates).  

For example, if a character occurs 3 times in all strings but not 4 times, you need to include that character three times in the final answer.

You may return the answer in any order.

*/

class Solution {
    public List<String> commonChars(String[] A) {
        int[] counts = new int[26];
        for(int i = 0; i < 26; i++)
            counts[i] = Integer.MAX_VALUE;
        
        for(String string : A){
            int[] curcount = new int[26];
            for(int i = 0; i < string.length(); i++)
                curcount[string.charAt(i) - 'a']++;
            for(int i = 0; i < 26; i++)
                counts[i] = Math.min(counts[i], curcount[i]); // Keep track of the minimum occurrences of a number so far
        }
        
        List<String> common = new ArrayList<>();
        for(int i = 0; i < 26; i++){
            for(int j = 0; j < counts[i]; j++)
                common.add("" + (char) (i + 'a'));
        }
        
        return common;
    }
}
