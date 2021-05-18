/* Noah Park

Given a url startUrl and an interface HtmlParser, implement a web crawler to crawl all links that are under the same hostname as startUrl. 

Return all urls obtained by your web crawler in any order.

Your crawler should:

Start from the page: startUrl
Call HtmlParser.getUrls(url) to get all urls from a webpage of given url.
Do not crawl the same link twice.
Explore only the links that are under the same hostname as startUrl.


As shown in the example url above, the hostname is example.org. For simplicity sake, you may assume all urls use http protocol without any port specified. For example, the urls http://leetcode.com/problems and http://leetcode.com/contest are under the same hostname, while urls http://example.org/test and http://example.com/abc are not under the same hostname.

The HtmlParser interface is defined as such: 

interface HtmlParser {
  // Return a list of all urls from a webpage of given url.
  public List<String> getUrls(String url);
}
Below are two examples explaining the functionality of the problem, for custom testing purposes you'll have three variables urls, edges and startUrl. Notice that you will only have access to startUrl in your code, while urls and edges are not directly accessible to you in code.

*/

/**
 * // This is the HtmlParser's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface HtmlParser {
 *     public List<String> getUrls(String url) {}
 * }
 */

class Solution {
    
    // Intuition: dfs on the graph problem. Set ensures we don't revisit nodes.
    // Time: O(V + E) where v is the number of vertices in the graph and e is the number of edges.
    // Space: O(V + E) in case the graph looks like a linked list.
    List<String> res = new ArrayList<>();
    Set<String> visited = new HashSet<>();
    String hostname;
    
    public List<String> crawl(String start, HtmlParser h) {
        hostname = start.split("/")[2];
        dfs(start, h);
        return res;
    }
    
    public void dfs(String start, HtmlParser h) {
        visited.add(start);
        res.add(start);
        List<String> neighbors = h.getUrls(start);
        
        for (String s : neighbors) 
            if (!visited.contains(s) && hostname.equals(s.split("/")[2])) dfs(s, h);
    }
    
    // Intuition: bfs on the graph problem. Set ensures we don't revisit nodes.
    // Time: O(V + E) where v is the number of vertices in the graph and e is the number of edges.
    // Space: O(V + E) in case the majority of the nodes are in the first level.
    public List<String> crawl2(String startUrl, HtmlParser htmlParser) {
        List<String> res = new ArrayList<>();
        String hostname = startUrl.split("/")[2];
        Queue<String> q = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        
        q.offer(startUrl);
        res.add(startUrl);
        visited.add(startUrl);
        
        while (!q.isEmpty()) {
            String cur = q.poll();
            List<String> neighbors = htmlParser.getUrls(cur);
            
            for (String s : neighbors) {
                if (!visited.contains(s) && hostname.equals(s.split("/")[2])) {
                    visited.add(s);
                    q.offer(s);
                    res.add(s);
                }
            }
        }
        
        return res;
    }
}
