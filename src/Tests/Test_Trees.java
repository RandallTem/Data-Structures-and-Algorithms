package Tests;

import Trees.*;

import java.util.Scanner;

public class Test_Trees {

    public void testBinarySearchTree() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        Scanner scanner = new Scanner(System.in);
        int option = -1;
        System.out.println("Choose option:");
        System.out.println("0. Exit\n1. Insert value\n2. Delete by value\n" +
                "3. Find value\n4. Get min\n5. Get max\n6. Show PreOrder\n" +
                "7. Show InOrder\n8. Show PostOrder\n9. Get size");
        Integer value;
        while (option != 0) {
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Enter value to insert");
                    value = scanner.nextInt();
                    tree.insert(value);
                    System.out.println("Value inserted");
                    break;
                case 2:
                    System.out.println("Enter value to delete");
                    value = scanner.nextInt();
                    System.out.println(tree.delete(value) ? "Element deleted" : "Element not found");
                    break;
                case 3:
                    System.out.println("Enter value to find");
                    value = scanner.nextInt();
                    System.out.println(tree.find(value) != null ? "Element found" : "Element not found");
                    break;
                case 4:
                    System.out.println("Min value = " + tree.getMin());
                    break;
                case 5:
                    System.out.println("Max value = " + tree.getMax());
                    break;
                case 6:
                    System.out.println("PreOrder");
                    tree.showPreOrder(tree.getRoot());
                    System.out.println();
                    break;
                case 7:
                    System.out.println("InOrder");
                    tree.showInOrder(tree.getRoot());
                    System.out.println();
                    break;
                case 8:
                    System.out.println("PostOrder");
                    tree.showPostOrder(tree.getRoot());
                    System.out.println();
                    break;
                case 9:
                    System.out.println("Tree size = " + tree.getSize());
                    break;
                case 0:
                    System.out.println("Quitting...");
                    return;
                default:
                    return;
            }
        }
    }

    public void testRedBlackTree() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        Scanner scanner = new Scanner(System.in);
        int option = -1;
        System.out.println("Choose option:");
        System.out.println("0. Exit\n1. Insert value\n2. Delete by value\n" +
                "3. Find value\n4. Get min\n5. Get max\n6. Show PreOrder\n" +
                "7. Show InOrder\n8. Show PostOrder\n9. Get size");
        Integer value;
        while (option != 0) {
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Enter value to insert");
                    value = scanner.nextInt();
                    tree.insert(value);
                    System.out.println("Value inserted");
                    break;
                case 2:
                    System.out.println("Enter value to delete");
                    value = scanner.nextInt();
                    System.out.println(tree.delete(value) ? "Element deleted" : "Element not found");
                    break;
                case 3:
                    System.out.println("Enter value to find");
                    value = scanner.nextInt();
                    System.out.println(tree.find(value) != null ? "Element found" : "Element not found");
                    break;
                case 4:
                    System.out.println("Min value = " + tree.getMin());
                    break;
                case 5:
                    System.out.println("Max value = " + tree.getMax());
                    break;
                case 6:
                    System.out.println("PreOrder");
                    tree.showPreOrder(tree.getRoot());
                    System.out.println();
                    break;
                case 7:
                    System.out.println("InOrder");
                    tree.showInOrder(tree.getRoot());
                    System.out.println();
                    break;
                case 8:
                    System.out.println("PostOrder");
                    tree.showPostOrder(tree.getRoot());
                    System.out.println();
                    break;
                case 9:
                    System.out.println("Tree size = " + tree.getSize());
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
