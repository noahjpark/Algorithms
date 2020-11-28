/* Noah Park

Design a logger system that receive stream of messages along with its timestamps, each message should be printed if and only if it is not printed in the last 10 seconds.

Given a message and a timestamp (in seconds granularity), return true if the message should be printed in the given timestamp, otherwise returns false.

It is possible that several messages arrive roughly at the same time.

*/

class Logger {
    
    // 1 solution uses hashmap, the other uses circular array
    //Map<String, Integer> log;
    private int[] times = new int[10];
    private Set[] nums = new Set[10];

    /** Initialize your data structure here. */
    public Logger() {
        //log = new HashMap<>();
        for(int i = 0; i < 10; i++)
            nums[i] = new HashSet<String>();
    }
    
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
//         if (!log.containsKey(message)) {
//             log.put(message, timestamp);
//             return true;
//         } else if (timestamp - log.get(message) >= 10) {
//             log.put(message, timestamp);
//             return true;
//         }
        
//         return false;
        int i = timestamp % 10;
        if(timestamp != times[i]){
            nums[i].clear();
            times[i] = timestamp;
        }
        for(int j = 0; j < 10; j++){
            if(timestamp - times[j] < 10) {
                if(nums[j].contains(message)) return false;
            }
        }
        nums[i].add(message);
        return true;
    }
}

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */
