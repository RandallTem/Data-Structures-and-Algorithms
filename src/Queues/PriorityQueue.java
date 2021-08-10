package Queues;

import LinkedList.OrderedLinkedList;

public class PriorityQueue<T extends Comparable> {

    private OrderedLinkedList<T> pqueue;

    //constructors:

    public PriorityQueue() {
        this.pqueue = new OrderedLinkedList<T>(false);
    }

    public PriorityQueue(boolean mode) {
        this.pqueue = new OrderedLinkedList<T>(mode);
    }

    //public:

    public boolean isEmpty() {
        return pqueue.isEmpty();
    }

    public void insert(T value) {
        pqueue.insert(value);
    }

    public T peek() {
        return pqueue.getFirst();
    }

    public T remove() {
        return pqueue.deleteFirst();
    }

    public int size() {
        return pqueue.getSize();
    }

    public void showPriorityQueue() {
        pqueue.showLinkedList();
    }
}
