/* Noah Park

Given an integer array arr, return the mean of the remaining integers after removing the smallest 5% and the largest 5% of the elements.

Answers within 10-5 of the actual answer will be considered accepted.

*/

class Solution {
    public double trimMean(int[] arr) {
        Arrays.sort(arr);
        int percent = (int) (arr.length * 0.05);
        double avg = 0;
        for(int i = percent; i < arr.length - percent; i++)
            avg += arr[i];
            
        avg /= (arr.length - (percent * 2));
        return avg;
    }
}
