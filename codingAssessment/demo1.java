public class demo1 {

    public static void main(String[] args) {
        String str = "abc";

        // Calculate bitmask for a string
        long bitmask = 0;
        for (int j = 0; j < str.length(); j++) {
            bitmask ^= 1L<<(str.charAt(j));
        }
        for (int i = 'a'; i <= 'z'; i++) {
            System.out.println(bitmask ^ i);
        }
    }
}
