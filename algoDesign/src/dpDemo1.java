public class dpDemo1 {

    static int cost(int n){
        int min = 100;
        if(n-11>=0){
            min = Math.min(cost(n-11)+1,min);
        }
        if(n-5>=0){
            min = Math.min(cost(n-5)+1,min);
        }
        if(n-1>=0){
            min = Math.min(cost(n-1)+1,min);
        }
        if(n == 0){
            return 0;
        }
        System.out.println("n = "+n+" number is "+ min);
        return min;
    }

    public static void main(String[] args) {
        int result = cost(11);
        System.out.println("The minimum usage is: " + result);
    }
}
