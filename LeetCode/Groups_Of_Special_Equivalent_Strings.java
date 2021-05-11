/* Noah Park

You are given an array A of strings.

A move onto S consists of swapping any two even indexed characters of S, or any two odd indexed characters of S.

Two strings S and T are special-equivalent if after any number of moves onto S, S == T.

For example, S = "zzxy" and T = "xyzz" are special-equivalent because we may make the moves "zzxy" -> "xzzy" -> "xyzz" that swap S[0] and S[2], then S[1] and S[3].

Now, a group of special-equivalent strings from A is a non-empty subset of A such that:

Every pair of strings in the group are special equivalent, and;
The group is the largest size possible (ie., there isn't a string S not in the group such that S is special equivalent to every string in the group)
Return the number of groups of special-equivalent strings from A.

*/

class Solution {
    
    // Intuition: Utilize a set to maintain the even swappable characters concatenated with the odd for each individual word. The set will not allow duplicates which allows for minimal groups.
    // Time: O(n) to iterate over all letters in A.
    // Space: O(n) to maintain a string for each word in A.
    public int numSpecialEquivGroups(String[] A) {
        Set<String> set = new HashSet<>();
        for (String word : A) {
            int[] even = new int[26], odd = new int[26];
            
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (i % 2 == 0) even[c - 'a']++;
                else odd[c - 'a']++;
            }
            
            StringBuilder temp = new StringBuilder();
            temp.append(Arrays.toString(even));
            temp.append(Arrays.toString(odd));
            
            set.add(temp.toString());
        }
        
        
        return set.size();
    }
}
