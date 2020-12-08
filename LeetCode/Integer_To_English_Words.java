/* Noah Park

Convert a non-negative integer num to its English words representation.

*/

class Solution {
    // pre cache all values that we need to type all integers
    String[] lessThanTwenty = new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    String[] lessThanHundred = new String[]{"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    
    // zero is an edge case, otherwise call the helper
    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        return convertToString(num);
    }
    
    public String convertToString(int num) {
        String res = ""; // return string
        
        if (num < 20) res = lessThanTwenty[num]; // up to 20: return the index into the pre cached array
        else if (num < 100) res = lessThanHundred[num / 10] + " " + convertToString(num % 10); // 20-99: Return the pre cached array value and the remainder using the function
        
        // All other calls will break the problem down into two parts: before the new value (h, t, m, b) and after. 
        else if (num < 1000) res = convertToString(num / 100) + " Hundred " + convertToString(num % 100); // 100-999
        else if (num < 1000000) res = convertToString(num / 1000) + " Thousand " + convertToString(num % 1000); // 1000-999999
        else if (num < 1000000000) res = convertToString(num / 1000000) + " Million " + convertToString(num % 1000000); // 1000000-999999999
        else if (num <= Integer.MAX_VALUE) res = convertToString(num / 1000000000) + " Billion " + convertToString(num % 1000000000); // 1000000000-MAX
        
        return res.trim(); // trim all ending spaces
    }
}
