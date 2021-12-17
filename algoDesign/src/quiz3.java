import java.util.Arrays;

public class quiz3 {

    // divide-and-conquer
    static int binDC(int n, int k){

        if(k == 0 || n == k){
            return 1;
        } else {
            return binDC(n-1,k-1) + binDC(n-1, k);
        }
    }

    // dynamic programming
    static int binDP(int n, int k){
        int[][] b = new int[n+1][k+1];

        for(int i=1;i<=n;i++){
            for(int j=0;j<=Math.min(i,k);j++){
                if( j==0 || i==j ){
                    b[i][j] = 1;
                } else {
                    b[i][j] = b[i-1][j-1]+b[i-1][j];
                }
//                System.out.println("i = "+i+" j = "+j+" [][]= "+b[i][j]);
            }
        }
        return b[n][k];
    }

    public static void main(String[] args) {
            long startTime=System.nanoTime();
            binDP(10,5);
            long endTime=System.nanoTime();
            System.out.println("Running time is = "+(endTime-startTime));
    }
}
