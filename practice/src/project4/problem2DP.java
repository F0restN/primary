package project4;

import java.util.ArrayList;

import static project4.utils.readCSV.getSizeOfCSV;
import static project4.utils.readCSV.importCSVToMatrix;

public class problem2DP {

    static void TSP(int n, int[][] matrix) {
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
                    dp[i][0] = matrix[i][0];
                } else {
                    for (int k = 1; k < n; k++) {
                        int other = (1 << (k - 1));
                        if ((other & j) > 0) {

                            if (dp[i][j] > matrix[i][k] + dp[k][other ^ j]) {
                                path[i][j] = k;
                            }

                            dp[i][j] = Math.min(dp[i][j], matrix[i][k] + dp[k][other ^ j]);

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
        // Read file
        String filePath = "/Users/drakezhou/IdeaProjects/primary/practice/src/project4/inputFiles/Project 4_Problem 2_InputData.csv";
        int matrixSize = getSizeOfCSV(filePath);
        int[][] matrix = importCSVToMatrix(matrixSize, filePath);

        // 2D-Array situation
        System.out.println("BnB for TSP 2D-Array : ");
        TSP(matrix.length, matrix);
    }
}
