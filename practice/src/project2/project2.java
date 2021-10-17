package project2;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class project2 {

    static LinkedList transferFromMatrixToLinkedList(){
        int[][] matrix = new int[][]{
                {0,1,3,99,99},
                {1,0,3,6,99},
                {3,3,0,4,2},
                {99,6,4,0,5},
                {99,99,2,5,0}
        };
        int scope = matrix[0].length;

        LinkedList list = new LinkedList();
        LinkedList a = new LinkedList();
        for(int i=1;i<scope;i++){
            for (int j=0;j<=i-1;j++){
                list.add(matrix[i][j]);
            }
        }
        System.out.println(list);
        return list;
    }

    static LinkedList initialPathLinkedList(LinkedList list){
        LinkedList path = new LinkedList();
        ListIterator it = list.listIterator();
        while (it.hasNext()){
            path.add(-1);
            it.next();
        }
        return path;
    }

    static void floydAlgoInArray(int from, int to){
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

        //Output the shortest path
        int lastVertex = path[from][to];
        while (lastVertex != from){
            System.out.println("From Node "+lastVertex+" To Node "+to+" Distance "+matrix[lastVertex][to]);
            to = lastVertex;
            lastVertex = path[from][lastVertex];
        }
        System.out.println("From Node "+lastVertex+" To Node "+to+" Distance "+matrix[lastVertex][to]);

    }

    static void floydAlgoInLinkedList(LinkedList<Integer> list){
        int x = 0;
        int y = 4;

        LinkedList<Integer> sub = (LinkedList<Integer>) list.clone();
        int scope = list.size()/2;
        LinkedList<Integer> path = initialPathLinkedList(list);


        for (int k =1;k<scope-1;k++){
            for (int i=1;i<scope;i++){
                for (int j=0;j<i;j++){
                    int a = sub.get(((i)*(i-1))/2+j);
                    int b = sub.get(((i)*(i-1))/2+k);
                    int c = sub.get(((k)*(k-1))/2+j);
                    if( a > b+c){
                        path.set(((i)*(i-1))/2+j, k);
                        sub.set(((i)*(i-1))/2+j, b+c);
                    }
                }
            }
        }
        System.out.println(sub);
        System.out.println(path);
    }

    static void dijkstraAlgoInArray(int from, int to){
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

        // Assign the start point
        int vnear = 0;
        for (int i = 0;i<scope;i++){
            touch[i] = 0;
            length[i] = matrix[from][i];
        }

        int count = scope;
        while (count-1>0) {
            // Find the nearest vertex
            int min = 99;
            for (int i = 0; i < scope; i++) {
                if (0 < length[i] && length[i] < min) {
                    min = length[i];
                    vnear = i;
                }
            }

            // Update the length
            for (int i = 0; i < scope; i++) {
                if (length[vnear] + matrix[vnear][i] < length[i]) {
                    length[i] = length[vnear] + matrix[vnear][i];
                    touch[i] = vnear;
                }
            }
            if (vnear == to) {
                System.out.println("From Node: 0 To Node: " + (vnear) + " Distance is: " + length[vnear]);
                return;
            }

            length[vnear] = -1;
            count--;
        }
    }

    public static void main(String[] args) {
        LinkedList list = transferFromMatrixToLinkedList();
        floydAlgoInLinkedList(list);
//        floydAlgoInArray(0,4);
        initialPathLinkedList(list);

//        dijkstraAlgoInArray(0,3);
    }
}
