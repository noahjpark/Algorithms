// Noah Park
/*

Problem: Given a binary tree, design an algorithm which creates a
linked list of all the nodes at each depth (e.g., if you have a tree
with depth D, you'll have D linked lists).

*/

import java.util.LinkedList;
import java.util.Queue;

public class List_of_Depths<T extends Comparable<T>> {

    // Returns a Linked List of Linked Lists where each list stores all the nodes at a
    // particular level in the tree
    public LinkedList<LinkedList<BinaryNode<T>>> createListFromTree(BST<T> bst){
        // Error checking
        // If bst is null or its root is null, there is nothing to add to the list
        // Simply return an empty Linked List
        if(bst == null){
            return new LinkedList<>();
        }
        else if(bst.getRoot() == null){
            return new LinkedList<>(new LinkedList<>());
        }

        // nodesList represents the linked list of linked lists
        // depth is the maximum depth of the tree
        LinkedList<LinkedList<BinaryNode<T>>> nodesList = new LinkedList<>();
        int depth = findDepth(bst.getRoot());

        // Populate the nodesList with the correct number of empty linked lists based on the
        // depth of the tree prior to applying breadth first search
        for(int i = 0; i < depth; i++){
            LinkedList<BinaryNode<T>> temp = new LinkedList<>();
            nodesList.add(temp);
        }

        // q is the queue used to traverse the tree using breadth first search
        // level is a matching queue to q that represents the current level (starting at 0)
        // that the node coming from q is at. We can use this to index into nodesList
        Queue<BinaryNode<T>> q = new LinkedList<>();
        Queue<Integer> level = new LinkedList<>();

        // Mark the root then push it to q
        // Push 0 into level since the root is at level 0 (first linked list index)
        BinaryNode<T> root = bst.getRoot();
        root.mark();
        q.add(root);
        level.add(0);

        // Traditional bfs search with a minor tweak
        while(!q.isEmpty()){
            BinaryNode<T> node = q.remove();
            int curLevel = level.remove(); // Use curLevel to index into nodesList
            node.visit();
            // Using curLevel to index will get us to the right "depth" associated linked
            // list in nodesList
            // We simply add the node to this point
            nodesList.get(curLevel).add(node);
            if(node.getLeft() != null){
                node.getLeft().mark();
                q.add(node.getLeft());
                level.add(curLevel + 1); // push curLevel + 1 since it is the next level
            }
            if(node.getRight() != null){
                node.getRight().mark();
                q.add(node.getRight());
                level.add(curLevel + 1); // push curLevel + 1 since it is the next level
            }
        }

        // Resets the all nodes' marked/visited attributes and returns the finished nodesList
        bst.reset();
        return nodesList;
    }

    // Finds the depth of the binary tree
    public int findDepth(BinaryNode<T> root){
        // If the current node reaches null, return 0 as there are no more levels to search
        if(root == null){
            return 0;
        }

        // Use of ternary operators to return the larger subtree depth
        return findDepth(root.getLeft()) < findDepth(root.getRight()) ?
                findDepth(root.getRight()) + 1 : findDepth(root.getLeft()) + 1;
    }

    // Testing the functions written in List_of_Depths.java
    public static void main(String[] args){

        // Use Minimal_Tree.java that we already wrote to build a search tree to test our function
        Minimal_Tree<Integer> m = new Minimal_Tree<>();
        List_of_Depths<Integer> l = new List_of_Depths<>();
        Integer[] intArr = new Integer[7];

        for(int i = 0; i < intArr.length; i++){
            intArr[i] = i;
        }

        BST<Integer> bst = m.createBST(intArr);

        m.printPreOrder(bst.getRoot());

        System.out.println();
        System.out.println("------------");
        System.out.println();

        LinkedList<LinkedList<BinaryNode<Integer>>> nodesList = l.createListFromTree(bst);

        for(int i = 0; i < nodesList.size(); i++){
            System.out.print("List " + i + ": ");
            for(BinaryNode<Integer> node : nodesList.get(i)){
                System.out.print(node.getKey() + " ");
            }
            System.out.println();
        }

    }
}
