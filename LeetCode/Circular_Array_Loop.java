/* Noah Park

You are given a circular array nums of positive and negative integers. If a number k at an index is positive, then move forward k steps. Conversely, if it's negative (-k), move backward k steps. Since the array is circular, you may assume that the last element's next element is the first element, and the first element's previous element is the last element.

Determine if there is a loop (or a cycle) in nums. A cycle must start and end at the same index and the cycle's length > 1. Furthermore, movements in a cycle must all follow a single direction. In other words, a cycle must not consist of both forward and backward movements.

*/

class Solution {
    public boolean circularArrayLoop(int[] nums) {
        // Iterate through the array
        for(int i = 0; i < nums.length; i++){
            boolean movingForward = nums[i] >= 0; // Keep track of our current direction
            int slow = i, fast = i;
            
            // Update fast and slow pointers but break if slow == fast (do while will do the first iteration once) or once a -1 is returned
            do{
                slow = findNextIndex(nums, movingForward, slow);
                fast = findNextIndex(nums, movingForward, fast);
                if(fast != -1) fast = findNextIndex(nums, movingForward, fast);
            } while(slow != -1 && fast != -1 && slow != fast);
            
            // slow == fast indicates a cycle if they both aren't -1
            if(slow != -1 && slow == fast) return true;
        }
        
        return false;
    }
    
    public int findNextIndex(int[] nums, boolean movingForward, int index){
        boolean direction = nums[index] >= 0; // Check the direction
        if(movingForward != direction) return -1; // change in direction - not a valid cycle
        
        // Find the next index but do proper wrapping
        int nextIndex = (index + nums[index]) % nums.length;
        if(nextIndex < 0) nextIndex += nums.length;
        
        // If there is a one index cycle return -1, not a valid cycle here either.
        if(nextIndex == index) return -1;
        
        return nextIndex;
    }
}
