package Sorting;

import java.util.ArrayList;

public class Fast_Sort { //O(N*longN)

    private <T extends Comparable> int partition(ArrayList<T> list, int start, int end) {
        int left_pointer = start-1, right_pointer = end+1;
        T temp, check_val = list.get(end);
        while (true) {
            while (left_pointer < end && (list.get(++left_pointer).compareTo(check_val) < 0)) {}
            while (start < right_pointer && (list.get(--right_pointer).compareTo(check_val) > 0)) {}
            if (left_pointer >= right_pointer) {
                break;
            } else {
                temp = list.get(left_pointer);
                list.set(left_pointer, list.get(right_pointer));
                list.set(right_pointer, temp);
            }
        }
        return left_pointer;
    }

    public <T extends Comparable> void fastSort(ArrayList<T> list, int start, int end) {
        if ((end - start) <= 0) {
            return;
        } else {
            int part_res = partition(list,start, end);
            fastSort(list, start, part_res-1);
            fastSort(list, part_res, end);
        }
    }

}
