package Trees;


import java.util.ArrayList;

public class Heap<T extends Comparable> {

    private ArrayList<T> heap;
    private int size, array_size;
    private boolean mode;

    //constructors:

    public Heap() {
        heap = new ArrayList<>();
        size = 0;
        array_size = 0;
        mode = false;
    }

    public Heap(boolean mode) {
        heap = new ArrayList<>();
        size = 0;
        array_size = 0;
        this.mode = mode;
    }

    //private:

    private void pushUp(int index) {
        T temp;
        if (!mode) {
            while (index != 0 && heap.get(index).compareTo(heap.get((index-1) / 2)) > 0) {
                temp = heap.get(index);
                heap.set(index, heap.get((index-1) / 2));
                heap.set((index-1) / 2, temp);
                index = (index-1) / 2;
            }
        } else {
            while (index != 0 && heap.get(index).compareTo(heap.get((index-1) / 2)) < 0) {
                temp = heap.get(index);
                heap.set(index, heap.get((index-1) / 2));
                heap.set((index-1) / 2, temp);
                index = (index-1) / 2;
            }
        }
    }

    private void pushDown(int index) {
        T temp;
        if (!mode) {
            /*while ((2 * index + 1) < heap.size() && heap.get(index).compareTo(heap.get((2 * index) + 1)) < 0) {
                temp = heap.get(index);
                heap.set(index, heap.get((2 * index) + 1));
                heap.set((2 * index) + 1, temp);
                index = (2 * index) + 1;
            }*/

            while(true) {
                if ((2 * index + 1) >= heap.size())
                    break;
                if ((2 * index + 2) >= heap.size() || heap.get(2 * index + 2).compareTo(heap.get(2 * index + 1)) < 0) {
                    if (heap.get(2 * index + 1).compareTo(heap.get(index)) < 0)
                        break;
                    temp = heap.get(index);
                    heap.set(index, heap.get((2 * index) + 1));
                    heap.set((2 * index) + 1, temp);
                    index = (2 * index) + 1;
                } else {
                    if (heap.get(2 * index + 2).compareTo(heap.get(index)) < 0)
                        break;
                    temp = heap.get(index);
                    heap.set(index, heap.get((2 * index) + 2));
                    heap.set((2 * index) + 2, temp);
                    index = (2 * index) + 2;
                }
            }
        } else {
            while(true) {
                if ((2 * index + 1) >= heap.size())
                    break;
                if ((2 * index + 2) >= heap.size() || heap.get(2 * index + 2).compareTo(heap.get(2 * index + 1)) > 0) {
                    if (heap.get(2 * index + 1).compareTo(heap.get(index)) > 0)
                        break;
                    temp = heap.get(index);
                    heap.set(index, heap.get((2 * index) + 1));
                    heap.set((2 * index) + 1, temp);
                    index = (2 * index) + 1;
                } else {
                    if (heap.get(2 * index + 2).compareTo(heap.get(index)) > 0)
                        break;
                    temp = heap.get(index);
                    heap.set(index, heap.get((2 * index) + 2));
                    heap.set((2 * index) + 2, temp);
                    index = (2 * index) + 2;
                }
            }
        }
    }

    //public:

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public void insert(T value) {
        heap.add(value);
        pushUp(heap.size()-1);
    }

    public T delete() {
        T val = null;
        if (heap.isEmpty())
            return null;
        val = heap.get(0);
        if (heap.size() == 1) {
            heap.remove(0);
        } else {
            heap.set(0, heap.remove(heap.size() - 1));
            pushDown(0);
        }
        return val;
    }

    public void showHeap() {
        for (T val : heap)
            System.out.print(val + " ");
        System.out.println();
    }

    public int getSize() {
        return heap.size();
    }
}
