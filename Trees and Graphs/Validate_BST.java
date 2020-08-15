// Noah Park
/*

Problem: Implement a function to check if a binary tree is a binary
search tree.

*/

import java.util.ArrayList;

public class Validate_BST<T extends Comparable<T>> {

    public boolean isBST(BST<T> bst){
        if(bst == null || bst.getRoot() == null){
            return true;
        }

        ArrayList<T> isbst = new ArrayList<>();
        this.addToArrayList(isbst, bst.getRoot());

        for(int i = 0; i < isbst.size() - 1; i++){
            T cur = isbst.get(i);
            for(int j = 0; j < isbst.size(); j++){
                if(cur.compareTo(isbst.get(j)) > 0){
                    return false;
                }
            }
        }

        return true;
    }

    public void addToArrayList(ArrayList<T> arr, BinaryNode<T> node){
        if(node != null){
            addToArrayList(arr, node.getLeft());
            arr.add(node.getKey());
            addToArrayList(arr, node.getRight());
        }
    }

    public static void main(String[] args){

    }

}
