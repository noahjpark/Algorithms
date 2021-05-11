/* Noah Park

Given an integer array nums, return the maximum result of nums[i] XOR nums[j], where 0 ≤ i ≤ j < n.

Follow up: Could you do this in O(n) runtime?

*/

class TrieNode {
    TrieNode[] children;
    
    public TrieNode() {
        children = new TrieNode[2];
    }
}

class Solution {
    
    // Intuition: Utilize a binary prefix tree. As we build the tree, we can build the xor'd max on the opposite side of each number. Each iteration we can maximize this.
    // Time: O(n) to iterate over nums to build the prefix tree.
    // Space: O(n) to maintain the numbers in the trie.
    public int findMaximumXOR(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        TrieNode root = new TrieNode();
        int res = 0, largest = nums[0], nl = nums.length;
        for (int num : nums)
            largest = Math.max(largest, num);
        int length = Integer.toBinaryString(largest).length(), mask = 1 << length; // we need to ensure we have this length for each number.
        
        String[] s = new String[nl];
        for (int i = 0; i < nl; i++)
            s[i] = Integer.toBinaryString(mask | nums[i]);
        
        for (String num : s) {
            TrieNode cur = root, xor = root;
            int curXor = 0;
            for (char c : num.toCharArray()) {
                if (cur.children[c - '0'] == null) cur.children[c - '0'] = new TrieNode();
                cur = cur.children[c - '0'];
                
                char flipped = c == '1' ? '0' : '1';
                if (xor.children[flipped - '0'] != null) {
                    curXor = (curXor << 1) | 1; // going in the opposite direction in an xor guarantees there's one one. 
                    xor = xor.children[flipped - '0'];
                } else {
                    curXor <<= 1; // Otherwise, we result in 0.
                    xor = xor.children[c - '0'];
                }
            }
            
            res = Math.max(res, curXor);
        }
        
        return res;
    }
    
}
