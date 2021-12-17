import java.io.*;
import java.util.Arrays;

public class quiz4 {

    static void graphStatusReport(String filePath) throws IOException {
        int[][] graph = readMatrixFromTxt(filePath);

        /*
        * To denote the status of a node [in-degree][out-degree][status]
        * 1 = Not Connected
        * 2 = Connected
        * 3 = All connected
        * */
        int nodes = graph.length;
        int[][] nodeStatus = new int[nodes][3];

        // For each out-degree.
        for(int i=0; i<nodes;i++){
            int sum = 0;
            for (int j=0;j<nodes;j++){
                if (graph[i][j]>0){
                    sum++;
                }
            }
            nodeStatus[i][1] = sum;
        };

        // For each in-degree.
        for(int j=0; j<nodes;j++){
            int sum = 0;
            for (int i=0;i<nodes;i++){
                if (graph[i][j]>0){
                    sum++;
                }
            }
            nodeStatus[j][0] = sum;
        };

        int count = 0;
        int completeCount = 0;
        for (int i = 0; i<nodeStatus.length; i++){
            if (nodeStatus[i][0]>0 && nodeStatus[i][1]>0){
                nodeStatus[i][2] = 2;
                count++;
                if (nodeStatus[i][0] == nodeStatus[i][1] && nodeStatus[i][0] == nodes-1){
                    nodeStatus[i][2] = 3;
                    completeCount++;
                }
            }
        }

        if (count < nodes){
            System.out.println("This graphic is NOT CONNECTED and NOT COMPLETE");
        } else {
            if (completeCount == nodes){
                System.out.println("This graphic is COMPLETE and CONNECTED");
            } else {
                System.out.println("This graphic is CONNECTED but NOT COMPLETE");
            }
        }

        int number = 1;
        for (int[] subArr : nodeStatus) {
            System.out.println("Node #"+number+" has "+(subArr[0]+subArr[1])+" degrees, include "+subArr[0]+ " in-degree and "+subArr[1]+" out-degree");
            number++;
        }

        System.out.println();
    }

    static int[][] readMatrixFromTxt(String filePath) throws IOException {

        InputStream inputStream = quiz4.class.getResourceAsStream(filePath);
        BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));

        // Read by line and store.
        String line = new String();
        String str = new String();
        while((line = bf.readLine()) != null) {
            str += line + ",";
        }
        String[] numbers = str.split(",");

        // Convert into integer and transfer into matrix
        int[][] matrix = new int[numbers.length][numbers.length];
        String[] stmp = null;
        for(int i = 0; i < numbers.length; i++) {
            stmp = numbers[i].split(" ");
            for(int j = 0; j < numbers.length; j++) {
                matrix[i][j] = Integer.parseInt(stmp[j]);
            }
        }

//        Print matrix
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }

        bf.close();
        return matrix;
    }

    public static void main(String[] args) throws Exception {
//        int[][] testCase1 = new int[][]{{0, 1, 0, 1, 0}, {1, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 0, 1, 0, 1}, {0, 0, 0, 1, 0}};
//        int[][] testCase2 = new int[][]{{0, 1, 1, 1}, {1, 0, 1, 1}, {1, 1, 0, 1}, {1, 1, 1, 0}};
//        int[][] testCase3 = new int[][]{{0, 1, 1, 1, 0}, {1, 0, 1, 1, 0}, {1, 1, 0, 1, 0}, {1, 1, 1, 0, 0}, {0, 0, 0, 0, 0}};
//        int[][] testCase4 = new int[][]{{0, 0, 0, 0, 0}, {1, 0, 0, 0, 0}, {0, 1, 0, 1, 0}, {0, 1, 1, 0, 1}, {0, 1, 1, 1, 0}};
//        graphStatusReport(testCase1);
//        graphStatusReport(testCase2);
//        graphStatusReport(testCase3);
//        graphStatusReport(testCase4);

        graphStatusReport("/lib/testCase1.txt");
        graphStatusReport("/lib/testCase2.txt");
        graphStatusReport("/lib/testCase3.txt");
        graphStatusReport("/lib/testCase4.txt");
    }
}
