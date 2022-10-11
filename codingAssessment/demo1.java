public class demo1 {

    public static void main(String[] args) {
        int[] test = new int[]{0,1,2,3,4,5,6,7,8};
        for (int i = 0; i < test.length; i++) {
            int value = test[i];
            System.out.println(3*(value/3));
        }
        System.out.println();
        for (int i = 0; i < test.length; i++) {
            int a = test[i];
            System.out.println(a);
        }
    }
}
