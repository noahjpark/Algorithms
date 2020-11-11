// Noah Park
/*

Problem: Write a function that takes in a Binary Tree and returns its max path sum.

A path is a collection of connected nodes in a tree where no node is connected to more than two
other nodes; a path sum is the sum of the values of the nodes in a particular path.

Each BinaryTree node has an integer value, a left child node, and a right child node.
Children nodes can either be BinaryTree nodes themselves or None / null.

*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Max_Path_Sum_In_Binary_Tree {
    // Time: O(n) | Space: O(log(n))
    public static int maxPathSum(BinaryTree tree) {
        List<Integer> max = findMax(tree);
        return max.get(1); // Return the running max path sum
    }

    public static List<Integer> findMax(BinaryTree tree){
        // If tree == null, return
        if(tree == null) return new ArrayList<>(Arrays.asList(0, Integer.MIN_VALUE));

        // Calculate the left subtree information
        // leftBranch is the maximum path sum as a branch (no triangle)
        // leftSum is the maximum path sum so far
        List<Integer> left = findMax(tree.left);
        int leftBranch = left.get(0);
        int leftSum = left.get(1);

        // Calculate the right subtree information
        // rightBranch is the maximum path sum as a branch (no triangle)
        // rightSum is the maximum path sum so far
        List<Integer> right = findMax(tree.right);
        int rightBranch = right.get(0);
        int rightSum = right.get(1);

        // maxBranch is the larger of the two branches
        int maxBranch = Math.max(leftBranch, rightBranch);

        // maxCurBranchSum is the larger of the included current node value or just the node value
        // maxCurTriangleSum is the larger of the maxBranchSum or the leftBranch + rightBranch + current node value
        // runningSum takes the larger of the left running sum, right running sum, or current branch sum.
        int maxCurBranchSum = Math.max(maxBranch + tree.value, tree.value);
        int maxCurTriangleSum = Math.max(maxCurBranchSum, leftBranch + rightBranch + tree.value);
        int runningSum = Math.max(leftSum, Math.max(rightSum, maxCurTriangleSum));

        return new ArrayList<>(Arrays.asList(maxCurBranchSum, runningSum));
    }

    static class BinaryTree {
        public int value;
        public BinaryTree left;
        public BinaryTree right;

        public BinaryTree(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args){
        int low, high;

        Scanner s = new Scanner(System.in);
        System.out.println("Enter in a lower bound and an upper bound: ");
        low = s.nextInt();
        high = s.nextInt();

        System.out.println("low: " + low + " high: " + high);
    }
}
