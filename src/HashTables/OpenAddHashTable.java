package HashTables;

import java.util.ArrayList;

public class OpenAddHashTable<T> {
    private double load_factor;
    private int max_size, cur_size;
    private ArrayList<T> table;
    private ArrayList<Integer> deleted;

    //constructors:

    public OpenAddHashTable() {
        max_size = 13;
        cur_size = 0;
        load_factor = 0.75;
        table = new ArrayList<>(max_size);
        deleted = new ArrayList<>();
        for (int i = 0; i < max_size; i++)
            table.add(null);
    }

    public OpenAddHashTable(int size) {
        max_size = size;
        cur_size = 0;
        load_factor = 0.75;
        table = new ArrayList<>(size);
        deleted = new ArrayList<>();
        for (int i = 0; i < size; i++)
            table.add(null);
    }

    public OpenAddHashTable(int size, double load_factor) {
        max_size = size;
        cur_size = 0;
        this.load_factor = load_factor <= 0.9 && load_factor >= 0.2 ? load_factor : 0.75;
        table = new ArrayList<>(size);
        deleted = new ArrayList<>();
        for (int i = 0; i < size; i++)
            table.add(null);
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
        ArrayList<T> old_table = table;
        this.table = new ArrayList<>(new_size);
        for (int i = 0; i < new_size; i++)
            table.add(null);
        cur_size = 0;
        max_size = new_size;
        for (int i = 0; i < old_table.size(); i++) {
            if (old_table.get(i) != null && !deleted.contains(i))
                insert(old_table.get(i));
        }
        deleted = new ArrayList<>();
    }

    //public:

    public void insert(T value) {
        int index = HashFunction.hash(value, max_size);
        if (table.get(index) != null && !deleted.contains(index)) {
            int step = HashFunction.doublehash(value, max_size);
            index = (index + step) % max_size;
            while (table.get(index) != null && !deleted.contains(index)) {
                index = (index + step) % max_size;
            }
        }
        table.set(index, value);
        if (deleted.contains(index))
            deleted.remove(new Integer(index));
        else
            cur_size++;
        if (1.0 * cur_size / max_size > load_factor)
            expandTable(getNextSize());
    }

    public boolean delete(T value) {
        int index = find(value);
        if (index == -1)
            return false;
        deleted.add(index);
        return true;
    }

    public int find(T value) {
        int index = HashFunction.hash(value, max_size);
        if (table.get(index) == null) {
            return -1;
        } else {
            if (table.get(index).equals(value) && !deleted.contains(index)) {
                return index;
            } else {
                int step = HashFunction.doublehash(value, max_size);
                index = (index + step) % max_size;
                while (table.get(index) != null && (!table.get(index).equals(value) || deleted.contains(index))) {
                    index = (index + step) % max_size;
                }
                if (table.get(index) == null)
                    return -1;
                else
                    return index;
            }
        }
    }

    public void showHashTable() {
        for (int i = 0; i < max_size; i++) {
            System.out.print(i + ": " + table.get(i));
            if (deleted.contains(i))
                System.out.print(" D");
            System.out.println();
        }
    }

    public void showStats() {
        System.out.println("Table size: " + max_size);
        System.out.println("Elements: " + cur_size);
        System.out.println("Fulfillment: " + (1.0 * cur_size / max_size));
    }

}
