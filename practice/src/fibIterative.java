import javax.lang.model.type.ArrayType;
import java.lang.reflect.Array;
import java.util.Arrays;


public class fibIterative {

    public static void main(String[] args) {
        fib(5);
    }

    public static void fib(int n) {
        int[] f = new int[n];
        f[0] = 0;
        f[1] = 1;
        for (int i = 2; i < f.length; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        System.out.println("The total list is : " + Arrays.toString(f));
        System.out.println("The "+n+"th number is : "+ f[n-1]);
    }
}
