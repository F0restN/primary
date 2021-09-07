import java.util.Arrays;
import java.util.LinkedList;

public class all3ElementSubsets {

    public static void main(String[] args) {
        System.out.println("=====CaseOne: {1,2,3,4}=====");
        getAllSubsets(new int[]{1, 2, 3, 4});
        System.out.println("=====CaseTwo: {7,3}=====");
        getAllSubsets(new int[]{7,3});
        System.out.println("=====CaseThree: {4, 1, 7, 4, 3, 9, 1, 5}=====");
        getAllSubsets(new int[]{4, 1, 7, 4, 3, 9, 1, 5});
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
}
