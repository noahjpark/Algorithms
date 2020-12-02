/* Noah Park

Given a list of non-negative integers nums, arrange them such that they form the largest number.

Note: The result may be very large, so you need to return a string instead of an integer.

*/

class Solution {
    public String largestNumber(int[] nums) {
        StringBuilder res = new StringBuilder(); // result string
        
        // convert nums to the string version for comparison sorting
        String[] n = new String[nums.length];
        for (int i = 0; i < n.length; i++) n[i] = String.valueOf(nums[i]);
        
        // comparator will compare putting s1s2 vs s2s1 and sort on the larger of the two
        Comparator<String> c = new Comparator<>() {
            public int compare(String s1, String s2) {
                String t1 = s1 + s2, t2 = s2 + s1;
                return t2.compareTo(t1);
            }
        };
        
        // sort using custom comparator
        Arrays.sort(n, c);
        
        // stupid edge case where we have many 0s
        if(n[0].equals("0")) return "0";
        
        // append the sorted array to the string builder
        for(String s : n) res.append(s);
        
        return res.toString();
    }
}
