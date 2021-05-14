/* Noah Park

You are given a phone number as a string number. number consists of digits, spaces ' ', and/or dashes '-'.

You would like to reformat the phone number in a certain manner. Firstly, remove all spaces and dashes. Then, group the digits from left to right into blocks of length 3 until there are 4 or fewer digits. The final digits are then grouped as follows:

2 digits: A single block of length 2.
3 digits: A single block of length 3.
4 digits: Two blocks of length 2 each.
The blocks are then joined by dashes. Notice that the reformatting process should never produce any blocks of length 1 and produce at most two blocks of length 2.

Return the phone number after formatting.

*/

class Solution {
    
    // Intuition: Follow the instructions. One pass to isolate the numbers. Then another to group into sections.
    // Time: O(n) two passes.
    // Space: O(n) to maintain the numbers and result strings.
    public String reformatNumber(String number) {
        StringBuilder nums = new StringBuilder(), res = new StringBuilder();
        
        for (char c : number.toCharArray())
            if (c != '-' && c != ' ') nums.append(c);
        
        int i = 0;
        for (; nums.length() - i > 4; i+=3) {
            res.append(nums.charAt(i));
            res.append(nums.charAt(i + 1));
            res.append(nums.charAt(i + 2));
            res.append("-");
        }
        
        if (nums.length() - i <= 3) 
            while (i < nums.length()) res.append(nums.charAt(i++));
        else if (nums.length() - i == 4) {
            res.append(nums.charAt(i));
            res.append(nums.charAt(i + 1));
            res.append("-");
            res.append(nums.charAt(i + 2));
            res.append(nums.charAt(i + 3));
        }
        
        if (res.charAt(res.length() - 1) == '-') res.deleteCharAt(res.length() - 1);
        
        return res.toString();
    }
}
