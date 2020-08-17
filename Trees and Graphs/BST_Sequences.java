// Noah Park
/*

Problem: A binary search tree was created by traversing through an array
from left to right and inserting each element. Given a binary search tree
with distinct elements, print all possible arrays that could have led to
this tree

*/

import sun.awt.image.ImageWatched;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BST_Sequences<T extends Comparable<T>> {

    // Book implementation done so I can understand what is happening
    public ArrayList<LinkedList<T>> allSequences(BinaryNode<T> node){

        // This will contain all the possible arrays that could have made the tree
        ArrayList<LinkedList<T>> result = new ArrayList<>();

        // No nodes in the tree
        if(node == null){
            result.add(new LinkedList<>());
            return result;
        }

        LinkedList<T> prefix = new LinkedList<>();
        prefix.add(node.getKey());

        // Recurse on the left and right subtrees
        ArrayList<LinkedList<T>> left = allSequences(node.getLeft());
        ArrayList<LinkedList<T>> right = allSequences(node.getRight());

        // Weave the lists together
        for(LinkedList<T> l : left){
            for(LinkedList<T> r : right){
                ArrayList<LinkedList<T>> weaved = new ArrayList<>();
                weaveLists(l, r, weaved, prefix);
                result.addAll(weaved);
            }
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    public void weaveLists(LinkedList<T> first, LinkedList<T> second, ArrayList<LinkedList<T>> results, LinkedList<T> prefix){
        // One list is empty. Add remainder to [a cloned] prefix and store result
        if(first.size() == 0 || second.size() == 0){
            LinkedList<T> result = (LinkedList<T>) prefix.clone();
            result.addAll(first);
            result.addAll(second);
            results.add(result);
            return;
        }

        // Recurse with head of first added to the prefix. Removing the head will damage first
        // so we need to put it back after
        T headFirst = first.removeFirst();
        prefix.addLast(headFirst);
        weaveLists(first, second, results, prefix);
        prefix.removeLast();
        first.addFirst(headFirst);

        // Do the same thing with second
        T headSecond = second.removeFirst();
        prefix.addLast(headSecond);
        weaveLists(first, second, results, prefix);
        prefix.removeLast();
        second.addFirst(headSecond);
    }

    // Generates all permutations of the bst that could have occurred from an array
    // Didn't work for many cases. There is a weave algorithm that works from the book,
    // however it is quite complicated
    @SuppressWarnings("unchecked")
    public void printAllArrays(BST<T> bst){
        if(bst.getSize() == 0){
            return;
        }
        else if(bst.getSize() == 1){
            System.out.println("{" + bst.getRoot().getKey() + "}");
            return;
        }
        else if(bst.getSize() == 2) {
            System.out.println(bst.getRoot().getLeft() != null ? "{" +
                    bst.getRoot().getKey() + ", " + bst.getRoot().getLeft() + "}" :
                    "{" + bst.getRoot().getKey() + ", " + bst.getRoot().getLeft() + "}");
            return;
        }
        int i = 0;
        T[] first = (T[]) new Comparable[bst.getSize()];
        Queue<BinaryNode<T>> q = new LinkedList<>();
        q.add(bst.getRoot());
        while(!q.isEmpty()){
            BinaryNode<T> cur = q.remove();
            first[i++] = cur.getKey();
            if(cur.getLeft() != null){
                q.add(cur.getLeft());
            }
            if(cur.getRight() != null){
                q.add(cur.getRight());
            }
        }

        T[] all = (T[]) new Comparable[first.length];
        for(int in = 0; in < first.length; in++){
            all[in] = first[in];
        }
        int swapIndex = all.length - 1;
        T temp = all[swapIndex];
        //System.out.println(temp);
        all[swapIndex] = all[swapIndex - 1];
        all[swapIndex - 1] = temp;

        while(!this.areEqual(all, first)){
            System.out.print("{");
            for(int in = 0; in < all.length; in++){
                System.out.print(in == all.length - 1 ? all[in] : all[in] + ", ");
            }
            System.out.print("}");
            System.out.println();

            if(swapIndex == 1){
                swapIndex = all.length - 1;
            }
            else{
                temp = all[swapIndex];
                all[swapIndex] = all[swapIndex - 1];
                all[swapIndex - 1] = temp;
                swapIndex--;
            }
        }

        System.out.print("{");
        for(int in = 0; in < first.length; in++){
            System.out.print(in == all.length - 1 ? all[in] : all[in] + ", ");
        }
        System.out.print("}");
    }

    // Check if two arrays are equal
    public boolean areEqual(T[] arr1, T[] arr2){
        if(arr1.length != arr2.length){
            return false;
        }

        for(int i = 0; i < arr1.length; i++){
            //System.out.println(arr1[i] + " " + arr2[i]);
            if(!arr1[i].equals(arr2[i])){
                return false;
            }
        }

        return true;
    }


    // Basic testing of the class
    public static void main(String[] args){
        BST_Sequences<Integer> b = new BST_Sequences<>();

        BinaryNode<Integer> r = new BinaryNode<>(2);
        BinaryNode<Integer> n1 = new BinaryNode<>(1);
        BinaryNode<Integer> n2 = new BinaryNode<>(3);
        BinaryNode<Integer> n4 = new BinaryNode<>(4);
        r.setLeft(n1);
        r.setRight(n2);
        n2.setRight(n4);

        BST<Integer> bst = new BST<>(r);

        ArrayList<LinkedList<Integer>> l = b.allSequences(bst.getRoot());
        for(LinkedList<Integer> list : l){
            System.out.print("{");
            for(Integer i : list){
                System.out.print(i + " ");
            }
            System.out.println("}");
        }
    }
}
