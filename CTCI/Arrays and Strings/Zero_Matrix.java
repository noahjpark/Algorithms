// Noah Park
/*

Problem: Write an algorithm such that if an element in an MxN matrix is 0, its entire row
and column are set to 0.

*/

import java.util.ArrayList;

public class Zero_Matrix {

    // Zeroes out all matching rows and columns to a zero element
    public static void zero(int[][] m){
        // Store the row and column indices
        ArrayList<Integer> rows = new ArrayList<>();
        ArrayList<Integer> cols = new ArrayList<>();

        // Find all zero elements and store their indices in the arraylist
        for(int i = 0; i < m.length; i++){
            for(int j = 0; j < m[i].length; j++){
                if(m[i][j] == 0){
                    if(!rows.contains(i)){
                        rows.add(i);
                    }
                    if(!cols.contains(j)){
                        cols.add(j);
                    }
                }
            }
        }

        // Go through the rows list and zero out all corresponding columns
        for(int i : rows){
            for(int j = 0; j < m[i].length; j++){
                m[i][j] = 0;
            }
        }

        // Go through the cols list and zero out all corresponding rows
        for(int j : cols){
            for(int i = 0; i < m.length; i++){
                m[i][j] = 0;
            }
        }
    }

    // Second function using a single arraylist
    // Slightly less code than above but same complexity
    public static void zero2(int[][] m){
        ArrayList<int[]> indices = new ArrayList<>();

        for(int i = 0; i < m.length; i++){
            for(int j = 0; j < m[i].length; j++){
                if(m[i][j] == 0){
                    indices.add(new int[]{i,j});
                }
            }
        }

        for(int[] i : indices){
            for(int j = 0; j < m[i[0]].length; j++){
                m[i[0]][j] = 0;
            }
            for(int k = 0; k < m.length; k++){
                m[k][i[1]] = 0;
            }
        }

    }



    public static void main(String[] args){
        int[][] m = new int[][] {{1,1,1},{1,0,1},{1,1,1},{1,0,1},{1,1,1}};
        zero(m);
        for(int[] i : m){
            for(int j : i){
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

}
