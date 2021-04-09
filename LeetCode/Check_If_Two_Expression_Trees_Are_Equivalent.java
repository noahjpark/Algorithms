/* Noah Park

A binary expression tree is a kind of binary tree used to represent arithmetic expressions. Each node of a binary expression tree has either zero or two children. Leaf nodes (nodes with 0 children) correspond to operands (variables), and internal nodes (nodes with two children) correspond to the operators. In this problem, we only consider the '+' operator (i.e. addition).

You are given the roots of two binary expression trees, root1 and root2. Return true if the two binary expression trees are equivalent. Otherwise, return false.

Two binary expression trees are equivalent if they evaluate to the same value regardless of what the variables are set to.

Follow up: What will you change in your solution if the tree also supports the '-' operator (i.e. subtraction)?

*/

/**
 * Definition for a binary tree node.
 * class Node {
 *     char val;
 *     Node left;
 *     Node right;
 *     Node() {this.val = ' ';}
 *     Node(char val) { this.val = val; }
 *     Node(char val, Node left, Node right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    
    // Intuition: Utilize counting sort with a size 26 frequency array to populate each tree's map with all of the occurrences of each lowercase letter (exclude + signs since they don't matter). Because everything is added, we just need to make sure both maps match.
    // Time: O(n) since both trees must be the same size (essentially two pass).
    // Space: O(1) since the arrays are always size 26
    public boolean checkEquivalence(Node root1, Node root2) {
        int[] map1 = new int[26], map2 = new int[26];
        preorder(root1, map1); preorder(root2, map2);
        
        for (int i = 0; i < 26; i++)
            if (map1[i] != map2[i]) return false;
        
        return true;
    }
    
    public void preorder(Node root, int[] map) {
        if (root != null) {
            if (root.val != '+') map[root.val - 'a']++;
            preorder(root.left, map);
            preorder(root.right, map);
        }
    }
}
