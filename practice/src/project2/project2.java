package project2;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

public class project2 {

    static void floydAlgoInArray(){
        int[][] matrix = new int[][]{
                {0,1,3,99,99},
                {1,0,3,6,99},
                {3,3,0,4,2},
                {99,6,4,0,5},
                {99,99,2,5,0}
        };
        int scope = matrix[0].length;
        int[][] sub = matrix.clone();
        int[][] path = new int[scope][scope];

        for (int k =1;k<scope;k++){
            for (int i=0;i<scope;i++){
                for (int j=0;j<scope;j++){
                    if(sub[i][j] > sub[i][k]+sub[k][j]){
                        path[i][j] =  k;
                        sub[i][j] = sub[i][k]+sub[k][j];
                    }
                }
            }
        }

        for (int[] ints : sub) {
            System.out.println(Arrays.toString(ints));
        }

        System.out.println();
        for (int[] ints : path) {
            System.out.println(Arrays.toString(ints));
        }
    }

    static void floydAlgoInLinkedList(){

    }

    static void dijkstraAlgoInArray(){
        int[][] matrix = new int[][]{
                {0,2,3,99,99},
                {2,0,3,6,99},
                {3,3,0,4,2},
                {99,6,4,0,5},
                {99,99,2,5,0}
        };

        int scope = matrix[0].length;
        int[] touch = new int[scope];
        int[] length = new int[scope];

        // Assign the start point as (0,0)
        int vnear = 0;
        for (int i = 0;i<scope;i++){
            touch[i] = 0;
            length[i] = matrix[0][i];
        }

        int count = scope;
        while (count-1>0){
            // Find the nearest vertex
            int min = 99;
            for (int i=0;i<scope;i++){
                if(0<length[i] && length[i]<min){
                    min = length[i];
                    vnear = i;
                }
            }

            // Update the length
            for (int i=0;i<scope;i++){
                if(length[vnear] + matrix[vnear][i] < length[i]){
                    length[i] = length[vnear] + matrix[vnear][i];
                    touch[i] = vnear;
                }
            }

            System.out.println("From Node: 0 To Node: "+(vnear)+" Distance is: "+ length[vnear]);

            length[vnear] = -1;
            count --;
        }

        System.out.println(Arrays.toString(length));
        System.out.println(Arrays.toString(touch));

    }

    public static void main(String[] args) {
        floydAlgoInArray();

        dijkstraAlgoInArray();
    }
}
