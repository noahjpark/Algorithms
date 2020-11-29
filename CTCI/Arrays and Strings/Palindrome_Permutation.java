/*

Noah Park

Problem:

Given a string, write a function to check if it is a permutation of a palindrome. A palindrome is a word or phrase that is the same forwards
or backwards. A permutation is a rearrangement of letters. The palindrome does not need to be limited to just dictionary words.

*/

import java.util.Hashtable;

public class Palindrome_Permutation {

    // Time: O(n)
    // Space: O(n)
    public static boolean palPerCheck(String word){
        Hashtable<Character, Integer> ht = new Hashtable<>();   // Initialize hash table to store each letter and their occurrences
        for(int i = 0; i < word.length(); i++){                 // Iterate through the word to add to the hash table
            // If the hash table does not contain the current character from the word and the current character is not a space, add it to
            // the hash table with an occurrence of one. Otherwise, it already exists in the hash table and must be incremented by one.
            if(word.charAt(i) != ' ') {
                if (!ht.containsKey(word.charAt(i))) {
                    ht.put(word.charAt(i), 1);
                } else {
                    ht.replace(word.charAt(i), ht.get(word.charAt(i)) + 1);
                }
            }
        }
        // oddCounter is to be used to count the single occurrence of any given letter if the number of letters is odd
        int oddCounter = 0;

        // If the number of letters is even, all keys in the hash table must have a value of two for the word to be a palindrome permutation
        // Return false if this is not the case
        if(trueCount(word) % 2 == 0){
            for(Character k : ht.keySet()){
                if(ht.get(k) != 2){
                    return false;
                }
            }
        }
        // If the number of letters is odd, all keys but one must have a value of two for the word to be a palindrome permutation. The oddCounter
        // takes care of this by incrementing by one for every occurrence of a value of one.
        else{
            for(Character k : ht.keySet()){
                if(ht.get(k) == 1){
                    oddCounter++;
                }
            }
            // If the oddCounter does not equal one, meaning we have either not found a value of one when there should be one for the odd number
            // of letters OR we have multiple values of one for multiple letters. We return false in either case.
            if(oddCounter != 1){
                return false;
            }
        }

        // If we made it this far, we can safely say that our word is a palindrome permutation.
        return true;
    }

    // Yields the number of characters in a given string excluding spaces
    public static int trueCount(String word){
        int count = 0;
        for(int i = 0; i < word.length(); i++){
            if(word.charAt(i) != ' '){
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args){
        String word1 = "cato tac";
        String word2 = "rcaecar";
        String word3 = "ottojffj";
        String word4 = "hello";
        System.out.println(palPerCheck(word1)); // true
        System.out.println(palPerCheck(word2)); // true
        System.out.println(palPerCheck(word3)); // true
        System.out.println(palPerCheck(word4)); // false
    }
}
