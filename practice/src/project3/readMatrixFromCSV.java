package project3;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import sun.security.util.Length;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.temporal.ValueRange;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("ALL")
public class readMatrixFromCSV {

    static int[][] readCSV () throws IOException {
        HashMap<vertex, Integer> verticsMap = new HashMap();
        HashMap<edge, Integer> edgeMap = new HashMap<edge, Integer>();

        Reader inG1 = new FileReader("/Users/drakezhou/IdeaProjects/primary/practice/src/project3/inputFiles/Project3_G1_Data.csv");
        CSVParser parser = CSVParser.parse(inG1, CSVFormat.EXCEL.withHeader().withSkipHeaderRecord(true));
        int lastNode = 0;
        for (CSVRecord record : parser) {
            String i = record.get(0);
            int c1 = Integer.valueOf(record.get(0)).intValue();
            int c2 = Integer.valueOf(record.get(1)).intValue();
            int c3 = Integer.valueOf(record.get(2)).intValue();
            String columnFour = record.get(3);
            String[] coordination  = columnFour.split("[(, )]+");
            double x = Double.valueOf(coordination[1]).doubleValue();
            double y = Double.valueOf(coordination[2]).doubleValue();
            verticsMap.put(new vertex(x, y, c1), c1);
            edgeMap.put(new edge(c1, c2, c3), c1);
            lastNode = c1;
        }

        HashMap<Integer, vertex> verticMapG2 = new HashMap<Integer, vertex>();
        Reader inG2 = new FileReader("/Users/drakezhou/IdeaProjects/primary/practice/src/project3/inputFiles/Project3_G2_Data.csv");
        parser = CSVParser.parse(inG2, CSVFormat.EXCEL.withHeader().withSkipHeaderRecord(true));
        for (CSVRecord record : parser) {
            String i = record.get(0);
            int columnOne = Integer.valueOf(record.get(0)).intValue();
            String columnFour = record.get(3);
            String[] coordination  = columnFour.split("[(, )]+");
            double x = Double.valueOf(coordination[1]).doubleValue();
            double y = Double.valueOf(coordination[2]).doubleValue();
            if (!verticsMap.containsKey(new vertex(x, y))){
                lastNode++;
                verticsMap.put(new vertex(x, y, lastNode), lastNode);
            }
            verticMapG2.put(columnOne, new vertex(x, y));

        }

        int max = 0;
//        for (Map.Entry<vertex, Integer> entry : verticsMap.entrySet()) {
//            vertex ele = entry.getKey();
//            Integer value = entry.getValue();
//            if (value > max) {
//                max = value;
//            }
//            System.out.println(ele);
//        }
//        System.out.println(max);

        Reader inG21 = new FileReader("/Users/drakezhou/IdeaProjects/primary/practice/src/project3/inputFiles/Project3_G2_Data.csv");
        parser = CSVParser.parse(inG21, CSVFormat.EXCEL.withHeader().withSkipHeaderRecord(true));
        for (CSVRecord record : parser) {
            int c1 = Integer.valueOf(record.get(0)).intValue();
            int c2 = Integer.valueOf(record.get(1)).intValue();
            int c3 = Integer.valueOf(record.get(2)).intValue();
            vertex obj = verticMapG2.get(c1);
            int currentNode = verticsMap.get(new vertex(obj.x, obj.y));
            obj = verticMapG2.get(c2);
            int nextNode = verticsMap.get(new vertex(obj.x, obj.y));
            edgeMap.put(new edge(currentNode, nextNode, c3), c3);
        }

        int[][] matrix = new int[verticsMap.size()][verticsMap.size()];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = 999999;
            }
        }

        edgeMap.forEach((k,v) -> {
            matrix[k.startPoint][k.endPoint] = k.getLen();
        });

//        for (int[] ints : matrix) {
//            System.out.println(Arrays.toString(ints));
//        }

        return matrix;
    }

    public static void main(String[] args) throws IOException {
        readCSV();
    }
}
