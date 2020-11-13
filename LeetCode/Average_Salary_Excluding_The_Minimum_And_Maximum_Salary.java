/* Noah Park

Given an array of unique integers salary where salary[i] is the salary of the employee i.

Return the average salary of employees excluding the minimum and maximum salary.

*/

class Solution {
    public double average(int[] salary) {
        int min = Integer.MAX_VALUE, max = 0;
        double avg = 0;
        for(int s : salary){
            min = Math.min(min, s);
            max = Math.max(max, s);
            avg += s;
        }
        
        avg -= (min + max);
        avg /= (salary.length - 2);
        
        return avg;
    }
}
