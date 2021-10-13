import java.io.*;
import java.util.Arrays;

public class quiz6 {

    static int[][] readMatrixFromCSV(String filePath) throws IOException {

        InputStream inputStream = quiz4.class.getResourceAsStream(filePath);
        BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));

        // Read by line and store.
        bf.readLine();
        String line = new String();
        String str = new String();
        while((line = bf.readLine()) != null) {
            str += line + "/";
        }
        String[] numbers = str.split("/");

        // Convert into integer and transfer into matrix
        int scope = numbers.length;
        int[][] matrix = new int[scope][scope];
        String[] stmp = null;
        for(int i = 0; i < scope; i++) {
            stmp = numbers[i].split(",");
            for(int j = 0; j < scope; j++) {
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

    public static void main (String args[]) throws IOException {
        readMatrixFromCSV("lib/Quiz6_Input_File.csv");

    }
}
