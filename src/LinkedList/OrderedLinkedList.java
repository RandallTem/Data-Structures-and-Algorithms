package LinkedList;

public class OrderedLinkedList<T extends Comparable> {

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
    private boolean mode;
    private int size;

    //constructors:

    public OrderedLinkedList() {
        this.first = null;
        this.mode = false;
        size = 0;
    }

    public OrderedLinkedList(boolean mode) {
        this.first = null;
        this.mode = mode;
        size = 0;
    }

    //public:

    public boolean isEmpty () {
        return first == null ? true : false;
    }

    public int getSize() {
        return size;
    }

    public void insert(T value) {
        Link<T> iterator = first;
        Link<T> prev = null;
        if (isEmpty()) {
            first = new Link<>(value);
        } else {
            Link<T> temp = new Link<>(value);
            if (mode) {
                while (iterator != null && iterator.getValue().compareTo(value) <= 0) {
                    prev = iterator;
                    iterator = iterator.getNext();
                }
            } else {
                while (iterator != null && iterator.getValue().compareTo(value) > 0) {
                    prev = iterator;
                    iterator = iterator.getNext();
                }
            }
            temp.setNext(iterator);
            if (prev != null) {
                prev.setNext(temp);
            } else {
                first = temp;
            }
        }
        size++;
    }


    public T getFirst() {
        return isEmpty() ? null : first.getValue();
    }


    public Link<T> find(T value) {
        Link<T> iterator = first;
        while (iterator != null && !iterator.getValue().equals(value)) {
            iterator = iterator.getNext();
        }
        return iterator;
    }

    public T deleteFirst() {
        T temp = null;
        if (!isEmpty()) {
            temp = first.getValue();
            first = first.getNext();
            size--;
        }
        return temp;
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
