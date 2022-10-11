import java.util.HashMap;
import java.util.Set;

public class sig01 {

    public static HashMap<Integer, Integer> mapAppend (HashMap<Integer, Integer> originalMap, int targetKey, int targetValue, int appendKey, int appendValue){
        if (appendKey != 0 || appendValue != 0){
            HashMap<Integer, Integer> resultMap = new HashMap<>();
            Set<Integer> keySet = originalMap.keySet();
            for (Integer i: keySet) {
                resultMap.put(i + appendKey, originalMap.get(i) + appendValue);
            }
            return resultMap;
        }
        return originalMap;
    }

//    map manipulate = 1. append value 2. insert object.
    public static long solution (String[] queryType, int[][] query){
        long sum = 0;
        int appendValue = 0;
        int appendKey = 0;
        HashMap<Integer, Integer> targetMap = new HashMap<>();

        for (int i = 0; i < queryType.length; i++) {
            String currQueryType = queryType[i];
            switch (currQueryType){
                case "insert":
                    targetMap = mapAppend(targetMap, query[i][0], query[i][1], appendKey, appendValue);
                    targetMap.put(query[i][0], query[i][1]);
                    appendKey = 0;
                    appendValue = 0;
                    break;
                case "addToValue":
                    appendValue = targetMap.isEmpty() ? 0 : query[i][0];
                    break;
                case "addToKey":
                    appendKey = targetMap.isEmpty() ? 0 : query[i][0];
                    break;
                case "get":
                    targetMap = mapAppend(targetMap, 0, 0, appendKey, appendValue);
                    sum += targetMap.get(query[i][0]);
                    appendKey = 0;
                    appendValue = 0;
                    break;
            }
            System.out.println(currQueryType);
            System.out.println(targetMap.toString());
        }
        return sum;
    }

    public static void main(String[] args) {
        String[] queryType = new String[]{"insert","insert","addToValue","addToKey","get"};
        int[][] query = new int[][]{{1,2},{2,3},{2},{1},{3}};
        System.out.println(solution(queryType, query));
    }

}
