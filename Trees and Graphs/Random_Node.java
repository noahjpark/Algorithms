// Noah Park
/*

Problem: You are implementing a binary tree class from scratch which, in
addition to insert, find, and delete, has a method getRandomNode()
which returns a random node from the tree. All nodes should be equally likely
to be chosen. Design and implement an algorithm for getRandomNode(), and explain
how you would implement the rest of the methods.

*/
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Random_Node<T extends Comparable<T>> {

    // Finds a random node from the tree
    // Each node has an equal chance of being chosen
    public BinaryNode<T> getRandomNode(BST<T> bst){
        // Can't choose a node if the tree, or its root, is null
        if(bst == null || bst.getRoot() == null){
            return null;
        }

        // Use the random module to choose a random node from a tree by generating an
        // index between [0,treeSize).
        Random r = new Random();
        int randNodeIndex = r.nextInt(bst.getSize());
        BinaryNode<T> ans = null;
        Queue<BinaryNode<T>> q = new LinkedList<>();
        q.add(bst.getRoot());
        int index = 0;
        while(!q.isEmpty()){
            // Removes the front node from the queue and sets the return
            // node to it if the index matches our random index
            BinaryNode<T> cur = q.remove();
            if(index == randNodeIndex){
                ans = cur;
                break;
            }

            // Increment index
            index++;

            // Adds the left and right nodes of the tree in a breadth
            // first search pattern if they exist
            if(cur.getLeft() != null){
                q.add(cur.getLeft());
            }
            if(cur.getRight() != null){
                q.add(cur.getRight());
            }
        }



        return ans;
    }

    // Basic testing of the class
    public static void main(String[] args){
        Random_Node<Integer> r = new Random_Node<>();

        BinaryNode<Integer> root = new BinaryNode<>(3);
        BinaryNode<Integer> n1 = new BinaryNode<>(4);
        BinaryNode<Integer> n2 = new BinaryNode<>(6);
        BinaryNode<Integer> n3 = new BinaryNode<>(5);
        BinaryNode<Integer> n4 = new BinaryNode<>(0);
        BinaryNode<Integer> n5 = new BinaryNode<>(2);
        BinaryNode<Integer> n6 = new BinaryNode<>(1);

        root.setLeft(n6);
        root.setRight(n3);
        n3.setLeft(n1);
        n3.setRight(n2);
        n6.setLeft(n4);
        n6.setRight(n5);

        BST<Integer> bst = new BST<>(root);
        int[] arr = new int[7];
        // Initialize the array with all 0s as we have not seen any nodes yet
        for(int i = 0; i < arr.length; i++){
            arr[i] = 0;
        }
        // Test 50000 random nodes to ensure an even distribution
        for(int i = 0; i < 50000; i++){
            BinaryNode<Integer> temp = r.getRandomNode(bst);
            arr[temp.getKey()]++;
        }
        System.out.println("Random node occurrences 50000 times:");
        System.out.print("[");
        for(int i = 0; i < arr.length; i++){
            System.out.print(i == arr.length - 1 ? arr[i] : arr[i] + ", ");
        }
        System.out.println("]");
    }
}
