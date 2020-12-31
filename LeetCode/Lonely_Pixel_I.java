/* Noah Park

Given an m x n picture consisting of black 'B' and white 'W' pixels, return the number of black lonely pixels.

A black lonely pixel is a character 'B' that located at a specific position where the same row and same column don't have any other black pixels.

*/

class Solution {
    public int findLonelyPixel(char[][] picture) {
        if (picture == null || picture.length == 0) return 0; // edge cases
    
        int rows = picture.length, cols = picture[0].length;
        int[] row = new int[rows], col = new int[cols]; // use frequency array instead of hashmap for faster access
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (picture[i][j] == 'B') { // accumulate marks at each row and column
                    row[i]++;
                    col[j]++;
                }
            }
        }
        
        int lonely = 0;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (picture[i][j] == 'B' && row[i] == 1 && col[j] == 1) lonely++; // a 'B' is lonely if it is the only one in its row/column
            }
        }
        
        return lonely;
    }
}
