/* Noah Park

Given the array nums consisting of 2n elements in the form [x1,x2,...,xn,y1,y2,...,yn].

Return the array in the form [x1,y1,x2,y2,...,xn,yn].

*/ 

class Solution {
    public int[] shuffle(int[] nums, int n) {
        int[] shuffled = new int[nums.length];
        int idx = 0;
        int x = 0, y = nums.length / 2;
        while(x < nums.length / 2){
            shuffled[idx++] = nums[x++];
            shuffled[idx++] = nums[y++];
        }
        return shuffled;
    }
}
