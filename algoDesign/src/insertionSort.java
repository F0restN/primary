import java.util.Arrays;

public class insertionSort {

        static int[] insertionSort(int[] sourceArray) throws Exception {
            // 对 arr 进行拷贝，不改变参数内容
            int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

            // 从下标为1的元素开始选择合适的位置插入，因为下标为0的只有一个元素，默认是有序的
            for (int i = 1; i < arr.length; i++) {

                // 记录要插入的数据
                int tmp = arr[i];

                // 从已经排序的序列最右边的开始比较，找到比其小的数
                int j = i;
                while (j > 0 && tmp < arr[j - 1]) {
                    arr[j] = arr[j - 1];
                    j--;
                }

                // 存在比其小的数，插入
                if (j != i) {
                    arr[j] = tmp;
                }
                System.out.println(Arrays.toString(arr));

            }
            return arr;
        }

    public static void main(String[] args) throws Exception {
//        insertionSort(new int[]{3,9,8,4,2,1,10,81,42,23});
        insertionSort(new int[]{6,5,4,3,2,1});
    }
}