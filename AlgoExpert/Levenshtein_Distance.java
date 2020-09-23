// Noah Park
/*

Problem: Write a function that takes in two strings and returns the minimum number of edit operations
that need to be performed on the first string to obtain the second string.

There are three edit operations: insertion of a character, deletion of a character, and substitution of a
character for another.

* Dynamic Programming *

*/

public class Levenshtein_Distance {
    // Time: O(N*M) | Space: O(N*M)
    public static int levenshteinDistance(String str1, String str2) {
        // Edits contains the edit comparisons between each letter of str1 and str2 starting with a blank space for each one
        // (hence the + 1 for both rows and columns)
        // Diagram is in notebook
        int[][] edits = new int[str1.length() + 1][str2.length() + 1]; // we are storing blanks as well

        // Populate the first row and first column with numbers 0 through the length of the string
        // This will represent the number of edits it takes to compare each letter. Again, this is hard
        // to picture without a diagram
        for(int col = 0; col < edits[0].length; col++){
            edits[0][col] = col;
        }
        for(int row = 0; row < edits.length; row++){
            edits[row][0] = row;
        }

        // Iterate through the edits array starting from 1,1 since we want to start comparing
        // actual letters (remember that the 0 index for both the rows and columns is a blank space)
        for(int i = 1; i < edits.length; i++){
            for(int j = 1; j < edits[0].length; j++){
                // Two cases:
                /*
                1. If the characters at (i-1, j-1) for each string match (-1 because of the blank space - need to adjust the indices), then we want to take the left upwards diagonal value that we
                just created on the previous iteration which represents the minimum number of edits so far. We don't change anything because the letters of both strings match and nothing needs to be edited.

                2. If the characters at these indices don't match, we add 1 (obviously because we have to do an edit) and take the minimum number of edits from its three valid neighbors (left, up, left up diagonal).
                Like above, we are taking the minimum number of edits, as that is what the goal of the problem is but adding one since we must make an edit here.
                */
                if(str1.charAt(i - 1) == str2.charAt(j - 1)){
                    edits[i][j] = edits[i - 1][j - 1];
                }
                else{
                    edits[i][j] = 1 + Math.min(edits[i - 1][j], Math.min(edits[i - 1][j - 1], edits[i][j - 1]));
                }
            }
        }

        // We can return the bottom right corner value that represents the minimum number of edits needed to match the strings
        return edits[str1.length()][str2.length()];
    }

    // Time: O(N*M) | Space: O(Minimum of (N,M))
    // This is optimized from above to use two arrays of the smaller string length instead of a two dimensional array of both strings length. Clearly, as the strings get large,
    // the space we are wasting will also become quite large. We notice that we are only using two particular rows at a given time, thus we only need two arrays to help us here.
    // We choose the smaller string to store, as it will optimize the space even a bit further.
    public static int levenshteinDistanceOptimal(String str1, String str2) {
        // Find which string is shorter among str1 and str2
        String shorter = str1;
        String longer = str2;
        if(shorter.length() > longer.length()){
            shorter = str2;
            longer = str1;
        }

        // Populate the 'previous' row with the values [0, shorter.length()] just like the top row represented in the unoptimized version
        int[] prev = new int[shorter.length() + 1];
        for(int i = 0; i < prev.length; i++){
            prev[i] = i;
        }
        int[] cur = new int[shorter.length() + 1]; // Initialize the cur array that will represent the current row we are looking at while the prev array represents the past row we just made

        // Iterate through the 'rows' of the arbitrary two dimensional matrix. Instead of a two dimensional matrix, however, we can just iterate through our previous row
        for(int i = 1; i < longer.length() + 1; i++){
            // Always initialize cur to a new array to build the new row
            cur = new int[shorter.length() + 1];
            cur[0] = i; // The first value of cur should always be the i value just like in the past version (blank space edits)
            for(int j = 1; j < shorter.length() + 1; j++){
                // The cases are identical. Instead of using an 'edits' two dimensional array, we use two separate 'row' arrays to do the same thing with less space
                if(shorter.charAt(j - 1) == longer.charAt(i - 1)){
                    cur[j] = prev[j - 1];
                }
                else{
                    cur[j] = 1 + Math.min(cur[j - 1], Math.min(prev[j], prev[j - 1]));
                }
            }
            // Update the prev row to the cur row. Cur will either be updated to a completely new row in the next iteration or we are done
            prev = cur;
        }

        // Return the last value in the cur row, as it will represent the minimum number of edits required to obtain str1 from str2 or vice versa
        return cur[shorter.length()];
    }
}
