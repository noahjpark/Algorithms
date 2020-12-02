/* Noah Park

Given a list of the scores of different students, items, where items[i] = [IDi, scorei] represents one score from a student with IDi, calculate each student's top five average.

Return the answer as an array of pairs result, where result[j] = [IDj, topFiveAveragej] represents the student with IDj and their top five average. Sort result by IDj in increasing order.

A student's top five average is calculated by taking the sum of their top five scores and dividing it by 5 using integer division.

*/

class Solution {
    public int[][] highFive(int[][] items) {
        Map<Integer, PriorityQueue<Integer>> total = new HashMap<>(); // HashMap will map student ids to their scores
        
        for (int[] student : items) {
            if(!total.containsKey(student[0])) total.put(student[0], new PriorityQueue<Integer>(5)); // only holding 5 scores and ensure that a new id has a score list
            PriorityQueue<Integer> p = total.get(student[0]);
            p.offer(student[1]); // offer the student score
            if(p.size() > 5) p.poll(); // remove the smallest from the pq if the size is greater than 5
        }
        
        // return list
        int[][] res = new int[total.size()][2];
        int i = 0;
        for (Map.Entry<Integer, PriorityQueue<Integer>> entry : total.entrySet()) { // iterate over entries in the map
            int val = 0;
            PriorityQueue<Integer> p = entry.getValue();
            while(!p.isEmpty()) val += p.poll(); // add up the 5 largest
            res[i][0] = entry.getKey();
            res[i++][1] = val / 5; // take the average
        }
        
        return res;
    }
}
