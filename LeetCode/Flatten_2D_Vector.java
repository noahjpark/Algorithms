/* Noah Park

Design an iterator to flatten a 2D vector. It should support the next and hasNext operations.

Implement the Vector2D class:

Vector2D(int[][] vec) initializes the object with the 2D vector vec.
next() returns the next element from the 2D vector and moves the pointer one step forward. You may assume that all the calls to next are valid.
hasNext() returns true if there are still some elements in the vector, and false otherwise.
 

*/

class Vector2D {
    
    // Intuition: Utilize two pointers to move in the vector.
    // Time: O(V/N) for next and hasNext().
    // Space: O(1) constant.
    int[][] v;
    int inner = 0, outer = 0;

    public Vector2D(int[][] vec) {
        v = vec;
    }
    
    // Move the pointers forward until we reach the next value. Return this value and increment inner once.
    public int next() {
        while (outer < v.length && inner == v[outer].length) { inner = 0; outer++; }
        return v[outer][inner++];
    }
    
    // Attempt to move i and o to their next value. Return if o pointer is still in bounds.
    public boolean hasNext() {
        int i = inner, o = outer;
        while (o < v.length && i == v[o].length) { i = 0; o++; }
        return o < v.length;
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D obj = new Vector2D(vec);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
