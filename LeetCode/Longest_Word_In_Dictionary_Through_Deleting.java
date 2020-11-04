/* Noah Park

Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some characters of the given string. 
If there are more than one possible results, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.

*/

class Solution {
    public String findLongestWord(String s, List<String> d) {
        String longest = "";
        
        for(String string : d){
            int p1 = 0, p2 = 0;
            while(p1 < s.length() && p2 < string.length()){
                if(s.charAt(p1) == string.charAt(p2)) p2++;
                p1++;
            }
            
            if(p2 == string.length()){
                if(string.length() > longest.length()) longest = string;
                else if(string.length() == longest.length()) longest = longest.compareTo(string) < 0 ? longest : string;
            }
        }
        
        return longest;
    }
}
