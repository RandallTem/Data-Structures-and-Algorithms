package Graphs;

import java.util.ArrayList;

public class WeightedGraph<T> {

    private ArrayList<ArrayList<Integer>> adj_matrix;
    private ArrayList<Integer> model;
    private ArrayList<T> vertexes;

    //constructor:

    public WeightedGraph() {
        adj_matrix = new ArrayList<>();
        model = new ArrayList<>();
        vertexes = new ArrayList<>();
    }

    //private:

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
        model.add(-1);
        adj_matrix.add(new ArrayList<>(model));
        for (int i = adj_matrix.size()-2; i >= 0; i--) {
            adj_matrix.get(i).add(-1);
        }
        adj_matrix.get(adj_matrix.size()-1).set(adj_matrix.size()-1, 0);
        vertexes.add(value);
        return true;
    }

    public boolean addDirectedEdge(T vertex1, T vertex2, int weight) {
        int index1 = vertexes.indexOf(vertex1);
        int index2 = vertexes.indexOf(vertex2);
        if (index1 != -1 && index2 != -1) {
            adj_matrix.get(index1).set(index2, weight);
            return true;
        }
        return false;
    }

    public boolean addUndirectedEdge(T vertex1, T vertex2, int weight) {
        int index1 = vertexes.indexOf(vertex1);
        int index2 = vertexes.indexOf(vertex2);
        if (index1 != -1 && index2 != -1) {
            adj_matrix.get(index1).set(index2, weight);
            adj_matrix.get(index2).set(index1, weight);
            return true;
        }
        return false;
    }

    public ArrayList<Integer> Dijkstra(T vertex) {
        int index = vertexes.indexOf(vertex);
        if (index == -1)
            return null;
        ArrayList<Integer> weights = new ArrayList<>(model);
        boolean[] is_visited = new boolean[weights.size()];
        is_visited[index] = true;
        weights.set(index, 0);
        while (true) {
            for (int i = 0; i < adj_matrix.size(); i++) {
                if (adj_matrix.get(index).get(i) > 0) {
                    if (weights.get(i) == -1 || weights.get(i) > (weights.get(index) + adj_matrix.get(index).get(i)))
                        weights.set(i, (weights.get(index) + adj_matrix.get(index).get(i)));

                }
            }
            int min = -1;
            for (int i = 0; i < weights.size(); i++) {
                if (!is_visited[i] && (min == -1 || weights.get(min) > weights.get(i)) && weights.get(i) != -1)
                    min = i;
            }
            if (min == -1)
                break;
            index = min;
            is_visited[index] = true;
        }
        return weights;
    }

    public ArrayList<ArrayList<Integer>> Floyd() {
        int size = adj_matrix.size();
        ArrayList<ArrayList<Integer>> access_matrix = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            access_matrix.add(new ArrayList<Integer>(adj_matrix.get(i)));
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    if (access_matrix.get(i).get(k) > 0 && access_matrix.get(j).get(i) > 0) {
                        if ((access_matrix.get(i).get(k) + access_matrix.get(j).get(i)) < access_matrix.get(j).get(k)
                            || access_matrix.get(j).get(k) == -1)
                            access_matrix.get(j).set(k, access_matrix.get(i).get(k) + access_matrix.get(j).get(i));
                    }
                }
            }
        }
        showMatrix(access_matrix);
        return access_matrix;
    }

    public int getShortestPath(T vertex1, T vertex2) {
        ArrayList<Integer> weights = Dijkstra(vertex1);
        int index = vertexes.indexOf(vertex2);
        if (weights == null || index == -1)
            return -1;
        return weights.get(index);
    }


    public void showAdjacencyMatrix() {
        showMatrix(adj_matrix);
    }

}
