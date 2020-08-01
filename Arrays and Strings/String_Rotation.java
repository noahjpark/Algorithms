// Noah Park
/*

Problem: Assume you have a method isSubstring which checks if one word is a substring of
another. Given two strings, s1 and s2, write code to check if s2 is a rotation of s1 using
only one call to isSubstring (e.g., "waterbottle" is a rotation of "erbottlewat").

*/

public class String_Rotation {

    // Checks if the second string is a rotation of the first
    public static boolean isRotation(String s1, String s2){

        // If we concatenate s2 with itself, it must contain a full version of the
        // string s1
        String doubled = s2 + s2;

        // If the lengths of s1 and s2 are equal and doubled contains the string s1,
        // there must be a rotation. Otherwise, there cannot be.
        return s1.length() == s2.length() && doubled.contains(s1);
    }

    public static void main(String[] args){
        String s1 = "waterbottle";
        String s2 = "erbottlewat";
        String s3 = "erbottlewatt";
        System.out.println(isRotation(s1, s2));
        System.out.println(isRotation(s1, s3));
    }

}
