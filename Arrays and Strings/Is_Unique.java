/*

Noah Park

Problem:

Implement an algorithm to deermine if a string has all unique characters. What if you cannot use additional data structures?

*/

import java.util.Hashtable;

public class Is_Unique {

    // Time: O(n^2)
    // Space: O(1)
    public static boolean isUnique(String word){
        // Iterate through both the word starting at 0 and 1 to compare each letter
        // Yes this is fairly naive and inefficient
        for(int i = 0; i < word.length() - 1; i++){
            for(int j = i + 1; j < word.length(); j++){
                if(word.charAt(i) == word.charAt(j)){   // If we found duplicate characters the word is not unique
                    return false;
                }
            }
        }
        return true;    // If we made it this far there are no duplicate characters
    }

    // Time: O(n)
    // Space: O(n)
    public static boolean isUniqueHash(String word){
        Hashtable<Integer, Character> ht = new Hashtable<>();   // Use a hash table to increase time complexity
        for(int i = 0; i < word.length(); i++) {
            if (ht.contains(word.charAt(i))) {  // If the hash table already contains the new letter the word is not unique
                return false;
            }
            ht.put(i, word.charAt(i));  // Insert the new letter from the word into the hash table
        }
        return true;    // If we made it this far there are no duplicate characters
    }

    public static void main(String[] args){
        String[] words = {"hello", "undo", "true", "check"};
        System.out.println(isUnique(words[0]));
        System.out.println(isUnique(words[1]));
        System.out.println(isUniqueHash(words[2]));
        System.out.println(isUniqueHash(words[3]));
    }
}
