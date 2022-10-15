import java.util.HashMap;

public class expComplementaryPairs {

    public static long countComplementaryPairs(String[] stringData){
        long ans = 0;
        HashMap<Long, Long> map = new HashMap<>();
        for (String str : stringData) {
            // Calculate bitmask for a string
            long bitmask = 0;
            for (int j = 0; j < str.length(); j++) {
                bitmask ^= 1L << (str.charAt(j) - 'a');
            }

            // Check whether  this string is contained in map
            // If yes, then total pairs +1
            long flag = 0;
            flag = map.getOrDefault(bitmask, 0L);
            if (flag > 0) {
                ans += flag;
                map.put(bitmask, flag + 1);
            } else {
                // if not, check with each alphabet
                for (int j = 0; j <= 25; j++) {
                    long tempBitmask = bitmask ^ (1L << j);
                    flag = map.getOrDefault(tempBitmask, 0L);
                    if (flag > 0) {
                        ans += flag;
                        break;
                    }
                }
                map.put(bitmask, 1L);
            }
        }
        System.out.println(map.toString());
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(countComplementaryPairs(new String[]{"abc", "cba", "bac", "kbac", "eabc", "ewrq", "eqrw"}));
    }
}
