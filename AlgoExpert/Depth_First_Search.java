// Noah Park
/*

Problem: You're given a node class that has a name and an array of optional children nodes.
When put together, nodes form an acyclic tree-like structure.

Implement the depthFirstSearch method on the Node class, which takes in an empty array, traverses
the tree using the Depth-first Search approach (specifically navigating the tree from left to right),
stores all of the nodes' names in the input array, and returns it.

*/

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Depth_First_Search {
    // Node class for solving the dfs problem
    static class Node {
        String name; // name of the node
        List<Node> children = new ArrayList<Node>(); // children array list of the current node
        boolean visited = false; // is the node visited or not, defaulted to false

        public Node(String name) {
            this.name = name;
        }

        // Driver function that calls the recursive dfs function
        // Executes in O(v+e) time and O(v) space where v is the number of vertexes and e is the number of edges
        public List<String> depthFirstSearch(List<String> array) {
            dfsrec(array, this); // Populate the array list using dfs
            return array;
        }

        // Recursive function to apply depth first search
        public void dfsrec(List<String> arr, Node cur){
            if(!cur.isVisited()){
                // If it is not visited:
                // visit it
                // add its name to the array
                // then search through its children to call the function recursively on each of them.
                cur.visit();
                arr.add(cur.name);
                for(Node child : cur.children){
                    dfsrec(arr, child);
                }
            }
        }

        // Alternate shorter version - Clement's solution
        public List<String> dfs(List<String> arr){
            arr.add(this.name);
            for(int i = 0; i < this.children.size(); i++){
                this.children.get(i).dfs(arr);
            }
            return arr;
        }

        // Helper function to visit a particular node
        public void visit(){
            this.visited = true;
        }

        // Returns whether the node has been visited or not
        public boolean isVisited(){
            return visited;
        }

        public Node addChild(String name) {
            Node child = new Node(name);
            children.add(child);
            return this;
        }
    }
}
