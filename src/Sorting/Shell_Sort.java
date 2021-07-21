package Sorting;

import java.util.ArrayList;

public class Shell_Sort { //O(N*(longN)^2)

    private <T extends Comparable> void insertionSortForShell(ArrayList<T> list, int h) {
        T chosen;
        for (int i = 0; i < list.size() - h; i++) {
            for (int j = i+h; j < list.size(); j+=h) {
                chosen = list.get(j);
                for (int k = j-h; k >= i; k-=h) {
                    if (list.get(k).compareTo(chosen) > 0) {
                        list.set(k+h, list.get(k));
                        if (k == i)
                            list.set(i, chosen);
                    } else {
                        list.set(k+h, chosen);
                        break;
                    }
                }
            }
        }
    }

    public <T extends Comparable> void shellSort(ArrayList<T> list) {
        int h = 1;
        while (h < list.size()) {
            h = 3 * h + 1;
        }
        h = (h - 1) / 3;
        while (h > 0) {
            insertionSortForShell(list, h);
            h = (h - 1) / 3;
        }
    }

}
