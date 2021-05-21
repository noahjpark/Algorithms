/* Noah Park

Given two vectors of integers v1 and v2, implement an iterator to return their elements alternately.

Implement the ZigzagIterator class:

ZigzagIterator(List<int> v1, List<int> v2) initializes the object with the two vectors v1 and v2.
boolean hasNext() returns true if the iterator still has elements, and false otherwise.
int next() returns the current element of the iterator and moves the iterator to the next element.

*/

public class ZigzagIterator {
    
    // Intuition: Two pointers zig zaggin through the two lists. Simply put, we always attempt to take the smaller index starting with i. If they are the same we prioritize i. If one of the lists is finished, obviously take the other list. Otherwise, hasNext() returns false.
    // Time: O(n + m) to iterate over both lists.
    // Space: O(n + m) to maintain the lists.
    List<Integer> l1, l2;
    int i, j;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        l1 = v1;
        l2 = v2;
        i = 0;
        j = 0;
    }

    public int next() {
        if (!hasNext()) return -1;
        
        if (i <= j) return i < l1.size() ? l1.get(i++) : l2.get(j++);
        else return j < l2.size() ? l2.get(j++) : l1.get(i++);
    }

    public boolean hasNext() {
        if (i >= l1.size() && j >= l2.size()) return false;
        return true;
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */
