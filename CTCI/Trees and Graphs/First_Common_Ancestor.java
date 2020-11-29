// Noah Park
/*

Problem: Design an algorithm and write code to find the first common ancestor of two nodes
in a binary tree. Avoid storing additional nodes in a data structure. NOTE: This is not
necessarily a binary search tree.

*/

import java.util.LinkedList;
import java.util.Queue;

public class First_Common_Ancestor<T extends Comparable<T>> {

    // If we had links to the parent node, we could just go up one by one on each node
    // until we reach a match once we normalize the depth aka get them on the same level

    // Alternatively, we can use a 'cover siblings' method. Start from one node then go
    // upwards in the tree tracking the parent and the opposing sibling. If the opposing
    // sibling 'covers' the other node, we can return the parent node.

    // If there are no links to parents we can consider the following: Use 'covers' to check
    // if the nodes are on opposing sides compared to 'root'. If they are, then root is clearly
    // the deepest ancestor. Otherwise, we can recursively call on the proper half of the tree
    // each time to check if the new 'root' (passed in with the left or right child), is the ancestor

    // Finds the first common ancestor of n1 and n2 in the tree 'tree'
    public Node<T> findFirstAncestor(Tree<T> tree, Node<T> n1, Node<T> n2){
        // If either node or the tree is null, we can't run this function
        if(n1 == null || n2 == null || tree == null){
            return null;
        }

        // This is our return node
        Node<T> ans = null;

        // Use a queue to go traverse the tree
        Queue<Node<T>> q = new LinkedList<>();
        q.add(tree.getRoot());
        while(!q.isEmpty()){
            Node<T> cur = q.remove();
            // Call the bfs search using the current node (ancestor) and both n1/n2
            // If they are both reachable, cur will be the first ancestor between them
            if(tree.bfs(cur, n1) && tree.bfs(cur, n2)){
                ans = cur;
            }
            q.addAll(cur.getChildren());
        }

        //If we reach this point, an ancestor was not found
        return ans;
    }

    // Basic testing of the class
    public static void main(String[] args){
        First_Common_Ancestor<Integer> f = new First_Common_Ancestor<>();

        Node<Integer> root = new Node<>(0);
        Node<Integer> n1 = new Node<>(1);
        Node<Integer> n2 = new Node<>(2);
        Node<Integer> n3 = new Node<>(3);
        Node<Integer> n4 = new Node<>(4);
        Node<Integer> n5 = new Node<>(5);
        Node<Integer> n6 = new Node<>(6);
        Node<Integer> n7 = new Node<>(7);

        root.addChild(n1);
        root.addChild(n2);
        root.addChild(n3);
        n1.addChild(n4);
        n2.addChild(n5);
        n2.addChild(n6);
        n4.addChild(n7);

        Tree<Integer> tree = new Tree<>(root);

        Node<Integer> ancestor1 = f.findFirstAncestor(tree, n4, n2);
        Node<Integer> ancestor2 = f.findFirstAncestor(tree, n5, n6);

        if(ancestor1 == null){
            System.out.println("No ancestor found");
        }
        else {
            System.out.println(ancestor1.getName());
        }

        if(ancestor2 == null){
            System.out.println("No ancestor found");
        }
        else {
            System.out.println(ancestor2.getName());
        }
    }

}
