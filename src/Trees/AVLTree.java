package Trees;

public class AVLTree<T extends Comparable> {

    private class Node<T> {
        private T value;
        private int height;
        private Node<T> leftChild;
        private Node<T> rightChild;
        private Node<T> parent;

        public Node(T value, Node<T> parent) {
            this.value = value;
            this.height = 0;
            this.leftChild = null;
            this.rightChild = null;
            this.parent = parent;
        }

        public T getValue() { return value; }

        public void setValue(T value) { this.value = value; }

        public int getHeight() { return height; }

        public void setHeight(int height) { this.height = height; }

        public Node<T> getParent() { return parent; }

        public void setParent(Node<T> parent) { this.parent = parent; }

        public Node<T> getLeftChild() { return leftChild; }

        public void setLeftChild(Node<T> leftChild) { this.leftChild = leftChild; }

        public Node<T> getRightChild() { return rightChild; }

        public void setRightChild(Node<T> rightChild) { this.rightChild = rightChild; }
    }

    private Node<T> root;
    private int size;

    //constructor:

    public AVLTree() {
        root = null;
        size = 0;
    }

    //private:

    private void doRightTurn(Node<T> node) {
        Node<T> left_child = node.getLeftChild();
        left_child.setParent(node.getParent());
        node.setParent(left_child);
        node.setLeftChild(left_child.getRightChild());
        if (node.getLeftChild() != null)
            node.getLeftChild().setParent(node);
        left_child.setRightChild(node);
        int left_height = node.getLeftChild() == null ? -1 : node.getLeftChild().getHeight();
        int right_height = node.getRightChild() == null ? -1 : node.getRightChild().getHeight();
        node.setHeight(left_height >= right_height ? left_height + 1 : right_height + 1);
        left_height = left_child.getLeftChild() == null ? -1 : left_child.getLeftChild().getHeight();
        right_height = left_child.getRightChild() == null ? -1 : left_child.getRightChild().getHeight();
        left_child.setHeight(left_height >= right_height ? left_height + 1 : right_height + 1);
        if (node == root)
            root = left_child;
        else
            if (left_child.getParent().getLeftChild() == node)
                left_child.getParent().setLeftChild(left_child);
            else
                left_child.getParent().setRightChild(left_child);
    }

    private void doLeftTurn(Node<T> node) {
        Node<T> right_child = node.getRightChild();
        right_child.setParent(node.getParent());
        node.setParent(right_child);
        node.setRightChild(right_child.getLeftChild());
        if (node.getRightChild() != null)
            node.getRightChild().setParent(node);
        right_child.setLeftChild(node);
        int left_height = node.getLeftChild() == null ? -1 : node.getLeftChild().getHeight();
        int right_height = node.getRightChild() == null ? -1 : node.getRightChild().getHeight();
        node.setHeight(left_height >= right_height ? left_height + 1 : right_height + 1);
        left_height = right_child.getLeftChild() == null ? -1 : right_child.getLeftChild().getHeight();
        right_height = right_child.getRightChild() == null ? -1 : right_child.getRightChild().getHeight();
        right_child.setHeight(left_height >= right_height ? left_height + 1 : right_height + 1);
        if (node == root)
            root = right_child;
        else
        if (right_child.getParent().getLeftChild() == node)
            right_child.getParent().setLeftChild(right_child);
        else
            right_child.getParent().setRightChild(right_child);
    }

    private int getBalanceValue(Node<T> node) {
        int left_height = node.getLeftChild() != null ? node.getLeftChild().getHeight() : -1;
        int right_height = node.getRightChild() != null ? node.getRightChild().getHeight() : -1;
        return left_height - right_height;
    }

    private void fixBalance(Node<T> node) {
        if (node != null) {
            int balance = getBalanceValue(node);
            if (balance > 1) {
                if (node.getLeftChild().getLeftChild() == null)
                    doLeftTurn(node.getLeftChild());
                doRightTurn(node);
            } else if (balance < -1) {
                if (node.getRightChild().getRightChild() == null)
                    doRightTurn(node.getRightChild());
                doLeftTurn(node);
            } else {
                int left_height = node.getLeftChild() == null ? -1 : node.getLeftChild().getHeight();
                int right_height = node.getRightChild() == null ? -1 : node.getRightChild().getHeight();
                node.setHeight(left_height >= right_height ? left_height + 1 : right_height + 1);
            }
            fixBalance(node.getParent());
        }
    }

    //public:

    public Node getRoot() {
        return root;
    }

    public boolean isEmpty() {
        return root == null ? true : false;
    }

    public void insert(T value) {
        if (isEmpty()) {
            root = new Node<T>(value, null);
        } else {
            Node<T> node = root;
            while (node != null) {
                if (value.compareTo(node.getValue()) > 0) {
                    if (node.getRightChild() == null) {
                        node.setRightChild(new Node<T>(value, node));
                        fixBalance(node.getRightChild());
                        break;
                    } else {
                        node = node.getRightChild();
                    }
                } else {
                    if (node.getLeftChild() == null) {
                        node.setLeftChild(new Node<T>(value, node));
                        fixBalance(node.getLeftChild());
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
        Node<T> node = find(value);
        if (node == null) {
            return false;
        } else {
            if (node.getLeftChild() == null && node.getRightChild() == null) {
                if (node == root) {
                    root = null;
                } else {
                    if (node.getParent().getLeftChild() == node)
                        node.getParent().setLeftChild(null);
                    else
                        node.getParent().setRightChild(null);
                    fixBalance(node.getParent());
                }
            } else if (node.getLeftChild() != null && node.getRightChild() == null) {
                if (node == root) {
                    root = node.getLeftChild();
                    node.getLeftChild().setParent(null);
                    fixBalance(root);
                } else {
                    if (node.getParent().getLeftChild() == node)
                        node.getParent().setLeftChild(node.getLeftChild());
                    else
                        node.getParent().setRightChild(node.getLeftChild());
                    fixBalance(node.getParent());
                }
            } else if (node.getLeftChild() == null && node.getRightChild() != null) {
                if (node == root) {
                    root = node.getRightChild();
                    node.getRightChild().setParent(null);
                    fixBalance(root);
                } else {
                    if (node.getParent().getLeftChild() == node)
                        node.getParent().setLeftChild(node.getRightChild());
                    else
                        node.getParent().setRightChild(node.getRightChild());
                    fixBalance(node.getParent());
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

    public Node<T> find(T value) {
        Node<T> node = root;
        while (node != null && !node.getValue().equals(value)) {
            if (value.compareTo(node.getValue()) > 0)
                node = node.getRightChild();
            else
                node = node.getLeftChild();
        }
        return node;
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
