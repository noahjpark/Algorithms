/* 

Noah Park

Problem:

Given two strings, write a method to decide if one is a permutation of the other

*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;

public class Check_Permutation {

    // Time: O(n)
    // Space: O(n)
    public static boolean checkPermutationSort(String word1, String word2){
        if(word1.length() != word2.length()){   // Can't be a permutation if the lengths of word1 and word2 are different
            return false;
        }
        ArrayList<Character> w1 = new ArrayList<>();    // Initialize an array list for both word1 and word2
        ArrayList<Character> w2 = new ArrayList<>();
        for(int i = 0; i < word1.length(); i++){    // Add the characters from each word to their corresponding array list
            w1.add(word1.charAt(i));
            w2.add(word2.charAt(i));
        }
        Collections.sort(w1);                       // Use the collections sort method to sort both array lists
        Collections.sort(w2);
        for(int i = 0; i < word1.length(); i++){    // Compare each index of each array list to ensure matching letters
            if(w1.get(i) != w2.get(i)){
                return false;                       // If the letters don't match, word1 is not a permutation of word2
            }
        }
        return true;                                // All the letters matched, so word1 is a permutation of word2
    }

    // Time: O(n^2)
    // Space: O(n)
    // This method was having issues I'm leaving it as a way to see where my logic was at
    // I decided not to fix the issues as the solution above is more efficient anyways
    public static boolean checkPermutationHash(String word1, String word2){
        if(word1.length() != word2.length()){   // Can't be a permutation if the lengths are different
            return false;
        }
        int check = 0;  // This will count for each matching letter
        Hashtable<Integer, Character> ht = new Hashtable<>();   // Store the letters of word2 to check against word1
        for(int j = 0; j < word2.length(); j++){      // Iterate through word2 and store in the hash table at key j
            ht.put(j, word2.charAt(j));
        }
        for(int i = 0; i < word1.length(); i++){    // Iterate through word1
            if(ht.contains(word1.charAt(i))){       // If the hash table contains the character at word1 we increment check
                check++;
                for(int j = 0; j < ht.size(); j++){ // Then we need to go through the hash table and remove the checked letter as to avoid duplicate counting
                    if (ht.get(j) == word1.charAt(i)) { // SOME ISSUE IS OCCURRING IN THIS FOR LOOP
                        ht.remove(j);
                    }
                }
            } else{ // If the hash table does not contain the letter from word1, then word2 is clearly not a permutation
                return false;
            }
        }
        return check == word1.length(); // returns false if we couldn't find all the letters from word1 in word2 and true if we did
    }

    public static void main(String[] args){
        String w1 = "paper";
        String w2 = "repap";
        String w3 = "hello";
        String w4 = "lleho";
        //System.out.println(checkPermutationHash(w1, w2));
        System.out.println(checkPermutationSort(w3, w4));
    }
}
