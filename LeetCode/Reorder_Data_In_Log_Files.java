/* Noah Park

You have an array of logs.  Each log is a space delimited string of words.

For each log, the first word in each log is an alphanumeric identifier.  Then, either:

Each word after the identifier will consist only of lowercase letters, or;
Each word after the identifier will consist only of digits.
We will call these two varieties of logs letter-logs and digit-logs.  It is guaranteed that each log has at least one word after its identifier.

Reorder the logs so that all of the letter-logs come before any digit-log.  The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.  The digit-logs should be put in their original order.

Return the final order of the logs.

*/

class Solution {
    public String[] reorderLogFiles(String[] logs) {
        if (logs == null || logs.length == 0) return logs; // edge cases
        
        List<String> initial = new ArrayList<>(); // store all logs for returning later
        
        // add all lowercase letter strings
        for (String str : logs) {
            String[] spl = str.split(" ");
            
            if (Character.isAlphabetic(spl[1].charAt(0))) initial.add(str);
        }
        
        // sort the strings: The best way to do this is actually to just call the custom sort on 'logs', however, this was more intuitive to me
        Comparator<String> c = new Comparator<>() {
            public int compare(String s1, String s2) {
                int firstSpaceOne = s1.indexOf(" ") + 1;
                int firstSpaceTwo = s2.indexOf(" ") + 1;
                
                String compareOne = s1.substring(firstSpaceOne, s1.length());
                String compareTwo = s2.substring(firstSpaceTwo, s2.length());
                
                if (compareOne.equals(compareTwo)) return s1.compareTo(s2);
                
                return compareOne.compareTo(compareTwo);
            }
        };
        
        Collections.sort(initial, c); // apply the sort
        
        // add all digit strings
        for (String str : logs) {
            String[] spl = str.split(" ");
            
            if (Character.isDigit(spl[1].charAt(0))) initial.add(str);
        }
        
        return initial.toArray(new String[initial.size()]);
    }
}
