/* Noah Park

Suppose Andy and Doris want to choose a restaurant for dinner, and they both have a list of favorite restaurants represented by strings.

You need to help them find out their common interest with the least list index sum. If there is a choice tie between answers, output all of them with no order requirement. You could assume there always exists an answer.

*/

class Solution {
    
    // Intuition: map string to its index and keep track of the minimum index sum so far. If the map contains the element in list2, clear the list/update min/ add to the list if it is a new min. If equal, add to list.
    // Time: O(n + m) for each list iterations
    // Space: O(min(n, m)) since we take the smaller list
    public String[] findRestaurant(String[] list1, String[] list2) {
        if (list1.length > list2.length) {
            String[] temp = list1;
            list1 = list2;
            list2 = temp;
        }
        Map<String, Integer> map = new HashMap<>();
        List<String> res = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        
        for (int i = 0; i < list1.length; i++)
            map.put(list1[i], i);
        
        for (int i = 0; i < list2.length; i++) {
            if (map.containsKey(list2[i])) {
                if (map.get(list2[i]) + i < min) {
                    min = i + map.get(list2[i]);
                    res.clear();
                    res.add(list2[i]);
                } else if (i + map.get(list2[i]) == min) res.add(list2[i]);
            }
        }
        
        return res.toArray(new String[res.size()]);
    }
}
