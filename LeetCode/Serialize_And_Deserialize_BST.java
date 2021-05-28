/* Noah Park

Serialization is converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You need to ensure that a binary search tree can be serialized to a string, and this string can be deserialized to the original tree structure.

The encoded string should be as compact as possible.

*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Intuition: Utilize the fact that a bst has required valued bounds for the left and right subtrees. We don't need any "null" denotations in our string as we can maintain the bounds at each point. However, if we had duplicates, we would need to maintain structure for the bst as in serialize/deserialize bt.
    // Time: O(n) to serialize/deserialize.
    // Space: O(h) height of the tree.
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder res = new StringBuilder();
        dfs(root, res);
        return res.toString();
    }
    
    public void dfs(TreeNode root, StringBuilder res) {
        if (root == null) return;
        
        res.append(root.val).append(",");
        dfs(root.left, res);
        dfs(root.right, res);
    }
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.length() == 0) return null;
        Deque<String> q = new LinkedList<>(Arrays.asList(data.split(",")));
        return dfs(q, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    public TreeNode dfs(Deque<String> q, int min, int max) {
        if (q.isEmpty()) return null;
        
        int val = Integer.parseInt(q.peek());
        if (val < min || val > max) return null;
        q.removeFirst();
        
        TreeNode root = new TreeNode(val);
        root.left = dfs(q, min, val);
        root.right = dfs(q, val, max);
        
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// String tree = ser.serialize(root);
// TreeNode ans = deser.deserialize(tree);
// return ans;
