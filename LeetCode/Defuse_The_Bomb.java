/* Noah Park

You have a bomb to defuse, and your time is running out! Your informer will provide you with a circular array code of length of n and a key k.

To decrypt the code, you must replace every number. All the numbers are replaced simultaneously.

If k > 0, replace the ith number with the sum of the next k numbers.
If k < 0, replace the ith number with the sum of the previous k numbers.
If k == 0, replace the ith number with 0.
As code is circular, the next element of code[n-1] is code[0], and the previous element of code[0] is code[n-1].

Given the circular array code and an integer key k, return the decrypted code to defuse the bomb!

*/

public int[] decrypt(int[] code, int k) {
        int n = code.length;
        int[] res = new int[n];
        
        if(k > 0) {
            for(int i = 0; i < n; i++){
                int sum = 0;
                for(int j = 1; j < k + 1; j++){
                    int idx = (i + j) % n;
                    sum += code[idx];
                }
                res[i] = sum;
            }
        } else if(k < 0) {
            k *= -1;
            for(int i = 0; i < n; i++){
                int sum = 0;
                for(int j = 1; j < k + 1; j++){
                    int idx = i - j;
                    if(idx < 0) idx = n + idx;
                    sum += code[idx];
                }
                res[i] = sum;
            }
        }
        
        return res;
    }
