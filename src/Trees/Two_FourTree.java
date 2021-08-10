package Trees;

import java.util.ArrayList;
import java.util.Arrays;

public class Two_FourTree<T extends Comparable> {

    private class Node<T extends Comparable> {
        ArrayList<T> values;
        ArrayList<Node<T>> children;

        public Node(T value) {
            values = new ArrayList<>(Arrays.asList(value, null, null));
            children = new ArrayList<>(Arrays.asList(null, null, null, null));
        }

        public T getValue(int index) {
            return values.get(index);
        }

        public void setValue(int index, T value) {
            values.set(index, value);
        }

        public void insertValue(T value) {
            if (isFull())
                return;
            int index = 0;
            while (values.get(index) != null && value.compareTo(values.get(index)) > 0) {
                index++;
            }
            for (int i = 1; i >= index; i--) {
                values.set(i+1, values.get(i));
            }
            values.set(index, value);
        }

        public boolean deleteValue(int index) {
            if (values.get(index) == null) {
                return false;
            } else {
                for (; index < 2; index++) {
                    values.set(index, values.get(index+1));
                }
                values.set(2, null);
                return true;
            }
        }

        public Node<T> getChild(int index) {
            return children.get(index);
        }

        public void setChild(int index, Node<T> node) {
            children.set(index, node);
        }

        public int contains(T value) {
           int counter = 0;
           while (counter < 3 && values.get(counter) != null && !values.get(counter).equals(value)) {
               counter++;
           }
           if (counter == 3 || values.get(counter) == null)
               return -1;
           else
               return counter;
        }

        public boolean isFull() {
            return values.get(0) != null && values.get(1) != null && values.get(2) != null ? true : false;
        }

        public boolean isLeaf() {
            return children.get(0) == null && children.get(1) == null &&
                    children.get(2) == null && children.get(3) == null ? true : false;
        }

    }

    private Node<T> root;
    private int size;

    //constructor:

    public Two_FourTree() {
        root = null;
        size = 0;
    }

    //private:

    private int getIndexOfChild(T value, Node<T> node) {
        int index = 0;
        while (index < 3 && node.getValue(index) != null && value.compareTo(node.getValue(index)) > 0) {
            index++;
        }
        return index;
    }

    private Node<T> breakNode(Node<T> node, Node<T> parent) {
        if (node == root) {
            Node<T> new_root = new Node<>(node.getValue(1));
            root = new_root;
            Node<T> brother = new Node<>(node.getValue(2));
            brother.setChild(0, node.getChild(2));
            brother.setChild(1, node.getChild(3));
            node.setChild(2, null);
            node.setChild(3, null);
            new_root.setChild(0, node);
            new_root.setChild(1, brother);
            node.setValue(1, null);
            node.setValue(2, null);
            return new_root;
        } else {
            Node<T> brother = new Node<>(node.getValue(2));
            brother.setChild(0, node.getChild(2));
            brother.setChild(1,node.getChild(3));

            node.setChild(2, null);
            node.setChild(3, null);
            node.setValue(2, null);

            parent.insertValue(node.getValue(1));
            node.setValue(1, null);
            int child_index = getIndexOfChild(brother.getValue(0), parent);
            for (int i = 2; i >= child_index; i--) {
                parent.setChild(i+1, parent.getChild(i));
            }
            parent.setChild(child_index, brother);
            return parent;
        }
    }

    private T findSuccessor(Node<T> node, T value) {
        Node<T> successor = node.getChild(node.contains(value)+1);
        while (successor.getChild(0) != null) {
            successor = successor.getChild(0);
        }
        return successor.getValue(0);
    }

    private int getCaseNumber(Node<T> node, Node<T> parent, Node<T> brother) {
        if (node.getValue(1) != null) {
            return 0;
        }
        if (brother.getValue(1) == null) {
            if (parent == root && parent.getValue(1) == null)
                return 1;
            else
                return 2;
        } else {
            return 3;
        }
    }

