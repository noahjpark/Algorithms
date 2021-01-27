/* Noah Park

Given a root of an N-ary tree, return a deep copy (clone) of the tree.

Each node in the n-ary tree contains a val (int) and a list (List[Node]) of its children.

class Node {
    public int val;
    public List<Node> children;
}
Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).

Follow up: Can your solution work for the graph problem?

*/

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    
    public Node() {
        children = new ArrayList<Node>();
    }
    
    public Node(int _val) {
        val = _val;
        children = new ArrayList<Node>();
    }
    
    public Node(int _val,ArrayList<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    // recursive solution
    public Node cloneTree(Node root) {
        if (root == null) return null;
        
        Node newRoot = new Node(root.val);
        
        // recursively add children
        for (Node child : root.children)
            newRoot.children.add(cloneTree(child));
        
        return newRoot;
    }
    
    // iterative solution
    public Node cloneTreeIt(Node root) {
        if (root == null) return root; // edge case
        
        Node newRoot = null;
        Queue<Node> q1 = new LinkedList<>();
        Queue<Node> q2 = new LinkedList<>();
        q1.offer(root);
        q2.offer(newRoot);
        
        // copy through queue
        while (!q1.isEmpty()) {
            Node nodes = q1.poll();
            Node cur = q2.poll();
            Node newNode = new Node(nodes.val);
            
            if (newRoot == null) newRoot = newNode; // update new root
            
            List<Node> ch = new ArrayList<>();
            
            for (Node child : nodes.children)
                ch.add(new Node(child.val));
        
            // update children
            if (cur == null) newRoot.children = ch;
            else cur.children = ch;
            
            // add all children
            q1.addAll(nodes.children);
            q2.addAll(ch);
                        
        }
        
        return newRoot;
    }
}
