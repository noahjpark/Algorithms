/* Noah Park

Given a date, return the corresponding day of the week for that date.

The input is given as three integers representing the day, month and year respectively.

Return the answer as one of the following values {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}.

*/

class Solution {
    
    int[] months = new int[]{ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    String[] days = new String[]{ "Friday", "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday" };
    
    // Intuition: Count days from 1971 January 1st until the given day. Starts on a friday and wraps around the days % 7.
    // Time and Space: O(1) constant bounded.
    public String dayOfTheWeek(int day, int month, int year) {
        int totalDays = 0;
        
        for (int i = 1971; i < year; i++)
            totalDays += (i % 4 == 0) ? 366 : 365;
        
        if((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) months[1] = 29; // check if this year is a leap year or not.
        
        for (int i = 0; i < month - 1; i++)
            totalDays += months[i];
        
        totalDays += day - 1;
        
        return days[totalDays % 7];
    }
}
