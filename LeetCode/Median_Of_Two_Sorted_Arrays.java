/* Noah Park

Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

*/

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length + nums2.length, k = n / 2; // we need to add k elements to the heap and need n for the odd/even cases
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        
        int p1 = 0, p2 = 0, i = 0; // pointers for the two arrays and keeping track of the numbers in the heap
        while(i <= k){
            if(p1 >= nums1.length && p2 >= nums2.length) break; // If pointers are out of bounds there are no more elements to add
            else if(p1 >= nums1.length) maxHeap.offer(nums2[p2++]); // Else, one is not out of bounds, if that one is p2, offer p2
            else if(p2 >= nums2.length) maxHeap.offer(nums1[p1++]); // Else, p1 is not out of bounds, we offer p1
            else maxHeap.offer(nums2[p2] < nums1[p1] ? nums2[p2++] : nums1[p1++]); // Else, none are out of bounds and we can offer the smaller of the two
            i++;
        }
        
        // If we had a total odd number of elements, we remove just the top, else we take the median between the top two elements
        return n % 2 == 1 ? maxHeap.poll() : ((double) maxHeap.poll() + (double) maxHeap.poll()) / 2;
    }
}
