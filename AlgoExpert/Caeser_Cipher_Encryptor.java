// Noah Park
/*

Problem: Given a non-empty string of lowercase letters and a non-negative integer representing
a key, write a function that returns a new string obtained by shifting every letter in the input
string by k positions in the alphabet, where k is the key.

Note that letters should "wrap" around the alphabet; in other words, the letter z shifted by
one returns the letter a.

*/

public class Caeser_Cipher_Encryptor {
    // Time: O(n) | Space: O(n)
    public static String caesarCypherEncryptor(String str, int key) {
        // Could alternatively use a character array
        StringBuilder ans = new StringBuilder(); // StringBuilder to return the final string
        int adjustedKey = key % 26; // Get the actual number of rotations will shift

        for(int i = 0; i < str.length(); i++){ // Iterate through the string
            char c = str.charAt(i); // Get our current character
            // Loop through the number of shifts we need to do
            // It is adjusted to be 26 or less. Any more would be full cycles and
            // doing much repetitive work we don't need to do.
            for(int j = 0; j < adjustedKey; j++){ // Now this for loop is constant since the adjustedKey is a value between 0 and 26 inclusive on both ends
                c += 1; // Add 1 for each shift
                if(c > 'z'){ // If c has a larger ascii value than 'z', set it back to 'a' (wrapping)
                    c = 'a';
                }
            }
            // Append c to the answer
            ans.append((char) c);
        }

        // Return the final string
        return ans.toString();
    }
}
