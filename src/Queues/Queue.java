package Queues;

import LinkedList.DoublyLinkedList;

public class Queue<T> {

    private DoublyLinkedList<T> queue;

    //constructor:

    public Queue() {
        queue = new DoublyLinkedList<>();
    }

    //public:

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void insert(T value) {
        queue.insertLast(value);
    }

    public T peek() {
        return queue.getFirst();
    }

    public T remove() {
        return queue.deleteFirst();
    }

    public int size() {
        return queue.getSize();
    }

    public void showQueue() {
        queue.showLinkedList();
    }
}
