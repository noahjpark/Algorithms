/* Noah Park

You are given an array of people, people, which are the attributes of some people in a queue (not necessarily in order). Each people[i] = [hi, ki] represents the ith person of height hi with exactly ki other people in front who have a height greater than or equal to hi.

Reconstruct and return the queue that is represented by the input array people. The returned queue should be formatted as an array queue, where queue[j] = [hj, kj] is the attributes of the jth person in the queue (queue[0] is the person at the front of the queue).

*/

class Solution {
    public int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0) return people; // edge cases
        
        // sort using tallest first, we can insert tallest into their position
        Comparator<int[]> c = new Comparator<>() {
            public int compare(int[] arr1, int[] arr2) {
                if (arr1[0] != arr2[0]) return Integer.compare(arr2[0], arr1[0]);
                else return Integer.compare(arr1[1], arr2[1]);
            }
        };
        
        Arrays.sort(people, c);
        
        List<int[]> list = new ArrayList<>();
        
        // insert next tallest at each point
        for(int[] person : people) {
            if (list.size() > person[1]) list.add(person[1], person);
            else list.add(person);
        }
        
        return list.toArray(new int[list.size()][2]);
    }
}
