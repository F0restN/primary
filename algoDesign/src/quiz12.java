import java.util.Arrays;

public class quiz12 {

    // Improved bubbleSort
    static void bubbleSort(int[] sourceArray) throws Exception {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        for (int i = 1; i < arr.length; i++) {
            boolean flag = true;

            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    arr[j] = arr[j] ^ arr[j + 1];
                    arr[j + 1] = arr[j] ^ arr[j + 1];
                    arr[j] = arr[j] ^ arr[j + 1];

                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }

        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) throws Exception {
        bubbleSort(new int[]{6,5,4,3,2,1});
    }
}
