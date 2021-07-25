package Queues;

import LinkedList.OrderedLinkedList;

public class PriorityQueue<T extends Comparable> {

    private OrderedLinkedList<T> pqueue;
    private boolean mode;

    public PriorityQueue() {
        this.pqueue = new OrderedLinkedList<T>();
        this.mode = false;
    }

    public PriorityQueue(boolean mode) {
        this.pqueue = new OrderedLinkedList<T>();
        this.mode = mode;
    }

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
