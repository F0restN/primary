import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class binarySearch {
    public static void main(String args[]) {
       binarySearch b = new binarySearch();
       b.method1();
    }

    public static long getTime(List list) {
        long time = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            int index = Collections.binarySearch(list, list.get(i));
            if (index != i) {
                System.out.println("ERROR!");
            }
        }
        return System.currentTimeMillis() - time;
    }


    //  Hand write binarySearch
    public void method1 () {
        int n = 6;
        int[] s = {3, 5, 6, 9, 10, 12};
        int x = 12;

        int index = 0;
        int low = 1;
        int high = s.length;

        while (low <= high && index == 0) {
            System.out.println("Attempt: Once");
            int mid = (low + high) / 2;
            System.out.println(mid);
            if (x == s[mid]) {
                index = mid;
            } else if (x < s[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        System.out.println("The location of target number is : " + (index + 1));
    }

    public void method2() {
        ArrayList arr = new ArrayList();
        for (int i=0;i<=1000;i++){
            arr.add(i);
        }


    }



}