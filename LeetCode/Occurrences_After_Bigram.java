/* Noah Park

Given words first and second, consider occurrences in some text of the form "first second third", where second comes immediately after first, and third comes immediately after second.

For each such occurrence, add "third" to the answer, and return the answer.

*/

class Solution {
    
    // Intuition: Straightforward algorithm. Split the words by spaces then as long as there is room in the array, check if the current word matches first, the next matches second, then add the following one.
    // Time: O(n) to iterate through the words in the sentence
    // Space: O(1) not counting the return list
    public String[] findOcurrences(String text, String first, String second) {
        String[] words = text.split(" ");
        List<String> res = new ArrayList<>();
        
        for (int i = 0; i < words.length; i++) 
            if (i < words.length - 2 && words[i].equals(first) && words[i + 1].equals(second)) res.add(words[i + 2]);
        
        return res.toArray(new String[res.size()]);
    }
}
