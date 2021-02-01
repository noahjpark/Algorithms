/* Noah Park

Given an n-ary tree, return the level order traversal of its nodes' values.

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
    // Space: O(n)
    public List<List<Integer>> levelOrder(Node root) {
        if (root == null) return new ArrayList<>(); // edge cases
        
        // return list and queue for bfs
        List<List<Integer>> res = new ArrayList<>();
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        
        // get level size before iterating then apply normal bfs
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> temp = new ArrayList<>();
            
            for (int i = 0; i < size; i++) {
                Node cur = q.poll();
                
                temp.add(cur.val);
                
                for (Node child : cur.children)
                    q.add(child);
            }
            
            res.add(temp);
        }
        
        return res;
    }
}
