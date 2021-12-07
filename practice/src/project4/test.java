package project4;

import java.util.ArrayList;
import java.util.Arrays;

public class test {

    static int boundCompute(int[][] matrix, problem2.node node) {

        int bound = 0;
        ArrayList<Integer> list = new ArrayList<>(node.path);
        ArrayList<int[]> tempMatrix = new ArrayList<>(Arrays.asList(matrix));

        if (list.size() > 1){
            for (int i = 0; i < list.size()-1; i++) {
                int a = list.get(i);
                int b = list.get(i+1);
                bound += matrix[a][b];
            }

            for (int number : list) {
                tempMatrix.remove(number);
            }

            for (int[] leftNodeArr : tempMatrix) {
                int min = Integer.MAX_VALUE;

                // Remove visited vertex
                for (int i : list) {
                    leftNodeArr[i] = 0;
                }

                for (int i : leftNodeArr) {
                    if (i < min && i != 0 && !list.contains(i)) {
                        min = i;
                    }
                }
                bound += min;
            }
        }

        System.out.println(bound);
        return bound;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {0, 14, 4, 10, 20},
                {14, 0, 7, 8, 7},
                {4, 5, 0, 7, 16},
                {11, 7, 9, 0, 2},
                {18, 7, 17, 4, 0}
        };
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        problem2.node node = new problem2.node();
        node.level = 0;
        node.path = list;

        boundCompute(matrix, node);
    }
}
