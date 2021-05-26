/* Noah Park

You own a Goal Parser that can interpret a string command. The command consists of an alphabet of "G", "()" and/or "(al)" in some order. The Goal Parser will interpret "G" as the string "G", "()" as the string "o", and "(al)" as the string "al". The interpreted strings are then concatenated in the original order.

Given the string command, return the Goal Parser's interpretation of command.

*/

class Solution {
    
    // Intuition: Linear processing of the command to convert to G, o, and al. 
    // Time: O(n) to iterate over command.
    // Space: O(n) for the return string.
    public String interpret(String command) {
        if (command == null || command.length() == 0) return "";
        
        StringBuilder res = new StringBuilder();
        
        for (int i = 0; i < command.length(); i++) {
            char c = command.charAt(i);
            
            if (c == 'G') res.append('G');
            else {
                if (command.charAt(i + 1) == ')') { res.append('o'); i++; }
                else { res.append("al"); i+=3; }
            }
        }
        
        return res.toString();
    }
}
