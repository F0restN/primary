public class quiz4 {

    static void graphStatusReport(int[][] graph){
        int nodes = graph.length;
        int[][] nodeStatus = new int[nodes][nodes];

        for(int i=0; i<nodes;i++){
            int sum = 0;
            for (int j=0;j<nodes;j++){
                if (graph[i][j]>0){
                    sum++;
                }
            }
            if (sum >0){

            }
        };


    }

    public static void main(String[] args) {

    }
}
