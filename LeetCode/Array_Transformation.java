/* Noah Park

Given an initial array arr, every day you produce a new array using the array of the previous day.

On the i-th day, you do the following operations on the array of day i-1 to produce the array of day i:

If an element is smaller than both its left neighbor and its right neighbor, then this element is incremented.
If an element is bigger than both its left neighbor and its right neighbor, then this element is decremented.
The first and last elements never change.
After some days, the array does not change. Return that final array.

*/

class Solution {
    
    // Intuition: Array simulation.
    public List<Integer> transformArray(int[] arr) {
        int n = arr.length;
        
        while (true) {
            int count = 0;
            int[] temp = new int[n];
            
            for (int i = 1; i < n - 1; i++) {
                if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) { temp[i]--; count++; }
                else if (arr[i] < arr[i - 1] && arr[i] < arr[i + 1]) { temp[i]++; count++; }
            }
            
            for (int i = 0; i < n; i++)
                arr[i] += temp[i];
            
            if (count == 0) break;
        }

        List<Integer> res = new ArrayList<>();
        for (int num : arr) res.add(num);
        
        return res;
    }
}
