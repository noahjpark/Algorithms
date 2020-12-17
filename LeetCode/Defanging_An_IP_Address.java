/* Noah Park

Given a valid (IPv4) IP address, return a defanged version of that IP address.

A defanged IP address replaces every period "." with "[.]".

*/

class Solution {
    // modifies . to [.]
    public String defangIPaddr(String address) {
        StringBuilder res = new StringBuilder();
        for (char c : address.toCharArray()) {
            if (c == '.') res.append("[.]");
            else res.append(c);
        }
        return res.toString();
    }
}
