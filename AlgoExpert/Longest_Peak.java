// Noah Park
/*

Problem: Write a function that takes in an array of integers and returns the length of the
longest peak in the array.

A peak is defined as adjacent integers in the array that are strictly increasing until they
reach a tip (the highest value in the peak), at which point they become strictly decreasing.
At least three integers are required to form a peak.

For example, the integers 1, 4, 10, 2 form a peak, but the integers 4, 0, 10 don't and neither
do the integers 1, 2, 2, 0. Similarly, the integers 1, 2, 3 don't form a peak because there aren't
any strictly decreasing integers after the 3.

The reason the optimal solution is O(n). Where a peak ends is where another peak starts and vice versa.
We really won't end up visiting many nodes more than once.

*/

public class Longest_Peak {

    /*
    Think about what makes a peak. Values increasing to the peak and values decreasing after
    the peak. All we need is to have at least 1 increasing value and 1 decreasing value to have
    a new valid peak. Once we know this, we just keep checking each value in the array once to compare
    the values near it.
    */

    // Optimal - Time: O(n) | Space: O(1)
    // Since we know that a peak must have at least an integer increasing before it and an integer
    // decreasing after it, we can simplify this problem by counting the number of integers increasing
    // to it and decreasing after. If both values are greater than 0, we have a peak and can see if this
    // peak is larger than our previous one (ans). Using helper function to count the increasing and decreasing
    // values makes this problem quite simple.
    public static int longestPeak(int[] array) {
        int ans = 0;
        int i = 1;

        while(i < array.length - 1){
            if(array[i - 1] < array[i] && array[i] > array[i + 1]) { // we have a peak
                int increasing = countIncreasing(array, i);
                int[] temp = countDecreasing(array, i);
                int decreasing = temp[0];
                int total = increasing + decreasing + 1;

                if (total > ans) {
                    ans = total;
                }
                i = temp[1];
            }
            else{
                i++;
            }
        }

        return ans;
    }

    // Counts the number of consecutive increasing values before the given index
    public static int countIncreasing(int[] array, int index){
        int total = 0;
        int prev = array[index--];
        while(index > -1 && array[index] < prev){
            total++;
            prev = array[index--];
        }
        return total;
    }

    // Counts the number of consecutive decreasing values after the given index
    // Note: We return both the total values AND the new index to begin at so to make
    // the search the most efficient.
    public static int[] countDecreasing(int[] array, int index){
        int total = 0;
        int prev = array[index++];
        while(index < array.length && array[index] < prev){
            total++;
            prev = array[index++];
        }
        return new int[]{total, index};
    }

    // Naive solution: Time: O(n^2) and Space: O(1)
    // This is a pretty inefficient solution to the above.
    // In fact, when looking for future peaks, they could have been found if continued
    // iteration occurred, but the inner loop allows us to find them later. All in all,
    // this solution is actually much more confusing anyways than the one above.
    public static int longestPeakNaive(int[] array) {
        boolean decreasing, increasing; // Use booleans to try to tell when we are increasing or decreasing
        int ans = 0; // Total peaks

        // Iterate through the array
        for(int i = 0; i < array.length - 1; i++){
            // Initialize to what we start with looking for only increasing values and
            // our current count to 1
            decreasing = false;
            increasing = true;
            int curCount = 1;
            // Iterate through all values in front of the current index starting from the current index
            for(int j = i; j < array.length - 1; j++){

                // If we are decreasing and hit an equal or larger number, break
                if(decreasing && array[j + 1] >= array[j]){
                    break;
                }

                // If we are increasing, find a decreasing value, and the current number of
                // values we have seen is larger than 1, we update decreasing and increasing,
                // since we are now looking at decreasing values
                else if(increasing && array[j + 1] < array[j] && curCount >= 2){
                    decreasing = true;
                    increasing = false;
                }

                // Caveat to the above: If the current count is less than 2, we can't
                // have a proper peak and must break from the current index counting
                else if(increasing && array[j + 1] < array[j] && curCount < 2){
                    break;
                }

                // If we are increasing and find an equal value, we have to break since it
                // cannot be apart of the current peak
                else if(increasing && array[j + 1] <= array[j]){
                    break;
                }

                // Update current value count
                curCount++;
            }
            // If we were decreasing and our last ans is smaller than our new peak size,
            // and the current count is 3 or more, we can update our peak size
            if(!increasing && ans < curCount && curCount > 2){
                ans = curCount;
            }
        }

        // Return the total number of peaks
        return ans;
    }
}
