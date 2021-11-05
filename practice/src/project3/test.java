package project3;

import java.util.Arrays;
import java.util.HashMap;

public class test {

    public static void main(String[] args) {
//        HashMap map = new HashMap();
//
//        edge a = new edge(0,1,503.22,401.11, 10);
//        edge b = new edge(0,1,503.22,401.11, 30);
//        edge c = new edge(0,1,101.22,401.11, 10);
//
//        HashMap v = new HashMap();
//        v.put(101.11,102.11);
//
//        map.put(a, 10);
//        map.put(b, 10);
//        map.put(c, 20);
//
//        System.out.println(a == b);
//        System.out.println(a.equals(b));
//        System.out.println(map);
//
//        String co = "(1342810.7499975562, 410206.0003773868)";
//        String[] kk = co.split("[(, )]+");
////        System.out.println(Arrays.toString(kk));
//        for (String s : kk) {
//            System.out.println(s);
//        }

        HashMap<vertex, Integer> vertics = new HashMap<vertex, Integer>();
        vertics.put(new vertex(101.11,202.22,10),30);
        vertics.put(new vertex(101.11,203.22,20),20);
        vertics.put(new vertex(201.11,302.22,1),0);
        vertics.put(new vertex(301.11,402.22,2),0);

        System.out.println(vertics.get(new vertex(101.11,202.22)));

        vertics.forEach((k,v) ->
                System.out.println(k));
    }

}
