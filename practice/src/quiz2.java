import java.util.ArrayList;

public class quiz2 {
    public static void main(String[] args) {
        int[][] case1 = new int[][]{
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        System.out.println(divide(5,case1));
        System.out.println();

        int[][] case2 = new int[][]{
                {2 , 4 , 9 , 14, 14, 15, 18},
                {21, 27, 31, 35, 42, 45, 50},
                {54, 59, 64, 69, 82, 84, 84}
        };
        System.out.println(divide(45,case2));
        System.out.println();

        int[][] case3 = new int[][]{
                {3   , 15  , 21  , 24  , 83  , 87  , 88  , 93  },
                {178 , 319 , 541 , 669 , 770 , 793 , 848 , 970 },
                {1439, 1546, 1853, 2124, 2234, 2459, 2518, 2978},
                {3111, 3406, 3490, 3669, 3796, 3936, 4112, 4776},
                {5277, 5667, 6067, 6555, 7890, 8056, 8234, 9968}
        };
        System.out.println(divide(2356, case3));
        System.out.println();
    }

    // If the maximum number of a row still < target, means target can't be in this row, Starts from row #0
    // If the minimum number of a col still > target, means target can't be in this col, Starts from the last column
    static String divide(int target, int[][] arr){
        int col = arr[0].length;
        int row = arr.length;

        int n = 0;
        int m = col -1;

        // Use the maximum and minimum to determine if target is possibly included.
        if(arr[0][0]> target || arr[row-1][col-1]<target){
            return "No included";
        } else {
            while (n<row && m>=0){
                printMatrix(n,m,arr);

                if (arr[n][m]<target){
                    n++;
                } else if(arr[n][m]>target) {
                    m--;
                } else {
                    return "The number you are finding is " + arr[n][m]+" is in ["+ (n+1) +","+ (m+1) +"]";
                }
            }
            return "Not included";
        }
    }

    static void printMatrix(int n,int m, int[][]arr){
        for(int i = n;i<arr.length;i++){
            for(int j=0;j<=m;j++){
                System.out.print(arr[i][j]+", ");
            }
            System.out.println();
        }
        System.out.println("====================");
    }
}
