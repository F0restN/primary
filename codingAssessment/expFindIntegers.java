import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class expFindIntegers {
    public static List<Integer> getFinalResult(List<Integer> arr, int k){
        List<Integer> ans = new ArrayList<>();
        for (int i = k; i <arr.size(); i++) {
            // Add element to heap
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(i, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2-o1;
                }
            });
            for (int j = 0; j < i; j++) {
                maxHeap.offer(arr.get(j));
            }

            // Pop until the corresponding element
            int temp = 0;
            int count = 1;
            while(!maxHeap.isEmpty() && count <= k){
                temp = maxHeap.poll();
                count++;
            }
            ans.add(temp);
        }

        return ans;
    }

    public static void main(String[] args) {

        List<Integer> arr = new ArrayList<>();
        arr.add(5);
        arr.add(1);
        arr.add(3);
        arr.add(2);
        arr.add(4);
        arr.add(9);

        System.out.println(getFinalResult(arr, 2));
    }
}
