/* Noah Park

In this exercise, one can expect a list of samples on Iris flowers. Each sample is represented with a tuple of two values: <petal_length, species>, where the first attribute is the measurement on the length of the petal for the sample, and the second attribute indicates the species of sample. Here is an example.



The task is to split the sample list into two sublists, while complying with the following two conditions:

The petal_length of sample in one sublist is less or equal than that of any sample in the other sublist.
The information gain on the species attribute of the sublists is maximal among all possible splits.
As output, one should just return the information gain.

In addition, one can expect that each value of petal_length is unique.  

 

In the above example, the optimal split would be L1 = [(0.5, 'setosa'), (1.0, 'setosa')] and L2 = [(1.5, 'versicolor'), (2.3, 'versicolor')]. According the above formulas, the maximum information gain for this example would be 1.0.

Note:  For certain languages (e.g. Java), there is no build-in type of tuple. As a reuslt, to make the input more general, we decompose the input into two lists: [petal_length] [species] respectively, where the petal_length would be of float value and the species is of string. The elements in the petal_length list and species list are associated to each other one by one by order.
 

*/

class Solution {
    
    // Intuition: Brute force calculation of all possibilites.
    // Time: O(n^2) to make all comparisons.
    // Space: O(n) to store species information.
    public double calculateMaxInfoGain(double[] petal_length, String[] species) {
        if (petal_length == null || petal_length.length == 0) return 0;
        
        int n = species.length;
        double total = calculateEntropy(new ArrayList<>(Arrays.asList(species))), res = 0;

        for (int i = 0; i < n; i++) {
            
            double cur = petal_length[i];
            List<String> l1 = new ArrayList<>(), l2 = new ArrayList<>();
            
            for (int j = 0; j < n; j++) {
                if (petal_length[j] < cur) l1.add(species[j]);
                else l2.add(species[j]);
            }
            
            double e1 = calculateEntropy(l1), e2 = calculateEntropy(l2), p1 = (double) l1.size() / n, p2 = (double) l2.size() / n;
            res = Math.max(res, total - e1*p1 - e2*p2);
        }
        
        return res;
    }
    
    public double calculateEntropy(List<String> species) {
        if (species == null || species.size() == 0) return 0;

        Map<String, Integer> map = new HashMap<>();
        int n = species.size();
        double res = 0;

        for (String num : species) 
            map.put(num, map.getOrDefault(num, 0) + 1);

        for (String key : map.keySet()) {
            double p = (double) map.get(key) / (double) n;
            res += -(p * (Math.log(p) / Math.log(2)));
        }

        return res;
    }
}
