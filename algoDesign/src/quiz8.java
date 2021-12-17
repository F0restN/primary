import com.sun.org.apache.xerces.internal.impl.xpath.regex.REUtil;

import java.util.Arrays;

public class quiz8 {

    static boolean isValidate(int row, int[] queen) {
        for (int i = 0; i < row; i++) {
            if (queen[row] == queen[i] || Math.abs(row - i) == Math.abs(queen[row] - queen[i])) {
                return false;
            }
        }
        return true;
    }


    /* // Another structure
    static int setNextQueen(int count, int row, int[] queen){
        if (row < queen.length){
            for (int j = 0; j< queen.length;j++){
                queen[row] = j;
                if (isValidate(row, queen)){
                    count = setNextQueen(count,row+1, queen);
                }
            }
        } else {
            count++;
            System.out.println(Arrays.toString(queen));
        }
        return count;
    }*/

    static int setNextQueen(int count, int row, int[] queen) {
        if (row >= queen.length) {
            count++;
//            System.out.println(Arrays.toString(queen));
            return count;
        }

        for (int j = 0; j < queen.length; j++) {
            queen[row] = j;
            if (isValidate(row, queen)) {
                count = setNextQueen(count, row + 1, queen);
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int count = 0;

        int[] queen = new int[4];
        System.out.println("When N = 4");
        System.out.println(setNextQueen(count, 0, queen));

        queen = new int[8];
        System.out.println("When N = 8");
        System.out.println(setNextQueen(count, 0, queen));

        queen = new int[10];
        System.out.println("When N = 10");
        System.out.println(setNextQueen(count, 0, queen));

        queen = new int[12];
        System.out.println("When N = 12");
        System.out.println(setNextQueen(count, 0, queen));
    }

}
