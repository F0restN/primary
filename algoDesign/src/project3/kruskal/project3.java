package project3.kruskal;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.*;

public class project3 {

    static int find(int[] list, int i) {
        while (list[i] > 0) {
            i = list[i];
        }
        return i;
    }

    static void kruskalArray() {
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

        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage memoryUsage = ((MemoryMXBean) memoryMXBean).getHeapMemoryUsage();

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
        System.out.println("Total is : "+sum);
        long usedMemorySize = memoryUsage.getUsed();
        System.out.println(usedMemorySize);
    }

    static void kruskalLinkedList() throws IOException {
        // Get matrix
//        int[][] matrix = new int[][]{
//            {0, 1, 3, 99, 99},
//            {1, 0, 3, 6 , 99},
//            {3, 3, 0, 4 , 2 },
//            {99, 6, 4, 0, 5 },
//            {99, 99, 2, 5, 0}
//        };

        int[][] matrix = new int[0][];
        ArrayList<Integer> matrixLinkedList = new ArrayList();
        matrix = readMatrixFromCSV.readCSV();
        matrixLinkedList = readMatrixFromCSV.transferFromMatrixToLinkedList(matrix);
//        System.out.println(matrixLinkedList);

        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage memoryUsage = ((MemoryMXBean) memoryMXBean).getHeapMemoryUsage();

        // Retrieve
        ArrayList<edge> matrixList = new ArrayList<edge>();
        for (int i = 1; i < matrix[0].length; i++) {
            for (int j = 0; j < i; j++) {
                edge e;
                if (i>=j){
                    e = new edge(j, i, matrixLinkedList.get((i*(i-1))/2+j));
                } else {
                    e = new edge(j, i, matrixLinkedList.get((j*(j-1))/2+i));
                }
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
        int[] containedVertex = new int[matrixLinkedList.size()];
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
        System.out.println("Total is : "+sum);
        long usedMemorySize = memoryUsage.getUsed();
        System.out.println("Memory Usage： "+usedMemorySize);
    }

    public static void main(String[] args) throws IOException {
//        kruskalArray();
        kruskalLinkedList();
    }
}
