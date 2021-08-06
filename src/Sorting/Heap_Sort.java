package Sorting;

import Trees.Heap;

import java.util.ArrayList;

public class Heap_Sort {

    public <T extends Comparable> void heapSort(ArrayList<T> list) {
        Heap<T> heap = new Heap<>(true);
        for (T val : list)
            heap.insert(val);
        for (int i = 0; i < list.size(); i++) {
            list.set(i, heap.delete());
        }
    }

}
