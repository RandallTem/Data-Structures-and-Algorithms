package Tests;

import Graphs.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Test_Graphs {

    public static void testGraph() {
        Graph<Character> graph = new Graph<>();
        Scanner scanner = new Scanner(System.in);
        int option = -1;
        System.out.println("Choose option:");
        System.out.println("0. Exit\n1. Add vertex\n2. Add undirected edge\n" +
                "3. Add directed edge\n4. Depth First Search\n5. Breadth First Search\n6. Get connectivity\n" +
                "7. Show adjacency matrix\n");
        Character value1, value2;
        ArrayList<Character> list = new ArrayList<>();
        while (option != 0) {
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Enter vertex id");
                    value1 = scanner.next().charAt(0);
                    graph.addVertex(value1);
                    System.out.println("Added vertex");
                    break;
                case 2:
                    System.out.println("Enter first vertex id");
                    value1 = scanner.next().charAt(0);
                    System.out.println("Enter second vertex id");
                    value2 = scanner.next().charAt(0);
                    graph.addUndirectedEdge(value1, value2);
                    System.out.println("Added edge");
                    break;
                case 3:
                    System.out.println("Enter first vertex id");
                    value1 = scanner.next().charAt(0);
                    System.out.println("Enter second vertex id");
                    value2 = scanner.next().charAt(0);
                    graph.addDirectedEdge(value1, value2);
                    System.out.println("Added edge");
                    break;
                case 4:
                    System.out.println("Enter start vertex id");
                    value1 = scanner.next().charAt(0);
                    list = graph.DepthFirstSearch(value1);
                    System.out.println(list);
                    break;
                case 5:
                    System.out.println("Enter start vertex id");
                    value1 = scanner.next().charAt(0);
                    list = graph.BreadthFirstSearch(value1);
                    System.out.println(list);
                    break;
                case 6:
                    graph.getGraphConnectivity();
                    break;
                case 7:
                    graph.showAdjacencyMatrix();
                    break;
                case 0:
                    System.out.println("Quitting...");
                    return;
                default:
                    return;
            }
        }
    }

    public static void testWeightedGraph() {
        WeightedGraph<Character> graph = new WeightedGraph<>();
        Scanner scanner = new Scanner(System.in);
        int option = -1;
        System.out.println("Choose option:");
        System.out.println("0. Exit\n1. Add vertex\n2. Add undirected edge\n" +
                "3. Add directed edge\n4. Get shortest path\n5. Get all shortest paths\n6. Show adjacency matrix\n");
        Character value1, value2;
        Integer value3;
        ArrayList<Character> list = new ArrayList<>();
        while (option != 0) {
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Enter vertex id");
                    value1 = scanner.next().charAt(0);
                    graph.addVertex(value1);
                    System.out.println("Added vertex");
                    break;
                case 2:
                    System.out.println("Enter first vertex id");
                    value1 = scanner.next().charAt(0);
                    System.out.println("Enter second vertex id");
                    value2 = scanner.next().charAt(0);
                    System.out.println("Enter weight");
                    value3 = scanner.nextInt();
                    graph.addUndirectedEdge(value1, value2, value3);
                    System.out.println("Added edge");
                    break;
                case 3:
                    System.out.println("Enter first vertex id");
                    value1 = scanner.next().charAt(0);
                    System.out.println("Enter second vertex id");
                    value2 = scanner.next().charAt(0);
                    System.out.println("Enter weight");
                    value3 = scanner.nextInt();
                    graph.addDirectedEdge(value1, value2, value3);
                    System.out.println("Added edge");
                    break;
                case 4:
                    System.out.println("Enter first vertex id");
                    value1 = scanner.next().charAt(0);
                    System.out.println("Enter second vertex id");
                    value2 = scanner.next().charAt(0);
                    System.out.println("Shortest path = " + graph.getShortestPath(value1, value2));
                    break;
                case 5:
                    graph.Floyd();
                    break;
                case 6:
                    graph.showAdjacencyMatrix();
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
