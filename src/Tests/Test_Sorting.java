package Tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import Sorting.*;

public class Test_Sorting {

    public void testSorting() {
        Bubble_Sort bubble_sort = new Bubble_Sort();
        Selection_Sort selection_sort = new Selection_Sort();
        Insertion_Sort insertion_sort = new Insertion_Sort();
        Merge_Sort merge_sort = new Merge_Sort();
        Shell_Sort shell_sort = new Shell_Sort();
        Fast_Sort fast_sort = new Fast_Sort();
        Radix_Sort radix_sort = new Radix_Sort();
        Heap_Sort heap_sort = new Heap_Sort();
        Long[]  test_array = new Long[15];
        Random rn = new Random();
        for (int i = 0; i < 15; i++) {
            test_array[i] = rn.nextLong() % 1000;
        }

        ArrayList<Long> test_list = new ArrayList<>(Arrays.asList(test_array));
        bubble_sort.bubbleSort(test_list);
        System.out.println(test_list);

        test_list = new ArrayList<>(Arrays.asList(test_array));
        selection_sort.selectionSort(test_list);
        System.out.println(test_list);

        test_list = new ArrayList<>(Arrays.asList(test_array));
        insertion_sort.insertionSort(test_list);
        System.out.println(test_list);

        test_list = new ArrayList<>(Arrays.asList(test_array));
        merge_sort.mergeSort(test_list);
        System.out.println(test_list);

        test_list = new ArrayList<>(Arrays.asList(test_array));
        shell_sort.shellSort(test_list);
        System.out.println(test_list);

        test_list = new ArrayList<>(Arrays.asList(test_array));
        fast_sort.fastSort(test_list, 0, test_list.size()-1);
        System.out.println(test_list);

        test_list = new ArrayList<>(Arrays.asList(test_array));
        radix_sort.radixSort(test_list);
        System.out.println(test_list);

        test_list = new ArrayList<>(Arrays.asList(test_array));
        heap_sort.heapSort(test_list);
        System.out.println(test_list);

    }

}
