package Sorting;

import java.util.ArrayList;
import java.util.Collections;

public class Radix_Sort { //O(N*longN)

    private void doSort(ArrayList<Long> list) {
        ArrayList<Long> sort = new ArrayList<>();
        ArrayList<ArrayList<Long>> grps;
        sort.addAll(list);
        int zero_counter = 0, counter = 0;
        while (zero_counter < list.size()) {
            zero_counter = 0;
            grps = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                grps.add(new ArrayList<>());
            }
            for (Long x: sort) {
                grps.get(Math.abs((int)(x / (Math.pow(10,counter))) % 10)).add(x);
                if ((int)(x / (Math.pow(10,counter+1))) == 0)
                    zero_counter++;
            }
            sort = new ArrayList<>();
            for (ArrayList<Long> arrays: grps) {
                sort.addAll(arrays);
            }
            counter++;
        }
        for (int i = 0; i < sort.size(); i++) {
            list.set(i, sort.get(i));
        }
    }

    public void radixSort(ArrayList<Long> list) {
        ArrayList<Long> negative = new ArrayList<>();
        ArrayList<Long> positive = new ArrayList<>();
        for (Long x: list) {
            if (x < 0)
                negative.add(x);
            else
                positive.add(x);
        }
        doSort(negative);
        Collections.reverse(negative);
        doSort(positive);
        for (int i = 0; i < negative.size(); i++) {
            list.set(i, negative.get(i));
        }
        for (int i = 0; i < positive.size(); i++) {
            list.set(i+negative.size(), positive.get(i));
        }
    }



}
