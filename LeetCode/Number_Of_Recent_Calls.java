/* Noah Park

You have a RecentCounter class which counts the number of recent requests within a certain time frame.

Implement the RecentCounter class:

RecentCounter() Initializes the counter with zero recent requests.
int ping(int t) Adds a new request at time t, where t represents some time in milliseconds, and returns the number of requests that has happened in the past 3000 milliseconds (including the new request). Specifically, return the number of requests that have happened in the inclusive range [t - 3000, t].
It is guaranteed that every call to ping uses a strictly larger value of t than the previous call.

*/

class RecentCounter {

    //LinkedList<Integer> pings;
    TreeMap<Integer, Integer> pings;
    
    public RecentCounter() {
        //pings = new LinkedList<>();
        pings = new TreeMap<>();
    }
    
    public int ping(int t) {
        // pings.add(t);
        // while(pings.getFirst() < (t - 3000))
        //     pings.removeFirst();
        // return pings.size();
        pings.put(t, 0);
        return pings.tailMap(t - 3000).size();
    }
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */
