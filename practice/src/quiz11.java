import java.io.Serializable;
import java.util.*;

public class quiz11 {

    static class node implements Serializable, Cloneable {
        int level;
        int profit;
        int weight;
        int bound;
        ArrayList<Integer> itemList;

        public node(int level, int profit, int weight) {
            this.level = level;
            this.profit = profit;
            this.weight = weight;
            this.itemList = new ArrayList<>();
        }

        @Override
        public String toString() {
            return "node{" +
                    "level=" + level +
                    ", profit=" + profit +
                    ", weight=" + weight +
                    ", bound=" + bound +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            node node = (node) o;
            return level == node.level && profit == node.profit && weight == node.weight && bound == node.bound;
        }

        @Override
        public int hashCode() {
            return Objects.hash(level, profit, weight, bound);
        }

        @Override
        public node clone() throws CloneNotSupportedException {
            return (node) super.clone();
        }
    }

    static int boundComputation(int[] p, int[] w, int weightLimitation, node v) {
        int currentWholeWeight = v.weight;

        if (currentWholeWeight >= weightLimitation) {
            return 0;
        } else {
            int n = w.length - 1;
            int j = v.level + 1;
            int currentProfit = v.profit;

            while (j <= n && currentWholeWeight + w[j] < weightLimitation) {
                currentWholeWeight += w[j];
                currentProfit += p[j];
                j++;
            }

            int k = j;

            if (k <= n) {
                currentProfit += (weightLimitation - currentWholeWeight) * (p[k] / w[k]);
            }

            return currentProfit;
        }
    }

    static void knapSack3(int n, int[] p, int[] w, int weightLimitation) throws CloneNotSupportedException {
        //
        int counter = 1;
        node bestNode = null;

        // Initialization
        PriorityQueue<node> pq = new PriorityQueue(n, new Comparator<node>() {
            @Override
            public int compare(node o1, node o2) {
                return -(o1.bound - o2.bound);
            }
        });
        node v = new node(0, 0, 0);
        node u = new node(0, 0, 0);
        int maxProfit = 0;
        v.bound = boundComputation(p, w, weightLimitation, v);
        pq.add(v.clone());

        while (!pq.isEmpty()) {
            v = pq.poll();
            if (v.bound > maxProfit) {
                counter++;
                u.level = v.level + 1;

                u.weight = v.weight + w[u.level];
                u.profit = v.profit + p[u.level];
                u.itemList = (ArrayList<Integer>) v.itemList.clone();
                u.itemList.add(u.level);

                if (u.weight <= weightLimitation && u.profit > maxProfit) {
                    maxProfit = u.profit;
                    bestNode = u.clone();
                }
                u.bound = boundComputation(p, w, weightLimitation, u);

                if (u.bound > maxProfit) {
                    pq.add(u.clone());
                }

                counter++;
                u.weight = v.weight;
                u.profit = v.profit;
                u.bound = boundComputation(p, w, weightLimitation, u);
                u.itemList = (ArrayList<Integer>) v.itemList.clone();
                if (u.bound > maxProfit) {
                    pq.add(u.clone());
                }
            }
        }

        System.out.println("The maximum profit is "+maxProfit);
        System.out.println(counter + " of nodes visited before reach the most optimal solution");
        assert bestNode != null;
        System.out.println("The most oprtimal solution contains item " + bestNode.itemList);
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        int[] p = new int[]{0,20,30,35,12,3};
//        int[] p = new int[]{0, 40, 30, 50, 10};
        int[] w = new int[]{0,2,5,7,3,1};
//        int[] w = new int[]{0, 2, 5, 10, 5};
        knapSack3(5,p,w,13);
//        knapSack3(4,p,w,16);

//        boundComputation(p, w, 16, new node(2, 70, 7));
    }
}
