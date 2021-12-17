package project4;

import com.sun.tools.javac.util.Convert;

import java.io.Serializable;
import java.util.*;

import static project4.utils.convert.convertArrToArrayList;
import static project4.utils.convert.convertArrayListToArr;
import static project4.utils.readCSV.getSizeOfCSV;
import static project4.utils.readCSV.importCSVToMatrix;

public class DPTSPFromTextbook {

    static class distanceObj {
        int p1;
        int[] p2;

        public distanceObj(int p1, int[] p2) {
            this.p1 = p1;
            this.p2 = p2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            distanceObj distance = (distanceObj) o;
            return p1 == distance.p1 && Arrays.equals(p2, distance.p2);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(p1);
            result = 31 * result + Arrays.hashCode(p2);
            return result;
        }
    }

    static int[] removeArrElement(int[] origin, int[] target) {
        if (origin.length - target.length == 0) {
            return new int[]{0};
        }

        int[] copy = new int[origin.length - target.length];

        for (int i = 0, j = 0; i < origin.length; i++) {
            boolean flag = true;
            for (int t : target) {
                if (origin[i] == t) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                copy[j++] = origin[i];
            }
        }

        return copy;
    }

    public static void findSubsets(LinkedList<List<Integer>> subset, ArrayList<Integer> nums, List<Integer> output, int index) {
        if (index == nums.size()) {
            subset.add(output);
            return;
        }

        findSubsets(subset, nums, new ArrayList<>(output), index + 1);
        output.add(nums.get(index));
        findSubsets(subset, nums, new ArrayList<>(output), index + 1);
    }

    static void dpTSP(int[][] matrix, int n) {
        //Initialize
        HashMap<distanceObj, Integer> distance = new HashMap<>();
        HashMap<distanceObj, Integer> opttour = new HashMap<>();
        HashMap<Integer, Integer> o2 = new HashMap<>();
        int[] vertices = new int[n];
        for (int i = 0; i < n; i++) {
            vertices[i] = i;
        }


        // Dynamic Programming
        for (int i = 1; i < n; i++) {
            distance.put(new distanceObj(i, new int[]{0}), matrix[i][0]);
        }

        LinkedList<List<Integer>> subset = new LinkedList<>();
        findSubsets(subset, convertArrToArrayList(removeArrElement(vertices, new int[]{0})), new LinkedList<>(), 0);

        HashMap<Integer, ArrayList<List<Integer>>> subsetCollection = new HashMap<>();
        for (int i = 0; i <= n; i++) {
            subsetCollection.put(i, new ArrayList<>());
        }
        for (List<Integer> list : subset) {
            subsetCollection.get(list.size()).add(list);
        }

        for (int k = 1; k <= n - 2; k++) {
            System.out.println(k);
            ArrayList<List<Integer>> lists = subsetCollection.get(k);

            for (int m = 0; m < lists.size(); m++) {
                List<Integer> list = lists.get(m);
                int[] a = convertArrayListToArr(list);
                int[] iterateArr = removeArrElement(vertices, a);

                for (int i : iterateArr) {
                    if (i == 0) {
                        continue;
                    }

                    int minDistance = 999999;
                    int minJ = 999999;
                    for (int j : a) {
                        int value = matrix[i][j] + distance.get(new distanceObj(j, removeArrElement(a, new int[]{vertices[j]})));
                        if (value < minDistance) {
                            minDistance = value;
                            minJ = j;
                        }
                    }
                    distance.put(new distanceObj(i, a), minDistance);
                    opttour.put(new distanceObj(i, a), minJ);
                    o2.put(i, minJ);
                }
            }


        }

        int minDistance = 999999;
        int minJ = 999999;
        for (int j = 1; j < n; j++) {
            int value = matrix[0][j] + distance.get(new distanceObj(j, removeArrElement(vertices, new int[]{0, vertices[j]})));
            if (value < minDistance) {
                minDistance = value;
                minJ = j;
            }
        }
        distance.put(new distanceObj(0, removeArrElement(vertices, new int[]{0})), minDistance);
        opttour.put(new distanceObj(0, removeArrElement(vertices, new int[]{0})), minJ);
        o2.put(0, minJ);
        int minLength = distance.get(new distanceObj(0, removeArrElement(vertices, new int[]{0})));
        System.out.println("Optimal solution distance = " + minLength);

        // Print path
        ArrayList<Integer> path = new ArrayList<>();
        ArrayList<Integer> verticesList = convertArrToArrayList(vertices);
        int vertex = 0;
        path.add(vertex);
        while (verticesList.size() > 1) {
            verticesList.remove(new Integer(vertex));
            int[] arr = convertArrayListToArr(verticesList);
            vertex = opttour.get(new distanceObj(vertex, arr));
            path.add(vertex);
        }
        path.add(0);
        System.out.println("Path = " + path);
    }

    public static void main(String[] args) throws Exception {
//        String filePath = "/Users/drakezhou/IdeaProjects/primary/practice/src/project4/inputFiles/Project 4_Problem 2_InputData.csv";
//        int matrixSize = getSizeOfCSV(filePath);
//        int[][] matrix = importCSVToMatrix(matrixSize, filePath);
//
        int[][] matrix = new int[][]{
                {0, 14, 4, 10, 20},
                {14, 0, 7, 8, 7},
                {4, 5, 0, 7, 16},
                {11, 7, 9, 0, 2},
                {18, 7, 17, 4, 0}
        };

        dpTSP(matrix, matrix.length);

    }
}
