/* Noah Park

You are given several logs, where each log contains a unique ID and timestamp. Timestamp is a string that has the following format: Year:Month:Day:Hour:Minute:Second, for example, 2017:01:01:23:59:59. All domains are zero-padded decimal numbers.

Implement the LogSystem class:

LogSystem() Initializes the LogSystem object.
void put(int id, string timestamp) Stores the given log (id, timestamp) in your storage system.
int[] retrieve(string start, string end, string granularity) Returns the IDs of the logs whose timestamps are within the range from start to end inclusive. start and end all have the same format as timestamp, and granularity means how precise the range should be (i.e. to the exact Day, Minute, etc.). For example, start = "2017:01:01:23:59:59", end = "2017:01:02:23:59:59", and granularity = "Day" means that we need to find the logs within the inclusive range from Jan. 1st 2017 to Jan. 2nd 2017, and the Hour, Minute, and Second for each log entry can be ignored.

*/

// Intuition: Map each number to its timestamp. Based on the case, we can compare parts of th string to see if they are within a range. Going from right to left ensures the larger numbers (year vs seconds) take precedence.
// Time: O(n) to iterate over all boundaries.
// Space: O(n) to maintain timestamps.
class LogSystem {
    
    Map<Integer, String> map;

    public LogSystem() {
        map = new HashMap<>();
    }
    
    public void put(int id, String timestamp) {
        map.put(id, timestamp);
    }
    
    public List<Integer> retrieve(String start, String end, String granularity) {
        List<Integer> res = new ArrayList<>();
        
        for (Integer id : map.keySet()) {
            String[] split = map.get(id).split(":"), s = start.split(":"), e = end.split(":");
            boolean ss = true, ee = true;
            int idx;
            
            switch (granularity) {
                case "Year" -> idx = 1;
                case "Month" -> idx = 2;
                case "Day" -> idx = 3;
                case "Hour" -> idx = 4;
                case "Minute" -> idx = 5;
                default -> idx = 6;
            }
            
            for (int i = idx - 1; i >= 0; i--) {
                if (split[i].compareTo(s[i]) < 0) ss = false;
                else if (split[i].compareTo(s[i]) > 0) ss = true;
                
                if (split[i].compareTo(e[i]) > 0) ee = false;
                else if (split[i].compareTo(e[i]) < 0) ee = true;
            }
            
            if (ss && ee) res.add(id);
        }
        
        return res;
    }
}

/**
 * Your LogSystem object will be instantiated and called as such:
 * LogSystem obj = new LogSystem();
 * obj.put(id,timestamp);
 * List<Integer> param_2 = obj.retrieve(start,end,granularity);
 */
