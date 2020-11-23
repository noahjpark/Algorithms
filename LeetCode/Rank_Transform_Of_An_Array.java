/* Noah Park

Given an array of integers arr, replace each element with its rank.

The rank represents how large the element is. The rank has the following rules:

Rank is an integer starting from 1.
The larger the element, the larger the rank. If two elements are equal, their rank must be the same.
Rank should be as small as possible.
 

*/

class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int n = arr.length;
        Map<Integer, Integer> rank = new HashMap<>(); // store the ranks of each value in the arr
        int[] res = Arrays.copyOf(arr, n); // deep copy
        Arrays.sort(res); // sort the array so that we can go in order to increase rank
        
        for(int num : res)
            rank.putIfAbsent(num, rank.size() + 1); // At each new value, we can get the rank from the size of the map + 1
        
        // We can get the rank from each spot in the arr and insert them into the sort array.
        for(int i = 0; i < n; i++)
            res[i] = rank.get(arr[i]);
        
        return res;
    }
}
