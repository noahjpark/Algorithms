/* Noah Park

You have a queue of integers, you need to retrieve the first unique integer in the queue.

Implement the FirstUnique class:

FirstUnique(int[] nums) Initializes the object with the numbers in the queue.
int showFirstUnique() returns the value of the first unique integer of the queue, and returns -1 if there is no such integer.
void add(int value) insert value to the queue.

*/

// Intuition: Maintain a doubly linked list of the unique values and a map of all values mapped to the node it corresponds to. Since we only keep unique values in the list, the nodes that have duplicates won't be apart of the list.
// Time: O(n) to initialize then O(1) after.
// Space: O(n) to maintain the list/map.
class Node {
    int val;
    Node next, prev;
    
    public Node(int val) {
        this.val = val;
    }
}

class FirstUnique {

    Node head, tail;
    Map<Integer, Node> map;
    
    public FirstUnique(int[] nums) {
        map = new HashMap<>();
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.prev = head;
        
        for (int num : nums)
            add(num);
    }
    
    public int showFirstUnique() {
        return head.next == tail ? -1 : head.next.val;
    }
    
    public void add(int value) {
        if (map.containsKey(value)) {
            Node n = map.get(value);
            if (n.next == null) return;
            n.prev.next = n.next;
            n.next.prev = n.prev;
            n.next = n.prev = null;
        } else {
            Node n = new Node(value);
            n.prev = tail.prev;
            n.next = tail;
            tail.prev.next = n;
            tail.prev = n;
            map.put(value, n);
        }
    }
}

/**
 * Your FirstUnique object will be instantiated and called as such:
 * FirstUnique obj = new FirstUnique(nums);
 * int param_1 = obj.showFirstUnique();
 * obj.add(value);
 */
