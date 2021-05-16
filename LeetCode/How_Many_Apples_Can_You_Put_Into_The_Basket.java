/* Noah Park

You have some apples, where arr[i] is the weight of the i-th apple.  You also have a basket that can carry up to 5000 units of weight.

Return the maximum number of apples you can put in the basket.

*/

class Solution {
    
    // Intuition: Utilize counting sort to avoid a full sorting algorithm. This way we can always look to increase our sum until it becomes too large.
    // Time: O(n) to create the frequency map.
    // Space: O(1) constant.
    public int maxNumberOfApples(int[] arr) {
        int[] freq = new int[1001];
        int sum = 0, apples = 0, total = 0;
        
        for (int num : arr) {
            total += num;
            freq[num]++;
        }
        
        if (total <= 5000) return arr.length;
        
        for (int i = 0; i < 1001; i++) {
            if (sum + freq[i]*i == 5000) return apples + freq[i];
            else if (sum + freq[i]*i > 5000) return apples + (5000 - sum) / i;
            else {
                sum += freq[i]*i;
                apples += freq[i];
            }
        }
        
        return 0;
    }
    
    // Intuition: Sort the array then choose elements until the total sum exceeds or equals 5000.
    // Time: O(nlogn) for sorting.
    // Space: O(sort) for sorting.
    public int maxNumberOfApples2(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        
        Arrays.sort(arr);
        int sum = 0;
        
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (sum >= 5000) return i;
        }
        
        return arr.length;
    }
}
