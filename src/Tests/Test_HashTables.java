package Tests;

import HashTables.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Test_HashTables {

    public void testOpenAddHashTable() {
        OpenAddHashTable<Integer> table = new OpenAddHashTable<>();
        Scanner scanner = new Scanner(System.in);
        int option = -1;
        System.out.println("Choose option:");
        System.out.println("0. Exit\n1. Insert value\n2. Delete value\n" +
                "3. Find value\n4. Show table\n5. Show stats\n");
        Integer value;
        while (option != 0) {
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Enter value to insert");
                    value = scanner.nextInt();
                    table.insert(value);
                    System.out.println("Value inserted");
                    break;
                case 2:
                    System.out.println("Enter value to delete");
                    value = scanner.nextInt();
                    System.out.println(table.delete(value) ? "Element deleted" : "Element not found");
                    break;
                case 3:
                    System.out.println("Enter value to find");
                    value = scanner.nextInt();
                    System.out.println(table.find(value) != -1 ? "Element found" : "Element not found");
                    break;
                case 4:
                    System.out.println("Table:");
                    table.showHashTable();
                    break;
                case 5:
                    System.out.println("Stats:");
                    table.showStats();
                    break;
                case 0:
                    System.out.println("Quitting...");
                    return;
                default:
                    return;
            }
        }
    }

    public void testSepChainHashTable() {
        SepChainHashTable<Integer> table = new SepChainHashTable<>();
        Scanner scanner = new Scanner(System.in);
        int option = -1;
        System.out.println("Choose option:");
        System.out.println("0. Exit\n1. Insert value\n2. Delete value\n" +
                "3. Find value\n4. Show table\n5. Show stats\n");
        Integer value;
        while (option != 0) {
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Enter value to insert");
                    value = scanner.nextInt();
                    table.insert(value);
                    System.out.println("Value inserted");
                    break;
                case 2:
                    System.out.println("Enter value to delete");
                    value = scanner.nextInt();
                    System.out.println(table.delete(value) ? "Element deleted" : "Element not found");
                    break;
                case 3:
                    System.out.println("Enter value to find");
                    value = scanner.nextInt();
                    System.out.println(table.find(value) != -1 ? "Element found" : "Element not found");
                    break;
                case 4:
                    System.out.println("Table:");
                    table.showHashTable();
                    break;
                case 5:
                    System.out.println("Stats:");
                    table.showStats();
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
