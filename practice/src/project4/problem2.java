package project4;

import sun.security.util.Length;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

import static jdk.nashorn.internal.objects.ArrayBufferView.length;

public class problem2 {

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
        ArrayList<Integer> list = new ArrayList<Integer>(node.path);
        int nodeNumber = list.get(list.size() - 1);
        int min = Integer.MAX_VALUE;
        int[] nodeArr = matrix[nodeNumber];
        for (int i : nodeArr) {
            if (i < min && !list.contains(i)) {
                min = i;
            }
        }
        return min;
    }

    static int boundCompute(int[][] matrix, node node) {

        int bound = 0;
        ArrayList<Integer> list = new ArrayList<>(node.path);
        ArrayList<int[]> tempMatrix = new ArrayList<>(Arrays.asList(matrix));

        for (Integer pastNode : list) {
            int min = Integer.MAX_VALUE;
            int[] nodeArr = matrix[pastNode];
            tempMatrix.remove(nodeArr);
            for (int i : nodeArr) {
                if (i < min && !list.contains(i)) {
                    min = i;
                }
            }

            bound += min;
        }

        return bound;
    }



    static void TSP(int n, int[][] matrix, ArrayList opttour) throws CloneNotSupportedException {

        // initialize
        PriorityQueue<node> pq = new PriorityQueue<>(n, new Comparator<node>() {
            @Override
            public int compare(node o1, node o2) {
                return -(o1.bound - o2.bound);
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
                v.level = v.level + 1;
                for (int i = 1; i < n; i++) {
                    if (!v.path.contains(i)) {
                        u.path = (ArrayList<Integer>) v.path.clone();
                        u.path.add(i);

                        if (u.level == n - 2) {
                            u.path.add(findLeftOne(matrix, u.path));
                            u.path.add(1);

                            if (lengthCompute(matrix, u) < minLength) {
                                minLength = lengthCompute(matrix, u);
                                opttour = (ArrayList) u.path.clone();
                            }

                        } else {
                            u.bound = boundCompute(matrix, u);
                            if (u.bound < minLength) {
                                pq.add(u);
                            }
                        }

                    }


                }
            }


        }
    }


}


