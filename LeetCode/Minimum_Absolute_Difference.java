/* Noah Park

Given an array of distinct integers arr, find all pairs of elements with the minimum absolute difference of any two elements. 

Return a list of pairs in ascending order(with respect to pairs), each pair [a, b] follows

a, b are from arr
a < b
b - a equals to the minimum absolute difference of any two elements in arr

*/

class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        List<List<Integer>> res = new ArrayList<>();
        int diff = Integer.MAX_VALUE;
        for(int i = 0; i < arr.length - 1; i++) 
            diff = Math.min(diff, arr[i + 1] - arr[i]);
        
        for(int i = 0; i < arr.length - 1; i++)
            if(arr[i + 1] - arr[i] == diff) res.add(Arrays.asList(arr[i], arr[i + 1]));
        
        return res;
    }
}
