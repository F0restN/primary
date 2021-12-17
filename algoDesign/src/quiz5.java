import java.util.ArrayList;

public class quiz5 {

    static void maxContiguousSublist (int[] list){
        // Special condition process
        if (list.length == 0){
            System.out.println("Due to the array is empty, so the maximum sublist is []");
            System.out.println("and the sum is: 0");
            return;
        }

        // Initialize
        int[] maxInSublist = new int[list.length];
        maxInSublist[0] = list[0];
        int result = list[0];
        int start = 0;
        int end = 0;

        //Dynamic Programming
        for (int i=1; i<list.length; i++){
            maxInSublist[i] = Math.max(maxInSublist[i-1],0)+list[i];
            if(maxInSublist[i-1]<0 && maxInSublist[i] > maxInSublist[i-1]){
                start = i;
                end = i;
            }
            if(result < maxInSublist[i]){
                result = maxInSublist[i];
                end = i;
            }
        }

        //Output
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i = start; i<=end;i++){
            arr.add(list[i]);
        }
        System.out.println("The maximum sublist is; "+arr);
        System.out.println("and the sum is: "+result);
    }

    public static void main(String[] args) {
        maxContiguousSublist(new int[]{});
        maxContiguousSublist(new int[]{1});
        maxContiguousSublist(new int[]{1,2,3,4});
        maxContiguousSublist(new int[]{-7, -4, -2, -8});
        maxContiguousSublist(new int[]{-2, 3, 5, -7});
        maxContiguousSublist(new int[]{-2, -3, 4, -1, -2, 1, 5, -3});
        maxContiguousSublist(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
    }

}
