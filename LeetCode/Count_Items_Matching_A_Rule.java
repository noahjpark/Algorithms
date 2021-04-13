/* Noah Park

You are given an array items, where each items[i] = [typei, colori, namei] describes the type, color, and name of the ith item. You are also given a rule represented by two strings, ruleKey and ruleValue.

The ith item is said to match the rule if one of the following is true:

ruleKey == "type" and ruleValue == typei.
ruleKey == "color" and ruleValue == colori.
ruleKey == "name" and ruleValue == namei.
Return the number of items that match the given rule.

*/

class Solution {
    
    // Intuition: Store the idx of which part of the item we need to compare. We have this information at the beginning of the problem. If ruleKey is type then idx is 0, if ruleKey is color then idx is 1, else if ruleKey is name then idx is 2. Then we just check each time ruleValue matches the idx of item. 
    // Time: O(n) to iterate over items. The equals method is negligible since the longest string is 10 long.
    // Space: O(1)
    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int count = 0, idx = ruleKey.equals("type") ? 0 : ruleKey.equals("name") ? 2 : 1;
        
        for (List<String> item : items)
            if (ruleValue.equals(item.get(idx))) count++;
        
        return count;
    }
}
