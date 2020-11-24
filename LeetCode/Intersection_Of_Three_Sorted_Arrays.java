/* Noah Park

Given three integer arrays arr1, arr2 and arr3 sorted in strictly increasing order, return a sorted array of only the integers that appeared in all three arrays.

*/

class Solution {
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        List<Integer> res = new ArrayList<>();
        int p1 = 0, p2 = 0, p3 = 0; // Three pointers for each array
        
        // loop until we finish one of the arrays
        while(p1 < arr1.length && p2 < arr2.length && p3 < arr3.length){
            int n1 = arr1[p1], n2 = arr2[p2], n3 = arr3[p3];
            if(n1 == n2 && n2 == n3) { res.add(n1); p1++; p2++; p3++; } // match
            else if(n1 < n2) p1++; // p1 is too small
            else if(n2 < n3) p2++; // p2 is too small
            else p3++; // p3 is too small
        }
        
        return res;
    }
}
