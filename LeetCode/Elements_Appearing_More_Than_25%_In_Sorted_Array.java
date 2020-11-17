/* Noah Park

Given an integer array sorted in non-decreasing order, there is exactly one integer in the array that occurs more than 25% of the time.

Return that integer.

*/

class Solution {
    public int findSpecialInteger(int[] arr) {
        double target = arr.length * 0.25;
        for(int i = 0; i < arr.length; i++){
            int cur = arr[i];
            int count = 0;
            while(i < arr.length && cur == arr[i]){
                i++;
                count++;
            }
            if(count > target) return cur;
        }
        return -1;
    }
}
