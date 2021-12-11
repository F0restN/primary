package project4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class test {

    static int boundCompute(int[][] matrix, problem2.node node) {
        int bound = 0;
        ArrayList<Integer> path = new ArrayList<>(node.path);
        HashMap<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < Arrays.asList(matrix).size(); i++) {
            map.put(i, Arrays.asList(matrix).get(i));
        }

        if (path.size() > 1) {
            // Get the exact distance for vertexs which already leave from.
            for (int i = 0; i < path.size() - 1; i++) {
                int a = path.get(i);
                int b = path.get(i + 1);
                bound += matrix[a][b];
            }

            // Remove them from the list so that we can compute the minimum cost left nodes
            for (int i = 0; i < path.size() - 1; i++) {
                map.remove(path.get(i));
            }

            // Remove visited vertex when computing
            // 1. for the last vistied vertex, do not include vertex 0
            int[] lastVisitedVertex = map.get(path.get(path.size() - 1));
            for (Integer i : path) {
                lastVisitedVertex[i] = 0;
            }
            // 2. for the rest non-visited vertices, do not include last visited vertex.
            ArrayList<Integer> leftVertics = new ArrayList<>();
            for (int i = 0; i < matrix.length; i++) {
                leftVertics.add(i);
            }
            leftVertics.removeAll(path);
            for (Integer vertex : leftVertics) {
                for (int i = 1; i < path.size(); i++) {
                    map.get(vertex)[path.get(i)] = 0;
                }
            }

            // Compute the bound
            for (int j: map.keySet()){
                int[] arr = map.get(j);
                int min = 999999;
                for (int i : arr) {
                    if (i < min && i != 0) {
                        min = i;
                    }
                }
                bound += min;
            }
        } else {
            bound = 21;
        }

        System.out.println(bound);
        return bound;
    }

    static int lengthCompute(int[][] matrix, problem2.node node) {
        // returns the length of the tour u.path
        ArrayList<Integer> path = new ArrayList<Integer>(node.path);

        // Calculate to exist vertex path.
        int length = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            int a = path.get(i);
            int b = path.get(i + 1);
            length += matrix[a][b];
        }

        // Calculate to last vertex and return

//        int lastVertex = path.get(path.size() - 1);
//        ArrayList<Integer> leftVertics = new ArrayList<>();
//        for (int i = 0; i < matrix.length; i++) {
//            leftVertics.add(i);
//        }
//        leftVertics.removeAll(path);
//        for (Integer vertex : leftVertics) {
//            length += matrix[lastVertex][vertex];
//            length += matrix[vertex][0];
//        }

//        System.out.println(length);
        return length;
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
//        list.add(1);
        list.add(2);
//        list.add(3);
//        list.add(4);
        problem2.node node = new problem2.node();
        node.level = 0;
        node.path = list;
//        lengthCompute(matrix, node);
        boundCompute(matrix, node);
    }
}
