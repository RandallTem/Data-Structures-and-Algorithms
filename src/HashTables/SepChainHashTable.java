package HashTables;

import LinkedList.SinglyLinkedList;

import java.util.ArrayList;

public class SepChainHashTable<T> {

    private ArrayList<SinglyLinkedList<T>> table;
    private double load_factor;
    private int max_size, cur_size;

    //constructors:

    public SepChainHashTable() {
        max_size = 13;
        cur_size = 0;
        load_factor = 3.0;
        table = new ArrayList<>(max_size);
        for (int i = 0; i < max_size; i++)
            table.add(new SinglyLinkedList<>());
    }

    public SepChainHashTable(int size) {
        max_size = size;
        cur_size = 0;
        load_factor = 3.0;
        table = new ArrayList<>(max_size);
        for (int i = 0; i < max_size; i++)
            table.add(new SinglyLinkedList<>());
    }

    public SepChainHashTable(int size, double load_factor) {
        max_size = size;
        cur_size = 0;
        this.load_factor = load_factor >= 0.5 ? load_factor : 0.75;
        table = new ArrayList<>(max_size);
        for (int i = 0; i < max_size; i++)
            table.add(new SinglyLinkedList<>());
    }

    //private:

    private boolean isPrime(int value) {
        for (int i = 2; i * i <= value; i++) {
            if (value % i == 0)
                return false;
        }
        return true;
    }

    private int getNextSize() {
        int new_size = max_size * 2 + 1;
        while (!isPrime(new_size)) {
            new_size += 2;
        }
        return new_size;
    }

    private void expandTable(int new_size) {
        ArrayList<SinglyLinkedList<T>> old_table = table;
        this.table = new ArrayList<>(new_size);
        for (int i = 0; i < new_size; i++)
            table.add(new SinglyLinkedList<>());
        cur_size = 0;
        max_size = new_size;
        for (int i = 0; i < old_table.size(); i++) {
            while (!old_table.get(i).isEmpty()) {
                insert(old_table.get(i).deleteFirst());
            }
        }
    }

    //public:

    public void insert(T value) {
        int index = HashFunction.hash(value, max_size);
        table.get(index).insertFirst(value);
        cur_size++;
        if (1.0 * cur_size / max_size > load_factor)
            expandTable(getNextSize());
    }

    public boolean delete(T value) {
        int index = find(value);
        if (index == -1) {
            return false;
        } else {
            table.get(index).deleteByValue(value);
            cur_size--;
            return true;
        }
    }

    public int find(T value) {
        int index = HashFunction.hash(value, max_size);
        if (table.get(index).find(value) != null)
            return index;
        else
            return -1;
    }

    public void showHashTable() {
        for (int i = 0; i < max_size; i++) {
            System.out.print(i + ": ");
            table.get(i).showLinkedList();
        }
    }

    public void showStats() {
        System.out.println("Table size: " + max_size);
        System.out.println("Elements: " + cur_size);
        System.out.println("Fulfillment: " + (1.0 * cur_size / max_size));
    }

}
