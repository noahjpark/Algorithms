/* Noah Park

Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

Implement the LRUCache class:

LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
int get(int key) Return the value of the key if the key exists, otherwise return -1.
void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.

*/

// doubly linked node class
class Node {
    Node next, prev;
    int key, value;
    
    public Node() {}
    
    public Node(int k, int v){
        key = k;
        value = v;
    }
}

class LRUCache {
    
    Map<Integer, Node> cache = new HashMap<>();
    int capacity, size = 0;
    Node head = new Node(), tail = new Node();

    public LRUCache(int capacity) {
        // set capacity
        this.capacity = capacity;
        
        // initialize list
        head.prev = tail.next = null;
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        Node res = cache.get(key); // get the node from the cache
        if(res == null) return -1; // if it isn't there, return -1
        
        // move the node to the front (end of the queue) most recently accessed
        access(res);
        
        // return the node's value
        return res.value;
    }
    
    public void put(int key, int value) {
        Node node = cache.get(key); // get the node from the cache
        if(node == null){ // if it isn't there, we need to add it
            // create a new node and add to the front of list and insert into map cache
            Node add = new Node();
            add.key = key; add.value = value;
            cache.put(key, add);
            addFront(add);
            
            // if the new size is larger than capacity, remove the tail from the list and cache
            if(++size > capacity){
                Node tail = evict();
                cache.remove(tail.key);
                size--;
            }
        }
        else{ // update the value of the node in the list and update its access to the front
            node.value = value;
            access(node);
        }
    }
    
    // Adds to the front of the linked list (end of queue)
    public void addFront(Node node){
        // update node's pointers
        node.next = head.next;
        node.prev = head;
        
        // update list pointers
        head.next.prev = node;
        head.next = node;
    }
    
    // Remove from the linked list (helper method for other methods)
    public void remove(Node node){
        // update list pointers
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    // Removes tail from the cache
    public Node evict(){
        Node res = tail.prev;
        remove(res);
        return res;
    }
    
    // Access the node by moving it to the front of the cache/linked list
    public void access(Node node){
        remove(node);
        addFront(node);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
