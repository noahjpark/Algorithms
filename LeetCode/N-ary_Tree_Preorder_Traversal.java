/* Noah Park

Given an n-ary tree, return the preorder traversal of its nodes' values.

Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).

 

Follow up:

Recursive solution is trivial, could you do it iteratively?

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
    public List<Integer> preorder(Node root) {
        if (root == null) return new ArrayList<>(); // edge cases
        
        List<Integer> res = new ArrayList<>();
        Stack<Node> stack = new Stack<>(); // use stack for DFS traversal
        stack.push(root);
        
        // iterate until stack is empty
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            
            res.add(cur.val);
            
            // populate backwards for preorder
            List<Node> children = cur.children;
            for (int i = children.size() - 1; i >= 0; i--) 
                stack.push(children.get(i));
        }
        
        return res;
    }
    
    public List<Integer> preorderRec(Node root) {
        List<Integer> res = new ArrayList<>(); // return list
        if (root != null) res.add(root.val); // add root if it exists
        preorder(root, res); // add the nodes recursively in preorder
        return res;
    }
    
    public void preorder(Node root, List<Integer> res) {
        if (root != null) { // as long as the root is not null, add children in preorder
            List<Node> children = root.children;
            
            for (Node child : children) {
                res.add(child.val);
                preorder(child, res);
            }
        }
    }
}
