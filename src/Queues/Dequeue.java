package Queues;

import LinkedList.DoublyLinkedList;

public class Dequeue<T> {

    DoublyLinkedList<T> dequeue;

    public Dequeue() {
        dequeue = new DoublyLinkedList<>();
    }

    public boolean isEmpty() {
        return dequeue.isEmpty();
    }

    public void insertLeft(T value) {
        dequeue.insertFirst(value);
    }

    public void insertRight(T value) {
        dequeue.insertLast(value);
    }

    public T peekLeft() {
        return dequeue.getFirst();
    }

    public T peekRight() {
        return dequeue.getLast();
    }

    public T removeLeft() {
        return dequeue.deleteFirst();
    }

    public T removeRight() {
        return dequeue.deleteLast();
    }

    public int size() {
        return dequeue.getSize();
    }

    public void showDequeue() {
        dequeue.showLinkedList();
    }

}
