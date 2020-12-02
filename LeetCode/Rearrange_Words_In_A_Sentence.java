/* Noah Park

Given a sentence text (A sentence is a string of space-separated words) in the following format:

First letter is in upper case.
Each word in text are separated by a single space.
Your task is to rearrange the words in text such that all words are rearranged in an increasing order of their lengths. If two words have the same length, arrange them in their original order.

Return the new text following the format shown above.

*/

class Solution {
    public String arrangeWords(String text) {
        String[] n = text.split(" "); // split each word into an array for sorting
        StringBuilder res = new StringBuilder(); // resulting string
        
        // custom comparator sorts based on lengths of each string
        Comparator<String> c = new Comparator<>() {
            public int compare(String s1, String s2) {
                return Integer.compare(s1.length(), s2.length());
            }
        };
        
        Arrays.sort(n, c); // sort using the custom comparator
        
        // append each word in lower case with a space after
        // this will result in all lowercase words with an extra space at the end
        for(String s : n) res.append(s.toLowerCase()).append(" ");
        
        // capitalize the first letter and exclude the last space
        return res.toString().substring(0, 1).toUpperCase() + res.toString().substring(1, res.length() - 1);
    }
}
