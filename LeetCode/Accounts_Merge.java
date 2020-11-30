/* Noah Park

Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.

Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

*/

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int[] parents = new int[accounts.size()]; // stores all parents of each email
        Map<String, Integer> accountHolder = new HashMap<>(); // maps all emails to an owner
        Map<Integer, TreeSet<String>> emails = new HashMap<>(); // stores all emails in sorted order
        
        for(int i = 0; i < accounts.size(); i++) parents[i] = i; // initialize all parents pointing to themselves
        
        for(int i = 0; i < accounts.size(); i++){ // iterate through all accounts
            for(int j = 1; j < accounts.get(i).size(); j++){ // only iterate through emails
                String cur = accounts.get(i).get(j); // get the current email
                if(accountHolder.containsKey(cur)){ // if we have seen it before, union the parent of i and the parent of itself so they are connected
                    int owner = accountHolder.get(cur);
                    int p1 = find(i, parents), p2 = find(owner, parents);
                    parents[p1] = p2;
                }
                else accountHolder.put(cur, i); // map the email to the index in which it resides
            }
        }
        
        // iterate through all accounts
        for(int i = 0; i < accounts.size(); i++){
            int parent = find(i, parents); // get each parent
            List<String> temp = accounts.get(i);
            emails.putIfAbsent(parent, new TreeSet<>()); // put each parent with a new tree set
            emails.get(parent).addAll(temp.subList(1, temp.size())); // add all emails to the set
        }
        
        List<List<String>> res = new ArrayList<>();
        for(Integer i : emails.keySet()){ // iterate through all keys in the emails key set
            String n = accounts.get(i).get(0); // get the owner of that particular email
            List<String> temp = new ArrayList<>(emails.get(i)); // get the emails from the email map
            temp.add(0, n); // add the owner to the front
            res.add(temp); // add that to the array list
        }
        return res;
    }
    
    public int find(int idx, int[] parents){
        // path compression and return parent of idx
        if(idx != parents[idx]) parents[idx] = find(parents[idx], parents);
        return parents[idx];
    }
}
