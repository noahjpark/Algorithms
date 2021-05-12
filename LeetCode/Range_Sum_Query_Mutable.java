/* Noah Park

Given an integer array nums, handle multiple queries of the following types:

Update the value of an element in nums.
Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
Implement the NumArray class:

NumArray(int[] nums) Initializes the object with the integer array nums.
void update(int index, int val) Updates the value of nums[index] to be val.
int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).

*/

// Intuition: Utilize a segment tree (break every range in 2 in a binary tree to store the sum of each range). 
// Time: O(n) to build the tree and O(log n) to sum the ranges and update the tree.
// Space: O(n) to maintain the segment tree.

class SegmentTreeNode {
    int start, end, sum;
    SegmentTreeNode left, right;
    
    public SegmentTreeNode(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class NumArray {
    
    SegmentTreeNode root;

    public NumArray(int[] nums) {
        root = createTree(nums, 0, nums.length - 1);
    }
    
    public SegmentTreeNode createTree(int[] nums, int i, int j) {
        if (i > j) return null;
        
        int mid = i + (j - i) / 2;
        SegmentTreeNode root = new SegmentTreeNode(i, j);
        if (i == j) root.sum = nums[i];
        else {
            root.left = createTree(nums, i, mid);
            root.right = createTree(nums, mid + 1, j);
            root.sum = root.left.sum + root.right.sum;
        }
        
        return root;
    }
    
    public void update(int index, int val) {
        update(root, index, val);
    }
    
    public void update(SegmentTreeNode root, int index, int val) {
        if (root.start == root.end) root.sum = val;
        else {
            int mid = root.start + (root.end - root.start) / 2;
            
            if (index <= mid) update(root.left, index, val);
            else update(root.right, index, val);
            
            root.sum = root.left.sum + root.right.sum;
        }
    }
    
    public int sumRange(int left, int right) {
        return sum(root, left, right);
    }
    
    public int sum(SegmentTreeNode root, int left, int right) {
        if (root.start == left && root.end == right) return root.sum;
        
        int mid = root.start + (root.end - root.start) / 2;
        
        if (left > mid) return sum(root.right, left, right);
        else if (right <= mid) return sum(root.left, left, right);
        else return sum(root.left, left, mid) + sum(root.right, mid + 1, right);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */
