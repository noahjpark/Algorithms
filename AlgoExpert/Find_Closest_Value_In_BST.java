// Noah Park
/*

Problem: Write a function that takes in a BST and a target integer value and returns the
closest value to that target value contained in the BST.

You can assume that there will only be one closest value.

Each BST node has an integer value, a left child node, and a right child node. A node is said
to be a valid BST node if and only if it satisfies the BST property: its value is strictly greater
than the values of every node to its left; its value is less than or equal to the values of every
node to its right; and its children nodes are either valid BST nodes themselves or None / null.

*/

public class Find_Closest_Value_In_BST {

    // Iterative solution: O(n) time and O(1) space
    public static int findClosestValueInBst(BST tree, int target) {
        if(tree == null){ // If the tree is null, there are no values to return
            return -1;
        }

        BST cur = tree; // don't destroy tree
        int ans = cur.value; // Start ans at the root's value

        // Loop until either we find the target value or we reach the end of a "dfs" following
        // The path where we attempt to find the closest value to target
        while(cur != null && ans != target){

            // Compare the absolute difference between cur's value and target and ans and target
            // Whichever is closer is what ans should continue to be or be set to
            int difference = Math.abs(cur.value - target);
            if(difference < Math.abs(ans - target)){
                ans = cur.value;
            }

            // Move left or right based on our current value compared to our target value
            if(cur.value < target){
                cur = cur.right;
            }
            else{
                cur = cur.left;
            }
        }

        // Return our closest value
        return ans;
    }

    // Recursive solution: O(n) time and O(1) space
    // Literally just a recursive implementation of the defined function above
    // Identically written but recursive
    public static int findClosestValueInBstRec(BST tree, int target) {
        if(tree == null){
            return -1;
        }

        return recHelper(tree, target, tree.value);
    }

    public static int recHelper(BST tree, int target, int ans){
        if(tree == null || ans == target){
            return ans;
        }

        int difference = Math.abs(tree.value - target);
        if(difference < Math.abs(ans - target)){
            ans = tree.value;
        }

        if(tree.value < target){
            return recHelper(tree.right, target, ans);
        }
        else{
            return recHelper(tree.left, target, ans);
        }
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
