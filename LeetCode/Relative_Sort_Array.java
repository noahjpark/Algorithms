/* Noah Park

Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements in arr2 are also in arr1.

Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2.  Elements that don't appear in arr2 should be placed at the end of arr1 in ascending order.

*/

class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        // Store the counts in order in an array since we know the maximum size is 1000
        int[] counts = new int[1001];
        for(int num : arr1)
            counts[num]++;
        
        // Use each number in arr2 to populate arr1, note that the values in arr1 have been stored in counts so we can override everything
        int idx = 0;
        for(int num : arr2){
            while(counts[num] > 0){
                arr1[idx++] = num;
                counts[num]--;
            }
        }
        
        // Get the remaining values, which will be in ascending order naturally, and populate arr1
        for(int i = 0; i < counts.length; i++){
            while(counts[i] > 0){
                arr1[idx++] = i;
                counts[i]--;
            }
        }
        
        return arr1;
    }
}
