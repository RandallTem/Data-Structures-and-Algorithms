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

    public OrderedLinkedList() {
        first = null;
        this.mode = false;
    }

    public OrderedLinkedList(boolean mode) {
        first = null;
        this.mode = mode;
    }

    public boolean isEmpty () {
        return first == null ? true : false;
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
    }


    public T getFirst() {
        return first == null ? null : first.getValue();
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
