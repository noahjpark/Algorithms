/* Noah Park

There is a stream of n (id, value) pairs arriving in an arbitrary order, where id is an integer between 1 and n and value is a string. No two pairs have the same id.

Design a stream that returns the values in increasing order of their IDs by returning a chunk (list) of values after each insertion. The concatenation of all the chunks should result in a list of the sorted values.

Implement the OrderedStream class:

OrderedStream(int n) Constructs the stream to take n values.
String[] insert(int id, String value) Inserts the pair (id, value) into the stream, then returns the largest possible chunk of currently inserted values that appear next in the order.

*/

class OrderedStream {
    
    String[] stream; // stores stream of strings
    int ptr = 0; // ptr to where we are in the stream currently

    public OrderedStream(int n) {
        stream = new String[n]; // initialize
    }
    
    public List<String> insert(int id, String value) {
        List<String> res = new ArrayList<>(); // return list
        
        stream[id - 1] = value; // update the stream
        
        // iterate until we reach the end of the stream or a null position
        while (ptr < stream.length && stream[ptr] != null) {
            res.add(stream[ptr++]);
        }
        
        return res;
    }
}

/**
 * Your OrderedStream object will be instantiated and called as such:
 * OrderedStream obj = new OrderedStream(n);
 * List<String> param_1 = obj.insert(id,value);
 */
