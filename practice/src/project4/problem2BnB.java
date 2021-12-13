package project4;
import java.io.Serializable;
import java.util.*;

import static project4.utils.readCSV.getSizeOfCSV;
import static project4.utils.readCSV.importCSVToMatrix;

public class problem2BnB {

    static class node implements Serializable, Cloneable {
        int level;
        int bound;
        ArrayList<Integer> path;

        public node() {
        }

        public node(int level, int bound, ArrayList<Integer> path) {
            this.level = level;
            this.bound = bound;
            this.path = path;
        }

        @Override
        public String toString() {
            return "Path = "+path;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public node clone() throws CloneNotSupportedException {
            return (node) super.clone();
        }
    }

    static int findLeftOne(int[][] matrix, ArrayList list) {
        int leftOne = 0;
        for (int i = 1; i < matrix.length; i++) {
            if (!list.contains(i)) {
                leftOne = i;
            }
        }
        return leftOne;
    }

    static int lengthCompute(int[][] matrix, node node) {
        // returns the length of the tour u.path
        ArrayList<Integer> path = new ArrayList<Integer>(node.path);

        // Calculate to exist vertex path.
        int length = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            int a = path.get(i);
            int b = path.get(i + 1);
            length += matrix[a][b];
        }
//        System.out.println(length);
        return length;
    }

    static int boundCompute(int[][] matrix, node node) {
        int bound = 0;
        ArrayList<Integer> path = new ArrayList<>(node.path);
        HashMap<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            map.put(i, matrix[i].clone());
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

//        System.out.println(bound);
        return bound;
    }

    static ArrayList<Integer> TSP(int n, int[][] matrix, ArrayList<Integer> opttour) throws CloneNotSupportedException {
        // initialize
        PriorityQueue<node> pq = new PriorityQueue<>(n, new Comparator<node>() {
            @Override
            public int compare(node o1, node o2) {
                return (o1.bound - o2.bound);
            }
        });

        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        node v = new node();
        node u = new node();
        v.level = 0;
        v.path = list;
        v.bound = boundCompute(matrix, v);
        int minLength = Integer.MAX_VALUE;
        pq.add(v.clone());

        while (!pq.isEmpty()) {
            v = pq.poll();

            if (v.bound < minLength) {
                u.level = v.level + 1;
                for (int i = 1; i < n; i++) {
                    if (!v.path.contains(i)) {
                        u.path = (ArrayList<Integer>) v.path.clone();
                        u.path.add(i);

                        if (u.level == n - 2) {
                            u.path.add(findLeftOne(matrix, u.path));
                            u.path.add(0);

                            if (lengthCompute(matrix, u) < minLength) {
                                minLength = lengthCompute(matrix, u);
                                opttour = u.path;
                            }

                        } else {
                            u.bound = boundCompute(matrix, u.clone());
                            if (u.bound < minLength) {
                                pq.add(u.clone());
                            }
                        }

                    }
                }
            }

        }


        return opttour;
    }

    public static void main(String[] args) throws Exception {
        // Initial
        String filePath = "/Users/drakezhou/IdeaProjects/primary/practice/src/project4/inputFiles/Project 4_Problem 2_InputData.csv";
        int matrixSize = getSizeOfCSV(filePath);
        int[][] matrix = importCSVToMatrix(matrixSize, filePath);

        // Main function
        ArrayList<Integer> opttour = new ArrayList<>();
        opttour = TSP(matrix.length, matrix, opttour);

        // Output
        System.out.println("Path = "+opttour);
        System.out.println("Length = "+lengthCompute(matrix, new node(0,0, opttour)));
    }

}


