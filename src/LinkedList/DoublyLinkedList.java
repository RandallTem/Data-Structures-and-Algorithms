package LinkedList;

public class DoublyLinkedList<T> {

    private class Link<V extends T> {
        private V value;
        private Link<V> next;
        private Link<V> prev;

        public Link(V value) {
            this.value = value;
            this.next = null;
            this.prev = null;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public void setNext(Link<V> next) {
            this.next = next;
        }

        public void setPrev(Link<V> prev) {
            this.prev = prev;
        }

        public V getValue() {
            return value;
        }

        public Link<V> getNext() {
            return next;
        }

        public Link<V> getPrev() {
            return prev;
        }
    }

    private Link<T> first;
    private Link<T> last;
    private int size;

    //constructor:

    public DoublyLinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    //public:

    public boolean isEmpty () {
        return first == null ? true : false;
    }

    public int getSize() {
        return size;
    }

    public void insertFirst(T value) {
        if (isEmpty()) {
            first = new Link<>(value);
            last = first;
        } else {
            first.setPrev(new Link<>(value));
            first.getPrev().setNext(first);
            first = first.getPrev();
        }
        size++;
    }

    public void insertLast(T value) {
        if (isEmpty()) {
            first = new Link<>(value);
            last = first;
        } else {
            last.setNext(new Link<>(value));
            last.getNext().setPrev(last);
            last = last.getNext();
        }
        size++;
    }

    public boolean insertAfter(T value, T after) {
        Link<T> found = find(after);
        if (found != null) {
            Link<T> new_link = new Link<T>(value);
            new_link.setNext(found.getNext());
            new_link.setPrev(found);
            if (found == last) {
                last = new_link;
            } else {
                found.getNext().setPrev(new_link);
            }
            found.setNext(new_link);
            size++;
            return true;
        } else {
            return false;
        }
    }

    public boolean insertBefore(T value, T before) {
        Link<T> found = find(before);
        if (found != null) {
            Link<T> new_link = new Link<T>(value);
            new_link.setNext(found);
            new_link.setPrev(found.getPrev());
            if (found == first) {
                first = new_link;
            } else {
                found.getPrev().setNext(new_link);
            }
            found.setPrev(new_link);
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
            if (first == last) {
                first = null;
                last = null;
            } else {
                first.getNext().setPrev(null);
                first = first.getNext();
            }
            size--;
        }
        return val;
    }

    public T getLast() {
        return isEmpty() ? null : last.getValue();
    }

    public T deleteLast() {
        T val = null;
        if (!isEmpty()) {
            val = last.getValue();
            if (first == last) {
                first = null;
                last = null;
            } else {
                last.getPrev().setNext(null);
                last = last.getPrev();
            }
            size--;
        }
        return val;
    }

    public boolean deleteByValue(T value) {
        Link<T> iterator = find(value);
        if (iterator != null) {
            if (first == last) {
                first = null;
                last = null;
            } else {
                if (iterator == first) {
                    first.getNext().setPrev(null);
                    first = first.getNext();
                } else if (iterator == last) {
                    last.getPrev().setNext(null);
                    last = last.getPrev();
                } else {
                    iterator.getPrev().setNext(iterator.getNext());
                    iterator.getNext().setPrev(iterator.getPrev());
                }
            }
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
