package project4;

import java.util.ArrayList;
import java.util.Arrays;

import static project4.problem2DP.TSP;
import static project4.utils.convert.convertMatrixToList;
import static project4.utils.readCSV.getSizeOfCSV;
import static project4.utils.readCSV.importCSVToMatrix;

public class DPTSPBits {

    static int getValue(int[] matrixList, int i, int j) {
        try {
            int a = Math.max(i, j);
            int b = Math.min(i, j);
            return matrixList[(a * (a - 1) / 2) + b];
        } catch (Exception e){
            return 0;
        }
    }

    static void TSP4List(int n, int[] matrixList) {

        int all = 1 << (n - 1);
        int[][] dp = new int[n][all];
        int[][] path = new int[n][all];
        for (int i = 0; i < dp.length; i++) {
            int[] dpRow = dp[i];
            for (int j = 0; j < dpRow.length; j++) {
                dp[i][j] = 999999;
            }
        }

        for (int j = 0; j < all; j++) {
            for (int i = 0; i < n; i++) {
                if (j == 0) {
                    dp[i][0] = getValue(matrixList, i, 0);
                } else {
                    for (int k = 1; k < n; k++) {
                        int other = (1 << (k - 1));
                        if ((other & j) > 0) {

                            int a = dp[i][j];
                            int b = getValue(matrixList, i, k) + dp[k][other ^ j];

                            if (a > b) path[i][j] = k;
                            dp[i][j] = Math.min(a, b);


                        }
                    }
                }
            }
        }
        System.out.println("Dynamic Programming");
        System.out.println("Length = " + dp[0][all - 1]);

        // Print Path
        ArrayList<Integer> pathList = new ArrayList<>();
        pathList.add(0);
        int A = all - 1;
        int vertex = path[0][A];
        pathList.add(vertex);
        for (int k = 0; k < n - 2; k++) {
            A = A ^ (1 << (vertex - 1));
            vertex = path[vertex][A];
            pathList.add(vertex);
        }
        pathList.add(0);

        System.out.println("Path = " + pathList);
    }


    public static void main(String[] args) throws Exception {
//        int[][] matrix = new int[][]{
//                {0, 14, 4, 10, 20},
//                {14, 0, 7, 8, 7},
//                {4, 5, 0, 7, 16},
//                {11, 7, 9, 0, 2},
//                {18, 7, 17, 4, 0}
//        };

//        int[][] matrix = new int[][]{
//                {0,2,9,99999},
//                {1,0,6,4},
//                {99999,7,0,8},
//                {6,3,99999,0}
//        };

        String filePath = "/Users/drakezhou/IdeaProjects/primary/practice/src/project4/inputFiles/Project 4_Problem 2_InputData.csv";
        int matrixSize = getSizeOfCSV(filePath);
        int[][] matrix = importCSVToMatrix(matrixSize, filePath);
        TSP4List(matrix.length, convertMatrixToList(matrix));
    }
}
