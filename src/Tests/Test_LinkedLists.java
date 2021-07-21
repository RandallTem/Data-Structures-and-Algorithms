package Tests;

import LinkedList.*;

import java.util.Scanner;

public class Test_LinkedLists {

    public void testSinglyLinkedList() {
        SinglyLinkedList<Integer> s_list = new SinglyLinkedList<>();
        Scanner scanner = new Scanner(System.in);
        int option = -1;
        System.out.println("Choose option:");
        System.out.println("0. Exit\n1. Insert first\n2. Insert after value\n" +
                "3. Delete first\n4. Delete element by value\n5. Find element\n6. Show list");
        Integer value_to_insert, value_to_find, value_to_delete, res_value;
        while (option != 0) {
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Enter value to insert");
                    value_to_insert = scanner.nextInt();
                    s_list.insertFirst(value_to_insert);
                    System.out.println("Element inserted");
                    break;
                case 2:
                    System.out.println("Enter value to insert after");
                    value_to_find = scanner.nextInt();
                    System.out.println("Enter value to insert");
                    value_to_insert = scanner.nextInt();
                    if (s_list.insertAfter(value_to_insert, value_to_find))
                        System.out.println("Element inserted");
                    else
                        System.out.println("Element was not found");
                    break;
                case 3:
                    res_value = s_list.deleteFirst();
                    System.out.println(res_value == null ? "List is empty" : "First element deleted: " + res_value);
                    break;
                case 4:
                    System.out.println("Enter value to delete");
                    value_to_find = scanner.nextInt();
                    boolean res = s_list.deleteByValue(value_to_find);
                    if (res)
                        System.out.println("Element was deleted");
                    else
                        System.out.println("Element was not found");
                    break;
                case 5:
                    System.out.println("Enter value to find");
                    value_to_find = scanner.nextInt();
                    if (s_list.find(value_to_find) != null)
                        System.out.println("Element found");
                    else
                        System.out.println("Element not found");
                    break;
                case 6:
                    s_list.showLinkedList();
                    break;
                case 0:
                    System.out.println("Quitting...");
                    return;
                default:
                    return;
            }
        }
    }

    public void testDoublyLinkedList() {
        DoublyLinkedList<Integer> d_list = new DoublyLinkedList<>();
        Scanner scanner = new Scanner(System.in);
        int option = -1;
        System.out.println("Choose option:");
        System.out.println("0. Exit\n1. Insert first\n2. Insert last\n3. Insert after value\n" +
                "4. 7Insert before value\n5. Delete first\n6. Delete last\n" +
                "7. Delete element by value\n8. Find element\n9. Show list");
        Integer value_to_insert, value_to_find, value_to_delete, res_value;
        while (option != 0) {
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Enter value to insert");
                    value_to_insert = scanner.nextInt();
                    d_list.insertFirst(value_to_insert);
                    System.out.println("Element inserted");
                    break;
                case 2:
                    System.out.println("Enter value to insert");
                    value_to_insert = scanner.nextInt();
                    d_list.insertLast(value_to_insert);
                    System.out.println("Element inserted");
                    break;
                case 3:
                    System.out.println("Enter value to insert after");
                    value_to_find = scanner.nextInt();
                    System.out.println("Enter value to insert");
                    value_to_insert = scanner.nextInt();
                    if (d_list.insertAfter(value_to_insert, value_to_find))
                        System.out.println("Element inserted");
                    else
                        System.out.println("Element was not found");
                    break;
                case 4:
                    System.out.println("Enter value to insert before");
                    value_to_find = scanner.nextInt();
                    System.out.println("Enter value to insert");
                    value_to_insert = scanner.nextInt();
                    if (d_list.insertBefore(value_to_insert, value_to_find))
                        System.out.println("Element inserted");
                    else
                        System.out.println("Element was not found");
                    break;
                case 5:
                    res_value = d_list.deleteFirst();
                    System.out.println(res_value == null ? "List is empty" : "First element deleted: " + res_value);
                    break;
                case 6:
                    res_value = d_list.deleteLast();
                    System.out.println(res_value == null ? "List is empty" : "Last element deleted: " + res_value);
                    break;
                case 7:
                    System.out.println("Enter value to delete");
                    value_to_find = scanner.nextInt();
                    boolean res = d_list.deleteByValue(value_to_find);
                    if (res)
                        System.out.println("Element was deleted");
                    else
                        System.out.println("Element was not found");
                    break;
                case 8:
                    System.out.println("Enter value to find");
                    value_to_find = scanner.nextInt();
                    if (d_list.find(value_to_find) != null)
                        System.out.println("Element found");
                    else
                        System.out.println("Element not found");
                    break;
                case 9:
                    d_list.showLinkedList();
                    break;
                case 0:
                    System.out.println("Quitting...");
                    return;
                default:
                    return;
            }
        }
    }

    public void testOrderedLinkedList() {
        Scanner scanner = new Scanner(System.in);
        int mode = 0;
        System.out.println("Choose mode:");
        System.out.println("0. Dec\n1. Inc");
        mode = scanner.nextInt();
        OrderedLinkedList<Integer> o_list = new OrderedLinkedList<>(mode == 0 ? false : true);
        int option = -1;
        System.out.println("Choose option:");
        System.out.println("0. Exit\n1. Insert value\n2. Get first\n3. Delete first\n" +
                "4. Find element\n5. Show list");
        Integer value;
        while (option != 0) {
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Enter value to insert");
                    value = scanner.nextInt();
                    o_list.insert(value);
                    System.out.println("Element inserted");
                    break;
                case 2:
                    value = o_list.getFirst();
                    System.out.println(value == null ? "List is empty" : "First element: " + value);
                    break;
                case 3:
                    value = o_list.deleteFirst();
                    System.out.println(value == null ? "List is empty" : "Element: " + value);
                    break;
                case 4:
                    System.out.println("Enter value to find");
                    value = scanner.nextInt();
                    if (o_list.find(value) != null)
                        System.out.println("Element found");
                    else
                        System.out.println("Element not found");
                    break;
                case 5:
                    o_list.showLinkedList();
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
