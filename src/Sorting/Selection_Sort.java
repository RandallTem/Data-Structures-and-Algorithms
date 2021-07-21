package Sorting;

import java.util.ArrayList;

public class Selection_Sort { //O(N^2)

    public <T extends Comparable> void selectionSort(ArrayList<T> list) {
        int min_index;
        T temp;
        for (int i = 0; i < list.size()-1; i++) {
            min_index = i;
            for (int j = i+1; j < list.size(); j++) {
                min_index = list.get(j).compareTo(list.get(min_index)) < 0 ? j : min_index;
            }
            if (min_index != i) {
                temp = list.get(i);
                list.set(i, list.get(min_index));
                list.set(min_index, temp);
            }
        }
    }

}
