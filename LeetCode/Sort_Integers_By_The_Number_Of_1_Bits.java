/* Noah Park

Given an integer array arr. You have to sort the integers in the array in ascending order by the number of 1's in their binary representation and in case of two or more integers have the same number of 1's you have to sort them in ascending order.

Return the sorted array.

*/

class Solution {
    // Time: O(n log n)
    // Space: O(n)
    public int[] sortByBits(int[] arr) {
        Comparator<int[]> c = new Comparator<>() { // sorts based on bit count otherwise in ascending order if bits are the same
            public int compare(int[] i, int[] j) {
                int val1 = i[1], val2 = j[1];
                if (val1 != val2) return Integer.compare(val1, val2);
                return Integer.compare(i[0], j[0]);
            }
        };
        
        // temp array for sorting
        int[][] temp = new int[arr.length][2];
        
        // put element with bit count
        for (int i = 0; i < arr.length; i++)
            temp[i] = new int[]{ arr[i], countOnes(arr[i]) };
    
        // sort with custom comparator
        Arrays.sort(temp, c);
        
        int[] res = new int[arr.length];
        
        // put just the elements into their corresponding positions after sorting
        for (int i = 0; i < res.length; i++) 
            res[i] = temp[i][0];
        
        return res;
    }
    
    // Time: O(log n)
    // Space: O(1)
    public int countOnes(int num) {
        int count = 0;
        
        while (num > 0) {
            if ((num & 1) == 1) count++;
            num >>= 1;
        }
        
        return count;
    }
}
