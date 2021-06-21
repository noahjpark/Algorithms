/* Noah Park

Given an integer numRows, return the first numRows of Pascal's triangle.

In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:

*/

class Solution {
    
    // Intuition: Build the first two rows manually, then utilize the previous row to build the next recurring rows.
    // Time: O(n*m) to iterate over the number of rows and the previous row in the sequence.
    // Space: O(n^2) for the resulting list.
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> first = new ArrayList<>(), second = new ArrayList<>();
        
        first.add(1); res.add(first); 
        if (numRows == 1) return res;
        
        second.add(1); second.add(1); res.add(second);
        if (numRows == 2) return res;
        
        for (int i = 2; i < numRows; i++) {
            List<Integer> prev = res.get(i - 1), cur = new ArrayList<>();
            cur.add(1);
            
            for (int j = 0; j < prev.size() - 1; j++)
                cur.add(prev.get(j) + prev.get(j + 1));
            
            cur.add(1);
            res.add(cur);
        }
        
        return res;
    }
}
