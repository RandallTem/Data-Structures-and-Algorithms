package Tests;

import Stack.*;

import java.util.Scanner;

public class Test_Stack {

    public void testStack() {
        Stack<Integer> stack = new Stack<Integer>();
        Scanner scanner = new Scanner(System.in);
        int option = -1;
        System.out.println("Choose option:");
        System.out.println("0. Exit\n1. Push\n2. Pop\n3. Peek\n4. Show stack");
        Integer value;
        while (option != 0) {
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Enter value to push");
                    value = scanner.nextInt();
                    stack.push(value);
                    System.out.println("Value pushed");
                    break;
                case 2:
                    System.out.println("Value = " + stack.pop());
                    break;
                case 3:
                    System.out.println("Value = " + stack.peek());
                    break;
                case 4:
                    stack.showStack();
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
