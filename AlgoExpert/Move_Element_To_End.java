// Noah Park
/*

Problem: You're given an array of integers and an integer. Write a function that moves all
instances of that integer in the array to the end of the array and returns the array.

The function should perform this in place (i.e., it should mutate the input array) and doesn't
need to maintain the order of the other integers.

*/

import java.util.List;

public class Move_Element_To_End {
    // Naive O(n^2) time and O(1) space first solution
    public static List<Integer> moveElementToEnd(List<Integer> array, int toMove) {
        /*
        Starting from the back of the array, iterate until we find a value that does not match
        'toMove'. We can swap this with an earlier occurrence of 'toMove' between 0 and i. Maybe
        make this more efficient by breaking if there are no values of toMove to swap.
        */
        for(int i = array.size() - 1; i >= 0; i--){
            int finished = 0;
            if(array.get(i) != toMove){
                for(int j = 0; j < i; j++){
                    if(array.get(j) == toMove){
                        int temp = array.get(j);
                        array.set(j, array.get(i));
                        array.set(i, temp);
                    }
                    if(finished == i - 1){
                        break;
                    }
                    finished++;
                }
            }
        }
        return array;
    }

    // Optimal O(n) time and O(!) space
    public static List<Integer> moveElementToEndOptimal(List<Integer> array, int toMove) {
        /*
        To do this in one pass, I figured we needed to use a slow pointer. Basically, the slow
        pointer will start at the beginning of the array, as does i, and whenever we find a value
        that should be in front of all 'toMove' values aka a value that is not 'toMove', we swap it
        with the slow pointer then increment the slow pointer. Slow pointer will always be on a
        'toMove' value when two DIFFERENT values are being swapped. In the case that the slow pointer
        is not on a 'toMove' value, it will be identical to i. Now we can get away with only using a single
        pass through the array with no extra space resulting in O(n) and O(1) time and space complexity, respectively.
        */
        int lastPtr = 0;
        for(int i = 0; i < array.size(); i++){
            if(array.get(i) != toMove){
                int temp = array.get(lastPtr);
                array.set(lastPtr, array.get(i));
                array.set(i, temp);
                lastPtr++;
            }
        }
        return array;
    }

    /*
    Clement's solution: Start at opposite ends of the array without sorting. The overall loop should iterate
    until i >= j. In this loop, move j to a value that isn't 'toMove' by calling while(i < j && array.get(j) == toMove) j--;
    By doing this, we get j to be a value that is NOT 'toMove' and needs to be moved to a value in front of all 'toMove'
    occurrences. Then an if conditional, outside of the inner while loop, checks if the value at i is 'toMove'... In this case,
    we swap the values at i and j and remember to increment i at the end.
    */
}
