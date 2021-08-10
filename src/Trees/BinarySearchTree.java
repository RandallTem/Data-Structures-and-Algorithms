package Trees;

public class BinarySearchTree<T extends Comparable> {

    private class Node<T> {
        private T value;
        private Node<T> leftChild;
        private Node<T> rightChild;

        public Node(T value) {
            this.value = value;
            leftChild = null;
            rightChild = null;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Node node) {
            this.leftChild = node;
        }

        public Node getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node node) {
            this.rightChild = node;
        }
    }

    private Node<T> root;
    private int size;

    //constructor:

    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    //public:

    public Node getRoot() {
        return root;
    }

    public boolean isEmpty() {
        return root == null ? true : false;
    }

    public Node find(T value) {
        Node<T> node = root;
        while (node != null && !node.getValue().equals(value)) {
            if (value.compareTo(node.getValue()) > 0)
                node = node.getRightChild();
            else
                node = node.getLeftChild();
        }
        return node;
    }

    public void insert(T value) {
        if (isEmpty()) {
            root = new Node<>(value);
        } else {
            Node node = root;
            while (node != null) {
                if (value.compareTo(node.getValue()) > 0) {
                    if (node.getRightChild() == null) {
                        node.setRightChild(new Node<>(value));
                        break;
                    } else {
                        node = node.getRightChild();
                    }
                } else {
                    if (node.getLeftChild() == null) {
                        node.setLeftChild(new Node<>(value));
                        break;
                    } else {
                        node = node.getLeftChild();
                    }
                }
            }
        }
        size++;
    }

    public boolean delete(T value) {
        Node<T> parent = null;
        Node<T> node = root;
        while (node != null && !node.getValue().equals(value)) {
            parent = node;
            if (value.compareTo(node.getValue()) > 0)
                node = node.getRightChild();
            else
                node = node.getLeftChild();
        }
        if (node == null) {
            return false;
        } else {
            if (node.getRightChild() == null && node.getLeftChild() == null) {
                if (node == root) {
                    root = null;
                } else {
                    if (parent.getRightChild() == node)
                        parent.setRightChild(null);
                    else
                        parent.setLeftChild(null);
                }
            } else if (node.getRightChild() == null && node.getLeftChild() != null) {
                if (node == root) {
                    root = node.getLeftChild();
                } else {
                    if (parent.getRightChild() == node)
                        parent.setRightChild(node.getLeftChild());
                    else
                        parent.setLeftChild(node.getLeftChild());
                }
            } else if (node.getRightChild() != null && node.getLeftChild() == null) {
                if (node == root) {
                    root = node.getRightChild();
                } else {
                    if (parent.getRightChild() == node)
                        parent.setRightChild(node.getRightChild());
                    else
                        parent.setLeftChild(node.getRightChild());
                }
            } else {
                Node<T> successor = node.getRightChild();
                while (successor.getLeftChild() != null) {
                    successor = successor.getLeftChild();
                }
                T val = successor.getValue();
                delete(val);
                size++;
                node.setValue(val);
            }
            size--;
            return true;
        }
    }

    public T getMin() {
        T min = null;
        if (!isEmpty()) {
            Node<T> node = root;
            while (node.leftChild != null) {
                node = node.getLeftChild();
            }
            min = node.getValue();
        }
        return min;
    }

    public T getMax() {
        T max = null;
        if (!isEmpty()) {
            Node<T> node = root;
            while (node.rightChild != null) {
                node = node.getRightChild();
            }
            max = node.getValue();
        }
        return max;
    }

    public void showInOrder(Node node) {
        if (node != null) {
            showInOrder(node.getLeftChild());
            System.out.print(node.getValue()+" ");
            showInOrder(node.getRightChild());
        }
    }

    public void showPreOrder(Node node) {
        if (node != null) {
            System.out.print(node.getValue()+" ");
            showPreOrder(node.getLeftChild());
            showPreOrder(node.getRightChild());
        }
    }

    public void showPostOrder(Node node) {
        if (node != null) {
            showPostOrder(node.getLeftChild());
            showPostOrder(node.getRightChild());
            System.out.print(node.getValue()+" ");
        }
    }

    public int getSize() {
        return size;
    }

}
