// Noah Park
/*

Problem: You have an integer and you can flip exactly one bit from a 0
to a 1. Write code to find the length of the longest sequence of 1s you
could create.

*/

public class Flip_Bit_to_Win {

    /*
        Thought Process:
        1. If the number is 0, return 1
        2. If the entire number is 1s, return the length of the 1s
        3. Two separate strings of 1s can be combined if they are only separated by 1 zero
    */
    public static int longestSequence(int n){
        // Longest bit sequence would be 1 if all 0s
        if(n == 0){
            return 1;
        }

        // ans stores the largest count so far
        // curCount stores the current count of 1s so far
        // changedZero tells us if we have changed a 0 for a 1 yet
        int ans = 0;
        int curCount = 0;
        boolean changedZero = false;

        // Use a temporary variable to not destroy n
        int temp = n;

        // iterate through all the bits in temp
        while(temp > 0){
            // Get the least significant bit
            int bit = temp & 1;
            // If the bit is zero we need to know if we have already swapped yet
            if(bit == 0){
                // If not, increment curCount as we are "changing" to a one and update changedZero
                if(!changedZero){
                    curCount++;
                    changedZero = true;
                }
                // If so, update ans to curCount and reset curCount
                else {
                    ans = curCount;
                    curCount = 0;
                }
            }
            // increment curCount if we find a one
            else{
                curCount++;
            }
            // Move temp along
            temp >>= 1;
        }

        return ans;
    }

    // Basic testing of the class
    public static void main(String[] args){
        int test = 1775;
        System.out.println(longestSequence(test));
    }
}
