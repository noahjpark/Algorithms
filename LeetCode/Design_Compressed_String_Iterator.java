/* Noah Park

Design and implement a data structure for a compressed string iterator. The given compressed string will be in the form of each letter followed by a positive integer representing the number of this letter existing in the original uncompressed string.

Implement the StringIterator class:

next() Returns the next character if the original string still has uncompressed characters, otherwise returns a white space.
hasNext() Returns true if there is any letter needs to be uncompressed in the original string, otherwise returns false.

*/

// Intuition: Three solutions: 1 -> Similar to the most optimal but uses parseInt to find the number. Second maintains lists of the necessary information. The final one is the most optimal.
// Time: O(n) to iterate over s.
// Space: O(1) constant.
class StringIterator {
    
    String s;
    int p = 0, n = 0;
    char c = ' ';
    
    public StringIterator(String s) {
        this.s = s;
    }
    
    public char next() {
        if (!hasNext()) return ' ';
        if (n == 0) {
            c = s.charAt(p++);
            while (p < s.length() && Character.isDigit(s.charAt(p)))
                n = (n * 10) + (s.charAt(p++) - '0');
        }
        n--;
        return c;
    }
    
    public boolean hasNext() {
        return p < s.length() || n > 0;
    }
    
}

// class StringIterator {
    
//     List<Character> chars;
//     List<Integer> nums;
//     int n, p1 = 0, p2 = 0;
    
//     public StringIterator(String s) {
//         chars = new ArrayList<>();
//         nums = new ArrayList<>();
//         n = s.length();
            
//         char cur = s.charAt(0);
//         int start = 1, end = 1;
//         for (int i = 1; i < n; i++) {
//             if (!Character.isDigit(s.charAt(i))) {
//                 chars.add(cur);
//                 nums.add(Integer.parseInt(s.substring(start, end)));
//                 cur = s.charAt(i);
//                 start = i + 1;
//                 end = i;
//             }
//             end++;
//         }
        
//         chars.add(cur);
//         nums.add(Integer.parseInt(s.substring(start, end)));
        
//         // System.out.println(chars.toString());
//         // System.out.println(nums.toString());
//     }
    
//     public char next() {
//         if (!hasNext()) return ' ';
        
//         char c = chars.get(p1);
//         nums.set(p2, nums.get(p2) - 1);
//         if (nums.get(p2) == 0) { p1++; p2++; }
        
//         return c;
//     }
    
//     public boolean hasNext() {
//         return p2 < nums.size() && nums.get(p2) > 0;
//     }
    
// }

// class StringIterator {
    
//     String s;
//     int p = 0, cur = 0, next;

//     public StringIterator(String compressedString) {
//         s = compressedString;
//         int start = 1, end = 1;
//         for (int i = 1; i < compressedString.length(); i++) {
//             if (!Character.isDigit(s.charAt(i))) break;
//             end++;
//         }
//         cur = Integer.parseInt(s.substring(start, end));
//         next = end;
//     }
    
//     public char next() {
//         if (hasNext()) {
//             cur--;
//             char res = s.charAt(p);
//             if (cur == 0) {
//                 p = next;
//                 int start = next + 1, end = start;
//                 if (start < s.length()) {
//                     for (int i = start; i < s.length(); i++) {
//                         if (!Character.isDigit(s.charAt(i))) break;
//                         end++;
//                     }
//                     cur = Integer.parseInt(s.substring(start, end));
//                 }
//                 next = end;
//             }
//             return res;
//         }
//         return ' ';
//     }
    
//     public boolean hasNext() {
//         return cur > 0 || next < s.length(); 
//     }
// }

/**
 * Your StringIterator object will be instantiated and called as such:
 * StringIterator obj = new StringIterator(compressedString);
 * char param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
