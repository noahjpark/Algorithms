/* Noah Park

Given a year Y and a month M, return how many days there are in that month.

*/

class Solution {
    
    int[] months = new int[]{ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    
    // Intuition: Test if year is a leap year and adjust feb if so. Return static months otherwise.
    // Time and Space: O(1) constant.
    public int numberOfDays(int Y, int M) {
        if ((Y % 4 == 0 && Y % 100 != 0) || (Y % 400 == 0)) months[1]++;
        return months[M - 1];
    }
}
