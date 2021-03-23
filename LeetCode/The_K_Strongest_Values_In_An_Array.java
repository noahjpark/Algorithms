/* Noah Park

Given an array of integers arr and an integer k.

A value arr[i] is said to be stronger than a value arr[j] if |arr[i] - m| > |arr[j] - m| where m is the median of the array.
If |arr[i] - m| == |arr[j] - m|, then arr[i] is said to be stronger than arr[j] if arr[i] > arr[j].

Return a list of the strongest k values in the array. return the answer in any arbitrary order.

Median is the middle value in an ordered integer list. More formally, if the length of the list is n, the median is the element in position ((n - 1) / 2) in the sorted list (0-indexed).

For arr = [6, -3, 7, 2, 11], n = 5 and the median is obtained by sorting the array arr = [-3, 2, 6, 7, 11] and the median is arr[m] where m = ((5 - 1) / 2) = 2. The median is 6.
For arr = [-7, 22, 17, 3], n = 4 and the median is obtained by sorting the array arr = [-7, 3, 17, 22] and the median is arr[m] where m = ((4 - 1) / 2) = 1. The median is 3.

*/

class Solution {
    // Time: O(n log n) single sort
    // Space: O(1)
    public int[] getStrongest(int[] arr, int k) {
        if (arr == null || arr.length == 0) return new int[]{}; // edge cases
        
        Arrays.sort(arr);
        int median = arr[(arr.length - 1) / 2], start = 0, end = arr.length - 1;
        int[] res = new int[k];
        
        for (int i = 0; i < k; i++) {
            int d1 = Math.abs(arr[start] - median), d2 = Math.abs(arr[end] - median);
            if (d1 == d2) res[i] = arr[end--];
            else res[i] = d1 > d2 ? arr[start++] : arr[end--];
        }
            
        return res;
    }
    
    // Time: O(n log n) two pass sort
    // Space: O(n) for the sorting list
    public int[] getStrongestCustomSort(int[] arr, int k) {
        if (arr == null || arr.length == 0) return new int[]{}; // edge cases
        
        Arrays.sort(arr);
        int median = arr[(arr.length - 1) / 2];
        
        List<Integer> a = new ArrayList<>();
        
        for (int num : arr) a.add(num);
        
        Comparator<Integer> c = new Comparator<>() {
            public int compare(Integer i, Integer j) {
                int d1 = Math.abs(i - median), d2 = Math.abs(j - median);
                if (d1 == d2) return Integer.compare(j, i);
                return Integer.compare(d2, d1);
            }
        };
        
        Collections.sort(a, c);
        
        int[] res = new int[k];
        
        for (int i = 0; i < k; i++) 
            res[i] = a.get(i);
        
        return res;
    }
}
