// Noah Park
/*

Problem: Write a function that, given a string, returns its longest palindromic substring.

A palindrome is defined as a string that's written the same forward and backward. Note that single
character strings are palindromes.

You can assume that there will only be one longest palindromic substring.

*/

public class Longest_Palindromic_Substring {
    // Optimal Solution
    // Time: O(n^2) | Space: O(1)
    public static String longestPalindromicSubstringOptimal(String str) {
        // We will look for substring indices
        // Initialize the longest substring indices to just the first character
        int[] longest = {0, 1};

        // Iterate through the rest of the string (no need to re check the first character)
        for(int i = 1; i < str.length(); i++){
            // Calculate both odd and even palindromes at each i
            // If we have an odd length palindrome, we already can count i then pass in
            // the indices i - 1 and i + 1. If we have an even length palindrome, we use
            // i as the right index and i - 1 as the left index. Note that we don't use i
            // and i + 1 since that will fall off the end of the string at the end of the
            // loop. Plus, we already counted the first index so we have no issues with i - 1.
            // We check which palindrome, odd or even, has a larger length between the start and
            // end indices and update the longest palindrome based on this.
            int[] odd = pal(str, i - 1, i + 1);
            int[] even = pal(str, i - 1, i);
            int[] newpal = odd[1] - odd[0] > even[1] - even[0] ? odd : even;
            longest = longest[1] - longest[0] > newpal[1] - newpal[0] ? longest : newpal;
        }

        // We return the substring of 'str' using the longest substring indices we found.
        return str.substring(longest[0], longest[1]);
    }

    // Returns an integer array of size 2 which contains the start and end indices for
    // creating the palindrome
    public static int[] pal(String str, int index1, int index2){
        // Starts from the given left and right pointers 'index1' and 'index2'
        // index1 will decrement while index2 increments
        // We will break when either index falls out of bounds or the characters at
        // those indices do not match
        while(index1 > -1 && index2 < str.length()){
            if(str.charAt(index1) != str.charAt(index2)) break;
            index1--;
            index2++;
        }

        // When we break from the loop, we only want to include the indices of the palindrome
        // itself. Since the second argument is exclusive, index2 is fine. But we need to add 1
        // too index1 since it either is too small (-1) or is a character that is not equal to that
        // of the right pointer. Since the left argument is inclusive while the right argument is exclusive,
        // we must add one to index1.
        return new int[] {index1 + 1, index2};
    }

    // Naive solution
    // Time: O(n^3) | Space: O(1)
    // Time is so high due to the recursive call in the nested loops
    public static String longestPalindromicSubstring(String str) {
        String ans = ""; // Initialize an empty string

        // Obtain all possible substrings of str by using nested for loops
        for(int i = 0; i < str.length(); i++){
            for(int j = i; j < str.length(); j++){
                // Create a substring from i to j + 1 - remember that the second argument is exclusive
                String tempPal = str.substring(i, j + 1);
                // If the substring is a palindrome and its length is longer than our longest palindrome
                // we have found so far, then update the longest palindrome
                if(isPalindrome(tempPal) && tempPal.length() > ans.length()) ans = tempPal;
            }
        }

        // Return the longest palindrome
        return ans;
    }

    // Checks if a string is a palindrome
    public static boolean isPalindrome(String str){
        if(str.length() == 0 || str.length() == 1){
            return true;
        }
        return str.charAt(0) == str.charAt(str.length() - 1) && isPalindrome(str.substring(1, str.length() - 1));
    }
}
