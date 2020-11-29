// Noah Park
/*

Problem: Implement a function to check if a binary tree is balanced.
For the purposes of this question, a balanced tree is defined to be
a tree such that the heights of the two subtrees of any node never differ
by more than one.

*/

import java.util.Stack;

public class Check_Balanced<T extends Comparable<T>> {

    // Checks if a binary tree is balanced
    // Definition of this is up top for this problem
    public boolean isBalanced(BST<T> bst){
        // Error checking: If bst is null or its root is null, technically it is balanced
        if(bst == null || bst.getRoot() == null){
            return true;
        }

        // I figured a stack to traverse the tree would work the best
        // Essentially we can check each nodes' difference in left/right subtree depth
        // and return false if it breaks the condition that the positive difference is greater
        // than 1
        Stack<BinaryNode<T>> s = new Stack<>();
        s.push(bst.getRoot());

        // While stack is not empty, we will traverse the entire tree
        while(!s.isEmpty()){
            BinaryNode<T> node = s.pop(); // Get the current node by popping from the stack

            // Find the positive difference in the left and right subtree depth
            int differenceInDepth = Math.abs(findDepth(node.getLeft()) - findDepth(node.getRight()));
            if(differenceInDepth > 1){ // If that difference is greater than 1 we must return false, as the tree cannot be balanced by definition
                return false;
            }

            // Push the left child if it exists
            if(node.getLeft() != null){
                s.push(node.getLeft());
            }

            // Push the right child if it exists
            if(node.getRight() != null){
                s.push(node.getRight());
            }
        }

        // The tree must be balanced, as we could not break the condition through the entirety of the tree
        return true;
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

    // Testing the functions out
    public static void main(String[] args){
        Minimal_Tree<Integer> m = new Minimal_Tree<>();
        Check_Balanced<Integer> c = new Check_Balanced<>();

        Integer[] intArr = new Integer[7];

        for(int i = 0; i < intArr.length; i++){
            intArr[i] = i;
        }

        BST<Integer> bst = m.createBST(intArr);

        System.out.println(c.isBalanced(bst));

        BinaryNode<Integer> n1 = new BinaryNode<>(0);
        BinaryNode<Integer> n2 = new BinaryNode<>(1);
        BinaryNode<Integer> n3 = new BinaryNode<>(2);

        n1.setRight(n2);
        n2.setRight(n3);
        BST<Integer> unbalanced = new BST<>(n1);

        System.out.println(c.isBalanced(unbalanced));
    }

}
