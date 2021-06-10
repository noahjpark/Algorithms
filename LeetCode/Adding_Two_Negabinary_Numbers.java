/* Noah Park

Given two numbers arr1 and arr2 in base -2, return the result of adding them together.

Each number is given in array format:  as an array of 0s and 1s, from most significant bit to least significant bit.  For example, arr = [1,1,0,1] represents the number (-2)^3 + (-2)^2 + (-2)^0 = -3.  A number arr in array, format is also guaranteed to have no leading zeros: either arr == [0] or arr[0] == 1.

Return the result of adding arr1 and arr2 in the same format: as an array of 0s and 1s with no leading zeros.

*/

class Solution {
    
    // https://en.wikipedia.org/wiki/Negative_base#Addition
    // Intuition: Converting to negative base involves keeping track of the remainder going negative/positive. If we have a negative sum, we create a positive remainder (1). Otherwise, if we have a sum of 2 or larger for this -2 base, we carry a negative remainder (-1). Otherwise, the remainder will be 0. We must keep looping until the remainder becomes zero so we actually finish the number. We must also remove leading zeroes.
    // Time: O(max(n, m)) to iterate over the longer array's length.
    // Space: O(n) to maintain the resulting list.
    public int[] addNegabinary(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null || arr1.length == 0 || arr2.length == 0) return new int[]{};
        
        Deque<Integer> temp = new LinkedList<>();
        int n = arr1.length, m = arr2.length, rem = 0;
        
        for (int i = 0; i < Math.max(n, m) || rem != 0; i++) {
            int num1 = (n - 1 - i >= 0) ? arr1[n - 1 - i] : 0, num2 = (m - 1 - i >= 0) ? arr2[m - 1 - i] : 0, sum = num1 + num2 + rem;
            temp.addFirst(Math.abs(sum) % 2);
            rem = sum < 0 ? 1 : sum > 1 ? -1 : 0;
        }
        
        while (temp.size() > 1 && temp.getFirst() == 0) temp.removeFirst(); // remove leading zeroes.
        
        int[] res = new int[temp.size()];
        for (int i = 0; !temp.isEmpty(); i++)
            res[i] = temp.removeFirst();
        
        return res;
    }
}
