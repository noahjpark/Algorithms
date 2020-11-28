/* Noah Park

We are given an array asteroids of integers representing asteroids in a row.

For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.

Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.

*/

class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>(); // stack will store all valid asteroids so far
        for(int ast : asteroids){ // iterate through all asteroids
            // move current asteroid to its valid spot by destroying smaller right travelling asteroids in the stack before it
            while(!stack.isEmpty() && stack.peek() > 0 && stack.peek() < -ast)
                stack.pop();
            if(stack.isEmpty() || ast > 0 || stack.peek() < 0) stack.add(ast); // if the stack is empty, the asteroid is travelling right, or the top is travelling left, we can add the current asteroid
            else if(ast < 0 && stack.peek() == -ast) stack.pop(); // else if the asteroid is travelling left and the top of the stack matches the asteroid, we destroy both
        }
        
        // copy stack to integer array
        int[] res = new int[stack.size()];
        for(int i = stack.size() - 1; i >= 0; i--)
            res[i] = stack.pop();
        
        return res;
    }
}
