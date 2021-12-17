package project4.utils;

import project4.problem3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class convert {

    public static ArrayList<Integer> convertArrToArrayList (int[] arr){
        ArrayList<Integer> copy = new ArrayList<>();
        for (int i : arr) {
            copy.add(i);
        }
        return copy;
    }

    public static int[] convertArrayListToArr (List<Integer> list){
        int[] convert = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            convert[i] = list.get(i);
        }

        return convert;
    }

    public static int[] convertMatrixToList (int[][] matrix) {

        int scope = matrix[0].length;

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i < scope; i++) {
            for (int j = 0; j <= i - 1; j++) {
                list.add(matrix[i][j]);
            }
        }

        return convertArrayListToArr(list);
    }

//    static ArrayList<problem3.node> deepClone(ArrayList<problem3.node> origin) throws CloneNotSupportedException {
//        ArrayList<problem3.node> copy = new ArrayList<>();
//
//        Iterator<problem3.node> iterator = origin.iterator();
//        while(iterator.hasNext()){
//            copy.add((problem3.node) iterator.next().clone());
//        }
//
//        return copy;
//    }
}
