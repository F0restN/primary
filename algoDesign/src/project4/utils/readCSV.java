package project4.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.Reader;

public class readCSV {

    public static int getSizeOfCSV(String filePath) throws Exception {
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

    public static int[][] importCSVToMatrix(int size, String filePath) throws Exception {
        int[][] matrix = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i != j) {
                    matrix[i][j] = 9999999;
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
}
