import java.util.Hashtable;

public class Is_Unique {

    // Time: O(n^2)
    // Space: O(1)
    public static boolean isUnique(String word){
        for(int i = 0; i < word.length() - 1; i++){
            for(int j = i + 1; j < word.length(); j++){
                if(word.charAt(i) == word.charAt(j)){
                    return false;
                }
            }
        }
        return true;
    }

    // Time: O(n)
    // Space: O(n)
    public static boolean isUniqueHash(String word){
        Hashtable<Integer, Character> ht = new Hashtable<>();
        for(int i = 0; i < word.length(); i++) {
            if (ht.contains(word.charAt(i))) {
                return false;
            }
            ht.put(i, word.charAt(i));
        }
        return true;
    }

    public static void main(String[] args){
        String[] words = {"hello", "undo", "true", "check"};
        System.out.println(isUnique(words[0]));
        System.out.println(isUnique(words[1]));
        System.out.println(isUniqueHash(words[2]));
        System.out.println(isUniqueHash(words[3]));
    }
}
