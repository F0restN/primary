import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;

public class quiz6 {

    static int[][] readMatrixFromCSV(String filePath) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader(filePath));

        // Read by line and store.
        bf.readLine();
        String line = new String();
        String str = new String();
        while ((line = bf.readLine()) != null) {
            str += line + "/";
        }
        String[] numbers = str.split("/");

        // Convert into integer and transfer into matrix
        int scope = numbers.length;
        int[][] matrix = new int[scope][scope];
        String[] stmp = null;
        for (int i = 0; i < scope; i++) {
            stmp = numbers[i].split(",");
            for (int j = 0; j < scope; j++) {
                matrix[i][j] = Integer.parseInt(stmp[j]);
            }
        }

//        Print matrix
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }

        bf.close();
        return matrix;
    }

    public static void main(String args[]) throws IOException {
        // Please replace the path below to the absolute path of yours.
        int[][] matrix = readMatrixFromCSV("/Users/drakezhou/IdeaProjects/primary/practice/src/lib/Quiz6_Input_File.csv");

        int scope = matrix[0].length;
        int[] distance = new int[scope];
        int[] nearest = new int[scope];

        // Assign the start point as (0,0)
        int sum = 0;
        int vnear = 0;
        for (int i = 0; i < scope; i++) {
            nearest[i] = 0;
            distance[i] = matrix[0][i];
        }

        int count = scope;
        while (count - 1 > 0) {
            /*
             * Update the distance
             * 1. Even though we only have one loop, we can still get the distance from all the other to E
             *    that's because we only update those whose distance is lower to the vnear than the other vertex in E.
             * 2. Both 0 and itself seen as infinity big which means unreachable
             * */
            for (int i = 0; i < scope; i++) {
                if (0 < matrix[i][vnear] && matrix[i][vnear] < distance[i] || distance[i] == 0 && matrix[i][vnear] > 0) {
                    distance[i] = matrix[i][vnear];
                    nearest[i] = vnear;
                }
            }

            /*
             * Get the nearest vertex
             * 1. If we set E as the vertices that has been covered
             * 2. Get the minimum number, means the nearest vertex to E
             * 3. use -1 as a mark to exclude vertices in E
             * */
            int min = Integer.MAX_VALUE;
            for (int i = 1; i < scope; i++) {
                if (0 < distance[i] && distance[i] < min) {
                    min = distance[i];
                    vnear = i;
                }
            }
            sum += distance[vnear];
            System.out.println("From Node: " + (nearest[vnear]) + " To Node: " + (vnear) + " Distance is: " + distance[vnear]);

            distance[vnear] = -1;
            count--;
        }
        System.out.println("The minimum-spinning tree is: " + sum + " feet");
    }
}
