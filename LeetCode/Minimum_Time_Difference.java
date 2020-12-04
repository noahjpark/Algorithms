/* Noah Park

Given a list of 24-hour clock time points in "HH:MM" format, return the minimum minutes difference between any two time-points in the list.

*/

class Solution {
    public int findMinDifference(List<String> timePoints) { // utilize counting sort
        int[] freq = new int[1440]; // optimize storage by storing a frequency list of each minute value
        int firstTime = Integer.MAX_VALUE, lastTime = Integer.MIN_VALUE; // pre process the starting and ending times for later
        for (String time : timePoints) {
            String hours = time.substring(0, 2), minutes = time.substring(3, time.length());
            int t = Integer.parseInt(hours) * 60 + Integer.parseInt(minutes);
            firstTime = Math.min(firstTime, t); lastTime = Math.max(lastTime, t); // pre process
            freq[t]++; // store frequency
        }
        
        // optimize by going from firstTime through lastTime
        // always comparing adjacent times
        int p1 = firstTime, p2 = firstTime + 1, min = Integer.MAX_VALUE;
        while (p2 < lastTime + 1) {
            if (freq[p2] > 1 || freq[p1] > 1) return 0; // if the frequency is larger than 1, difference can be 0, since we have multiple occurrences of the same time
            else if (freq[p2] == 1) { min = Math.min(min, p2 - p1); p1 = p2; } // take minimum of the current difference and the difference between the two frequencies and move p1 to the next
            p2++;
        }
        
        return Math.min(min, 1440 + firstTime - lastTime); // final edge case to check wrap around from the final value to 0
    }
    
    public int findMinDifferenceSuboptimal(List<String> timePoints) {
        ArrayList<Integer> res = new ArrayList<>(); // store times for sort
        for (String time : timePoints) { // iterate over all times and convert to minute values
            String hours = time.substring(0, 2), minutes = time.substring(3, time.length());
            int h = Integer.parseInt(hours), m = Integer.parseInt(minutes);
            res.add(h * 60 + m); // add total minutes for each time
        }
        
        Collections.sort(res); // sort res
        res.add(1440 + res.get(0)); // this is a wrapper value to check the first to the last time
        
        // take the minimum difference between any two times
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < res.size() - 1; i++)
            min = Math.min(min, res.get(i + 1) - res.get(i));
        
        return min;
    }
}
