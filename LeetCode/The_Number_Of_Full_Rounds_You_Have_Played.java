/* Noah Park

A new online video game has been released, and in this video game, there are 15-minute rounds scheduled every quarter-hour period. This means that at HH:00, HH:15, HH:30 and HH:45, a new round starts, where HH represents an integer number from 00 to 23. A 24-hour clock is used, so the earliest time in the day is 00:00 and the latest is 23:59.

Given two strings startTime and finishTime in the format "HH:MM" representing the exact time you started and finished playing the game, respectively, calculate the number of full rounds that you played during your game session.

For example, if startTime = "05:20" and finishTime = "05:59" this means you played only one full round from 05:30 to 05:45. You did not play the full round from 05:15 to 05:30 because you started after the round began, and you did not play the full round from 05:45 to 06:00 because you stopped before the round ended.
If finishTime is earlier than startTime, this means you have played overnight (from startTime to the midnight and from midnight to finishTime).

Return the number of full rounds that you have played if you had started playing at startTime and finished at finishTime.

*/

class Solution {
    
    // Intuition: First separate hours and minutes. Then get the minutes to even round starting times. If startMins goes to the end of the hour, adjust the minutes back to 0 and move the hour forward once. If we have the edge case where we are within the same hour in order, we can simply see the difference between start and end minutes. Once we have the minutes calculated we simply consider the hour cases and return the total.
    // Time and Space: O(1) technically the hours and minutes are bounded. 
    public int numberOfRounds(String startTime, String finishTime) {
        if (startTime == null || finishTime == null || startTime.length() == 0 || finishTime.length() == 0) return 0;
        
        // Separate minutes and hours from each time
        int startHours = Integer.parseInt(startTime.substring(0, 2)), endHours = Integer.parseInt(finishTime.substring(0, 2)), startMins = Integer.parseInt(startTime.substring(3)), endMins = Integer.parseInt(finishTime.substring(3)), res = 0;
        
        // adjust minutes to the nearest start time and nearest end time of actual rounds
        if (startMins % 15 != 0) {
            startMins += (15 - startMins % 15);
            if (startMins == 60) {
                startMins = 0;
                startHours++;
            }   
        }
        if (endMins % 15 != 0) endMins -= (endMins % 15);
        
        // edge case if the hours are the same and mins are in order
        if (startHours == endHours && startMins <= endMins) {
            for (; startMins < endMins; startMins += 15) res++;
            return res;
        }
        
        // normally get to even minutes then calculate hours
        for (; startMins < 60; startMins += 15) res++;
        for (; endMins > 0; endMins -= 15) res++;
        
        // calculate hour cases
        if (endHours < startHours) res += startMins == 0 ? (24 - startHours + endHours) * 4 : (23 - startHours + endHours) * 4;
        else if (startHours == endHours && endMins < startMins) res += 23*4;
        else res += startMins == 0 ? (endHours - startHours) * 4: (endHours - (startHours + 1)) * 4;
        
        return res;
    }
    
}
