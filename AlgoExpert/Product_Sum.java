// Noah Park
/*

Problem: Write a function that takes in a "special" array and returns its product sum.

A "special" array is a non-empty array that contains either integers or other "special" arrays.
The product sum of a "special" array is the sum of its elements, where "special" arrays inside it
are summed themselves and then multiplied by their level of depth.

The depth of a "special" array is how far nested it is. For instance, the depth of [] is 1; the depth
of the inner array in [[]] is 2; the depth of the innermost array in [[[]]] is 3.

Therefore, the product sum of [x, y] is x + y; the product sum of [x, [y, z]] is x + 2 * (y + z); the
product sum of [x, [y, [z]]] is x + 2 * (y + 3z).

*/

import java.util.ArrayList;
import java.util.List;

public class Product_Sum {
    // Time: O(n) | Space: O(h) where h is the height of the recursive calls/depth of the deepest inner array
    public static int productSum(List<Object> array) {
        return productHelper(array, 1);
    }

    @SuppressWarnings("unchecked")
    public static int productHelper(List<Object> array, int depth){
        int curSum = 0; // needs to be set to 0 at the start of each function call otherwise, will add things we already added

        // Iterate through the array:
        // 1. If we find an array within the array, recursive call the function and add its value with the increased depth multiplied by the increased depth to the current sum
        // 2. Alternatively, if we just find numbers then add the number to our current sum
        for(Object o : array){
            if(o instanceof ArrayList){
                int inner = productHelper((List<Object>) o, depth + 1);
                curSum += ((depth + 1) * inner);
            }
            else{
                curSum += (int) o;
            }
        }

        return curSum;
    }
}
