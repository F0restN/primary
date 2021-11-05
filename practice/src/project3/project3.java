package project3;

import java.io.IOException;
import java.util.*;

public class project3 {

    static int find(int[] list, int i) {
        while (list[i] > 0) {
            i = list[i];
        }
        return i;
    }

    static void kruskal() {
        // Get matrix

        int[][] matrix = new int[0][];
        try {
            matrix = readMatrixFromCSV.readCSV();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        int[][] matrix = new int[][]{
//                {0, 1, 3, 99, 99},
//                {1, 0, 3, 6 , 99},
//                {3, 3, 0, 4 , 2 },
//                {99, 6, 4, 0, 5 },
//                {99, 99, 2, 5, 0}
//        };

        // Retrieve
        ArrayList<edge> matrixList = new ArrayList<edge>();
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < i; j++) {
                edge e = new edge(j, i, matrix[j][i]);
                matrixList.add(e);
            }
        }

        // sort
        Collections.sort(matrixList, new Comparator<edge>() {
            @Override
            public int compare(edge o1, edge o2) {
                if (o1.len < o2.len) {
                    return -1;
                } else if (o1.len == o2.len) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
//        System.out.println(matrixList.toString());

        // Kruskal Algo
        int sum = 0;
        int[] containedVertex = new int[matrix.length];
        ArrayList<edge> minSpinTree = new ArrayList<edge>();
        Iterator<edge> it = matrixList.iterator();
        try {
            while (it.hasNext()) {
                edge e = it.next();
                int p = find(containedVertex, e.getStartPoint());
                int q = find(containedVertex, e.getEndPoint());
                if (q != p) {
                    minSpinTree.add(e);
                    containedVertex[p] = q;
                    sum += e.getLen();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        minSpinTree.forEach( ele -> System.out.println(ele));
        System.out.println(sum);
    }

    public static void main(String[] args) {
        kruskal();
    }
}
