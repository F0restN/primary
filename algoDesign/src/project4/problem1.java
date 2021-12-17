package project4;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.Reader;
import java.util.*;

public class problem1 {

    static class pivot implements Comparable<pivot>{
        int number;
        int value;

        public pivot(int number, int value) {
            this.number = number;
            this.value = value;
        }

        @Override
        public String toString() {
            return "#"+number+" : "+value+"\n";
        }

        @Override
        public int compareTo(pivot o) {
            return -(int)(this.value - o.value);
        }
    }

    static int getSizeOfCSV(String filePath) throws Exception {
        Reader in = new FileReader(filePath);
        CSVParser parser = CSVParser.parse(in, CSVFormat.EXCEL.withHeader().withSkipHeaderRecord(true));
        int size = 0;
        int max;
        for (CSVRecord record : parser) {
            int columnOne = Integer.parseInt(record.get(0));   // Node ID
            int columnTwo = Integer.parseInt(record.get(1));   // Node ID
            max = Math.max(columnOne, columnTwo);
            if (max > size) {
                size = max;
            }
        }
        return size+1;
    }

    static int[][] importCSVToMatrix(int size, String filePath) throws Exception {
        int[][] matrix = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i != j) {
                    matrix[i][j] = 999999999;
                }
            }
        }

        Reader in2 = new FileReader(filePath);
        CSVParser parser2 = CSVParser.parse(in2, CSVFormat.EXCEL.withHeader().withSkipHeaderRecord(true));
        for (CSVRecord record : parser2) {
            int columnOne = Integer.parseInt(record.get(0));   // Node ID
            int columnTwo = Integer.parseInt(record.get(1));   // Node ID
            int columnThree = Integer.parseInt(record.get(2));   // Node ID
            matrix[columnOne][columnTwo] = columnThree;
        }

        return matrix;
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
            }
        }
//        System.out.println("From Node 197 To Node 27, and the distance is " + sub[197][27]);


        int[] record = new int[1427];

        for (int i = 0; i < 1427; i++) {
            for (int j = 0; j < 1427; j++) {
                path(record, path, i, j);
            }
        }

        List<pivot> sortedRecord = new ArrayList<>();

//         # To display the record arr.
        for (int i = 0; i < record.length; i++) {
            sortedRecord.add(new pivot(i, record[i]));
        }

        Collections.sort(sortedRecord);

        System.out.println("=== TOP 20 ===");
        for (int i = 0; i < 20; i++) {
            pivot p = sortedRecord.get(i);
            System.out.println("# "+p.number+" "+p.value);
        }
    }

    static ArrayList path(int[] record, int[][] path, int q, int r) {
        ArrayList arr = new ArrayList();

        if (path[q][r] != 0) {
            arr = path(record, path, q, path[q][r]);
//            System.out.println("To node " + path[q][r]);
            record[path[q][r]] += 1;
            arr = path(record, path, path[q][r], r);
        }

        return arr;
    }

    public static void main(String[] args) throws Exception {
        String path = "/Users/drakezhou/IdeaProjects/primary/practice/src/project4/inputFiles/Project 4_Problem 1_InputData.csv";
        int[][] matrix = importCSVToMatrix(getSizeOfCSV(path), path);
        floydAlgoInArray(matrix);
    }

}
