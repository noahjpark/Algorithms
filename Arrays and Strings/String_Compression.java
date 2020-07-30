public class String_Compression {

    /*

      Implement a method to perform basic string compression using
      the counts of repeated characters.

     */
    public static String compress(String s){
        // compressed will be the compressed string
        String compressed = "";

        // Use i to iterate over the string
        int i = 0;
        while(i < s.length()){
            // Build each substring
            String cur = "";
            cur += s.charAt(i);
            // Count the number of letter occurrences
            int count = 1;
            // Compare the rest of the letters until we find
            // a mismatch
            for (int j = i + 1; j < s.length(); j++) {
                // If equal, add to the count and check if we
                // are at the end and break if so
                // Sets i to j so the loop will end after
                // concatenating the current substring
                if(s.charAt(i) == s.charAt(j)){
                    count++;
                    if(j == s.length() - 1){
                        i = j;
                        break;
                    }
                }
                // If not equal, set i to j - 1 and i will be
                // adjusted/incremented at the end
                // Breaks from the j loop
                else{
                    i = j - 1;
                    break;
                }
            }
            // Concatenate the substring and increment i
            cur += String.valueOf(count);
            i += 1;
            compressed += cur;
        }
        // Return the new string if its length is less than s
        return compressed.length() < s.length() ? compressed : s;
    }

    // Much better time complexity using a string builder
    public static String compress2(String s){
        // compressed will be the compressed string using a string builder
        StringBuilder compressed = new StringBuilder();
        // start the count at 0
        int count = 0;
        for(int i = 0; i < s.length(); i++){
            // Increment for each letter
            count += 1;

            // If the next letter is at the end or that does not match our current letter
            if(i + 1 >= s.length() || s.charAt(i) != s.charAt(i + 1)){
                // Append the necessary information and reset count
                compressed.append(s.charAt(i));
                compressed.append(count);
                count = 0;
            }
        }
        // Return the new string if its length is less than s
        return compressed.length() < s.length() ? compressed.toString() : s;
    }

    public static void main(String[] args){
        String s = "aabcccccaaa";
        String c = compress(s);
        System.out.println(c);
    }

}
