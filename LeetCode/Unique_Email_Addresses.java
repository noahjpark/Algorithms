/* Noah Park

Every valid email consists of a local name and a domain name, separated by the '@' sign. Besides lowercase letters, the email may contain one or more '.' or '+'.

For example, in "alice@leetcode.com", "alice" is the local name, and "leetcode.com" is the domain name.
If you add periods '.' between some characters in the local name part of an email address, mail sent there will be forwarded to the same address without dots in the local name. Note that this rule does not apply to domain names.

For example, "alice.z@leetcode.com" and "alicez@leetcode.com" forward to the same email address.
If you add a plus '+' in the local name, everything after the first plus sign will be ignored. This allows certain emails to be filtered. Note that this rule does not apply to domain names.

For example, "m.y+name@email.com" will be forwarded to "my@email.com".
It is possible to use both of these rules at the same time.

Given an array of strings emails where we send one email to each email[i], return the number of different addresses that actually receive mails.

*/

class Solution {
    
    // Intuition: Break into local and domain parts. Replace all '.' with nothing in the local part. Then find the first occurrence of a '+' and substring it if it exists.
    // Time: O(n*m) where n is the number of emails and m is the number of characters in each email.
    // Space: O(n*m) for the set.
    public int numUniqueEmails(String[] emails) {
        Set<String> res = new HashSet<>();
        
        for (String s : emails) {
            String[] split = s.split("@");
            split[0] = split[0].replace(".", "");
            int idx = split[0].indexOf("+");
            
            StringBuilder toAdd = new StringBuilder();
            toAdd.append(idx == -1 ? split[0] : split[0].substring(0, idx));
            toAdd.append("@");
            toAdd.append(split[1]);
            
            res.add(toAdd.toString());
        }
        
        return res.size();
    }
}