    private void adaptTreeForDeletion(Node<T> node, Node<T> parent) {
        Node<T> brother;
        int child_number = getIndexOfChild(node.getValue(0), parent);

        if (child_number == 0) {
            brother = parent.getChild(1);
        } else if (child_number == 1) {
            if (parent.getChild(2) != null && parent.getChild(2).getValue(1) != null)
                brother = parent.getChild(2);
            else
                brother = parent.getChild(0);
        } else if (child_number == 2) {
            if (parent.getChild(3) != null && parent.getChild(3).getValue(1) != null)
                brother = parent.getChild(3);
            else
                brother = parent.getChild(1);
        } else {
            brother = parent.getChild(2);
        }

        int case_num = getCaseNumber(node, parent, brother);

        int index_node, index_brother;
        switch (case_num) {
            case 1:
                Node<T> new_root = new Node<>(root.getValue(0));
                new_root.insertValue(node.getValue(0));
                new_root.insertValue(brother.getValue(0));
                if (brother == root.getChild(0)) {
                    new_root.setChild(0, brother.getChild(0));
                    new_root.setChild(1, brother.getChild(1));
                    new_root.setChild(2, node.getChild(0));
                    new_root.setChild(3, node.getChild(1));
                } else {
                    new_root.setChild(0, node.getChild(0));
                    new_root.setChild(1, node.getChild(1));
                    new_root.setChild(2, brother.getChild(0));
                    new_root.setChild(3, brother.getChild(1));
                }
                parent = new_root;
                root = new_root;
                break;
            case 2:
                index_node = getIndexOfChild(node.getValue(0), parent);
                index_brother = getIndexOfChild(brother.getValue(0), parent);
                if (index_brother > index_node) {
                    node.insertValue(parent.getValue(index_node));
                    parent.deleteValue(index_node);
                    node.insertValue(brother.getValue(0));
                    for (int i = index_brother; i < 3; i++) {
                        parent.setChild(i, parent.getChild(i + 1));
                    }
                } else {
                    node.insertValue(parent.getValue(index_node-1));
                    parent.deleteValue(index_node-1);
                    node.insertValue(brother.getValue(0));
                    parent.setChild(index_brother, parent.getChild(index_node));
                    for (int i = index_node; i < 3; i++) {
                        parent.setChild(i, parent.getChild(i + 1));
                    }
                }
                parent.setChild(3, null);
                break;
            case 3:
                index_node = getIndexOfChild(node.getValue(0), parent);
                index_brother = getIndexOfChild(brother.getValue(0), parent);
                if (index_brother > index_node) {
                    node.insertValue(parent.getValue(index_node));
                    parent.deleteValue(index_node);
                    parent.insertValue(brother.getValue(0));
                    brother.deleteValue(0);
                    node.setChild(2, brother.getChild(0));
                    for (int i = 0; i < 3; i++) {
                        brother.setChild(i, brother.getChild(i+1));
                    }
                    brother.setChild(3, null);
                } else {
                    node.insertValue(parent.getValue(index_node-1));
                    parent.deleteValue(index_node-1);
                    node.setChild(1, node.getChild(0));
                    if (brother.getValue(2) == null) {
                        parent.insertValue(brother.getValue(1));
                        brother.deleteValue(1);
                        node.setChild(0, brother.getChild(3));
                        brother.setChild(3, null);
                    } else {
                        parent.insertValue(brother.getValue(2));
                        brother.deleteValue(2);
                        node.setChild(0, brother.getChild(2));
                        brother.setChild(2, null);
                    }
                }
                break;
        }
    }

    //public:

    public Node<T> getRoot() {
        return root;
    }

    public boolean isEmpty() {
        return root == null ? true : false;
    }

    public void insert(T value) {
        if (isEmpty()) {
            root = new Node<>(value);
        } else {
            Node<T> node = root;
            Node<T> parent = null;
            while (!node.isLeaf()) {
                if (node.isFull()) {
                    node = breakNode(node, parent);
                }
                parent = node;
                node = node.getChild(getIndexOfChild(value, node));
            }
            if (node.isFull()) {
                node = breakNode(node, parent);
                node = node.getChild(getIndexOfChild(value, node));
            }
            node.insertValue(value);
        }
        size++;
    }

    public boolean delete(T value) {
        Node<T> node = root;
        Node<T> parent = null;
        while (node != null && node.contains(value) == -1) {
            parent = node;
            node = node.getChild(getIndexOfChild(value, node));
            if (node == null)
                break;
            if (node.contains(value) != -1) {
                adaptTreeForDeletion(node, parent);
                node = find(value);
            } else {
                adaptTreeForDeletion(node, parent);
            }
        }
        if (node == null)
            return false;
        if (node.isLeaf()) {
            node.deleteValue(node.contains(value));
            if (node == root && node.getValue(0) == null)
                root = null;
        } else {
            T val = findSuccessor(node, value);
            delete(val);
            size++;
            node = find(value);
            node.setValue(node.contains(value), val);
        }
        size--;
        return true;
    }


    public Node<T> find(T value) {
        Node<T> node = root;
        while (node != null && node.contains(value) == -1) {
            node = node.getChild(getIndexOfChild(value, node));
        }
        return node;
    }

    public T getMin() {
        T min = null;
        if(!isEmpty()) {
            Node<T> node = root;
            while (node.getChild(0) != null) {
                node = node.getChild(0);
            }
            min = node.getValue(0);
        }
        return min;
    }

    public T getMax() {
        T max = null;
        if (!isEmpty()) {
            Node<T> node = root;
            while(true) {
                int counter = 2;
                while (node.getValue(counter) == null) {
                    counter--;
                }
                if (node.getChild(counter+1) != null) {
                    node = node.getChild(counter+1);
                } else {
                    max = node.getValue(counter);
                    break;
                }
            }
        }
        return max;
    }

    public void showNode(Node<T> node) {
        System.out.println(node.getValue(0)+" "+
                node.getValue(1)+" "+
                node.getValue(2));
    }

    public void showPreOrder(Node<T> node) {
        if (node != null) {
            showNode(node);
            showPreOrder(node.getChild(0));
            showPreOrder(node.getChild(1));
            showPreOrder(node.getChild(2));
            showPreOrder(node.getChild(3));
        }
    }

    public void showPostOrder(Node<T> node) {
        if (node != null) {
            showPostOrder(node.getChild(0));
            showPostOrder(node.getChild(1));
            showPostOrder(node.getChild(2));
            showPostOrder(node.getChild(3));
            showNode(node);
        }
    }

    public int getSize() {
        return size;
    }
}
