/* Noah Park

Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. The result should also be sorted in ascending order.

An integer a is closer to x than an integer b if:

|a - x| < |b - x|, or
|a - x| == |b - x| and a < b

*/

class Solution {
    
    // Intuition: Binary search to the closest element to x then expand left/right until the result is full.
    // Time: O(log n + k) for bs then expansion.
    // Space: O(k) for resulting list.
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0, right = arr.length - 1;
        
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] < x) left = mid;
            else right = mid;
        }
        
        List<Integer> res = new ArrayList<>();
        
        while (right < arr.length && arr[right] == x && k-- > 0) res.add(arr[right++]);
        while (k-- > 0) {
            if (left < 0) res.add(arr[right++]);
            else if (right >= arr.length) res.add(0, arr[left--]);
            else if (Math.abs(arr[left] - x) <= Math.abs(arr[right] - x)) res.add(0, arr[left--]);
            else res.add(arr[right++]);
        }
        
        return res;
    }
}
