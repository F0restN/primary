import java.util.ArrayList;

public class quiz13 {

    static int calculateDistance(ArrayList<Integer> solution) {
        int count = 0;
        for (Integer step : solution) {
            count += step;
        }
        return count;
    }

    static void robot(ArrayList<Integer> solution, int[] distance, int w, int count, int lastPoint) {
        if (count < w) {
            for (int i = lastPoint; i < distance.length; i++) {
                solution.add(distance[i]);
                robot(solution, distance, w, calculateDistance(solution), i);
                solution.remove(solution.size() - 1);
            }
        }

        if (count == w) {
            System.out.println(solution);
        }
    }

    public static void main(String[] args) {
        int[] distance = new int[]{1, 2, 3};
        ArrayList<Integer> solution = new ArrayList<>();
        for (int w = 1; w <= 5; w++) {
            System.out.println("When N=" + w);
            robot(solution, distance, w, 0, 0);
            System.out.println();
        }
    }
}
