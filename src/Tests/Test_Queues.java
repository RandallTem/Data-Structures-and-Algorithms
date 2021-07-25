package Tests;

import Queues.*;

import java.util.Scanner;

public class Test_Queues {

    public void testQueue() {
        Queue<Integer> queue = new Queue<>();
        Scanner scanner = new Scanner(System.in);
        int option = -1;
        System.out.println("Choose option:");
        System.out.println("0. Exit\n1. Insert\n2. Peek\n3. Remove\n4. Get size\n5. Show queue");
        Integer value;
        while (option != 0) {
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Enter value to insert");
                    value = scanner.nextInt();
                    queue.insert(value);
                    System.out.println("Value inserted");
                    break;
                case 2:
                    System.out.println("Value = " + queue.peek());
                    break;
                case 3:
                    System.out.println("Value = " + queue.remove());
                    break;
                case 4:
                    System.out.println("Size = " + queue.size());
                    break;
                case 5:
                    queue.showQueue();
                    break;
                case 0:
                    System.out.println("Quitting...");
                    return;
                default:
                    return;
            }
        }
    }

    public void testDequeue() {
        Dequeue<Integer> dequeue = new Dequeue<>();
        Scanner scanner = new Scanner(System.in);
        int option = -1;
        System.out.println("Choose option:");
        System.out.println("0. Exit\n1. Insert left\n2. Insert right\n3. Peek left\n4. Peek right\n" +
                "5. Remove left\n6. Remove right\n7. Get size\n8. Show dequeue");
        Integer value;
        while (option != 0) {
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Enter value to insert");
                    value = scanner.nextInt();
                    dequeue.insertLeft(value);
                    System.out.println("Value inserted");
                    break;
                case 2:
                    System.out.println("Enter value to insert");
                    value = scanner.nextInt();
                    dequeue.insertRight(value);
                    System.out.println("Value inserted");
                    break;
                case 3:
                    System.out.println("Left value = " + dequeue.peekLeft());
                    break;
                case 4:
                    System.out.println("Right value = " + dequeue.peekRight());
                    break;
                case 5:
                    System.out.println("Left value = " + dequeue.removeLeft());
                    break;
                case 6:
                    System.out.println("Right value = " + dequeue.removeRight());
                    break;
                case 7:
                    System.out.println("Size = " + dequeue.size());
                    break;
                case 8:
                    dequeue.showDequeue();
                    break;
                case 0:
                    System.out.println("Quitting...");
                    return;
                default:
                    return;
            }
        }
    }

    public void testPriorityQueue() {
        Scanner scanner = new Scanner(System.in);
        int mode = 0;
        System.out.println("Choose mode:");
        System.out.println("0. Dec\n1. Inc");
        mode = scanner.nextInt();
        PriorityQueue<Integer> pqueue = new PriorityQueue<>(mode == 0 ? false : true);
        int option = -1;
        System.out.println("Choose option:");
        System.out.println("0. Exit\n1. Insert\n2. Peek\n3. Remove\n4. Get size\n5. Show queue");
        Integer value;
        while (option != 0) {
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Enter value to insert");
                    value = scanner.nextInt();
                    pqueue.insert(value);
                    System.out.println("Value inserted");
                    break;
                case 2:
                    System.out.println("Value = " + pqueue.peek());
                    break;
                case 3:
                    System.out.println("Value = " + pqueue.remove());
                    break;
                case 4:
                    System.out.println("Size = " + pqueue.size());
                    break;
                case 5:
                    pqueue.showPriorityQueue();
                    break;
                case 0:
                    System.out.println("Quitting...");
                    return;
                default:
                    return;
            }
        }
    }

}
