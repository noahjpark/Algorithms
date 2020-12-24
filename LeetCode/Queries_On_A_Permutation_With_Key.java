/* Noah Park

Given the array queries of positive integers between 1 and m, you have to process all queries[i] (from i=0 to i=queries.length-1) according to the following rules:

In the beginning, you have the permutation P=[1,2,3,...,m].
For the current i, find the position of queries[i] in the permutation P (indexing from 0) and then move this at the beginning of the permutation P. Notice that the position of queries[i] in P is the result for queries[i].
Return an array containing the result for the given queries.

*/

class Solution {
    public int[] processQueries(int[] queries, int m) {
        int[] arr = new int[m]; // array with values 1 through m
        for (int i = 0; i < m; i++) arr[i] = i + 1;
        
        int[] res = new int[queries.length]; // resulting positions list
        int ptr = 0;
        
        for (int query : queries) { // iterate through queries
            for (int i = 0; i < m; i++) { // find query in array
                if (query == arr[i]) { // when we find it, add its position to the resulting list
                    res[ptr++] = i;
                    for (int j = i; j > 0; j--) { // move to the front
                        int temp = arr[j - 1];
                        arr[j - 1] = arr[j];
                        arr[j] = temp;
                    }
                }
            }
        }
        
        return res;
    }
}
