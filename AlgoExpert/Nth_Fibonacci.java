// Noah Park
/*

Problem: The Fibonacci sequence is defined as follows: the first number of the sequence is 0,
the second number is 1, and the nth number is the sum of the (n - 1)th and (n - 2)th numbers. Write
a function that takes in an integer n and returns the nth Fibonacci number.

Important note: the Fibonacci sequence is often defined with its first two numbers as F0 = 0 and
F1 = 1. For the purpose of this question, the first Fibonacci number is F0; therefore, getNthFib(1)
is equal to F0, getNthFib(2) is equal to F1, etc...

*/

public class Nth_Fibonacci {
    // Time: O(n) | Space O(1)
    public static int getNthFib(int n) {
        if(n == 1){ // First Fibonacci number
            return 0;
        }
        else if(n == 2){ // Second Fibonacci number
            return 1;
        }
        // Use the previous two numbers in the sequence to generate the next one
        return getNthFib(n - 1) + getNthFib(n - 2);
    }
}
