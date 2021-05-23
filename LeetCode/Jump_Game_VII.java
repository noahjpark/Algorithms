/* Noah Park

You are given a 0-indexed binary string s and two integers minJump and maxJump. In the beginning, you are standing at index 0, which is equal to '0'. You can move from index i to index j if the following conditions are fulfilled:

i + minJump <= j <= min(i + maxJump, s.length - 1), and
s[j] == '0'.
Return true if you can reach index s.length - 1 in s, or false otherwise.

*/

class Solution {
    
    // Intuition: This is really tricky! The TLE constraints are too high for a medium imo. We maintain the elements in the queue that are zeroes from the string. Whenever the front of our queue goes out of range (i.e. i - maxJump becomes too large) we continuously empty the queue from the front. For each character that is within our jumping range (i.e. i - minJump is large enough) we can add our element to the queue.
    // Time: O(n) to iterate over s.
    // Space: O(n) to maintain the queue.
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        if (s.charAt(n - 1) == '1') return false;
        
        Deque<Integer> q = new LinkedList<>();
        q.addLast(0);
        
        for (int i = 0; i < n; i++) {
            while (!q.isEmpty() && q.peek() < i - maxJump) q.removeFirst();
            if (s.charAt(i) == '0' && !q.isEmpty() && q.peek() <= i - minJump) q.addLast(i);
        }
        
        return !q.isEmpty() && q.peekLast() == n - 1;
    }
}
