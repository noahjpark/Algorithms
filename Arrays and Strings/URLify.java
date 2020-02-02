/*

Noah Park

Problem:

Write a method to replace all spaces in a string with '%20'. You may assume that the string has sufficient space at the end to hold the
additional characters, and that you are given the "true" length of the string.

*/

public class URLify {

    // Time: O(n)
    // Space: O(1)
    // I think this is one of the most efficient solutions possible, as we must go through all characters regardless resulting in O(n) complexity
    // We succeeded in completing this problem in O(1) space as well
    public static char[] url(char[] st, int l){
        // Starting from the back proved to be the correct way to go, since we are going to move everything that way
        for(int i = l - 1; i >= 0; i--){    // i starts at the last index and goes towards the first
            if(st[i] == ' '){               // If we find a space
                int j = l - 1;              // Start an index j at the current ending index
                l += 2;                     // Increment our true index by 2 since we are adding 2 extra characters and changing one '%' '2' and '0'
                int trailer = l - 1;        // Start an index trailer at the new ending index
                while(j != i){              // Basically create a smaller problem where j must get to i, the current space
                    st[trailer--] = st[j--]; // This is where we move everything down two spaces to fit the new characters '2' and '0'
                }                            // This is why we set trailer to be 2 indexes further down that j
                st[i] = '%';                // Then we change the character of the space to be the '%'
                st[i + 1] = '2';            // Change the next character to '2' since we moved a copy of it down two indexes
                st[i-- + 2] = '0';          // Same as the line above, but we also decrement i so that we keep looking for a new space in the word
            }                               // Thus, when we find a new space, the process begins again where we move all characters down two indexes, including the newly added '%, '2', and '0'
        }
        return st;                          // Return the newly updated character array
    }

    public static void main(String[] args){
        String str = "Mr John Smith    "; // Initialize with extra spaces at the end
        char[] st = str.toCharArray();    // Convert to a char array
        int l = 0;
        for(int i = 0; i < st.length; i++){ // Go through the char array and find where there are back to back spaces, this is our true index
            if(st[i] == ' ' && st[i+1] == ' '){
                l = i;
                break;                      // Break out of the loop when we have found this, as there are no more characters following this point
            }
        }
        char[] c = url(st, l);
        for(char ch : c){
            System.out.print(ch);
        }
    }
}
