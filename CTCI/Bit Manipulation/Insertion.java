// Noah Park
/*

Problem: You are given two 32-bit numbers, N and M, and two bit positions,
i and j. Write a method to insert M into N such that M starts at bit j
and ends at bit i. You can assume that the bits j through i have enough space
to fit all of M. That is, if M = 10011, you can assume that there are at least
5 bits between j and i. You would not, for example, have j = 3 and i = 2,
because M could not fully fit between bit 3 and bit 2.

*/

public class Insertion {

    /*

    Thought Process:
    1. Clear the bits in N between j and i
    2. Shift M over to match N
    3. Or M into N

    */
    public static int insert(int n, int m, int i, int j){
        // clearMask is all ones above the J bit
        // maintainEnd is all ones after the i bit
        // We put them together to clear all the bits from
        // i through j in N
        int clearMask = (-1 << (j + 1));
        int maintainEnd = (1 << i) - 1;
        clearMask |= maintainEnd;

        n &= clearMask; // Clear middle bits

        // Shift m over i bits to match what we cleared in n
        m <<= i;

        // Or the bits together
        n |= m;

        // Return our combined number
        return n;
    }

    // Basic testing of the class
    public static void main(String[] args){
        int n = 0B10000000000;
        int m = 0B10011;
        int i = 2;
        int j = 6;
        System.out.println(Integer.toBinaryString(insert(n, m, i, j)));
    }
}
