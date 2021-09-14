import javax.xml.crypto.Data;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class all3ElementSubsets {

    public static void main(String[] args) {
//        System.out.println("=====CaseOne: {1,2,3,4}=====");
//        getAllSubsets(new int[]{1, 2, 3, 4,5});
//        System.out.println("=====CaseTwo: {7,3}=====");
//        getAllSubsets(new int[]{7,3});
//        System.out.println("=====CaseThree: {4, 1, 7, 4, 3, 9, 1, 5}=====");
//        getAllSubsets(new int[]{4, 1, 7, 4, 3, 9, 1, 5});

        int[] data = new int[3];
        getAllSubsetsRecu(new int[]{1,2,3,4,5},0,0, data);
    }

    public static void getAllSubsets(int[] s){
        int len = s.length;
        int[] f = new int[3];

        if (len>2){
            for(int i=0; i<len-2;i++){
                f[0] = s[i];
                for(int j=i+1;j<=len-1;j++){
                    f[1] = s[j];
                    for(int k=j+1;k<=len-1;k++){
                        f[2] = s[k];
                        System.out.println(Arrays.toString(f));
                    }
                }
            }
        } else {
            System.out.println("No subset");
        }
    }

    public static void getAllSubsetsRecu(int[] inputArr, int currIndex, int i, int[] data){
        if (currIndex == 3){
            System.out.println(Arrays.toString(data));
            return;
        }

        if(i>=inputArr.length){
            return;
        }
        data[currIndex] = inputArr[i];
        getAllSubsetsRecu(inputArr, currIndex+1, i+1, data);
        getAllSubsetsRecu(inputArr, currIndex, i+1, data);
    }

}
