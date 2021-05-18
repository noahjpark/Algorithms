/* Noah Park

Given a list paths of directory info, including the directory path, and all the files with contents in this directory, return all the duplicate files in the file system in terms of their paths. You may return the answer in any order.

A group of duplicate files consists of at least two files that have the same content.

A single directory info string in the input list has the following format:

"root/d1/d2/.../dm f1.txt(f1_content) f2.txt(f2_content) ... fn.txt(fn_content)"
It means there are n files (f1.txt, f2.txt ... fn.txt) with content (f1_content, f2_content ... fn_content) respectively in the directory "root/d1/d2/.../dm". Note that n >= 1 and m >= 0. If m = 0, it means the directory is just the root directory.

The output is a list of groups of duplicate file paths. For each group, it contains all the file paths of the files that have the same content. A file path is a string that has the following format:

"directory_path/file_name.txt"

*/

class Solution {
    
    // Intuition: Map all unique file contents to the files that contain these contents.
    // Time: O(n*m) where n is the number of paths and m is the number of characters in each path.
    // Space: O(n*m) for the result.
    public List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> map = new HashMap<>();
        List<List<String>> res = new ArrayList<>();
        
        for (String path : paths) {
            String[] split = path.split(" ");
            
            for (int i = 1; i < split.length; i++) {
                StringBuilder s = new StringBuilder(), fileContent = new StringBuilder();
                s.append(split[0] + "/"); // root
                boolean file = false;
                
                for (int j = 0; j < split[i].length() - 1; j++) {
                    char c = split[i].charAt(j);
                    
                    if (file && c != ')') fileContent.append(c);
                    else if (c == '(') file = true;
                    else s.append(c);
                }
                
                if (!map.containsKey(fileContent.toString())) map.put(fileContent.toString(), new ArrayList<>());
                map.get(fileContent.toString()).add(s.toString());
                if (map.get(fileContent.toString()).size() == 2) res.add(map.get(fileContent.toString()));
            }
        }
        
        return res;
    }
}
