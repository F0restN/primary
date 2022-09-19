import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class imc02 {

    public static int[] getResult(int[] arrival, int[] street){
        int[] result = new int[arrival.length];
        // Store the sequence number of car in correspondent street.
//        [0,3]   [1, 2]
//        Queue<Integer> result = new LinkedList<>();
        Queue<Integer> ms = new LinkedList<>();
        Queue<Integer> fa = new LinkedList<>();

        int count = 0;
        int prevSec = -1;
        // Time processing
        for (int currentSec = 0; count < arrival.length; currentSec++) {
            // 1. Add arrival car to correspondent Queue
            for (int n = count; n < arrival.length; n++) {
                if (arrival[n] == currentSec){
                    switch (street[n]){
                        case 0:
                            ms.add(n);
                            break;
                        case 1:
                            fa.add(n);
                            break;
                    }
                }
            }
            // 2. Determine whether there is a car should move now.
            if (fa.isEmpty() && ms.isEmpty()){
                prevSec = -1;
            } else {
                switch (prevSec) {
                    case -1:
                    case 1:
                        if (!fa.isEmpty()){
                            result[fa.poll()] = currentSec;
                            prevSec = 1;
                        } else {
                            result[ms.poll()] = currentSec;
                            prevSec = 0;
                        }
                        count ++;
                        break;
                    case 0:
                        if (!ms.isEmpty()){
                            result[ms.poll()] = currentSec;
                            prevSec = 0;
                        } else {
                            result[fa.poll()] = currentSec;
                            prevSec = 1;
                        }
                        count ++;
                        break;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arrival = {0,0,1,4};
        int[] street = new int[]{0,1,1,0};
        System.out.println(Arrays.toString(getResult(arrival, street)));
    }
}
