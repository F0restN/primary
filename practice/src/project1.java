import java.lang.reflect.Array;
import java.math.BigInteger;
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

    static void alaCart(BigInteger a, BigInteger b){
        BigInteger absA = a.abs();
        BigInteger absB = b.abs();

        BigInteger min;
        BigInteger max;

        int result = absA.compareTo(absB);
        if (result == 1){
            min = absB;
            max = absA;
        } else {
            min = absA;
            max = absB;
        }

        BigInteger sum = new BigInteger("0");
        boolean flag = false;
        int e = min.compareTo(new BigInteger("1"));
        //Ala Carte
        int sig;
        while (min.compareTo(new BigInteger("1")) >= 0) {
            if (min.mod(new BigInteger("2")).compareTo(new BigInteger("0")) != 0){
                sum = sum.add(max);
            }
            min = min.divide(new BigInteger("2"));
            max = max.multiply(new BigInteger("2"));
        }

        // Determine if its negative or positive
        int sig1 = a.signum();
        int sig2 = b.signum();
        if (sig1 + sig2 == 0){
            sum = sum.multiply(new BigInteger("-1"));
        }

        System.out.println(sum);
        return;
    }

    static void rectangularMulti(String a, String b){


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

          alaCart(new BigInteger("7000"),new BigInteger("7294"));
          alaCart(new BigInteger("25"), new BigInteger("5038385"));
          alaCart(new BigInteger("-59724"), new BigInteger("783"));
          alaCart(new BigInteger("8516"), new BigInteger("-82147953548159344"));
          alaCart(new BigInteger("45952456856498465985"), new BigInteger("98654651986546519856"));
          alaCart(new BigInteger("-45952456856498465985"), new BigInteger("-98654651986546519856"));

    }
}
