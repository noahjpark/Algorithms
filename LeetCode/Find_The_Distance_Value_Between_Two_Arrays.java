/* Noah Park

Given two integer arrays arr1 and arr2, and the integer d, return the distance value between the two arrays.

The distance value is defined as the number of elements arr1[i] such that there is not any element arr2[j] where |arr1[i]-arr2[j]| <= d.

*/

class Solution {
    // Time: O(m log m + n log m) where n is the length of arr1 and m is the length of arr2
    // Space: O(1)
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int res = 0, n = arr2.length;
        
        // sort to apply binary search
        Arrays.sort(arr2);
        
        // find the smallest difference and see if it is larger than d
        for (int num : arr1) {
            int minDistance = binarySearch(arr2, num);
            
            if (minDistance > d) res++;
        }
        
        return res;
    }
    
    // binary search to find the closest value to num
    public int binarySearch(int[] arr2, int num) {
        int left = 0, right = arr2.length - 1;
        
        // iterate until left and right are next to each other
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            
            if (arr2[mid] < num) left = mid;
            else right = mid;
        }
        
        // left and right will be the indices of the two closest values to num, take the smaller difference
        return Math.min(Math.abs(num - arr2[left]), Math.abs(num - arr2[right]));
    }
    
    // Time: O(n*m) where n is the length of arr1 and m is the length of arr2
    // Space: O(1)
    public int findTheDistanceValueBruteForce(int[] arr1, int[] arr2, int d) {
        int res = 0;
        
        // Intuition: count all differences that are greater than d and increment our result each time all differences were larger than d
        for (int num : arr1) {
            int count = 0;
            
            for (int val : arr2)
                if (Math.abs(num - val) > d) count++;
        
            
            if (count == arr2.length) res++; // all differences greater than d
        }
    
        return res;
    }
}
