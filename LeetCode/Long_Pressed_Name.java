/* Noah Park

Your friend is typing his name into a keyboard.  Sometimes, when typing a character c, the key might get long pressed, and the character will be typed 1 or more times.

You examine the typed characters of the keyboard.  Return True if it is possible that it was your friends name, with some characters (possibly none) being long pressed.

*/

class Solution {
    public boolean isLongPressedName(String name, String typed) {
        if(name == null || typed == null || name.length() > typed.length()) return false;
        
        int p1 = 0, p2 = 0;
        
        while(p1 < name.length() && p2 < typed.length()){
            if(name.charAt(p1) == typed.charAt(p2)) { p1++; p2++; }
            else if(p2 >= 1 && typed.charAt(p2) == typed.charAt(p2 - 1)) p2++;
            else return false;
        }
        
        if(p1 < name.length()) return false;
        while(p2 < typed.length()){
            if(typed.charAt(p2) != typed.charAt(p2 - 1)) return false;
            p2++;
        }
        
        return true;
    }
}
