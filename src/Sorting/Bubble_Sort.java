package Sorting;

import java.util.ArrayList;

public class Bubble_Sort { //O(N^2)

    public <T extends Comparable> void bubbleSort(ArrayList<T> list) {
        T temp;
        for (int i = list.size()-1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (list.get(j).compareTo(list.get(j+1)) > 0) {
                    temp = list.get(j);
                    list.set(j, list.get(j+1));
                    list.set(j+1, temp);
                }
            }
        }
    }

}
