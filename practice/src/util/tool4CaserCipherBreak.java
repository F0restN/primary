package util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class tool4CaserCipherBreak {

    public static void main(String[] args) {
        // Step 0 : Initialize the crypto map and bring in the cipher text.

            // Initialize the crypto map
        HashMap<String, Integer> cryptoMap = new HashMap<>();
        {
            cryptoMap.put("A", 0);
            cryptoMap.put("B", 1);
            cryptoMap.put("C", 2);
            cryptoMap.put("D", 3);
            cryptoMap.put("E", 4);
            cryptoMap.put("F", 5);
            cryptoMap.put("G", 6);
            cryptoMap.put("H", 7);
            cryptoMap.put("I", 8);
            cryptoMap.put("J", 9);
            cryptoMap.put("K", 10);
            cryptoMap.put("L", 11);
            cryptoMap.put("M", 12);
            cryptoMap.put("N", 13);
            cryptoMap.put("O", 14);
            cryptoMap.put("P", 15);
            cryptoMap.put("Q", 16);
            cryptoMap.put("R", 17);
            cryptoMap.put("S", 18);
            cryptoMap.put("T", 19);
            cryptoMap.put("U", 20);
            cryptoMap.put("V", 21);
            cryptoMap.put("W", 22);
            cryptoMap.put("X", 23);
            cryptoMap.put("Y", 24);
            cryptoMap.put("Z", 25);
        }
        HashMap<Integer, String> cryptoMapNub2Char = new HashMap<>();
        {
            cryptoMapNub2Char.put(0,"A");
            cryptoMapNub2Char.put(1,"B");
            cryptoMapNub2Char.put(2,"C");
            cryptoMapNub2Char.put(3,"D");
            cryptoMapNub2Char.put(4,"E");
            cryptoMapNub2Char.put(5,"F");
            cryptoMapNub2Char.put(6,"G");
            cryptoMapNub2Char.put(7,"H");
            cryptoMapNub2Char.put(8,"I");
            cryptoMapNub2Char.put(9,"J");
            cryptoMapNub2Char.put(10,"K");
            cryptoMapNub2Char.put(11,"L");
            cryptoMapNub2Char.put(12,"M");
            cryptoMapNub2Char.put(13,"N");
            cryptoMapNub2Char.put(14,"O");
            cryptoMapNub2Char.put(15,"P");
            cryptoMapNub2Char.put(16,"Q");
            cryptoMapNub2Char.put(17,"R");
            cryptoMapNub2Char.put(18,"S");
            cryptoMapNub2Char.put(19,"T");
            cryptoMapNub2Char.put(20,"U");
            cryptoMapNub2Char.put(21,"V");
            cryptoMapNub2Char.put(22,"W");
            cryptoMapNub2Char.put(23,"X");
            cryptoMapNub2Char.put(24,"Y");
            cryptoMapNub2Char.put(25,"Z");

        }
            // Initialize the monogramFreqMap
        HashMap<String, Double> monogramFreqMap = new HashMap<>();
        {
            monogramFreqMap.put("A", 0.085);
            monogramFreqMap.put("B", 0.016);
            monogramFreqMap.put("C", 0.031);
            monogramFreqMap.put("D", 0.038);
            monogramFreqMap.put("E", 0.120);
            monogramFreqMap.put("F", 0.021);
            monogramFreqMap.put("G", 0.020);
            monogramFreqMap.put("H", 0.049);
            monogramFreqMap.put("I", 0.073);
            monogramFreqMap.put("J", 0.002);
            monogramFreqMap.put("K", 0.008);
            monogramFreqMap.put("L", 0.042);
            monogramFreqMap.put("M", 0.252);
            monogramFreqMap.put("N", 0.071);
            monogramFreqMap.put("O", 0.074);
            monogramFreqMap.put("P", 0.020);
            monogramFreqMap.put("Q", 0.001);
            monogramFreqMap.put("R", 0.063);
            monogramFreqMap.put("S", 0.067);
            monogramFreqMap.put("T", 0.089);
            monogramFreqMap.put("U", 0.026);
            monogramFreqMap.put("V", 0.010);
            monogramFreqMap.put("W", 0.018);
            monogramFreqMap.put("X", 0.001);
            monogramFreqMap.put("Y", 0.017);
            monogramFreqMap.put("Z", 0.001);
        }

        String cipherText = "T E B K F K Q E B Z L R O P B L C E R J X K B S B K Q P";
        String[] cipherArr = cipherText.split(" ");

        System.out.println(Arrays.toString(cipherArr));
        System.out.println(cipherArr.length);

        int total = cipherArr.length;

        //Step1: Calculate the frequency of each
        HashMap<String, Double> freq = new HashMap();
        for (String ele: cipherArr) {
            if (freq.containsKey(ele)){
                double num = freq.get(ele);
                num ++;
                freq.put(ele, num);
            } else {
                freq.put(ele, 1.0);
            }
        }

        Iterator<Map.Entry<String, Double>> it = freq.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, Double> itEntry = it.next();
            Double perFreq = itEntry.getValue() / total;
            freq.put(itEntry.getKey(), perFreq);
        }

        System.out.println(freq.toString());

        //Step3: Apply the formula
        double[] sega = new double[26];
        double nub = 0;
        for (int i=0;i<=25;i++){
            nub = 0;
            for (String ch: freq.keySet()) {
                String a = cryptoMapNub2Char.get((cryptoMap.get(ch)-i+26) % 26);
                double b = monogramFreqMap.get(a);
                double c = freq.get(ch);
                nub += freq.get(ch) * monogramFreqMap.get(cryptoMapNub2Char.get((cryptoMap.get(ch)-i+26) % 26));
            }
            sega[i] = nub;
            System.out.println("I = "+i+" "+nub);
        }
    }
}
