// Noah Park
/*

Problem: Write a function that takes in a non-empty sorted array of distinct integers, constructs
a BST from the integers, and returns the root of the BST.

The function should minimize the height of the BST.

You've been provided with a BST class that you'll have to use to construct the BST.

*/

import java.util.List;

public class Min_Height_BST {
    // Calls the recursive helper function to create a BST from the sorted array
    public static BST minHeightBst(List<Integer> array) {
        return createBst(array, 0, array.size() - 1);
    }

    // Helper function to create the BST from the sorted array
    public static BST createBst(List<Integer> array, int start, int end){
        // If the start index is larger than the end index, we need to stop
        if(start > end){
            return null;
        }

        /*
        Otherwise, we get the middle index between start and end. Then we create a node
        when retrieving that value from the array. From there, we can utilize the insert
        method to call in the correct order. First call will insert to the left, since all
        the values are smaller in the left sub-array and vice versa for the right. I made a
        change to the insert method so that instead of taking in values to create a node to
        insert, it takes in the node itself. Finally, after all calls have finished, the driver
        call will return the original node to the driver function resulting in the completed BST.
        */
        int middle = (start + end) / 2;
        BST node = new BST(array.get(middle));
        node.insert(createBst(array, start, middle - 1));
        node.insert(createBst(array, middle + 1, end));

        return node;
    }

    static class BST {
        public int value;
        public BST left;
        public BST right;

        public BST(int value) {
            this.value = value;
            left = null;
            right = null;
        }

        public void insert(BST node) {
            if(node == null){
                return;
            }
            if (node.value < this.value) {
                if (left == null) {
                    left = node;
                } else {
                    left.insert(node);
                }
            } else {
                if (right == null) {
                    right = node;
                } else {
                    right.insert(node);
                }
            }
        }
    }
}
