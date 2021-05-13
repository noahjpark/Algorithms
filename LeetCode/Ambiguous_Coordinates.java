/* Noah Park

We had some 2-dimensional coordinates, like "(1, 3)" or "(2, 0.5)".  Then, we removed all commas, decimal points, and spaces, and ended up with the string s.  Return a list of strings representing all possibilities for what our original coordinates could have been.

Our original representation never had extraneous zeroes, so we never started with numbers like "00", "0.0", "0.00", "1.0", "001", "00.01", or any other number that can be represented with less digits.  Also, a decimal point within a number never occurs without at least one digit occuring before it, so we never started with numbers like ".1".

The final answer list can be returned in any order.  Also note that all coordinates in the final answer have exactly one space between them (occurring after the comma.)

*/

class Solution {
    
    // Intuition: A much more concise version of the problem based on the intuition that we could store all split parts of each substring by making them in an list. We could then go through each list and pair parts together.
    // Time and Space: O(n^3) since we must process each split for each split and store the result.
    public List<String> ambiguousCoordinates(String s) {
        if (s == null || s.length() == 0) return new ArrayList<>();
        
        List<String> res = new ArrayList<>();
        int n = s.length();
        
        for (int i = 1; i < n - 2; i++) {
            List<String> first = split(s.substring(1, i + 1)), second = split(s.substring(i + 1, n - 1));
            
            for (String fir : first) 
                for (String sec : second) 
                    res.add("(" + fir + ", " + sec + ")");
        }
        
        return res;
    }
    
    public List<String> split(String s) {
        List<String> res = new ArrayList<>();
        
        for (int i = 1; i < s.length(); i++) {
            String s1 = s.substring(0, i), s2 = s.substring(i), d = (s1 + "." + s2);
            
            if (s2.charAt(s2.length() - 1) == '0') continue;
            res.add(d);
            if (s1.charAt(0) == '0') break;
        }
        
        if (s.charAt(0) != '0' || s.length() == 1) res.add(s);
        return res;
    }
    
    // Intuition: I decided to split into cases: Original split strings with no decimals, original left with right decimals, original right with left decimals, then both decimals. This resulted in longer overall code.
    // Time and Space: O(n^3) since we must process each split for each split and store the result.
    public List<String> ambiguousCoordinates2(String s) {
        if (s == null || s.length() == 0) return new ArrayList<>();
        
        List<String> res = new ArrayList<>();
        int n = s.length();
        
        for (int i = 1; i < n - 2; i++) {
            String first = s.substring(1, i + 1), second = s.substring(i + 1, n - 1);
            int n1 = first.length(), n2 = second.length();
            
            if ((first.charAt(0) != '0' || n1 == 1) && ((second.charAt(0) != '0' || n2 == 1)) res.add("(" + first + ", " + second + ")"); // original split
            if (second.charAt(0) != '0' || (second.charAt(0) == '0' && n2 == 1)) addStrings(first, second, res, true);                    // original left with decimal right
            if (first.charAt(0) != '0' || (first.charAt(0) == '0' && n1 == 1)) addStrings(first, second, res, false);                     // original right with decimal left
            addPairs(first, second, n1, n2, res);                                                                                         // decimal both
        }
        
        return res;
    }
    
    public void addPairs(String first, String second, int n1, int n2, List<String> res) {
        for (int j = 1; j < n1; j++) {
            String s1 = first.substring(0, j), s2 = first.substring(j), d1 = (s1 + "." + s2);

            if (s2.charAt(s2.length() - 1) == '0') continue;

            for (int k = 1; k < n2; k++) {
                String t1 = second.substring(0, k), t2 = second.substring(k), d2 = (t1 + "." + t2);

                if (t2.charAt(t2.length() - 1) == '0') continue;
                res.add("(" + d1 + ", " + d2 + ")");
                if (t1.charAt(0) == '0') break;
            }

            if (s1.charAt(0) == '0') break;
        }
    }
    
    public void addStrings(String first, String second, List<String> res, boolean splitFirst) {
        for (int i = 1; i < (splitFirst ? first.length() : second.length()); i++) {
            String s1 = splitFirst ? first.substring(0, i) : second.substring(0, i), s2 = splitFirst ? first.substring(i) : second.substring(i), d = (s1 + "." + s2);
            
            if (s2.charAt(s2.length() - 1) == '0') continue;
            res.add(splitFirst ? "(" + d + ", " + second + ")" : "(" + first + ", " + d + ")");
            if (s1.charAt(0) == '0') break;
        }
    }
    
}
