/* Noah Park

There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.

Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not. And you have to output the total number of friend circles among all the students.

*/

class Solution {
    public int findCircleNum(int[][] M) {
        // n is the number of friends
        int n = M.length;
        int[] friends = new int[n]; // store node relationships in the array
        for (int i = 0; i < n; i++) friends[i] = i; // initailize all nodes to point to themselves
        for (int i = 0; i < n; i++) { // update the relationships from the given M array
            for (int j = 0; j < n; j++)
                if(i != j && M[i][j] == 1) union(i, j, friends);
        }
        // Store all source nodes in the set, this will be the number of friend groups
        Set<Integer> set = new HashSet<>();
        for(int friend : friends) set.add(find(friend, friends)); // only store top parents
        return set.size();
    }
    
    public void union(int edge1, int edge2, int[] friends) {
        // union to different edges
        int parent1 = find(edge1, friends), parent2 = find(edge2, friends);
        friends[parent1] = parent2;
    }
    
    public int find(int edge, int[] friends) {
        // path compression, ensures all nodes point to a source node
        if(friends[edge] != edge) friends[edge] = find(friends[edge], friends);
        return friends[edge]; // return the top parent
    }
}

