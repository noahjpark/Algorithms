/* Noah Park

Given an integer n, return any array containing n unique integers such that they add up to 0.

*/

class Solution {
    public int[] sumZero(int n) {
        int num = 1, idx = 0;
        int[] res = new int[n];
        
        // All we need to do is add all numbers and their complements until we have n numbers or n - 1 numbers. The sum of all numbers will be 0 then.
        while(num * 2 <= n){
            res[idx] = num;
            res[idx + 1] = (num * -1);
            idx += 2;
            num++;
        }
        
        // Finally, we can add a 0 value if we have an odd number n.
        if(n % 2 == 1) res[n - 1] = 0;
        
        return res;
    }
}
