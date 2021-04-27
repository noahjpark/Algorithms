/* Noah Park

Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result must appear as many times as it shows in both arrays and you may return the result in any order.

*/

class Solution {
    
    // Intuition: Utilize a single map rather than two from the below solution. We only need the frequencies from one array to begin with.
    // Time: O(n + m) iterating over nums1 and nums2.
    // Space: O(min(n, m)) since we store the smaller array.
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) return intersect(nums2, nums1);
        
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer num : nums1) map.put(num, map.getOrDefault(num, 0) + 1);
        
        List<Integer> arr = new ArrayList<>();
        
        for (int num : nums2) {
            if (map.getOrDefault(num, 0) > 0) {
                arr.add(num);
                map.put(num, map.get(num) - 1);
            }
        }
        
        int[] res = new int[arr.size()];
        int i = 0;
        
        for (Integer num : arr)
            res[i++] = num;
        
        return res;
    }
    
    // Intuition: Map frequencies of each array. Then add the smaller frequency of matching values.
    // Time: O(n + m) to iterate over nums1 and nums2.
    // Space: O(n + m) to store the frequencies.
    public int[] intersect2(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map1 = new HashMap<>(), map2 = new HashMap<>();
        
        for (Integer num : nums1) map1.put(num, map1.getOrDefault(num, 0) + 1);
        for (Integer num : nums2) map2.put(num, map2.getOrDefault(num, 0) + 1);
        
        Map<Integer, Integer> smaller = map1.size() < map2.size() ? map1 : map2;
        Map<Integer, Integer> larger = map1 == smaller ? map2 : map1;
        
        List<Integer> nums = new ArrayList<>();
        
        for (Integer key : smaller.keySet()) 
            if (larger.containsKey(key))
                for (int i = 0; i < Math.min(larger.get(key), smaller.get(key)); i++)
                    nums.add(key);
        
        
        int[] ans = new int[nums.size()];
        for(int i = 0; i < nums.size(); i++){
            ans[i] = nums.get(i);
        }
        
        return ans;
    }
    
    // Intuition: Two pointer solution with sorted arrays. We add as many of a matching value as possible, otherwise move pointers until we reach a matching value in each array.
    // Time: O(n log n + m log m) to sort the arrays.
    // Space: O(sort) not counting output array otherwise O(Math.min(n, m)).
    public int[] intersect3(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        ArrayList<Integer> nums = new ArrayList<>();
        int l = 0, r = 0;
        while(l < nums1.length && r < nums2.length){
            if(nums1[l] == nums2[r]){
                nums.add(nums1[l]);
                l++;
                r++;
            }
            else if(nums1[l] < nums2[r]) l++;
            else r++;
        }
        
        int[] ans = new int[nums.size()];
        for(int i = 0; i < nums.size(); i++){
            ans[i] = nums.get(i);
        }
        
        return ans;
    }
}
