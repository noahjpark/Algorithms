/* Noah Park

We have n chips, where the position of the ith chip is position[i].

We need to move all the chips to the same position. In one step, we can change the position of the ith chip from position[i] to:

position[i] + 2 or position[i] - 2 with cost = 0.
position[i] + 1 or position[i] - 1 with cost = 1.
Return the minimum cost needed to move all the chips to the same position.

*/

class Solution {
    public int minCostToMoveChips(int[] position) {
        // This is really a problem of counting even and odd positions. Since we can move two position for free, we just take the minimum of the odd or even positions and return it as our minimum cost to put all chips to the same position.
        
        int even = 0, odd = 0;
        for(int num : position){
            if(num % 2 == 0) even++;
            else odd++;
        }
        
        return Math.min(even, odd);
    }
}
