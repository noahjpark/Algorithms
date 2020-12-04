/* Noah Park

A character in UTF8 can be from 1 to 4 bytes long, subjected to the following rules:

For 1-byte character, the first bit is a 0, followed by its unicode code.
For n-bytes character, the first n-bits are all one's, the n+1 bit is 0, followed by n-1 bytes with most significant 2 bits being 10.
This is how the UTF-8 encoding would work:

*/

class Solution {
    public boolean validUtf8(int[] data) {      
        int cur = 0; // keeps track of the next 10xxxxxx nums that have to be there
        
        for (int num : data) { // iterate over all data
            if (cur == 0) { // if we have the start of a utf8
                if ((num >> 5) == 0b110) cur = 1; // pattern match with 2
                else if((num >> 4) == 0b1110) cur = 2; // pattern match with 3
                else if((num >> 3) == 0b11110) cur = 3; // pattern match with 4
                else if((num >> 7) != 0) return false; // doesn't follow the other patterns and doesn't have a zero at the start
            } else { // we need to ensure the start is 10xxxxxx
                if ((num >> 6) != 0b10) return false;
                cur--;
            }
        }
        
        return cur == 0; // if there were more numbers we needed to check, we have to return false
    }
    
    // not sure why this runs slower but it does
    public boolean validUtf8Suboptimal(int[] data) {
        int cur = 0; // keeps track of the next 10xxxxxx nums that have to be there
        
        for (int num : data) { // iterate over all data
            // store each number & with num
            int first = 128 & num, second = 192 & num, third = 224 & num, fourth = 240 & num, moreThanFour = 248 & num;
            if(moreThanFour == 248) return false; // 5 or more 1s in a row is bad
            else if (cur > 0) { // if we have to match with 10xxxxxx
                if (first != 128) return false; // no match
                cur--;
            }
            else if (first == 0) continue; // 0 in front is skipped
            else if (fourth == 240) cur = 3; // check four then three then two due to the & signs
            else if (third == 224) cur = 2; 
            else if (second == 192) cur = 1;
            else return false;
        }
        
        return cur == 0; // if there were more numbers we needed to check, we have to return false
    }
}
