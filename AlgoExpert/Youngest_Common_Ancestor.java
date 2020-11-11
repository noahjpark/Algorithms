// Noah Park
/*

Problem: You're given three inputs all of which are instances of an AncestralTree class that have
an ancestor property pointing to their youngest ancestor. The first input is the top ancestor in an
ancestral tree (i.e., the only instance that has no ancestor -- its ancestor property points to None /
null), and the other two inputs are descendants in the ancestral tree.

Write a function that returns the youngest common ancestor to the two descendants.

Note that a descendant is considered its own ancestor. So in the simple ancestral tree below,
the youngest common ancestor to nodes A and B is node A.

*/

public class Youngest_Common_Ancestor {
    // Time: O(d) where d is the depth of the deeper descendant
    // Space: O(1)
    // Thoughts:
	/*
	My idea was to add a visited attribute to the AncestralTree class. Bring a pointer from
	the first descendant all the way to the topAncestor each iteration marking the current node
	as visited. Then use a similar loop and iterate from the second descendant until we either
	reach the top or a visited node. Return the pointer to the node when that loop breaks.
	.
	.
	.
	Another solution which I had thought of had actually ended up being the solution given to this
	problem. Adjust the ancestors, until they are on the same depth (we have to find the depth first).
	Then adjust them upwards until they reach the same level. Finally, move them each up one both at the
	same time until they are equal.
	*/
    public static AncestralTree getYoungestCommonAncestor(
            AncestralTree topAncestor, AncestralTree descendantOne, AncestralTree descendantTwo) {

        AncestralTree one = descendantOne;
        AncestralTree two = descendantTwo;

        // Visit all the ancestors until we reach the top
        while(one.ancestor != null){
            one.visit();
            one = one.ancestor;
        }

        // Traverse the ancestors until we reach the first visited one - this is our youngest ancestor
        while(!two.isVisited() && two.ancestor != null){
            two = two.ancestor;
        }

        // If we need to unvisit the nodes we visited call the function below
        unvisit(descendantOne);
        unvisit(descendantTwo);

        return two;
    }

    // If we needed to unvisit the nodes we visited
    public static void unvisit(AncestralTree node){
        if(node.ancestor != null){
            node.visited = false;
            unvisit(node.ancestor);
        }
    }

    static class AncestralTree {
        public char name;
        public AncestralTree ancestor;
        public boolean visited;

        AncestralTree(char name) {
            this.name = name;
            this.ancestor = null;
            this.visited = false;
        }

        // This method is for testing only.
        void addAsAncestor(AncestralTree[] descendants) {
            for (AncestralTree descendant : descendants) {
                descendant.ancestor = this;
            }
        }

        void visit(){
            visited = true;
        }

        boolean isVisited(){
            return visited;
        }

        void reset(){
            visited = false;
        }
    }
}
