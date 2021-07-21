package Sorting;

import java.util.ArrayList;

public class Merge_Sort { //O(N*longN)

    private <T extends Comparable> ArrayList<T> merge(ArrayList<T> a, ArrayList<T> b) {
        ArrayList<T> new_list = new ArrayList<>();
        int iterator_a = 0, iterator_b = 0;
        for (int i = 0; i < a.size()+b.size(); i++){
            if (iterator_a < a.size() && iterator_b < b.size()) {
                if (a.get(iterator_a).compareTo(b.get(iterator_b)) < 0)
                    new_list.add(a.get(iterator_a++));
                else
                    new_list.add(b.get(iterator_b++));
            } else {
                if (iterator_a == a.size())
                    while (iterator_b < b.size()) {
                        new_list.add(b.get(iterator_b++));
                    }
                else
                    while (iterator_a < a.size()) {
                        new_list.add(a.get(iterator_a++));
                    }
            }
        }
        return new_list;
    }

    public <T extends Comparable> void mergeSort(ArrayList<T> list) {
        if (list.size() > 1) {
            ArrayList<T> a = new ArrayList<>(list.subList(0, list.size() / 2));
            ArrayList<T> b = new ArrayList<>(list.subList(list.size() / 2, list.size()));
            mergeSort(a);
            mergeSort(b);
            ArrayList<T> new_list = merge(a, b);
            for (int i = 0; i < list.size(); i++) {
                list.set(i, new_list.get(i));
            }
        }
    }

}
