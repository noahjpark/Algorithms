// Noah Park
/*

Problem: You are given a list of projects and a list of dependencies (which
is a list of pairs of projects where the second project is dependent on the first
project). All of a project's dependencies must be built before the project is. Find a
build order that will allow the projects to be built. If there is no valid build order,
return an error.

*/

import java.util.ArrayList;

public class Build_Order<T extends Comparable<T>> {

    // I implemented it so that the edge would be directed from the dependent portion to the required portion
    // That way it will be easy to tell if there are no children then it can be added to the build order
    public ArrayList<T> getBuildOrder(ArrayList<T> projects, ArrayList<T[]> dependencies){
        Graph<T> g = this.buildGraph(projects, dependencies); // Use the buildGraph helper function
        ArrayList<T> buildOrder = new ArrayList<>(); // Build order list

        int index; // Represents the index into the nodes list

//        for(Node<T> n : g.getNodes()){
//            System.out.print(n.getName());
//            for(Node<T> child : n.getChildren()){
//                System.out.print(" " + child.getName());
//            }
//            System.out.println();
//        }

        // Iterates through the graph nodes
        while(g.getNodes().size() > 0) {
            index = -1; // Initialize to -1 to tell if there is no proper order
            for (int i = 0; i < g.getNodes().size(); i++) { // Iterate through the nodes
                if (g.getNodes().get(i).getChildren().size() == 0) { // If a node doesn't have any children, it is next in the order
                    index = i; // Update index to this node and break from the inner loop
                    break;
                }
                System.out.println(g.getNodes().get(i).getName() + " " + g.getNodes().get(i).getChildren());
            }
            System.out.println();
//            if(index != -1) {
//                System.out.println(g.getNodes().size() + " " + g.getNodes().get(index).getName());
//            }
//            for(Node<T> n : g.getNodes()){
//                System.out.println(n.getName() + " ");
//                for(Node<T> ch : n.getChildren()){
//                    System.out.print(ch.getName() + " ");
//                }
//                System.out.println();
//            }

            // If the index is -1 at any point, then there is no more order to go through
            if(index == -1){
                return null;
            }

            // Add the value of the node to the build order then remove it from the graph
            System.out.println(g.getNodes().get(index).getName());
            buildOrder.add(g.getNodes().get(index).getName());
            g.remove(g.getNodes().get(index));
        }

        return buildOrder;
    }

    // Build a graph when given the proper projects and dependencies
    public Graph<T> buildGraph(ArrayList<T> projects, ArrayList<T[]> dependencies){
        // Initialize an empty graph
        Graph<T> g = new Graph<>();

        // Populate the graph with all necessary nodes
        for(T data : projects){
            Node<T> newNode = new Node<>(data);
            g.addNode(newNode);
        }

        // Add edges based on dependencies list
        for(T[] dep : dependencies){
            Node<T> first = new Node<>(dep[0]);
            Node<T> second = new Node<>(dep[1]);
            int index = 0;
            for(int i = 0; i < g.getNodes().size(); i++){
                if(g.getNodes().get(i).getName().equals(second.getName())){
                    index = i;
                    break;
                }
            }
            //System.out.println(g.getNodes().get(index).getName());
            g.getNodes().get(index).addChild(first);
        }

        // Return the finished graph
        return g;
    }

    // Basic testing of the class
    public static void main(String[] args){
        Build_Order<Character> c = new Build_Order<>();

        ArrayList<Character> projects = new ArrayList<>();
        projects.add('a');
        projects.add('b');
        projects.add('c');
        projects.add('d');
        projects.add('e');
        projects.add('f');

        ArrayList<Character[]> dependencies = new ArrayList<>();
        dependencies.add(new Character[]{'a', 'd'});
        dependencies.add(new Character[]{'f', 'b'});
        dependencies.add(new Character[]{'b', 'd'});
        dependencies.add(new Character[]{'f', 'a'});
        dependencies.add(new Character[]{'d', 'c'});

        ArrayList<Character> buildOrder = c.getBuildOrder(projects, dependencies);

        if(buildOrder == null){
            System.out.println("No valid build order exists.");
        }
        else {
            System.out.print("Build Order: ");
            for (Character ch : buildOrder) {
                System.out.print(ch + ", ");
            }
        }
    }
}
