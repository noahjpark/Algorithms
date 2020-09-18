// Noah Park
/*

Problem: Write a function that takes in a Binary Tree and inverts it. In other words, the function
should swap every left node in the tree for its corresponding right node.

Each BinaryTree node has an integer value, a left child node, and a right child node.
Children nodes can either be BinaryTree nodes themselves or None / null.

*/

public class Invert_Binary_Tree {
    /*
	1. Traverse tree in pre order so we can adjust our current node.
	2. Swap the left and right nodes
	3. Call the same method on the left and right subtree
	*/
    public static void invertBinaryTree(BinaryTree tree) {
        invert(tree);
    }

    // Traverses in pre-order fashion
    public static void invert(BinaryTree tree){
        if(tree != null){
            BinaryTree temp = tree.left; // Do classic swap on left and right subtrees
            tree.left = tree.right;
            tree.right = temp;

            // Visit the left and right subtrees
            invert(tree.left);
            invert(tree.right);
        }
    }

    static class BinaryTree {
        public int value;
        public BinaryTree left;
        public BinaryTree right;

        public BinaryTree(int value) {
            this.value = value;
        }
    }

    // Could alternatively use a queue to solve this problem iteratively
}
