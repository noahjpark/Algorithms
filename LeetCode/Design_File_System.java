/* Noah Park

You are asked to design a file system that allows you to create new paths and associate them with different values.

The format of a path is one or more concatenated strings of the form: / followed by one or more lowercase English letters. For example, "/leetcode" and "/leetcode/problems" are valid paths while an empty string "" and "/" are not.

Implement the FileSystem class:

bool createPath(string path, int value) Creates a new path and associates a value to it if possible and returns true. Returns false if the path already exists or its parent path doesn't exist.
int get(string path) Returns the value associated with path or returns -1 if the path doesn't exist.
 

*/

class FileSystem {
    
    // Intuition: Map all paths to their values. When creating paths, if we have a duplicate, we don't add. Otherwise, we find the parent to the current directory by starting near the end and moving left until we find the first slash. If i (first slash) is the first character, this is an edge case and we must add the path regardless. Otherwise, if the map does not contain the parent directory, we don't add either. Finally we simply add the path since its parent is mapped.
    // Time: O(n) to iterate over the path.
    // Space: O(m) to maintain the map.
    Map<String, Integer> map;

    public FileSystem() {
        map = new HashMap<>();
    }
    
    public boolean createPath(String path, int value) {
        if (map.containsKey(path)) return false;

        int i = path.length() - 2;
        while (path.charAt(i) != '/') i--;
        
        if (i == 0) { map.put(path, value); return true; }
        if (!map.containsKey(path.substring(0, i))) return false;
        
        map.put(path, value);
        return true;
    }
    
    public int get(String path) {
        return map.getOrDefault(path, -1);
    }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * boolean param_1 = obj.createPath(path,value);
 * int param_2 = obj.get(path);
 */
