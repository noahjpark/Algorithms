/* Noah Park

Alice and Bob have candy bars of different sizes: A[i] is the size of the i-th bar of candy that Alice has, and B[j] is the size of the j-th bar of candy that Bob has.

Since they are friends, they would like to exchange one candy bar each so that after the exchange, they both have the same total amount of candy.  (The total amount of candy a person has is the sum of the sizes of candy bars they have.)

Return an integer array ans where ans[0] is the size of the candy bar that Alice must exchange, and ans[1] is the size of the candy bar that Bob must exchange.

If there are multiple answers, you may return any one of them.  It is guaranteed an answer exists.

*/

class Solution {
    public int[] fairCandySwap(int[] A, int[] B) {
        int sum1 = 0, sum2 = 0;
        Set<Integer> set = new HashSet<>();
        
        for(int i : A)
            sum1 += i;
        for(int i : B){
            sum2 += i;
            set.add(i);
        }
        
        // Arrays.sort(A);
        // Arrays.sort(B);
        // int p1 = A.length - 1;
        // int p2 = B.length - 1;
        // while(p1 > -1 && p2 > -1){
        //     int s1 = sum1 - A[p1] + B[p2];
        //     int s2 = sum2 - B[p2] + A[p1];
        //     if(s1 == s2) return new int[]{A[p1], B[p2]};
        //     else if(s1 < s2) p1--;
        //     else p2--;
        // }
        
        int d = (sum2 - sum1) / 2;
        for(int i : A)
            if(set.contains(i + d)) return new int[]{i, i + d};
        
        return new int[]{};
    }
}
