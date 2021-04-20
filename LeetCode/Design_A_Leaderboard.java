/* Noah Park

Design a Leaderboard class, which has 3 functions:

addScore(playerId, score): Update the leaderboard by adding score to the given player's score. If there is no player with such id in the leaderboard, add him to the leaderboard with the given score.
top(K): Return the score sum of the top K players.
reset(playerId): Reset the score of the player with the given id to 0 (in other words erase it from the leaderboard). It is guaranteed that the player was added to the leaderboard before calling this function.
Initially, the leaderboard is empty.

*/

class Leaderboard {
    
    // Intuition: Utilize a sorted map (reversed so in descending order) along with the normal player map to maintain the largest scores in order as a BST.
    // Time: O(log n) for add and reset. O(K) for top.
    // Space: O(n) for the maps.
    Map<Integer, Integer> map;
    TreeMap<Integer, Integer> scores;
    
    public Leaderboard() {
        map = new HashMap<>();
        scores = new TreeMap<>(Collections.reverseOrder());
    }
    
    public void addScore(int id, int score) {
        if (!map.containsKey(id)) {
            map.put(id, score);
            scores.put(score, scores.getOrDefault(score, 0) + 1);
        } else {
            scores.put(map.get(id), scores.get(map.get(id)) - 1);
            if (scores.get(map.get(id)) == 0) scores.remove(map.get(id));
            
            map.put(id, map.getOrDefault(id, 0) + score);
            scores.put(map.get(id), scores.getOrDefault(map.get(id), 0) + 1);
        }
    }
    
    public int top(int K) {
        int sum = 0;
        
        for (Map.Entry<Integer, Integer> entry : scores.entrySet()) {
            if (K <= entry.getValue()) { sum += K*entry.getKey(); break; }
            else { sum += entry.getKey()*entry.getValue(); K -= entry.getValue(); }
        }
        
        return sum;
    }
    
    public void reset(int id) {
        int score = map.get(id);
        map.remove(id);
        scores.put(score, scores.get(score) - 1);
        if (scores.get(score) == 0) scores.remove(score);
    }
}


/*
class Leaderboard {

    // Intuition: Map all player ids to their scores with a Hash Map. When we need the top K elements we can add to an array and sort (brute force). We can optimize this using a min/max heap to store all top K elements.
    // Time: O(1) for add and reset. O(K) + O(N log K) for the top method.
    // Space: O(n + K) for the map and heap.
    Map<Integer, Integer> map;
    
    public Leaderboard() {
        map = new HashMap<>();
    }
    
    public void addScore(int playerId, int score) {
        map.put(playerId, map.getOrDefault(playerId, 0) + score);
    }
    
    public int top(int K) {
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>((a, b) -> (a.getValue() - b.getValue()));
        int sum = 0;
        
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > K) minHeap.poll();
        }
        
        while (minHeap.size() > 0 && K-- > 0)
            sum += minHeap.poll().getValue();
            
        return sum;
    }
    
    public void reset(int playerId) {
        map.remove(playerId);
    }
}
*/

/**
 * Your Leaderboard object will be instantiated and called as such:
 * Leaderboard obj = new Leaderboard();
 * obj.addScore(playerId,score);
 * int param_2 = obj.top(K);
 * obj.reset(playerId);
 */
