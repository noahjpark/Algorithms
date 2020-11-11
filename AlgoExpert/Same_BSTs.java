// Noah Park
/*

Problem: An array of integers is said to represent the Binary Search Tree (BST) obtained by inserting each integer
in the array, from left to right, into the BST.

Write a function that takes in two arrays of integers and determines whether these arrays represent the same BST.
Note that you're not allowed to construct any BSTs in your code.

A BST is a Binary Tree that consists only of BST nodes. A node is said to be a valid BST node if an only if it satisfies
the BST property: its value is strictly greater than the values of every node to its left; its value is less than or equal
to the values of every node to its right; and its children nodes are either valid BST nodes themselves or None / null.

*/

import java.util.ArrayList;
import java.util.List;

public class Same_BSTs {
    // Less optimal
    // Time: O(n^2) | Space: O(n^2)
    public static boolean sameBsts(List<Integer> arrayOne, List<Integer> arrayTwo) {
        // If the sizes are different or the first element (roots) are different, return false
        if(arrayOne.size() != arrayTwo.size() || !arrayOne.get(0).equals(arrayTwo.get(0))) return false;
        return helper(arrayOne, arrayTwo); // Call recursive helper method
    }

    public static boolean helper(List<Integer> arr1, List<Integer> arr2){
        // If the arrays are empty, return true all values matched
        if(arr1.size() == 0 && arr2.size() == 0) return true;
        // If the sizes are different or the first element (roots) are different, return false
        if(arr1.size() != arr2.size() || !arr1.get(0).equals(arr2.get(0))) return false;

        // Create 2 lists for each array, one for the left and right
        List<Integer> left1 = new ArrayList<>();
        List<Integer> right1 = new ArrayList<>();
        List<Integer> left2 = new ArrayList<>();
        List<Integer> right2 = new ArrayList<>();
        // Get the value to compare, this will be the same for both arr1 and arr2
        int val = arr1.get(0);

        // Iterate through the rest of the values in the arrays
        for(int i = 1; i < arr1.size(); i++){
            // Add to the left or right based on the properties of a BST
            if(arr1.get(i) < val) left1.add(arr1.get(i));
            else right1.add(arr1.get(i));
            if(arr2.get(i) < val) left2.add(arr2.get(i));
            else right2.add(arr2.get(i));
        }

        // Ensure that the values continue matching on both the rest of the left and right subtrees
        return helper(left1, left2) && helper(right1, right2);
    }

    public static boolean sameBSTS(List<Integer> arrayOne, List<Integer> arrayTwo){
        return helper(arrayOne, arrayTwo, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static boolean helper(List<Integer> arr1, List<Integer> arr2, int i1, int i2, int min, int max){
        if(i1 == -1 || i2 == -1) return i1 == i2;
        if(!arr1.get(i1).equals(arr2.get(i2))) return false;

        int l1 = findSmall(arr1, i1, min);
        int l2 = findSmall(arr1, i2, min);
        int r1 = findLarge(arr2, i1, max);
        int r2 = findLarge(arr2, i2, max);

        return helper(arr1, arr2, l1, l2, min, arr1.get(i1)) && helper(arr1, arr2, r1, r2, arr1.get(i1), max);
    }

    public static int findSmall(List<Integer> arr, int in, int min){
        for(int i = in + 1; i < arr.size(); i++){
            if(arr.get(i) < arr.get(in) && arr.get(i) >= min) return i;
        }
        return -1;
    }

    public static int findLarge(List<Integer> arr, int in, int max){
        for(int i = in + 1; i < arr.size(); i++){
            if(arr.get(i) >= arr.get(in) && arr.get(i) < max) return i;
        }
        return -1;
    }
}
