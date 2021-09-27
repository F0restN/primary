import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class project1 {

    static void ques1 (int[]a, int m, int n){
        System.out.println("HelloWorld");
        if (a[m] <= a[m+1]){
            System.out.println("This array is sorted in ascendant order");
            System.out.println(Arrays.toString(a));
            return;
        }

        if(n-(m+1) <= m-0){
            // Situation #1: Minor array is the later one,
            int[] aux = Arrays.copyOfRange(a,m+1,n+1);

            // minCursor = end point of minor array & majCursor == end point of major array
            int mainCursor = a.length-1;
            int minCursor = aux.length-1;
            int majCursor = m;
            while (majCursor>=0 && minCursor>=0){
                if (a[majCursor] > aux[minCursor]){
                    a[mainCursor] = a[majCursor];
                    majCursor--;
                } else {
                    a[mainCursor] = aux[minCursor];
                    minCursor--;
                }
                mainCursor --;
                System.out.println(Arrays.toString(a));
            }
            if (minCursor >=0){
                System.arraycopy(aux,0,a,0,minCursor-0+1);
            }
            System.out.println(Arrays.toString(a));

        } else {
            // Situation #2: Minor array is the former one,
            int[] aux = Arrays.copyOfRange(a,0,m+1);

            // minCursor = start point of minor array & majCursor == start point of major array
            int mainCursor = 0;
            int minCursor = 0;
            int majCursor = m+1;
            while (majCursor<=n && minCursor<=aux.length-1){
                if (a[majCursor] < aux[minCursor]){
                    a[mainCursor] = a[majCursor];
                    majCursor++;
                } else {
                    a[mainCursor] = aux[minCursor];
                    minCursor++;
                }
                mainCursor ++;
                System.out.println(Arrays.toString(a));
            }
            if (minCursor <= aux.length-1){
                System.arraycopy(aux,minCursor,a,mainCursor,minCursor-aux.length+1);
            }
            System.out.println(Arrays.toString(a));
        };
    }

    static void alaCart(int a,int b){
        ArrayList<Integer> list = new ArrayList<Integer>();
        int min = Math.min(a,b);
        int max = Math.max(a,b);
        int sum = 0;
        while (min >= 1){
            if (min % 2 != 0){
                sum = sum + max * 2;
            }
            min = min / 2;
        }
    }


    public static void main(String[] args) {
        // Array A = [0...m][m+1...n]
        // m & n are index of A
//        int[] a;
//        a = new int[]{3,7,9};
//        ques1(a,0,2);
//
//        a= new int[]{2,7,9,1};
//        ques1(a,2,3);
//
//        a = new int[]{1,7,10,15,3,8,12,18};
//        ques1(a,3,7);
//
//        a = new int[]{1, 3, 5, 5, 15, 18, 21, 5, 5, 6, 8, 10, 12, 16, 17, 17, 20, 25, 28};
//        ques1(a,6, 18);

        System.out.println(100,8);
    }
}
