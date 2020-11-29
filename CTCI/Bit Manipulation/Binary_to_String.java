// Noah Park
/*

Problem: Given a real number between 0 and 1 (e.g., 0.72) that is passed in as a double, print
the binary representation. If the number cannot be represented accurately in binary with at
most 32 characters, print "ERROR".

*/

public class Binary_to_String {

    /*

    Thought Process:
    1. We need to keep track of the number of characters
    2. We need to convert the real number from base 10 to binary (base 2)

    This doesn't appear to work for all decimals that it should...
    */
    public static String binaryString(double real){
        // real is out of bounds
        if(real >= 1 || real <= 0){
            return "ERROR";
        }

        // ans will be our string obtained from a string builder
        StringBuilder ans = new StringBuilder();
        ans.append("."); // start with a period

        // loop until real becomes zero
        while(real > 0){
            // bound ans to 32 characters
            if(ans.length() > 32){
                return "ERROR";
            }

            // Use the algorithm from the book to add either a '1' or a '0'
            double temp = real * 2;
            if(temp >= 1){
                ans.append("1");
                real = temp - 1;
            }
            else{
                ans.append("0");
                real = temp;
            }
        }
        return ans.toString();
    }

    // Basic testing of the class
    public static void main(String[] args){
        System.out.println(binaryString(0.873));
    }
}
