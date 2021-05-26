/* Noah Park

Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.

Machine 1 (sender) has the function:

string encode(vector<string> strs) {
  // ... your code
  return encoded_string;
}
Machine 2 (receiver) has the function:
vector<string> decode(string s) {
  //... your code
  return strs;
}
So Machine 1 does:

string encoded_string = encode(strs);
and Machine 2 does:

vector<string> strs2 = decode(encoded_string);
strs2 in Machine 2 should be the same as strs in Machine 1.

Implement the encode and decode methods.

You are not allowed to solve the problem using any serialize methods (such as eval).

*/

public class Codec {

    // Intuition: Since we can't buffer each string with a character, as all characters are involved, we can store the lengths of each string in an array and just chain all strings together.
    // Time: O(n) to iterate over all letters in the strings.
    // Space: O(n) to maintain the lengths of each string.
    int[] lengths;
    
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder res = new StringBuilder();
        lengths = new int[strs.size()];
        int i = 0;
        
        for (String s : strs) {
            res.append(s);
            lengths[i++] = s.length();
        }
        
        return res.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> res = new ArrayList<>();
        int p = 0;
        
        for (int i = 0; i < lengths.length; i++) {
            res.add(lengths[i] == 0 ? "" : s.substring(p, p + lengths[i]));
            p += lengths[i];
        }
        
        return res;
    }
    
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));
