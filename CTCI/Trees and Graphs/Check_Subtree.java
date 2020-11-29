// Noah Park
/*

Problem: T1 and T2 are two very large binary trees, with T1 much bigger than T2. Create an
algorithm to determine if T2 is a subtree of T1.

A tree T2 is a subtree of T1 if there exists a node n in T1 such that the subtree of n is identical
to T2. That is, if you cut off the tree at node n, the two trees would be identical.

*/

public class Check_Subtree<T extends Comparable<T>> {

    // Checks if bst1 contains bst2
    public boolean containsSubtree(BST<T> bst1, BST<T> bst2){
        if(bst1 == null){ // If bst1 is null, it can't contain any subtrees
            return false;
        }
        else if(bst2 == null){ // If bst2 is null, bst1 must contain it (bst1 must not be null here)
            return true;
        }

        // Recursive helper method for cleaner code
        return containsSubtreeHelper(bst1.getRoot(), bst2.getRoot());
    }

    // Helps check the subtrees
    public boolean containsSubtreeHelper(BinaryNode<T> n1, BinaryNode<T> n2){
        if(n2 == null){ // If the root of the other tree is null, return true
            return true;
        }
        if(n1 == null){ // If we reach the end of the first tree, return false
            return false;
        }
        if(this.areIdentical(n1, n2)){ // Check if the subtrees are identical
            return true;
        }

        // Recurse down the left and right subtrees of the first tree
        return containsSubtreeHelper(n1.getLeft(), n2) || containsSubtreeHelper(n1.getRight(), n2);
    }

    // Checks if two subtrees are identical from the given nodes
    public boolean areIdentical(BinaryNode<T> n1, BinaryNode<T> n2){
        if(n1 == null && n2 == null){ // If we reach the end of the trees at the same time, return true
            return true;
        }
        if(n1 == null || n2 == null){ // If we reach the end of the trees at different times, return false
            return false;
        }

        // Returns true if the nodes are equal and their left/right recursive calls on the subtrees are equal. Otherwise, returns false
        return n1.equals(n2) &&
                areIdentical(n1.getLeft(), n2.getLeft()) &&
                areIdentical(n1.getRight(), n2.getRight());
    }
}
