import com.sun.org.apache.xerces.internal.impl.xpath.regex.REUtil;

import java.util.Arrays;

public class quiz8 {

    static boolean isValidate(int row, int[] queen){
        for (int i = 0;i<row;i++){
            if (queen[row] == queen[i] || Math.abs(row-i) == Math.abs(queen[row]-queen[i])){
                return false;
            }
        }
        return true;
    };

    static void setNextQueen(int row, int[] queen){
        if (row >= queen.length){
            System.out.println("complete");
            System.out.println(Arrays.toString(queen));
            return;
        }

        for (int j = 0; j< queen.length;j++){
            queen[row] = j;
            if (isValidate(row, queen)){
                setNextQueen(row+1, queen);
            }
        }
    }

    public static void main(String[] args) {
        int[] queen = new int[4];
        setNextQueen(0, queen);
//        System.out.println(Arrays.toString(result));

    }

}
