// Noah Park
/*

Problem: You're given a Node class that has a name and an array of optional children nodes. When
put together, nodes form an acyclic tree-like structure.

Implement the breadthFirstSearch method on the Node class, which takes in an empty array,
traverses the tree using the Breadth-first Search approach (specifically navigating the tree
from left to right), stores all of the nodes' names in the input array, and returns it.

*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Breadth_First_Search {
    static class Node {
        String name;
        List<Node> children = new ArrayList<Node>();

        public Node(String name) {
            this.name = name;
        }

        // Optimal Solution | Time: O(v + e) | Space: O(v)
        // v is the number of vertices, and e is the number of edges
        public List<String> breadthFirstSearch(List<String> array) {
            Queue<Node> q = new LinkedList<>(); // Use a queue to add children from left to right
            q.add(this); // Initialize it with this node

            while(!q.isEmpty()){ // Iterate until we visit all nodes
                Node cur = q.remove(); // Remove the front node
                array.add(cur.name); // Add it to the list
                for(Node child : cur.children){ // Add its children from left to right
                    q.add(child);
                }
            }

            // Return the build array
            return array;
        }

        public Node addChild(String name) {
            Node child = new Node(name);
            children.add(child);
            return this;
        }
    }
}
