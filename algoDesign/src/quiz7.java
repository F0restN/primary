import sun.awt.image.ImageWatched;

import javax.xml.bind.ValidationEvent;
import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class quiz7 {

    static Object[] readMatrixFromCSV(String filePath) throws IOException {
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
        LinkedList list = new LinkedList();
        String[] stmp = null;
        for (int i = 1; i < scope; i++) {
            stmp = numbers[i].split(",");
            for (int j = 0; j < i; j++) {
                int value = Integer.parseInt(stmp[j]);
                if (value!=0){
                    list.add(value);
                } else {
                    list.add(9999999);
                }
            }
        }
        System.out.println(list.getClass());
        System.out.println(list);

        Object[] result = new Object[2];
        result[0] = list;
        result[1] = scope;
        return result;
    }

    public static void main(String args[]) throws IOException {
        // Please replace the path below to the absolute path of yours.
        Object[] resultSet = readMatrixFromCSV("/Users/drakezhou/IdeaProjects/primary/practice/src/lib/Quiz7_Input_File.csv");
        LinkedList<Integer> list = (LinkedList) resultSet[0];
        int scope = (int) resultSet[1];


        int[] distance = new int[scope];
        int[] nearest = new int[scope];

        // Assign the start point as (0,0)
        int sum = 0;
        int vnear = 0;
        for (int i = 0; i < scope; i++) {
            nearest[i] = 0;
            distance[i] = list.get((i * (i - 1)) / 2 + 0);
        }

        int count = scope;
        while (count - 1 > 0) {
            /*
            * When using LinkedList to store graphic for (x,y) n is #nodes
            * 1. x > y
            * 2. x = n - 1
            * 3. y = x - 1
            * */
            int limitation = 0;
            if (vnear == scope-1){
                limitation = scope-1;
            } else {
                limitation = scope;
            }
            /*
             * Update the distance
             * 1. Even though we only have one loop, we can still get the distance from all the other to E
             *    that's because we only update those whose distance is lower to the vnear than the other vertex in E.
             * 2. Both 0 and itself seen as infinity big which means unreachable
             * */
            for (int i = 0; i < limitation; i++) {
                int a = Math.max(vnear, i);
                int b = Math.min(vnear, i);
                if (0 < list.get((a * (a - 1)) / 2 + b) && list.get((a * (a - 1)) / 2 + b) < distance[i]) {
                    distance[i] = list.get((a * (a - 1)) / 2 + b);
                    nearest[i] = vnear;
                }
            }

            /*
             * Get the nearest vertex
             * 1. If we set E as the vertices that has been covered
             * 2. Get the minimum number, means the nearest vertex to E
             * 3. use -1 as a mark to exclude vertices in E
             * */
            int min = 9999999;
            for (int i = 1; i < scope; i++) {
                if (0 < distance[i] && distance[i] < min) {
                    min = distance[i];
                    vnear = i;
                }
            }
            System.out.println("From Node: " + (nearest[vnear]) + " To Node: " + (vnear) + " Distance is: " + distance[vnear]);
            sum += distance[vnear];

            distance[vnear] = -1;

            count--;
        }
        System.out.println("The minimum-spinning tree is: " + sum + " feet");
    }
}
