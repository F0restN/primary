package project2;

import jdk.nashorn.internal.runtime.Scope;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.*;

public class project2 {
    static int[][] importCSVToMatrix() throws Exception {
        Reader in = new FileReader("/Users/drakezhou/IdeaProjects/primary/practice/src/project2/inputFiles/Project2_Input_File3.csv");
        CSVParser parser = CSVParser.parse(in, CSVFormat.EXCEL.withHeader().withSkipHeaderRecord(true));
        int size = 0;
        int max = 0;
        for (CSVRecord record : parser) {
            String i = record.get(0);
            int columnOne = Integer.valueOf(record.get(0)).intValue();   // Node ID
            int columnTwo = Integer.valueOf(record.get(1)).intValue();   // Node ID
            int columnThree = Integer.valueOf(record.get(2)).intValue();   // Node ID
            max = Math.max(columnOne, columnTwo);
            if (max > size) {
                size = max;
            }
        }

        size = size + 10;
        int[][] matrix = new int[size][size];


        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i != j) {
                    matrix[i][j] = 999999999;
                }
            }
        }
//        in.close();

        Reader in2 = new FileReader("/Users/drakezhou/IdeaProjects/primary/practice/src/project2/inputFiles/Project2_Input_File3.csv");
        CSVParser parser2 = CSVParser.parse(in2, CSVFormat.EXCEL.withHeader().withSkipHeaderRecord(true));
        for (CSVRecord record : parser2) {
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
        return list;
    }

    static ArrayList initialPathLinkedList(ArrayList list) {
        ArrayList path = new ArrayList();

        ListIterator it = list.listIterator();
        while (it.hasNext()) {
            path.add(0);
            it.next();
        }
        return path;
    }

    static void floydAlgoInArray(int[][] matrix) {
        long start = System.currentTimeMillis();
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
            }
        }
        System.out.println("From Node 197 To Node 27, and the distance is " + sub[197][27]);
        path(path, 197, 27);
        System.out.println("To Node 27");
        System.out.println();

        System.out.println("From Node 65 To Node 280, and the distance is "+sub[65][280]);
        path(path, 65, 280);
        System.out.println("To Node 280");
        System.out.println();

        System.out.println("From Node 187 To Node 68, and the distance is "+sub[187][68]);
        path(path, 187, 68);
        System.out.println("To Node 68");
        System.out.println();
//        long end = System.currentTimeMillis();
//        System.out.println("Floyd in arr: " + (end - start));

    }

    static void path(int[][] path, int q, int r) {
        if (path[q][r] != 0) {
            path(path, q, path[q][r]);
            System.out.println("To node " + path[q][r]);
            path(path, path[q][r], r);
        }
    }

    static void floydAlgoInLinkedList(ArrayList<Integer> list, int size) {
        long start = System.currentTimeMillis();
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

        System.out.println("From Node 197 || Destination:27 || Distance is " + sub.get((197 * (197 - 1)) / 2 + 27));
        pathForLinkedList(path, 197, 27);
        System.out.println("To Node 27");
        System.out.println();

        System.out.println("From Node:65 || Destination:280 || Distance: " + sub.get((280 * (280 - 1)) / 2 + 65));
        pathForLinkedList(path, 65, 280);
        System.out.println("To Node 280");
        System.out.println();

        System.out.println("From Node 187 || Destination:68 || Distance: " + sub.get((187 * (187 - 1)) / 2 + 68));
        pathForLinkedList(path, 187, 68);
        System.out.println("To Node 68");
        System.out.println();
//        long end = System.currentTimeMillis();
//        System.out.println("Floyd in list: " + (end - start));
    }

    static void pathForLinkedList(ArrayList<Integer> path, int q, int r) {
        int a = Math.max(q, r);
        int b = Math.min(q, r);
        if (0 != path.get((a * (a - 1)) / 2 + b)) {
            pathForLinkedList(path, q, path.get((a * (a - 1)) / 2 + b));
            System.out.println("To node " + path.get((a * (a - 1)) / 2 + b));
            pathForLinkedList(path, path.get((a * (a - 1)) / 2 + b), r);
        }
    }

    static void dijkstraAlgoInArray(int[][] matrix, int from, int to) {
        long start = System.currentTimeMillis();
        int scope = matrix[0].length;
        int[] touch = new int[scope];
        int[] length = new int[scope];
        int[] path = new int[scope];

        // Assign the start point
        int vnear = from;
        for (int i = 0; i < scope; i++) {
            touch[i] = 0;
            length[i] = matrix[from][i];
        }

        int count = scope;
        while (count - 1 > 0) {
            // Find the nearest vertex
            int min = 999999999;
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
                    path[i] = vnear;
                }
            }

