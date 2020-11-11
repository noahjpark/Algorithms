// Noah Park
/*

Problem: Implement a MinHeap class that supports:

    - Building a Min Heap from an input array of integers
    - Inserting integers in the heap
    - Removing the heap's minimum / root value
    - Peeking at the heap's minimum / root value
    - Sifting integers up and down the heap, which is to be used when inserting and removing values

Note that the heap should be represented in the form of an array.

A min heap is a binary tree that is complete (all levels full except for the last one which
is filled from the left to the right). It also satisfies the min heap property. Each nodes value
must be smaller than or equal to its childrens' values.

In an array, the root of the min heap starts at 0. To find the index of its children, take its index i and multiply
i by 2 and add 1 to get the index left child. Add 1 to this value to get the index of the right child.
The index of a parent node will be (rounding down) ((i - 1) / 2).
*/

import java.util.ArrayList;
import java.util.List;

public class Min_Heap_Construction {
    static class MinHeap {
        // Arraylist to represent the heap
        List<Integer> heap = new ArrayList<Integer>();

        // Constructor calls buildHeap to build the heap
        public MinHeap(List<Integer> array) {
            heap = buildHeap(array);
            for(Integer i : heap){
                System.out.println(i);
            }
        }

        // This caused me loads of trouble
        // I realized, I just needed to go through the array and call siftUp on each
        // element in the min heap instead of siftDown due to siftDown moving elements
        // that are not the minimum values up. If we wanted to call siftDown, we would need
        // to go backwards in the array
        // O(n) time | O(1) space
        public List<Integer> buildHeap(List<Integer> array) {
            for(int i = 0; i < array.size(); i++){
                siftUp(i, array);
            }
            return array;
        }

        // O(log(n)) time | O(1) space
        public void siftDown(int currentIdx, int endIdx, List<Integer> heap) {
            // Get the left and right children indices (we will need these each iteration as well)
            int leftIdx = currentIdx * 2 + 1;
            int rightIdx = leftIdx + 1;

            // Loop while there is at least one child
            // endIdx is the last actual index in the heap. If leftIndex is less than or
            // equal to this value, then there must be a left child and potentially even a right one
            while(leftIdx <= endIdx){
                // Initialize a minimum index and value that goes along with it
                int min;
                int minIdx;

                // If there is no right child
                // then set the min and minIdx to the left child
                if(rightIdx >= heap.size()) {
                    minIdx = leftIdx;
                    min = heap.get(minIdx);
                }

                // There are two children and we need the minimum index and value of the two
                else{
                    min = Math.min(heap.get(leftIdx), heap.get(rightIdx));
                    minIdx = heap.get(leftIdx) < heap.get(rightIdx) ? leftIdx : rightIdx;
                }

                // If the parent is smaller than its minimum child, we should break
                if(heap.get(currentIdx) <= min){
                    break;
                }

                // Otherwise, swap the values of the smaller child and the current parent
                int temp = heap.get(currentIdx);
                heap.set(currentIdx, min);
                heap.set(minIdx, temp);

                // Update all indices to move to the next "nodes"
                currentIdx = minIdx;
                leftIdx = currentIdx * 2 + 1;
                rightIdx = leftIdx + 1;
            }
        }

        // Much easier than siftDown
        // O(log(n)) time | O(1) space
        public void siftUp(int currentIdx, List<Integer> heap) {
            int parent = (currentIdx - 1) / 2; // Get the parent index of our current node

            // Iterate while the parent value is larger than the node value since we need to
            // swap them if this is the case
            while(heap.get(parent) > heap.get(currentIdx)){
                // Swap the parent and child values
                int temp = heap.get(parent);
                heap.set(parent, heap.get(currentIdx));
                heap.set(currentIdx, temp);

                // Move up the heap and update the indices to reflect the changing of nodes
                currentIdx = parent;
                parent = (currentIdx - 1) / 2;
            }
        }

        // Returns the top value of the min heap if there is one, else -1
        public int peek() {
            if(heap.isEmpty()){
                return -1;
            }
            return heap.get(0);
        }

        // Removes the top element of the min heap and returns it if there is one, else -1
        public int remove() {
            if(heap.isEmpty()){
                return -1;
            }
            else if(heap.size() == 1){
                int min = peek();
                heap = new ArrayList<Integer>();
                return min;
            }
            // Swap the first and last values then remove the last (the old minimum value of the min heap)
            // Then sift the newly swapped value down to satisfy the min heap property
            int min = peek();
            heap.set(0, heap.get(heap.size() - 1));
            heap.remove(heap.size() - 1);
            siftDown(0, heap.size() - 1, heap);
            return min;
        }

        // Inserts an element into the min heap
        public void insert(int value) {
            // Add the new value to the end of the heap list
            // Then pass in its index and sift it upwards until the min heap property has been satisfied
            heap.add(value);
            int index = heap.size() - 1;
            siftUp(index, heap);
        }
    }
}
