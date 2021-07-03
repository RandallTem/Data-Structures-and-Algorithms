import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Test {

    public void testSorting() {
        Sorting sort = new Sorting();
        Long[] test_array = {5L, 1L, -15L, 7L, 0L, 0L, 5L, -20L, 53L, -53L, 10000L, -2L, -3L, -4L, 1L};

        ArrayList<Long> test_list = new ArrayList<>(Arrays.asList(test_array));
        sort.bubbleSort(test_list);
        System.out.println(test_list);

        test_list = new ArrayList<>(Arrays.asList(test_array));
        sort.selectionSort(test_list);
        System.out.println(test_list);

        test_list = new ArrayList<>(Arrays.asList(test_array));
        sort.insertionSort(test_list);
        System.out.println(test_list);

        test_list = new ArrayList<>(Arrays.asList(test_array));
        sort.mergeSort(test_list);
        System.out.println(test_list);

        test_list = new ArrayList<>(Arrays.asList(test_array));
        sort.shellSort(test_list);
        System.out.println(test_list);

        test_list = new ArrayList<>(Arrays.asList(test_array));
        sort.fastSort(test_list, 0, test_list.size()-1);
        System.out.println(test_list);

        test_list = new ArrayList<>(Arrays.asList(test_array));
        sort.radixSort(test_list);
        System.out.println(test_list);
    }




    public static void main(String[] args) {
        Test test = new Test();
        test.testSorting();
    }
}
