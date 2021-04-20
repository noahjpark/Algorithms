/* Noah Park

You are given a string array features where features[i] is a single word that represents the name of a feature of the latest product you are working on. You have made a survey where users have reported which features they like. You are given a string array responses, where each responses[i] is a string containing space-separated words.

The popularity of a feature is the number of responses[i] that contain the feature. You want to sort the features in non-increasing order by their popularity. If two features have the same popularity, order them by their original index in features. Notice that one response could contain the same feature multiple times; this feature is only counted once in its popularity.

Return the features in sorted order.

*/

class Element {
    String word;
    int idx;
    int freq;
    
    public Element(String word, int idx, int freq) {
        this.word = word;
        this.idx = idx;
        this.freq = freq;
    }
}

class Solution {
    
    // Intuition: Map the frequency of each word from features using a set of words from features and a set of used words for each response to not reuse certain words. Then utilize a custom Element class to sort each element in an array by frequency (descending) otherwise maintaining stable original order from features. For some reason this has a better runtime than just sorting features.
    // Time: O(n + m*k) where n is the length of features, m is the number of responses, and k is the words in each response.
    // Space: O(n + k) to store all words from features and all words from each given response at a time.
    public String[] sortFeatures(String[] features, String[] responses) {
        Set<String> words = new HashSet<>();
        Map<String, Integer> map = new HashMap<>();
        
        for (String word : features) words.add(word);
        for (String response : responses) {
            Set<String> used = new HashSet<>();
            
            for (String word : response.split(" ")) {
                if (words.contains(word) && !used.contains(word)) {
                    map.put(word, map.getOrDefault(word, 0) + 1);
                    used.add(word);
                }
            }
        }
        
        Element[] unsorted = new Element[features.length];
        
        for (int i = 0; i < features.length; i++) 
            unsorted[i] = new Element(features[i], i, map.getOrDefault(features[i], 0));
        
        Comparator<Element> c = new Comparator<>() {
            public int compare(Element e1, Element e2) {
                if (e2.freq == e1.freq) return e1.idx - e2.idx;
                return e2.freq - e1.freq;
            }
        };
        Arrays.sort(unsorted, c);
        
        String[] res = new String[features.length];
        
        for (int i = 0; i < features.length; i++)
            res[i] = unsorted[i].word;
        
        return res;
    }
}
