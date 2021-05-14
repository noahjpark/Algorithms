/* Noah Park

The abbreviation of a word is a concatenation of its first letter, the number of characters between the first and last letter, and its last letter. If a word has only two characters, then it is an abbreviation of itself.

For example:

dog --> d1g because there is one letter between the first letter 'd' and the last letter 'g'.
internationalization --> i18n because there are 18 letters between the first letter 'i' and the last letter 'n'.
it --> it because any word with only two characters is an abbreviation of itself.
Implement the ValidWordAbbr class:

ValidWordAbbr(String[] dictionary) Initializes the object with a dictionary of words.
boolean isUnique(string word) Returns true if either of the following conditions are met (otherwise returns false):
There is no word in dictionary whose abbreviation is equal to word's abbreviation.
For any word in dictionary whose abbreviation is equal to word's abbreviation, that word and word are the same.

*/

class ValidWordAbbr {
    
    // Intuition: Map all abbreviations to the words that make the abbreviation. A word is unique if its abbreviation is not in the map OR it is in the map and the word that abbreviates to that abbreviation is unique i.e. the set size is 1.
    // Time: O(n) to iterate through the word dictionary and create the map.
    // Space: O(n) to maintain the map.
    Map<String, Set<String>> map = new HashMap<>();

    public ValidWordAbbr(String[] dictionary) {
        for (String word : dictionary) {
            String a = abbreviate(word);
            if (!map.containsKey(a)) map.put(a, new HashSet<>());
            map.get(a).add(word);
        }
    }
    
    public String abbreviate(String s) {
        if (s == null || s.length() <= 2) return s;
        
        StringBuilder res = new StringBuilder();
        
        res.append(s.charAt(0));
        res.append(s.length() - 2);
        res.append(s.charAt(s.length() - 1));
        
        return res.toString();
    }
    
    public boolean isUnique(String word) {
        String a = abbreviate(word);
        return !map.containsKey(a) || (map.get(a).contains(word) && map.get(a).size() == 1);
    }
}

/**
 * Your ValidWordAbbr object will be instantiated and called as such:
 * ValidWordAbbr obj = new ValidWordAbbr(dictionary);
 * boolean param_1 = obj.isUnique(word);
 */
