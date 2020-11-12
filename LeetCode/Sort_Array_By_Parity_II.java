/* Noah Park

Given an array A of non-negative integers, half of the integers in A are odd, and half of the integers are even.

Sort the array so that whenever A[i] is odd, i is odd; and whenever A[i] is even, i is even.

You may return any answer array that satisfies this condition.

*/

class Solution {
    public int[] sortArrayByParityII(int[] A) {
        // Initially I looked for all evens to fill in each even place
        // int evenPtr = 0;
        // for(int i = 0; i < A.length; i++){
        //     while(evenPtr < A.length && A[evenPtr] % 2 == 0) evenPtr += 2;
        //     if(evenPtr > A.length - 1) break;
        //     if(A[i] % 2 == 0 && i % 2 != 0){
        //         int temp = A[evenPtr];
        //         A[evenPtr] = A[i];
        //         A[i] = temp;
        //         evenPtr += 2;
        //     }
        // }
        // return A;
        
        // This is a bit better, as we are looking for the first occurence of an even number to swap with the odd number when both are out of place.
        // i is always on even and oddPtr is always on odd.
        int oddPtr = 1;
        for(int i = 0; i < A.length; i += 2){
            if(A[i] % 2 != 0){
                while(A[oddPtr] % 2 == 1) oddPtr += 2;
                
                int temp = A[i];
                A[i] = A[oddPtr];
                A[oddPtr] = temp;
            }
        }
        
        return A;
    }
}
