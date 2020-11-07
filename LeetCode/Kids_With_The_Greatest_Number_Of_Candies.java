/* Noah Park

Given the array candies and the integer extraCandies, where candies[i] represents the number of candies that the ith kid has.

For each kid check if there is a way to distribute extraCandies among the kids such that he or she can have the greatest number of candies among them. 
Notice that multiple kids can have the greatest number of candies.

*/

class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = 0;
        for(int num : candies)
            max = Math.max(max, num);
        
        List<Boolean> ans = new ArrayList<>();
        for(int num : candies)
            ans.add((num + extraCandies >= max) ? true : false);
        
        return ans;
    }
}
