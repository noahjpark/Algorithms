// Noah Park
/*

Problem: Write a function that takes in a non-empty string and that returns a boolean representing
whether the string is a palindrome.

A palindrome is defined as a string that's written the same forward and backward. Note that
single-character strings are palindromes.

*/

public class Palindrome_Check {
    // Iterative solution - Time: O(n) (length of the string) | Space: O(1)
    public static boolean isPalindrome(String str) {
        int j = str.length() - 1; // start a pointer at the end of the string
        // Iterate from the start to the halfway point. If the string is even length, we will check all characters.
        // Otherwise, we will miss the center one, but that doesn't matter.
        for(int i = 0; i < str.length() / 2; i++){
            if(str.charAt(i) != str.charAt(j)){ // If any complement index doesn't match, return false
                return false;
            }
            j--; // decrement j while incrementing i
        }

        // All characters matched and the string is a palindrome
        return true;
    }

    // Recursive solution
    public static boolean isPalindromeRec(String str) {
        return isPal(str);
    }

    // Time: O(n) (length of the string) | Space: O(1)
    public static boolean isPal(String str){
        if(str.length() == 0 || str.length() == 1){ // If the string is length 0 or 1, we have a palindrome (base case)
            return true;
        }

        // Return true if the characters at opposing indices match and all characters in the recursive call match like this as well. Else, return false.
        return str.charAt(0) == str.charAt(str.length() - 1) && isPal(str.substring(1, str.length() - 1));
    }
}
