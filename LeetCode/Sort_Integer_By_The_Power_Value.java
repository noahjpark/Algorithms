/* Noah Park

The power of an integer x is defined as the number of steps needed to transform x into 1 using the following steps:

if x is even then x = x / 2
if x is odd then x = 3 * x + 1
For example, the power of x = 3 is 7 because 3 needs 7 steps to become 1 (3 --> 10 --> 5 --> 16 --> 8 --> 4 --> 2 --> 1).

Given three integers lo, hi and k. The task is to sort all integers in the interval [lo, hi] by the power value in ascending order, if two or more integers have the same power value sort them by ascending order.

Return the k-th integer in the range [lo, hi] sorted by the power value.

Notice that for any integer x (lo <= x <= hi) it is guaranteed that x will transform into 1 using these steps and that the power of x is will fit in 32 bit signed integer.

*/

class Solution {
    Integer[] powerMap = new Integer[1001]; // constant map for memoization on the power values
    
    // Time: O(n log n + n*x) where n = lo + hi - 1 elements in the heap and x is the time it takes for the power function to run
    // Space: O(n) where n is the same as above
    public int getKth(int lo, int hi, int k) {
        Comparator<int[]> c = new Comparator<>() {
            public int compare(int[] arr1, int[] arr2) {
                if (arr1[1] != arr2[1]) return Integer.compare(arr1[1], arr2[1]);
                else return Integer.compare(arr1[0], arr2[0]);
            }
        };
        
        // use array instead of priority queue
        int[][] arr = new int[hi - lo + 1][2];
        
        for (int i = 0; i < hi - lo + 1; i++)
            arr[i] = new int[]{ lo + i, power(lo + i) };
        
        // built in sort using the custom comparator
        Arrays.sort(arr, c);
        
        // k'th element will be at k - 1
        return arr[k - 1][0];
    }
    
    // Time: O(n log n + n*x) where n = lo + hi - 1 elements in the heap and x is the time it takes for the power function to run
    // Space: O(n) where n is the same as above
    public int getKthHeap(int lo, int hi, int k) {
        // comparator sorts based on power value in ascending order
        // if two power values are equal, it sorts the original value in ascending order
        Comparator<int[]> c = new Comparator<>() {
            public int compare(int[] arr1, int[] arr2) {
                if (arr1[1] != arr2[1]) return Integer.compare(arr1[1], arr2[1]);
                else return Integer.compare(arr1[0], arr2[0]);
            }
        };
        
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(c);
        
        // will be in ascending order in the min heap
        for (int i = lo; i <= hi; i++)
            minHeap.offer(new int[]{ i, power(i) });
        
        // poll until the top element is the k'th power value
        while (!minHeap.isEmpty() && k > 1) {
            int[] temp = minHeap.poll();
            k--;
        }
    
        
        return minHeap.isEmpty() ? -1 : minHeap.peek()[0];
    }
    
    // For simplicity sake we will say that this function takes O(x) time, as we do not know the exact time bound on this
    public int power(int x) {
        if (powerMap[x] != null) return powerMap[x]; // memoized solution, don't recompute
        
        int steps = 0;
        
        while (x != 1) {
            if (x % 2 == 0) x /= 2;
            else x = 3*x + 1;
            steps++;
        }
        
        powerMap[x] = steps; // store solution so we don't need to recompute
        return steps;
    }
}
