/* Noah Park

Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

*/

class Solution {
    
    // Intuition: map all numbers to their letter equivalents. Since the letters have to come in the order that the numbers do (3's letters can't come before 2's), we just follow the order and apply the permutations.
    // Time: O(4^n) 4^n for the four letter combinations creating the recursive tree.
    // Space: O(n) the number of digits in the string since we have to recursively call.
    HashMap<String, String> map = new HashMap<>() {{ put("2", "abc"); put("3", "def"); put("4", "ghi");
        put("5", "jkl"); put("6", "mno"); put("7", "pqrs"); put("8", "tuv"); put("9", "wxyz"); }};
    List<String> res = new ArrayList<>();
    
    public List<String> letterCombinations(String digits) {
        if (digits.length() > 0) perm(digits, "");
        return res;
    }
    
    public void perm(String digits, String s) {
        if (digits.length() == 0) res.add(s);
        else {
            String letters = map.get(digits.substring(0, 1));
            for (char c : letters.toCharArray())
                perm(digits.substring(1), s + c);
        }
    }
}
