/* Noah Park

Given a date string in the form Day Month Year, where:

Day is in the set {"1st", "2nd", "3rd", "4th", ..., "30th", "31st"}.
Month is in the set {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"}.
Year is in the range [1900, 2100].
Convert the date string to the format YYYY-MM-DD, where:

YYYY denotes the 4 digit year.
MM denotes the 2 digit month.
DD denotes the 2 digit day.

*/

class Solution {
    
    // Intuition: Ad-hoc conversion.
    // Time and Space: O(1) constant.
    public String reformatDate(String date) {
        if (date == null || date.length() == 0) return date;
        
        StringBuilder res = new StringBuilder();
        String[] split = date.split(" ");
        Map<String, String> map = new HashMap<>();
        map.put("Jan", "01"); map.put("Feb", "02"); map.put("Mar", "03"); map.put("Apr", "04"); map.put("May", "05");
        map.put("Jun", "06"); map.put("Jul", "07"); map.put("Aug", "08"); map.put("Sep", "09"); map.put("Oct", "10");
        map.put("Nov", "11"); map.put("Dec", "12");
        String day = split[0].substring(0, split[0].length() - 2);
        
        res.append(split[2]).append("-").append(map.get(split[1])).append("-").append(day.length() == 1 ? "0" + day : day);
        
        return res.toString();
    }
}
