package project4.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static project4.utils.convert.convertArrToArrayList;

public class testsubset {

    public static void findSubsets(List<List<Integer>> subset, ArrayList<Integer> nums, ArrayList<Integer> output, int index) {
        if (index == nums.size()) {
            subset.add(output);
            return;
        }

        findSubsets(subset, nums, new ArrayList<>(output), index + 1);
        output.add(nums.get(index));
        findSubsets(subset, nums, new ArrayList<>(output), index + 1);
    }

    public static void main(String[] args) {

        ArrayList<Integer> num = new ArrayList<>();
        num.add(1);
        num.add(2);
        num.add(3);
        num.add(4);
        num.add(5);
        List<List<Integer>> subset = new ArrayList<>();
        findSubsets(subset, num, new ArrayList<>(), 0);
        HashMap<Integer, ArrayList<List<Integer>>> subsetCollection = new HashMap<>();
        for (int i = 0; i <= num.size(); i++) {
            subsetCollection.put(i, new ArrayList<>());
        }
        for (List<Integer> list : subset) {
            subsetCollection.get(list.size()).add(list);
        }
        System.out.println(subsetCollection);
    }
}
