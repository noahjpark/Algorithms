import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;

public class Check_Permutation {

    // Time: O(n^2)
    // Space: O(n)
    public static boolean checkPermutationHash(String word1, String word2){
        if(word1.length() != word2.length()){
            return false;
        }
        int check = 0;
        Hashtable<Integer, Character> ht = new Hashtable<>();
        for(int j = 0; j < word2.length(); j++){
            ht.put(j, word2.charAt(j));
        }
        for(int i = 0; i < word1.length(); i++){
            if(ht.contains(word1.charAt(i))){
                check++;
                for(int j = 0; j < ht.size(); j++){
                    if (ht.get(j) == word1.charAt(i)) {
                        ht.remove(j);
                    }
                }
            } else{
                return false;
            }
        }
        return check == word1.length();
    }

    // Time: O(n)
    // Space: O(n)
    public static boolean checkPermutationSort(String word1, String word2){
        if(word1.length() != word2.length()){
            return false;
        }
        ArrayList<Character> w1 = new ArrayList<>();
        ArrayList<Character> w2 = new ArrayList<>();
        for(int i = 0; i < word1.length(); i++){
            w1.add(word1.charAt(i));
            w2.add(word2.charAt(i));
        }
        Collections.sort(w1);
        Collections.sort(w2);
        for(int i = 0; i < word1.length(); i++){
            if(w1.get(i) != w2.get(i)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
        String w1 = "paper";
        String w2 = "repap";
        String w3 = "hello";
        String w4 = "lleho";
        //System.out.println(checkPermutationHash(w1, w2));
        System.out.println(checkPermutationSort(w3, w4));
    }
}
