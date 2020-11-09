/* Noah Park

You are given an array of distinct integers arr and an array of integer arrays pieces, where the integers in pieces are distinct. 

Your goal is to form arr by concatenating the arrays in pieces in any order. However, you are not allowed to reorder the integers in each array pieces[i].

Return true if it is possible to form the array arr from pieces. Otherwise, return false.

*/

class Solution {
    public boolean canFormArray(int[] arr, int[][] pieces) {
        // Map the index from pieces using its first value from each integer array as the index into map. Else -1.
        int[] map = new int[101];
        Arrays.fill(map, -1);
        for(int i = 0; i < pieces.length; i++)
            map[pieces[i][0]] = i;
        
        // Match indices from arr into pieces
        int i = 0;
        while(i < arr.length){
            int idx = map[arr[i]]; // Get the index back into pieces - arr[i] has to be our current match
            if(idx == -1) return false; // No match exists in pieces
            int j = 0;
            while(j < pieces[idx].length) // Iterate through the elements in the integer array in pieces
                if(arr[i++] != pieces[idx][j++]) return false; // Ensure all elements match else return false. Increment i and j each iteration.
        }
        return true;
    }
}
