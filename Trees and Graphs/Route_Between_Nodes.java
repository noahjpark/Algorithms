// Noah Park
/*

Problem: Given a directed graph, design an algorithm to find out
whether there is a route between two nodes.

*/

public class Route_Between_Nodes<T extends Comparable<T>> {

    public boolean existsPath(Graph<T> g, Node<T> start, Node<T> destination){
        // If either node is null, there cannot be a path
        if(start == null || destination == null){
            return false;
        }

        // bfs function I wrote in graph.java checks if destination is reachable from
        // start. Returns a boolean which we return here.
        return g.bfs(start, destination);
    }

    // Basic testing of routes
    public static void main(String[] args){
        Route_Between_Nodes<String> route = new Route_Between_Nodes<>();
        Node<String> n1 = new Node<>("Start");
        Node<String> n2 = new Node<>("Second");
        Node<String> n3 = new Node<>("Third");
        Node<String> n4 = new Node<>("Fourth");
        Node<String> n5 = new Node<>("Final");

        n1.addChild(n2);
        n2.addChild(n3);
        n2.addChild(n4);
        n4.addChild(n5);
        n5.addChild(n3);

        Graph<String> g = new Graph<>();
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.addNode(n4);
        g.addNode(n5);

        //g.dfs(n1);

        System.out.println(route.existsPath(g, n1, n2));
        System.out.println(route.existsPath(g, n1, n5));
        System.out.println(route.existsPath(g, n5, n2));

    }

}
