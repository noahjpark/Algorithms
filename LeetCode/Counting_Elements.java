/* Noah Park

Given an integer array arr, count how many elements x there are, such that x + 1 is also in arr.

If there're duplicates in arr, count them seperately.

*/

class Solution {
    public int countElements(int[] arr) {
        HashSet<Integer> set = new HashSet<>(); // don't need to store all duplicates
        
        // add all numbers to the set
        for (int num : arr)
            set.add(num);
        
        int elements = 0;
        
        // if we contain the next in the sequence in the set, we count the current number
        for (int num : arr) 
            if (set.contains(num + 1)) elements++;
    
        
        return elements;
    }
}
