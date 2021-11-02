import java.lang.reflect.Array;
import java.util.ArrayList;

public class quiz9 {

    static int subset(int totalNub, int[] set, int count, ArrayList sub) {
        int len = set.length;
        // Cutting branch criterion
        if (count > len) {
            return totalNub;
        }

        // Backtracking
        for (int i = count; i < len; i++) {
            sub.add(set[i]);
            // Left node (means choose)
            totalNub = subset(totalNub, set, i + 1, sub);
            // Backtracking to last node and apply to right node(means do not choose)
            sub.remove(sub.size()-1);
        }

        totalNub++;
        System.out.println(sub);
        return totalNub;
    }

    public static void main(String[] args) {
        int[] subset = new int[]{1, 2, 3};
        System.out.println("Total Number is: "+ subset(0, subset, 0, new ArrayList()));

        subset = new int[]{1,2,3,4,5};
        System.out.println("Total Number is: "+ subset(0, subset, 0, new ArrayList()));

        subset = new int[]{1,2,3,4,5,6,7};
        System.out.println("Total Number is: "+ subset(0, subset, 0, new ArrayList()));
    }
}
