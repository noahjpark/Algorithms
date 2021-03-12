/* Noah Park

In a town, there are N people labelled from 1 to N.  There is a rumor that one of these people is secretly the town judge.

If the town judge exists, then:

The town judge trusts nobody.
Everybody (except for the town judge) trusts the town judge.
There is exactly one person that satisfies properties 1 and 2.
You are given trust, an array of pairs trust[i] = [a, b] representing that the person labelled a trusts the person labelled b.

If the town judge exists and can be identified, return the label of the town judge.  Otherwise, return -1.

*/

class Solution {
    // Time: O(R) where r is the number of relationships in trust | Space: O(N)
    // can optimize into one array
    public int findJudge(int N, int[][] trust) {
        int[] trusting = new int[N + 1]; // trust relationships
        int[] trusted = new int[N + 1]; // trusted relationships
        
        for (int[] pair : trust) {
            trusting[pair[0]]++;
            trusted[pair[1]]++;
        }
        
        for (int i = 1; i < N + 1; i++)
            if (trusting[i] == 0 && trusted[i] == N - 1) return i; // judge relationship
        
        return -1;
    }
    
    public int findJudgeSuboptimal(int N, int[][] trust) {
        int judge = -1;
        
        for (int i = 1; i < N + 1; i++) 
            if (!trusts(i, trust) && isTrusted(i, N, trust)) judge = i;
        
        return judge;
    }
    
    public boolean trusts(int person, int[][] trust) {
        for (int i = 0; i < trust.length; i++)
            if (trust[i][0] == person) return true;
        return false;
    }
    
    public boolean isTrusted(int person, int N, int[][] trust) {
        int count = 0;
        
        for (int i = 0; i < trust.length; i++)
            if (trust[i][1] == person) count++;
        
        return count == N - 1;
    }
}
