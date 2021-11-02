import java.lang.reflect.Array;
import java.util.ArrayList;

public class quiz9 {

    public ArrayList<ArrayList> ans = new ArrayList();

    public void subset(int[] set, int count, ArrayList sub) {
        int len = set.length;
        if (count > len) {
            return;
        }

        for (int i = count; i < len; i++) {
            sub.add(set[i]);
            subset(set, i + 1, sub);
            sub.remove(sub.size()-1);
        }
        System.out.println(sub);
        ans.add(new ArrayList(sub));
    }

    public static void main(String[] args) {
        quiz9 qz = new quiz9();

        int[] subset = new int[]{1, 2, 3};
        ArrayList sub = new ArrayList();
        qz.subset(subset, 0, sub);
        System.out.println(qz.ans);
        for (ArrayList list: qz.ans) {
            System.out.println(list);
        }
    }
}
