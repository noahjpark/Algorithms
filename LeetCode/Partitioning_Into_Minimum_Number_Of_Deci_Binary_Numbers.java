/* Noah Park

A decimal number is called deci-binary if each of its digits is either 0 or 1 without any leading zeros. For example, 101 and 1100 are deci-binary, while 112 and 3001 are not.

Given a string n that represents a positive decimal integer, return the minimum number of positive deci-binary numbers needed so that they sum up to n.

*/

class Solution {
    
    // Intuition: We can put a 1 in any place to get to that position's value in n. Following this, if we don't need anymore, we can just use 0s. Thus, we notice that we just must fill 1s to the largest value since a 1 can be in other positions for each number we use as well. Thus, we only care about the largest number in the string. Keep in mind for an example like 259, we could use 2 numbers in the first position with all 1s since the larger numbers can remove 1s as well. As we take numbers, the total we need decreases resulting in a total of whatever the largest single digit number is.
    // Time: O(n) to iterate over n.
    // Space: O(1) constant.
    public int minPartitions(String n) {
        if (n == null || n.length() == 0) return 0;
        
        char max = '0';
        
        for (char c : n.toCharArray())
            if (c > max) max = c;
        
        return max - '0';
    }
}
