import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Sorting {

    /*--------------------Bubble Sort Start-----------------------*/

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

    /*---------------------Bubble Sort End------------------------*/ //O(N^2)

    /*------------------Selection Sort Start----------------------*/

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

    /*-------------------Selection Sort End-----------------------*/

    /*------------------Insertion Sort Start----------------------*/ //O(N^2)

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

    /*------------------Insertion Sort End------------------------*/

    /*------------------Merge Sort Start--------------------------*/ //O(N^2)

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

    /*--------------------Merge Sort End--------------------------*/ //O(N*longN)

    /*-------------------Shell Sort Start-------------------------*/

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

    /*--------------------Shell Sort End--------------------------*/ ////O(N*(longN)^2)

    /*--------------------Fast Sort Start-------------------------*/

    private <T extends Comparable> int partition(ArrayList<T>list, int start, int end) {
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

    /*---------------------Fast Sort End--------------------------*/ //O(N*longN)

    /*--------------------Radix Sort Start------------------------*/

    private void doSort(ArrayList<Long> list) {
        ArrayList<Long> sort = new ArrayList<>();
        ArrayList<ArrayList<Long>> grps;
        sort.addAll(list);
        int zero_counter = 0, counter = 0;
        while (zero_counter < list.size()) {
            zero_counter = 0;
            grps = new ArrayList<ArrayList<Long>>();
            for (int i = 0; i < 10; i++) {
                grps.add(new ArrayList<Long>());
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

    /*---------------------Radix Sort End-------------------------*/ //O(N*longN)





    public static void main(String[] args) {
        Sorting z = new Sorting();
        ArrayList<Long> list= new ArrayList<>(Arrays.asList(1L, 3L, 5L, -5L, -10L, -3L, 0L, 15L,  -100L, 16L, -3L));
        z.radixSort(list);
        System.out.println(list);
    }
}
