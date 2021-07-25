package LinkedList;

public class SinglyLinkedList<T> {

    private class Link<V extends T> {
        private V value;
        private Link<V> next;

        public Link(V value) {
            this.value = value;
            this.next = null;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public void setNext(Link<V> next) {
            this.next = next;
        }

        public V getValue() {
            return value;
        }

        public Link<V> getNext() {
            return next;
        }
    }

    private Link<T> first;
    private int size;

    public SinglyLinkedList() {
        first = null;
        size = 0;
    }

    public boolean isEmpty () {
        return first == null ? true : false;
    }

    public int getSize() {
        return size;
    }

    public void insertFirst(T value) {
        Link<T> new_link = new Link<>(value);
        new_link.setNext(first);
        first = new_link;
        size++;
    }

    public boolean insertAfter(T value, T after) {
        Link<T> found = find(after);
        if (found != null) {
            Link<T> new_link = new Link<T>(value);
            new_link.setNext(found.getNext());
            found.setNext(new_link);
            size++;
            return true;
        } else {
            return false;
        }
    }

    public Link<T> find(T value) {
        Link<T> iterator = first;
        while (iterator != null && !iterator.getValue().equals(value)) {
            iterator = iterator.getNext();
        }
        return iterator;
    }

    public T getFirst() {
        return isEmpty() ? null : first.getValue();
    }

    public T deleteFirst() {
        T val = null;
        if (!isEmpty()) {
            val = first.getValue();
            first = first.getNext();
            size--;
        }
        return val;
    }

    public boolean deleteByValue(T value) {
        Link<T> iterator = first, prev = null;
        while (iterator != null && !iterator.getValue().equals(value)) {
            prev = iterator;
            iterator = iterator.getNext();
        }
        if (iterator != null) {
            if (iterator == first)
                first = first.getNext();
            else
                prev.setNext(iterator.getNext());
            size--;
            return true;
        } else {
            return false;
        }
    }

    public void showLinkedList() {
        Link<T> iterator = first;
        while (iterator != null) {
            System.out.print(iterator.getValue()+" ");
            iterator = iterator.getNext();
        }
        System.out.println();
    }
}
