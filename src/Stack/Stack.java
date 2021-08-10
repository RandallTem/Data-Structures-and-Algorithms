package Stack;

import LinkedList.SinglyLinkedList;

public class Stack<T> {

    private SinglyLinkedList<T> stack;

    //constructor:

    public Stack() {
        stack = new SinglyLinkedList<>();
    }

    //public:

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public void push(T value) {
        stack.insertFirst(value);
    }

    public T peek() {
        return stack.getFirst();
    }

    public T pop() {
        return stack.deleteFirst();
    }

    public int size() {
        return stack.getSize();
    }

    public void showStack() {
        stack.showLinkedList();
    }
}