//             Output
            if (vnear == to) {
                System.out.println("From Node: " + from + " To Node: " + (vnear) + " Distance is: " + length[vnear]);

                // calculate the path
                int i = to;
                Stack<Integer> s = new Stack();
                s.push(to);
                while (i != 0) {
                    if (path[i] == 0) {
                        s.push(from);
                    } else {
                        s.push(path[i]);
                    }
                    i = path[i];
                }

                System.out.print("The path is: ");
                while (!s.isEmpty()) {
                    System.out.print(s.pop() + " ");
                }
                System.out.println();
                System.out.println();
                return;
            }

            length[vnear] = -1;
            count--;
        }
        long end = System.currentTimeMillis();
        System.out.println("Dijkstra in arr"+(end-start));
    }

    static void dijkstraAlgoInLinkedList(ArrayList<Integer> list, int from, int to, int scope) {
        long start = System.currentTimeMillis();
        int[] touch = new int[scope];
        int[] length = new int[scope];
        int[] path = new int[scope];

        // Assign the start point
        int vnear = from;
        for (int i = 0; i < scope; i++) {
            int a = Math.max(from, i);
            int b = Math.min(from, i);
            touch[i] = 0;
            length[i] = list.get((a * (a - 1)) / 2 + b);
        }

        int count = scope;
        while (count - 1 > 0) {
            // Find the nearest vertex
            int min = 999999999;
            for (int i = 0; i < scope; i++) {
                if (0 < length[i] && length[i] < min) {
                    min = length[i];
                    vnear = i;
                }
            }

            // Update the length
            for (int i = 0; i < scope; i++) {
                int a = Math.max(vnear, i);
                int b = Math.min(vnear, i);
                if (length[vnear] + list.get((a * (a - 1)) / 2 + b) < length[i]) {
                    length[i] = length[vnear] + list.get((a * (a - 1)) / 2 + b);
                    touch[i] = vnear;
                    path[i] = vnear;
                }
            }

//             Output
            if (vnear == to) {
                System.out.println("From Node: "+from+" To Node: " + (vnear) + " Distance is: " + length[vnear]);

                // calculate the path
                int i = to;
                Stack<Integer> s = new Stack();
                s.push(to);
                while (i != 0){
                    if (path[i] == 0){
                        s.push(from);
                    } else {
                        s.push(path[i]);
                    }
                    i = path[i];
                }

                System.out.print("The path is: ");
                while (!s.isEmpty()){
                    System.out.print(s.pop()+" ");
                }
                System.out.println();
                System.out.println();
                return;
            }

            length[vnear] = -1;
            count--;
        }

        long end = System.currentTimeMillis();
        System.out.println("Dijkstra in List: "+(end-start));
    }

    public static void main(String[] args) throws Exception {
        // Initialize veriables
        long start = System.currentTimeMillis();
        int[][] matrix = importCSVToMatrix();
//        long end = System.currentTimeMillis();
        ArrayList list = transferFromMatrixToLinkedList(matrix);
//        long end2 = System.currentTimeMillis();
//        System.out.println("Matrix time " + (end - start));
//        System.out.println("list time " + (end2 - start));


        System.out.println("====Dijkstra Algo in List====");
        dijkstraAlgoInLinkedList(list, 197, 27, matrix.length);
        dijkstraAlgoInLinkedList(list, 65, 280, matrix.length);
        dijkstraAlgoInLinkedList(list, 187, 68, matrix.length);

        System.out.println("====Dijkstra Algo in 2D-Array====");
        dijkstraAlgoInArray(matrix, 197, 27);
        dijkstraAlgoInArray(matrix, 65, 280);
        dijkstraAlgoInArray(matrix, 187, 68);

        System.out.println("====Floyd Algo in 2D-Array====");
        floydAlgoInArray(matrix);

        System.out.println("====Floyd Algo in List====");
        floydAlgoInLinkedList(list, matrix.length);

    }
}
