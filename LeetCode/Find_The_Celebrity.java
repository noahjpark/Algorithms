/* Noah Park

Suppose you are at a party with n people (labeled from 0 to n - 1), and among them, there may exist one celebrity. The definition of a celebrity is that all the other n - 1 people know him/her, but he/she does not know any of them.

Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information about whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).

You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a function int findCelebrity(n). There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.

*/

/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class Solution extends Relation {
    
    public int findCelebrity(int n) {
        int celeb = 0; // choose initial celebrity as 0
        
        // update celebrity whenever celeb knows someone since we know a celeb can't know someone, i is our new potential celeb
        for (int i = 1; i < n; i++) 
            if (knows(celeb, i)) celeb = i;
        
        // ensure that the chosen celeb is valid. If they know someone or someone does not know them, return -1.
        for (int i = 0; i < n; i++)
            if (i != celeb && (knows(celeb, i) || !knows(i, celeb))) return -1;
        
        return celeb;
    }
    
    public int findCelebritySuboptimal(int n) {
        Map<Integer, Integer> inDegree = new HashMap<>();
        Map<Integer, Integer> children = new HashMap<>();
        
        // initialize the graph
        for (int i = 0; i < n; i++) {
            inDegree.put(i, 0);
            children.put(i, 0);
        }
        
        // create the graph
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(knows(i, j) && i != j) {
                    inDegree.put(j, inDegree.get(j) + 1);
                    children.put(i, children.get(i) + 1);
                }
            }
        }
        
        // find the person with n - 1 people that know them and no people they know
        for (Map.Entry<Integer, Integer> entry : inDegree.entrySet()) {
            if(entry.getValue() == n - 1 && children.get(entry.getKey()) == 0) return entry.getKey();
        }
        
        return -1;
    }
}
