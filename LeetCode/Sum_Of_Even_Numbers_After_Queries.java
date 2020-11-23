/* Noah Park

We have an array A of integers, and an array queries of queries.

For the i-th query val = queries[i][0], index = queries[i][1], we add val to A[index].  Then, the answer to the i-th query is the sum of the even values of A.

(Here, the given index = queries[i][1] is a 0-based index, and each query permanently modifies the array A.)

Return the answer to all queries.  Your answer array should have answer[i] as the answer to the i-th query.

*/

class Solution {
    public int[] sumEvenAfterQueries(int[] A, int[][] queries) {
        // res stores the result after each query
        int[] res = new int[A.length];
        int i = 0, evens = 0; // i is the index into res and evens is our even sum
        
        // get starting even sum
        for(int num : A)
            if(num % 2 == 0) evens += num;
        
        // do each query
        for(int[] query : queries){
            int val = query[0], idx = query[1];
            if(A[idx] % 2 == 0) evens -= A[idx]; // if the value was included in the even sum, subtract it
            A[idx] += val; // do the query
            if(A[idx] % 2 == 0) evens += A[idx]; // if the value is even after the query, put it into even sum
            res[i++] = evens; // update the after query values
        }
        
        return res;
    }
}
