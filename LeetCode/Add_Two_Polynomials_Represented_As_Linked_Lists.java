/* Noah Park

A polynomial linked list is a special type of linked list where every node represents a term in a polynomial expression.

Each node has three attributes:

coefficient: an integer representing the number multiplier of the term. The coefficient of the term 9x4 is 9.
power: an integer representing the exponent. The power of the term 9x4 is 4.
next: a pointer to the next node in the list, or null if it is the last node of the list.
For example, the polynomial 5x3 + 4x - 7 is represented by the polynomial linked list illustrated below:



The polynomial linked list must be in its standard form: the polynomial must be in strictly descending order by its power value. Also, terms with a coefficient of 0 are omitted.

Given two polynomial linked list heads, poly1 and poly2, add the polynomials together and return the head of the sum of the polynomials.

PolyNode format:

The input/output format is as a list of n nodes, where each node is represented as its [coefficient, power]. For example, the polynomial 5x3 + 4x - 7 would be represented as: [[5,3],[4,1],[-7,0]].

*/

/**
 * Definition for polynomial singly-linked list.
 * class PolyNode {
 *     int coefficient, power;
 *     PolyNode next = null;
 
 *     PolyNode() {}
 *     PolyNode(int x, int y) { this.coefficient = x; this.power = y; }
 *     PolyNode(int x, int y, PolyNode next) { this.coefficient = x; this.power = y; this.next = next; }
 * }
 */

class Solution {
    
    // Intuition: Similar to the merge sort algorithm. Prioritize higher power polynomials. If we have a zero sum, don't create a new node for the list.
    // Time: O(n + m) to iterate over the lists.
    // Space: O(n) to create a new list if all powers match.
    public PolyNode addPoly(PolyNode poly1, PolyNode poly2) {
        PolyNode dummy = new PolyNode(), cur = dummy, p1 = poly1, p2 = poly2;
        
        while (p1 != null && p2 != null) {
            if (p1.power > p2.power) { cur.next = p1; p1 = p1.next; cur = cur.next; }
            else if (p2.power > p1.power) { cur.next = p2; p2 = p2.next; cur = cur.next; }
            else {
                int sum = p1.coefficient + p2.coefficient;
                if (sum != 0) { cur.next = new PolyNode(sum, p1.power); cur = cur.next; }
                p1 = p1.next;
                p2 = p2.next;
            }
        }
        
        cur.next = null;
        
        if (p1 != null) cur.next = p1;
        if (p2 != null) cur.next = p2;
        
        return dummy.next;
    }
}
