// Noah Park
/*

Problem: Write a function that takes in an array of strings and groups anagrams together.

Anagrams are strings made up of exactly the same letters, where order doesn't matter. For example,
"cinema" and "iceman" are anagrams; similarly, "foo" and "ofo" are anagrams.

Your function should return a list of anagram groups in no particular order.

*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Group_Anagrams {
    // Optimal Solution
    // Time: O(w*n*log(n)) | Space: O(w*n) where w is the number of words and n is the length of the longest word
    public static List<List<String>> groupAnagramsOptimal(List<String> words) {
        // We will store the anagrams in a hashmap. The key will be the string in
        // sorted alphabetical order while the value is a list of the actual word itself
        HashMap<String, List<String>> anagrams = new HashMap<>();

        // Iterate through the strings in the given array
        for(String s : words){
            // Sort the string by putting it into a char array then obtaining a
            // String back from the sorted character array
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);

            // If the hashmap contains the sorted string, simply add to the arraylist at that
            // key using the original, unsorted string.
            // Otherwise, put a new arraylist with the unsorted string (as a list) at the key of the sorted string
            if(anagrams.containsKey(sorted)) anagrams.get(sorted).add(s);
            else anagrams.put(sorted, new ArrayList<>(Arrays.asList(s)));
        }

        // Return the values as an array list
        return new ArrayList<>(anagrams.values());
    }

    // Naive solution
    // Time: O(n^2*m) where n is the number of words and m is the length of a given string | Space(n)
    public static List<List<String>> groupAnagrams(List<String> words) {
        // anagrams stores the lists of each anagram
        // checked lets us know that we already checked a word
        ArrayList<List<String>> anagrams = new ArrayList<>();
        ArrayList<String> checked = new ArrayList<>();

        // Nested for loops to compare all words
        for(int i = 0; i < words.size(); i++){
            // If we already checked a word, continue to the next iteration
            // Otherwise, make a new array list and add the current word to it
            // Also, add the word to the checked list
            if(checked.contains(words.get(i))) continue;
            ArrayList<String> group = new ArrayList<>();
            group.add(words.get(i));
            checked.add(words.get(i));
            for(int j = i + 1; j < words.size(); j++){
                // If the current word we are at is an anagram of the i index and we haven't checked it yet,
                // add it to the anagram group and the checked list
                if(isAnagram(words.get(i), words.get(j)) && !checked.contains(words.get(j))){
                    group.add(words.get(j));
                    checked.add(words.get(j));
                }
            }
            // Add the built group of anagrams to the anagrams list
            anagrams.add(group);
        }

        return anagrams;
    }

    // Checks if two strings are anagrams of each other
    public static boolean isAnagram(String s1, String s2){
        if(s1.length() != s2.length()){
            return false;
        }

        HashMap<Character, Integer> h1 = new HashMap<>();
        HashMap<Character, Integer> h2 = new HashMap<>();

        for(int i = 0; i < s1.length(); i++){
            if(!h1.containsKey(s1.charAt(i))) h1.put(s1.charAt(i), 1);
            else h1.put(s1.charAt(i), h1.get(s1.charAt(i)) + 1);

            if(!h2.containsKey(s2.charAt(i))) h2.put(s2.charAt(i), 1);
            else h2.put(s2.charAt(i), h2.get(s2.charAt(i)) + 1);
        }

        for(Character c : h1.keySet()){
            if(h1.get(c) != h2.get(c)){
                return false;
            }
        }

        return true;
    }
}
