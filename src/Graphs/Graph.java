package Graphs;

import Stack.Stack;
import Queues.Queue;

import java.util.ArrayList;
import java.util.Iterator;

public class Graph<T> {

    private ArrayList<ArrayList<Integer>> adj_matrix;
    private ArrayList<Integer> model;
    private ArrayList<T> vertexes;

    //constructor:

    public Graph() {
        adj_matrix = new ArrayList<>();
        model = new ArrayList<>();
        vertexes = new ArrayList<>();
    }

    //private:

    private void checkGraphConnectivityIfUndirected(ArrayList<ArrayList<Integer>> access_matrix) {
        for (int i = 0; i < access_matrix.size(); i++) {
            for (int j = 0; j < access_matrix.size(); j++) {
                if (access_matrix.get(i).get(j) == 1)
                    access_matrix.get(j).set(i, 1);
            }
        }
        int counter_in = 0, counter_out = 0;
        Iterator<Integer> iterator;
        for (int i = 0; i < access_matrix.size(); i++) {
            iterator = access_matrix.get(i).iterator();
            while (iterator.hasNext())
                counter_in += iterator.next() == 1 ? 1 : 0;
            counter_out += counter_in == access_matrix.size() ? 1 : 0;
            counter_in = 0;
        }
        if (counter_out == 0)
            System.out.println("Graph is not connected");
        else
            System.out.println("Graph is connected");
    }

    private void showMatrix(ArrayList<ArrayList<Integer>> matrix) {
        System.out.print("    ");
        for (T v : vertexes) {
            System.out.print(v + "   ");
        }
        System.out.println();
        for (int i = 0; i < matrix.size(); i++) {
            System.out.print(vertexes.get(i) + "   ");
            for (int val : matrix.get(i))
                System.out.print(val + "   ");
            System.out.println();
        }
    }

    //public:

    public boolean addVertex(T value) {
        if (vertexes.contains(value))
            return false;
        model.add(0);
        adj_matrix.add(new ArrayList<>(model));
        for (int i = adj_matrix.size()-2; i >= 0; i--) {
            adj_matrix.get(i).add(0);
        }
        adj_matrix.get(adj_matrix.size()-1).set(adj_matrix.size()-1, 1);
        vertexes.add(value);
        return true;
    }

    public boolean addDirectedEdge(T vertex1, T vertex2) {
        int index1 = vertexes.indexOf(vertex1);
        int index2 = vertexes.indexOf(vertex2);
        if (index1 != -1 && index2 != -1) {
            adj_matrix.get(index1).set(index2, 1);
            return true;
        }
        return false;
    }

    public boolean addUndirectedEdge(T vertex1, T vertex2) {
        int index1 = vertexes.indexOf(vertex1);
        int index2 = vertexes.indexOf(vertex2);
        if (index1 != -1 && index2 != -1) {
            adj_matrix.get(index1).set(index2, 1);
            adj_matrix.get(index2).set(index1, 1);
            return true;
        }
        return false;
    }

    public ArrayList<T> DepthFirstSearch(T start_vertex) {
        Integer index = vertexes.indexOf(start_vertex);
        if (index == -1)
            return null;
        boolean found = false;
        Stack<Integer> stack = new Stack<Integer>();
        ArrayList<Integer> res = new ArrayList<>();
        boolean[] is_visited = new boolean[adj_matrix.size()];
        stack.push(index);
        res.add(index);
        is_visited[index] = true;
        while (!stack.isEmpty()) {
            for (int i = 0; i < adj_matrix.size(); i++) {
                if (adj_matrix.get(index).get(i) == 1 && !is_visited[i]) {
                    index = i;
                    stack.push(index);
                    res.add(index);
                    is_visited[index] = true;
                    found = true;
                    break;
                }
            }
            if (!found) {
                stack.pop();
                index = stack.peek();
            }
            found = false;
        }
        ArrayList<T> ret = new ArrayList<>();
        for (int i : res)
            ret.add(vertexes.get(i));
        return ret;
    }

    public ArrayList<T> BreadthFirstSearch(T start_vertex) {
        Integer index = vertexes.indexOf(start_vertex);
        if (index == -1)
            return null;
        Queue<Integer> queue = new Queue<>();
        ArrayList<Integer> res = new ArrayList<>();
        boolean[] is_visited = new boolean[adj_matrix.size()];
        queue.insert(index);
        res.add(index);
        is_visited[index] = true;
        while (!queue.isEmpty()) {
            index = queue.remove();
            for (int i = 0; i < adj_matrix.size(); i++) {
                if (adj_matrix.get(index).get(i) == 1 && !is_visited[i]) {
                    queue.insert(i);
                    res.add(i);
                    is_visited[i] = true;
                }
            }
        }
        ArrayList<T> ret = new ArrayList<>();
        for (int i : res)
            ret.add(vertexes.get(i));
        return ret;
    }

    public ArrayList<ArrayList<Integer>> Uorshall() {
        int size = adj_matrix.size();
        ArrayList<ArrayList<Integer>> access_matrix = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            access_matrix.add(new ArrayList<Integer>(adj_matrix.get(i)));
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    if (access_matrix.get(j).get(k) == 0) {
                        if (access_matrix.get(i).get(k) == 1 && access_matrix.get(j).get(i) == 1) {
                            access_matrix.get(j).set(k, 1);
                        }
                    }
                }
            }
        }
        showMatrix(access_matrix);
        return access_matrix;
    }

    public void getGraphConnectivity() {
        int counter_in = 0, counter_out = 0;
        Iterator<Integer> iterator;
        ArrayList<ArrayList<Integer>> access_matrix = Uorshall();
        for (int i = 0; i < access_matrix.size(); i++) {
            iterator = access_matrix.get(i).iterator();
            while (iterator.hasNext())
                counter_in += iterator.next() == 1 ? 1 : 0;
            counter_out += counter_in == access_matrix.size() ? 1 : 0;
            counter_in = 0;
        }
        if (counter_out == 0)
            checkGraphConnectivityIfUndirected(access_matrix);
        else if (counter_out == access_matrix.size())
            System.out.println("Graph is strongly connected");
        else
            System.out.println("Graph is connected");
    }

    public void showAdjacencyMatrix() {
        showMatrix(adj_matrix);
    }

}
