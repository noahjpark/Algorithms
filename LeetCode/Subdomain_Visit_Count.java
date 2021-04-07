/* Noah Park

A website domain like "discuss.leetcode.com" consists of various subdomains. At the top level, we have "com", at the next level, we have "leetcode.com", and at the lowest level, "discuss.leetcode.com". When we visit a domain like "discuss.leetcode.com", we will also visit the parent domains "leetcode.com" and "com" implicitly.

Now, call a "count-paired domain" to be a count (representing the number of visits this domain received), followed by a space, followed by the address. An example of a count-paired domain might be "9001 discuss.leetcode.com".

We are given a list cpdomains of count-paired domains. We would like a list of count-paired domains, (in the same format as the input, and in any order), that explicitly counts the number of visits to each subdomain.

*/

class Solution {
    
    // Intuition: Store all frequencies of each domain and subdomain in a hashmap based on the visits passed in. Then make a pass through the hashmap populating a stringbuilder to finally be added to the arraylist that is being returned.
    // Time: O(n) to visit all domains in the array
    // Space: O(n) to store all the domains in the array and their subdomains (at most 3)
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> map = new HashMap<>();
        List<String> res = new ArrayList<>();
        
        for (String s : cpdomains) {
            String[] split = s.split(" "), d = split[1].split("\\.");
            int visits = Integer.parseInt(split[0]);
            
            map.put(split[1], map.getOrDefault(split[1], 0) + visits);
            if (d.length == 3) map.put(d[1] + "." + d[2], map.getOrDefault(d[1] + "." + d[2], 0) + visits);
            map.put(d[d.length - 1], map.getOrDefault(d[d.length - 1], 0) + visits);
        }
        
        for (String key : map.keySet()) {
            StringBuilder s = new StringBuilder();
            
            s.append(map.get(key));
            s.append(" ");
            s.append(key);
            
            res.add(s.toString());
        }
        
        return res;
    }
}
