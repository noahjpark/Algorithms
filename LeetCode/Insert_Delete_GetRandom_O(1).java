/* Noah Park

Implement the RandomizedSet class:

bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present, false otherwise.
bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false otherwise.
int getRandom() Returns a random element from the current set of elements (it's guaranteed that at least one element exists when this method is called). Each element must have the same probability of being returned.
Follow up: Could you implement the functions of the class with each function works in average O(1) time?

*/

class RandomizedSet {
    
    HashMap<Integer, Integer> map; // maps values to their indexes in the list
    ArrayList<Integer> arr; // stores values
    Random r; // chooses random index

    /** Initialize your data structure here. */
    public RandomizedSet() {
        map = new HashMap<>();
        arr = new ArrayList<>();
        r = new Random();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) return false; // if we already have this value, don't duplicate
        
        // otherwise add to the map and arr
        map.put(val, arr.size());
        arr.add(val);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) return false; // can't remove something that is not there
        
        int idx = map.get(val); // get the index of the removing value
        
        // if the index is not already at the end, swap with the end and update the value that was swapped to the new index in the map
        if (idx < arr.size() - 1) {
            int temp = arr.get(idx);
            arr.set(idx, arr.get(arr.size() - 1));
            arr.set(arr.size() - 1, temp);

            map.put(arr.get(idx), idx);
        }
        
        // remove the value
        map.remove(val);
        arr.remove(arr.size() - 1);
        
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        return arr.get(r.nextInt(arr.size())); // pick random element within the bounds
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
