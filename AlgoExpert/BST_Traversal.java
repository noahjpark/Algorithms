// Noah Park
/*

Problem: Write three functions that take in a Binary Search Tree (BST) and an empty array,
traverse the BST, add its nodes' values to the input array, and return that array. The three
functions should traverse the BST using the in-order, pre-order, and post-order tree-traversal
techniques, respectively.

*/

import java.util.List;

public class BST_Traversal {
    public static List<Integer> inOrderTraverse(BST tree, List<Integer> array) {
        if(tree != null){
            inOrderTraverse(tree.left, array);
            array.add(tree.value);
            inOrderTraverse(tree.right, array);
        }
        return array;
    }

    public static List<Integer> preOrderTraverse(BST tree, List<Integer> array) {
        if(tree != null){
            array.add(tree.value);
            preOrderTraverse(tree.left, array);
            preOrderTraverse(tree.right, array);
        }
        return array;
    }

    public static List<Integer> postOrderTraverse(BST tree, List<Integer> array) {
        if(tree != null){
            postOrderTraverse(tree.left, array);
            postOrderTraverse(tree.right, array);
            array.add(tree.value);
        }
        return array;
    }

    static class BST {
        public int value;
        public BST left;
        public BST right;

        public BST(int value) {
            this.value = value;
        }
    }
}
