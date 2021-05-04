/* Noah Park

You are given coordinates, a string that represents the coordinates of a square of the chessboard. Below is a chessboard for your reference.



Return true if the square is white, and false if the square is black.

The coordinate will always represent a valid chessboard square. The coordinate will always have the letter first, and the number second.

*/

class Solution {
    
    // Intuition: Utilize the counting sort approach to store the starting squares for 'a' through 'h' on the chess board. From here we notice that every even space (starting from zero) is matching the starting square and every odd one is flipped. Thus, we flip the value based on what coordinates at its 1 index gives us if it is odd.
    // Time and Space: O(1) constant since the chessboard is 8x8.
    public boolean squareIsWhite(String coordinates) {
        boolean[] start = new boolean[8];
        
        for (int i = 1; i < 8; i += 2)
            start[i] = true;
        
        return (coordinates.charAt(1) - '1') % 2 == 0 ? start[coordinates.charAt(0) - 'a'] : !start[coordinates.charAt(0) - 'a'];
    }
}
