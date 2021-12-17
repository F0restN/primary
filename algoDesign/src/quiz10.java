import java.util.ArrayList;

public class quiz10 {

    static class item {
        int profit;
        int weight;
        int per;
        int number;

        public item(int profit, int weight, int per, int number) {
            this.profit = profit;
            this.weight = weight;
            this.per = per;
            this.number = number;
        }

        @Override
        public String toString() {
            return "#"+number + " ---> " + profit + "P | "+weight+"W ";
        }
    }

    static int knapSack(int index, int profit, int weight, int maxProfit, ArrayList<item> backPack, ArrayList<item> itemList) {
        int weightLimit = 9;

        if (weight <= weightLimit && profit > maxProfit) {
            maxProfit = profit;
            System.out.println(backPack);
            System.out.println("The maximum profit for now is: " + maxProfit+" "+weight);
            System.out.println();
        }

        int currentTotalProfit = 0;
        int currentTotalWeight = 0;
        ArrayList data = new ArrayList();

        if (promising(backPack, itemList, maxProfit, index)) {
            backPack.add(itemList.get(index + 1));
            for (item item : backPack) {
                currentTotalProfit += item.profit;
                currentTotalWeight += item.weight;
            }
            maxProfit = knapSack(index + 1, currentTotalProfit, currentTotalWeight, maxProfit, backPack, itemList);
            currentTotalProfit = 0;
            currentTotalWeight = 0;

            backPack.remove(backPack.size() - 1);
            for (item item : backPack) {
                currentTotalProfit += item.profit;
                currentTotalWeight += item.weight;
            }
            maxProfit = knapSack(index + 1, currentTotalProfit, currentTotalWeight, maxProfit, backPack, itemList);
        }

        return maxProfit;
    }

    static boolean promising(ArrayList<item> backPack, ArrayList<item> itemList, int maxProfit, int index) {
        int weightLimit = 9;

        int currentTotalProfit = 0;
        int currentTotalWeight = 0;
        for (item item : backPack) {
            currentTotalProfit += item.profit;
            currentTotalWeight += item.weight;
        }

        // Branch cutting criterion #1: When weight>W
        if (currentTotalWeight >= weightLimit) {
            return false;
        }

        int k = index;
        if (backPack.isEmpty()) {
            k = index + 1;
        }
        int weight = currentTotalWeight;
        while (weight < weightLimit) {
            // Branch cutting criterion #2: When all item included in backpack
            weight += itemList.get(k).weight;
            k++;
            if (k >= itemList.size()) {
                return false;
            }
        }

        int bound = currentTotalProfit;
        for (int i = index + 1; i < k; i++) {
            item obj = itemList.get(i);
            bound += obj.profit;
            currentTotalWeight += obj.weight;
        }

        bound += (weightLimit - currentTotalWeight) * itemList.get(k).per;

        // Branch cutting criterion #3: When bound is less than maxProfit.
        return bound > maxProfit;
    }

    public static void main(String[] args) {
        ArrayList<item> backPack = new ArrayList<>();
        ArrayList<item> itemList = new ArrayList<>();

        itemList.add(new item(20,2,10,1));
        itemList.add(new item(30,5,6,2));
        itemList.add(new item(35,7,5,3));
        itemList.add(new item(12,3,4,4));
        itemList.add(new item(3,1,3,5));

        knapSack(-1, 0, 0, 0, backPack, itemList);
    }

}
