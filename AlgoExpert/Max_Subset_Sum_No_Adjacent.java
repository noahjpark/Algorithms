// Noah Park
/*

Problem: Write a function that takes in an array of positive integers and returns the maximum
sum of non-adjacent elements in the array.

If a sum can't be generated, the function should return 0.

*Dynamic Programming*

*/

public class Max_Subset_Sum_No_Adjacent {
    // Naive solution using Dynamic Programming
    public static int maxSubsetSumNoAdjacent(int[] array) {

        // Store maxes in this array
        int[] maxes = new int[array.length];

        // Iterate through the array once
        for(int i = 0; i < array.length; i++){
            // Put the first element into maxes from array
            if(i == 0){
                maxes[i] = array[i];
            }
            // Put the larger of the first two elements into maxes[1] from array
            else if(i == 1){
                maxes[i] = array[i] < array[i - 1] ? array[i - 1] : array[i];
            }

            // Else use the last two maximum values to generate our new one
            // If our i - 2 value plus our i value is larger than our i - 1 value,
            // we have found a new larger max and update maxes accordingly. Otherwise,
            // the i - 1 is still the largest max and inserted into the maxes array
            else{
                int prevMax = maxes[i - 1];
                int newPossibleMax = maxes[i - 2] + array[i];
                if(newPossibleMax > prevMax){
                    maxes[i] = newPossibleMax;
                }
                else{
                    maxes[i] = prevMax;
                }
            }
        }

        // If the maxes array is empty, return 0, otherwise return the last value, as it
        // will be the largest in the array
        return maxes.length == 0 ? 0 : maxes[maxes.length - 1];
    }

    // Optimal solution using Dynamic Programming
    public static int maxSubsetSumNoAdjacentOptimal(int[] array) {
        // If the array contains only 0 or 1 value,
        // return the corresponding values for those
        if(array.length == 0){
            return 0;
        }
        else if(array.length == 1){
            return array[0];
        }

        // Need to store two values for the old 2 values i - 1 and i - 2
        int prevMax = array[0];
        int curMax = Math.max(array[0], array[1]);

        // Iterate through the rest of the array:
        // 1. Generate a new max using curMax (i - 1) and prevMax + array[i] (i - 2 value + value at i)
        // 2. Update prevMax to curMax (essentially adding one to an index)
        // 3. Update curMax to the max value just calculated which is similar to adding one to the index as we
        // are updating the max
        for(int i = 2; i < array.length; i++){
            int max = Math.max(curMax, prevMax + array[i]);
            prevMax = curMax;
            curMax = max;
        }

        // Return curMax - the largest value we could generate
        return curMax;
    }
}
