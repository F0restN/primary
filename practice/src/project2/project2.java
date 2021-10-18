package project2;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.*;

public class project2 {
    static int[][] importCSVToMatrix() throws Exception {
//        Reader in = new FileReader("/Users/drakezhou/IdeaProjects/primary/practice/src/project2/inputFiles/Project2_Input_File3.csv");
//        int size = CSVParser.parse(in, CSVFormat.RFC4180.withSkipHeaderRecord()).getRecords().size();
//        System.out.println(size);
        int size = 500;
        int[][] matrix = new int[size][size];

        for(int i =0;i<size;i++){
            for (int j=0; j<size;j++){
                if (i != j){
                    matrix[i][j] = 999999999;
                }
            }
        }
//        in.close();

        Reader in2 = new FileReader("/Users/drakezhou/IdeaProjects/primary/practice/src/project2/inputFiles/Project2_Input_File3.csv");
        CSVParser parser = CSVParser.parse(in2, CSVFormat.EXCEL.withHeader().withSkipHeaderRecord(true));
        for (CSVRecord record : parser) {
            String i = record.get(0);
            int columnOne = Integer.valueOf(record.get(0)).intValue();   // Node ID
            int columnTwo = Integer.valueOf(record.get(1)).intValue();   // Node ID
            int columnThree = Integer.valueOf(record.get(2)).intValue();   // Node ID
            matrix[columnOne][columnTwo] = columnThree;

        }

        // Test case
//        matrix = new int[][]{
//                {0, 1, 3, 99, 99},
//                {1, 0, 3, 6, 99},
//                {3, 3, 0, 4, 2},
//                {99, 6, 4, 0, 5},
//                {99, 99, 2, 5, 0}
//        };

        return matrix;
    }

    static ArrayList transferFromMatrixToLinkedList(int[][] matrix) {
        int scope = matrix[0].length;

        ArrayList list = new ArrayList();
        for (int i = 1; i < scope; i++) {
            for (int j = 0; j <= i - 1; j++) {
                list.add(matrix[i][j]);
            }
        }
        System.out.println(list.size());
        return list;
    }

    static ArrayList initialPathLinkedList(ArrayList list) {
        ArrayList path = new ArrayList();

        ListIterator it = list.listIterator();
        while (it.hasNext()) {
            path.add(0);
            it.next();
        }
        System.out.println(path.size());
        return path;
    }

    static void floydAlgoInArray(int[][] matrix) {
        int scope = matrix[0].length;
        int[][] sub = matrix.clone();
        int[][] path = new int[scope][scope];

        for (int k = 1; k < scope; k++) {
            for (int i = 0; i < scope; i++) {
                for (int j = 0; j < scope; j++) {
                    if (sub[i][j] > sub[i][k] + sub[k][j]) {
                        path[i][j] = k;
                        sub[i][j] = sub[i][k] + sub[k][j];
                    }
                }
                System.out.println(sub[197][27]);
            }
        }
        System.out.println("From Node 197 To Node 27, and the distance is "+sub[197][27]);
        path(path, 197, 27);
        System.out.println("To Node 27");
        System.out.println();

//        System.out.println("From Node 65 To Node 280, and the distance is "+sub[65][280]);
//        path(path, 65, 280);
//        System.out.println("To Node 280");
//        System.out.println();
//
//        System.out.println("From Node 187 To Node 68, and the distance is "+sub[187][68]);
//        path(path, 187, 68);
//        System.out.println("To Node 68");
//        System.out.println();
    }

    static void path(int[][] path, int q, int r){
        if (path[q][r]!=0){
            path(path, q, path[q][r]);
            System.out.println( "To node "+path[q][r]);
            path(path, path[q][r], r);
        }
    }

    static void floydAlgoInLinkedList(ArrayList<Integer> list, int size) {
        int scope = size;
        ArrayList<Integer> sub = (ArrayList<Integer>) list.clone();
        ArrayList<Integer> path = initialPathLinkedList(list);

        for (int k = 0; k < scope - 1; k++) {
            for (int j = 0; j < scope - 1; j++) {
                for (int i = j + 1; i < scope; i++) {
                    int a, b, c;
                    a = sub.get(((i) * (i - 1)) / 2 + j);

                    if (k > i) {
                        b = sub.get((k * (k - 1)) / 2 + i);
                    } else {
                        b = sub.get((i * (i - 1)) / 2 + k);
                    }

                    if (k > j) {
                        c = sub.get((k * (k - 1)) / 2 + j);
                    } else {
                        c = sub.get((j * (j - 1)) / 2 + k);
                    }

                    if (a > b + c) {
                        path.set(((i) * (i - 1)) / 2 + j, k);
                        sub.set(((i) * (i - 1)) / 2 + j, b + c);
                    }
                }

            }
        }

        System.out.println("From Node 197 To Node 27, and the distance is " + sub.get((197 * (197 - 1)) / 2 + 27));
        pathForLinkedList(path, 27, 197);
//        System.out.println("To Node 27");
        System.out.println();

//        System.out.println("From Node 65 To Node 280, and the distance is " + sub.get((280 * (280 - 1)) / 2 + 65));
//        pathForLinkedList(path, 65, 280);
//        System.out.println("To Node 280");
//        System.out.println();
//
//        System.out.println("From Node 187 To Node 68, and the distance is " + sub.get((187 * (187 - 1)) / 2 + 68));
//        pathForLinkedList(path, 187, 68);
//        System.out.println("To Node 68");
//        System.out.println();
    }

    static void pathForLinkedList(ArrayList<Integer> path, int q, int r){
        if (0 != path.get((q*(q-1))/2+r)){
            pathForLinkedList(path, q, path.get((q*(q-1))/2+r));
            System.out.println( "To node "+ path.get((q*(q-1))/2+r) );
            pathForLinkedList(path, path.get((q*(q-1))/2+r), r);
        }
    }

    static void dijkstraAlgoInArray(int from, int to) {
        int[][] matrix = new int[][]{
                {0, 2, 3, 99, 99},
                {2, 0, 3, 6, 99},
                {3, 3, 0, 4, 2},
                {99, 6, 4, 0, 5},
                {99, 99, 2, 5, 0}
        };

        int scope = matrix[0].length;
        int[] touch = new int[scope];
        int[] length = new int[scope];

        // Assign the start point
        int vnear = 0;
        for (int i = 0; i < scope; i++) {
            touch[i] = 0;
            length[i] = matrix[from][i];
        }

        int count = scope;
        while (count - 1 > 0) {
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

    public static void main(String[] args) throws Exception {
        int[][] matrix = importCSVToMatrix();
//        floydAlgoInArray(matrix);

        ArrayList list = transferFromMatrixToLinkedList(matrix);
        floydAlgoInLinkedList(list, 500);
//        floydAlgoInLinkedList(list, matrix[0].length);

//        initialPathLinkedList(list);

//        dijkstraAlgoInArray(0,3);
    }
}
