// Noah Park
/*

Problem: You are given a binary tree in which each node contains
an integer value (which might be positive or negative). Design an
algorithm to count the number of paths that sum to a given value.
The path does not need to start or end at the root or a leaf, but it must
go downwards (traveling only from parent nodes to child nodes).

*/

import java.util.Hashtable;

public class Path_with_Sum {

    public int optimizedSearch(BST<Integer> bst, int value){
        if(bst == null || bst.getRoot() == null){
            return 0;
        }

        Hashtable<Integer, Integer> paths = new Hashtable<>();

        return optimizedSearchHelper(bst.getRoot(), 0, value, paths);
    }

    public int optimizedSearchHelper(BinaryNode<Integer> node, int currentSum, int targetSum, Hashtable<Integer, Integer> paths){
        if(node == null){
            return 0;
        }

        // Increment currentSum by our node's value
        currentSum += node.getKey();

        int count = paths.getOrDefault(currentSum - targetSum, 0);

        if(currentSum == targetSum){
            count++;
        }

        paths.put(currentSum, paths.getOrDefault(currentSum, 0) + 1);
        count += optimizedSearchHelper(node.getLeft(), currentSum, targetSum, paths);
        count += optimizedSearchHelper(node.getRight(), currentSum, targetSum, paths);
        paths.put(currentSum, paths.getOrDefault(currentSum, 0) - 1);

        return count;
    }

    // Function called to count all paths which then calls the helper function(s)
    public int numPaths(BST<Integer> bst, int value){
        // If the tree or the root are null, there aren't any paths
        if(bst == null || bst.getRoot() == null){
            return 0;
        }

        return countAllPaths(bst.getRoot(), value); // Call helper
    }

    // Counts all paths starting from the current node using another helper function
    public int countAllPaths(BinaryNode<Integer> node, int value){
        // If the node reaches null,
        if(node == null){
            return 0;
        }

        // Find the total paths from the current node
        int curNodePaths = countEachPath(node, value, 0);

        // Call this function recursively to keep counting throughout the tree
        int leftNodePaths = countAllPaths(node.getLeft(), value);
        int rightNodePaths = countAllPaths(node.getRight(), value);

        // Return total paths found
        return curNodePaths + leftNodePaths + rightNodePaths;
    }

    // Recursively counts all paths when given a particular node
    public int countEachPath(BinaryNode<Integer> node, int target, int current){
        // If node is null we reached the end
        if(node == null){
            return 0;
        }

        // Add to our current sum
        current += node.getKey();

        // Count total paths
        int totalPaths = 0;
        if(current == target){ // If current == target, we found a new path
            totalPaths++;
        }

        // Count subpaths from the current node (left and right trees)
        totalPaths += countEachPath(node.getLeft(), target, current);
        totalPaths += countEachPath(node.getRight(), target, current);

        // Return total paths
        return totalPaths;
    }

    // Basic testing of the class
    public static void main(String[] args){

    }
}
