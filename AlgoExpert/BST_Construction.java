// Noah Park
/*

Problem: Write a BST class for a Binary Search Tree. The class should support:

    - Inserting values with the insert method
    - Removing values with the remove method; this method should only remove the first instance of a given value
    - Searching for values with the contains method

Note that you can't remove values from a single-node tree. In other words, calling the remove method
on a single-node tree should simply not do anything.

Each BST node has an integer value, a left child node, and a right child node. A node is said to be a
valid BST node if and only if it satisfies the BST property: its value is strictly greater than the
values of every node to its left; its value is less than or equal to the values of every node to its right
and its children nodes are either valid BST nodes themselves or None / null.

*/

import java.util.*;

class BST_Construction {
    static class BST {
        public int value;
        public BST left;
        public BST right;

        public BST(int value) {
            this.value = value;
        }

        // Time: O(n) | Space: O(1)
        public BST insert(int value) {
            BST cur = this;
            while (true) {
                if (cur.value > value) {
                    if (cur.left != null) {
                        cur = cur.left;
                    } else {
                        cur.left = new BST(value);
                        break;
                    }
                } else if (cur.value <= value) {
                    if (cur.right != null) {
                        cur = cur.right;
                    } else {
                        cur.right = new BST(value);
                        break;
                    }
                }
            }
            return this;
        }

        // Time: O(n) | Space: O(1)
        public boolean contains(int value) {
            BST cur = this;
            while (cur != null) {
                if (cur.value == value) {
                    return true;
                } else if (cur.value > value) {
                    cur = cur.left;
                } else {
                    cur = cur.right;
                }
            }
            return false;
        }

        // Time: O(n) | Space: O(1)
        public BST remove(int value) {
            // Initially, the root node has no parent, thus null
            remove(value, null);
            return this;
        }

        public void remove(int value, BST parent) {
            // Get to our target BST node
            // Use recursion here
            if (value < this.value) {
                if (left != null) {
                    left.remove(value, this);
                }
            } else if (value > this.value) {
                if (right != null) {
                    right.remove(value, this);
                }
            }

            // We found our target
            else {
                // 2 children
                if (left != null && right != null) {
                    this.value = right.findMin();
                    right.remove(this.value, this);
                }
                // root
                else if (parent == null) {
                    if (left != null) {
                        this.value = left.value;
                        this.right = left.right;
                        this.left = left.left;
                    } else if (right != null) {
                        this.value = right.value;
                        this.right = right.right;
                        this.left = right.left;
                    }
                }
                // Single child or no children
                else if (parent.left == this) {
                    parent.left = left != null ? left : right;
                } else if (parent.right == this) {
                    parent.right = right != null ? right : left;
                }
            }
        }

        public int findMin() {
            if (left == null) {
                return value;
            } else {
                return left.findMin();
            }
        }

        public static void main(String[] args) {
            BST b = new BST(10);
            b.insert(5);
            b.insert(15);
            b.insert(2);
            b.insert(5);
            b.insert(13);
            b.insert(22);
            b.insert(1);
            b.insert(14);
            b.insert(12);
            b.remove(10);
            System.out.println(b.contains(15));
        }
    }

    public static void main(String[] args){

    }
}

