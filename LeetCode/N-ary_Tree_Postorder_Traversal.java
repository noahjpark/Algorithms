/* Noah Park

Given an n-ary tree, return the postorder traversal of its nodes' values.

Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).

*/

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    // Time: O(n)
    // Space: O(h) where h is the height of the tree
    public List<Integer> postorder(Node root) {
        if (root == null) return new ArrayList<>();
        
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        LinkedList<Integer> res = new LinkedList<>();
        
        // post order algorithm in reverse for the stack
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            
            res.addFirst(cur.val);
            
            for (Node child : cur.children)
                stack.push(child);
        }
        
        return res;
    }
    
    public List<Integer> postorderrec(Node root) {
        if (root == null) return new ArrayList<>();
        
        List<Integer> res = new ArrayList<>();
        postorderhelper(root, res);
        
        return res;
    }
    
    public void postorderhelper(Node root, List<Integer> res) {
        if (root == null) return;
        
        for (Node child : root.children) 
            postorderhelper(child, res);
        
        res.add(root.val);
    }
}
