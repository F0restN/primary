import java.io.*;
import java.util.Arrays;

public class quiz6 {
    static int[][] readMatrixFromTxt(String filePath) throws IOException {

        InputStream inputStream = quiz6.class.getResourceAsStream(filePath);
        BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));

        // Read by line and store.
        bf.readLine();
        String line = new String();
        String str = new String();
        while((line = bf.readLine()) != null) {
            str += line + "|";
        }
        String[] numbers = str.split("|");

        // Convert into integer and transfer into matrix
        int[][] matrix = new int[numbers.length][numbers.length];
        String[] stmp = null;
        for(int i = 0; i < numbers.length; i++) {
            stmp = numbers[i].split(" ");
            for(int j = 0; j < numbers.length; j++) {
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
//        try {
//            BufferedReader reader = new BufferedReader(new FileReader("/Users/drakezhou/IdeaProjects/primary/practice/src/lib/Quiz6_Input_File.csv"));
//            reader.readLine();
//            String line = null;
//            while((line=reader.readLine())!=null){
//                String item[] = line.split("");//CSV格式文件为逗号分隔符文件，这里根据逗号切分
//
//                String last = item[item.length-1];//这就是你要的数据了
////                int value = Integer.parseInt(last);//如果是数值，可以转化为数值
//                System.out.println(last);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        int[][] a = readMatrixFromTxt("/lib/Quiz6_Input_File.csv");

    }
}
