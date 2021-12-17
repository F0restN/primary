import java.util.ArrayList;
import java.util.Scanner;

public class fibRecursive {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("The number of fib: ");
        System.out.println(fib(sc.nextInt()-1));
        fibInRecur(5);
    }

    // Recursive
    public static int fib(int n){
        if (n>1) {
           int a = fib(n-1);
           int b = fib(n-2);
//          System.out.println("n-1: "+ a + " ||| "+"n-2: "+b);
           return a + b;
        } else {
            return n;
        }
    };

    public static void fibInRecur(int n){
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i = 0;i<n;i++){
            list.add(fib(i));
        }
        System.out.println(list);
    }
}
