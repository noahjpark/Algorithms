// Noah Park

public class Rotate_Matrix {

    // Given an image represented by an NxN matrix, where each
    // pixel is in the image is 4 bytes, write a method to rotate
    // the image by 90 degrees. Can you do this in place?
    public static void rotate(int[][] m){
        // curLength represents the length - 1 of the particular side
        // i will be the layer we are in
        // length will be constant as the length of the overall side
        int curLength = m.length - 1;
        int i = 0;
        int length = curLength;
        // Loop while the length of our layer is greater than 1 (only in an odd case; otherwise, the minimum is 4)
        while(curLength > 1){
            // Use j as an offset
            for(int j = 0; j < curLength; j++){
                // Right top corner rotates downwards
                int temp = m[i + j][length - i];
                m[i + j][length - i] = m[i][i + j];
                // Bottom right corner rotates leftwards
                int temp2 = m[length - i][length - i - j];
                m[length - i][length - i - j] = temp;
                // Bottom left corner rotates upwards
                temp = m[length - i - j][i];
                m[length - i - j][i] = temp2;
                // Top left corner rotates rightwards
                m[i][i + j] = temp;
            }
            // Cut off the sides and increment i, our layer
            curLength -= 2;
            i++;
        }
    }

    public static void main(String[] args){
        int[][] m = new int[][] {{0,0,0},{1,1,1},{2,2,2}};
        int[][] n = new int[][] {{0,0,0,0,0},{1,1,1,1,1},{2,2,2,2,2},{3,3,3,3,3},{4,4,4,4,4}};
        int[][] p = new int[][] {{0,0,0,0,0,0,0},{1,1,1,1,1,1,1},{2,2,2,2,2,2,2},{3,3,3,3,3,3,3},{4,4,4,4,4,4,4},{5,5,5,5,5,5,5},{6,6,6,6,6,6,6}};
        for(int[] i : m){
            for(int j : i){
                System.out.print(j + " ");
            }
            System.out.println();
        }
        System.out.println();
        rotate(m);
        for(int[] i : m){
            for(int j : i){
                System.out.print(j + " ");
            }
            System.out.println();
        }
        System.out.println();
        for(int[] i : n){
            for(int j : i){
                System.out.print(j + " ");
            }
            System.out.println();
        }
        System.out.println();
        rotate(n);
        for(int[] i : n){
            for(int j : i){
                System.out.print(j + " ");
            }
            System.out.println();
        }
        System.out.println();
        for(int[] i : p){
            for(int j : i){
                System.out.print(j + " ");
            }
            System.out.println();
        }
        System.out.println();
        rotate(p);
        for(int[] i : p){
            for(int j : i){
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

}
