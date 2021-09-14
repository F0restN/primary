import java.util.Arrays;

public class demo {

    public static void main(String args[]) {
        int result = function(4);
        System.out.println("The step of moving X items is: " + result );
    }

    /*
    * @Description: Tower of Hanoi
    * @param: x is the item of stacks
    * @Date: 09/14/2021
    *
    * */
    static int function(int x){
        int step;
        if ( x == 2) {
            step = 3;
        } else {
            step = 2 * function(x-1) + 1;
        }
        return step;
    }

}
