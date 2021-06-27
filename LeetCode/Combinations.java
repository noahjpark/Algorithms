/* Noah Park

Given two integers n and k, return all possible combinations of k numbers out of the range [1, n].

You may return the answer in any order.

*/

class Solution {
    
    // Intuition: Typical combinations backtracking dfs algorithm. Optimized to stop looping when we can't include any more values.
    // Time: O(k * nchoosek)
    // Space: O(nchoosek) for the result list.
    List<List<Integer>> res = new ArrayList<>();
    int n;
    
    public List<List<Integer>> combine(int nn, int k) {
        n = nn;
        combinations(1, new ArrayList<>(), k);
        return res;
    }
    
    public void combinations(int cur, List<Integer> temp, int k) {
        if (k == 0) { res.add(new ArrayList<>(temp)); return; }
        
        for (int i = cur; i + k - 1 <= n; i++) {
            temp.add(i);
            combinations(i + 1, temp, k - 1);
            temp.remove(temp.size() - 1);
        }
    }
    
}
