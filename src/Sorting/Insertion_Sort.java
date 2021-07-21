package Sorting;

import java.util.ArrayList;

public class Insertion_Sort { //O(N^2)

    public <T extends Comparable> void insertionSort(ArrayList<T> list) {
        T chosen;
        for (int i = 1; i < list.size(); i++) {
            chosen = list.get(i);
            for (int j = i-1; j >= 0; j--) {
                if (list.get(j).compareTo(chosen) > 0) {
                    list.set(j+1, list.get(j));
                    if (j == 0)
                        list.set(0, chosen);
                } else {
                    list.set(j+1, chosen);
                    break;
                }
            }
        }
    }

}
