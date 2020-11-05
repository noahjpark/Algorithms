/* Noah Park

There are N dominoes in a line, and we place each domino vertically upright.

In the beginning, we simultaneously push some of the dominoes either to the left or to the right.



After each second, each domino that is falling to the left pushes the adjacent domino on the left.

Similarly, the dominoes falling to the right push their adjacent dominoes standing on the right.

When a vertical domino has dominoes falling on it from both sides, it stays still due to the balance of the forces.

For the purposes of this question, we will consider that a falling domino expends no additional force to a falling or already fallen domino.

Given a string "S" representing the initial state. S[i] = 'L', if the i-th domino has been pushed to the left; S[i] = 'R', if the i-th domino has been pushed to the right; S[i] = '.', if the i-th domino has not been pushed.

Return a string representing the final state. 

*/

class Solution {
    public String pushDominoes(String dominoes) {
        char[] fall = dominoes.toCharArray();
        int i = 0, left = -1, right = -1;
        int n = dominoes.length();
        
        while(i <= n){
            if(i == n || dominoes.charAt(i) == 'R'){
                // If right > left, we should fill in 'R' between the previous seen 'R' and the current one at i
                if(right > left){
                    while(right < i)
                        fall[right++] = 'R';
                }
                right = i;
            }
            else if(dominoes.charAt(i) == 'L') {
                // If left > right or we are at the initial position and find a left character, we should mark all characters as 'L' since we only have the 'L' direction so far.
                if(left > right || (left == -1 && right == -1)){
                    while(++left < i)
                        fall[left] = 'L';
                }
                // If right > left, it means that our most recently seen direction is right and we have now found a left in front of it.
                // We must move inwards until they bunch up.
                else {
                    left = i;
                    int l = right + 1, r = left - 1;
                    while(l < r){
                        fall[l++] = 'R';
                        fall[r--] = 'L';
                    }
                }
            }
            
            i++;
        }
        
        return new String(fall);
    }
}
