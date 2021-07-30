package Trees;

public class RedBlackTree<T extends Comparable> {

    private class Node<T> {

        private T value;
        private boolean black;
        private Node<T> parent;
        private Node<T> leftChild;
        private Node<T> rightChild;

        public Node(T value, boolean black) {
            this.value = value;
            this.leftChild = null;
            this.rightChild = null;
            this.parent = null;
            this.black = black;
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

        public Node<T> getParent() {
            return parent;
        }

        public void setParent(Node<T> parent) {
            this.parent = parent;
        }

        public boolean isBlack() {
            return black;
        }

        public void changeColor() {
            this.black = black ? false : true;
        }

    }

    private Node<T> root;
    private int size;

    public RedBlackTree() {
        root = null;
        size = 0;
    }

    public Node getRoot() {
        return root;
    }

    public boolean isEmpty() {
        return root == null ? true : false;
    }


    //private:

    private void swapColors(Node<T> node) {
        if (node != root) node.changeColor();
        node.getLeftChild().changeColor();
        node.getRightChild().changeColor();
    }

    private void doRightTurn(Node<T> node) {
        Node<T> parent = node.getParent();
        Node<T> l_child = node.getLeftChild();
        node.setLeftChild(l_child.getRightChild());
        l_child.setRightChild(node);
        node.setParent(l_child);
        if (parent == null) {
            l_child.setParent(null);
            root = l_child;
        } else {
            if (parent.getLeftChild() == node)
                parent.setLeftChild(l_child);
            else
                parent.setRightChild(l_child);
            l_child.setParent(parent);
        }
    }

    private void doLeftTurn(Node<T> node) {
        Node<T> parent = node.getParent();
        Node<T> r_child = node.getRightChild();
        node.setRightChild(r_child.getLeftChild());
        r_child.setLeftChild(node);
        node.setParent(r_child);
        if (parent == null) {
            r_child.setParent(null);
            root = r_child;
        } else {
            if (parent.getLeftChild() == node)
                parent.setLeftChild(r_child);
            else
                parent.setRightChild(r_child);
            r_child.setParent(parent);
        }
    }

    private boolean isBlackWithRedChildren(Node<T> node) {
        if (node.isBlack() && !node.getRightChild().isBlack() && !node.getLeftChild().isBlack())
            return true;
        else
            return false;
    }

    private int getRelations(Node<T> node) {
        if (node.getParent().getLeftChild() == node &&
                node.getParent().getParent().getLeftChild() == node.getParent())
            return 1; //left outer grandchild
        else if (node.getParent().getRightChild() == node &&
                node.getParent().getParent().getLeftChild() == node.getParent())
            return 2; //left inner grandchild
        else if (node.getParent().getLeftChild() == node &&
                node.getParent().getParent().getRightChild() == node.getParent())
            return 3; //right inner grandchild
        else if (node.getParent().getLeftChild() == node &&
                node.getParent().getParent().getLeftChild() == node.getParent())
            return 4; //right outer grandchild
        return -1;
    }

    private boolean fixIfTwoReds(Node<T> node) {
        if (!node.isBlack() && !node.getParent().isBlack()) {
            switch (getRelations(node)) {
                case 1:
                    node.getParent().changeColor();
                    node.getParent().getParent().changeColor();
                    doRightTurn(node.getParent().getParent());
                    break;
                case 2:
                    node.changeColor();
                    node.getParent().getParent().changeColor();
                    doLeftTurn(node.getParent());
                    doRightTurn(node.getParent());
                    break;
                case 3:
                    node.changeColor();
                    node.getParent().getParent().changeColor();
                    doRightTurn(node.getParent());
                    doLeftTurn(node.getParent());
                    break;
                case 4:
                    node.getParent().changeColor();
                    node.getParent().getParent().changeColor();
                    doLeftTurn(node.getParent().getParent());
                    break;
            }
            return true;
        } else {
            return false;
        }
    }

    private int getCaseNumber(Node<T> x, Node<T> parent, Node<T> w) {
        if (x != null && !x.isBlack()) {
            return 0;
        } else {
            if (w != null && !w.isBlack()) {
                return 10;
            }
            if (w == null || w.isBlack()){
                if ((w.getLeftChild() == null || w.getLeftChild().isBlack())
                        && (w.getRightChild() == null || w.getRightChild().isBlack()))
                    return 20;
                if (parent.getLeftChild() == x &&
                        (w.getLeftChild() != null && !w.getLeftChild().isBlack()) &&
                        (w.getRightChild() == null || w.getRightChild().isBlack()))
                    return 31;
                if (parent.getRightChild() == x &&
                        (w.getLeftChild() == null || w.getLeftChild().isBlack()) &&
                        (w.getRightChild() != null && !w.getRightChild().isBlack()))
                    return 32;
                if (parent.getLeftChild() == x &&
                        (w.getRightChild() != null && !w.getRightChild().isBlack()))
                    return 41;
                if (parent.getRightChild() == x &&
                        (w.getLeftChild() != null && !w.getLeftChild().isBlack()))
                    return 42;
            }
        }
        return -1;
    }

    private void fixDelete(Node<T> x, Node<T> parent) {
        Node<T> w = parent.getLeftChild() == x ? parent.getRightChild() : parent.getLeftChild();
        int case_num = getCaseNumber(x, parent, w);
        switch (case_num) {
            case 0:
                if (!x.isBlack())
                    x.changeColor();
                break;
            case 10:
                if (!w.isBlack())
                    w.changeColor();
                if(parent.isBlack())
                    parent.changeColor();
                if (parent.getLeftChild() == x)
                    doLeftTurn(parent);
                else
                    doRightTurn(parent);
                fixDelete(x, parent);
                break;
            case 20:
                if (w.isBlack())
                    w.changeColor();
                x = parent;
                if (!x.isBlack())
                    x.changeColor();
                else
                    if (x.getParent() != null)
                        fixDelete(x, x.getParent());
                break;
            case 31:
                if (!w.getLeftChild().isBlack())
                    w.getLeftChild().changeColor();
                if (w.isBlack())
                    w.changeColor();
                doRightTurn(w);
                fixDelete(x, parent);
                break;
            case 32:
                if (!w.getRightChild().isBlack())
                    w.getRightChild().changeColor();
                if (w.isBlack())
                    w.changeColor();
                doLeftTurn(w);
                fixDelete(x, parent);
                break;
            case 41:
                if (parent.isBlack() != w.isBlack())
                    w.changeColor();
                if (!parent.isBlack())
                    parent.changeColor();
                if (!w.getRightChild().isBlack())
                    w.getRightChild().changeColor();
                doLeftTurn(parent);
                break;
            case 42:
                if (parent.isBlack() != w.isBlack())
                    w.changeColor();
                if (!parent.isBlack())
                    parent.changeColor();
                if (!w.getLeftChild().isBlack())
                    w.getLeftChild().changeColor();
                doRightTurn(parent);
                break;
        }

    }


    //public:

    public void insert(T value) {
        if (isEmpty()) {
            root = new Node<T>(value, true);
        } else {
            Node<T> node = root;
            while (node != null) {
                if (node.getRightChild() != null && node.getLeftChild() != null) {
                    while (isBlackWithRedChildren(node)) {
                        swapColors(node);
                        if (fixIfTwoReds(node))
                            node = root;
                    }
                }
                if (value.compareTo(node.getValue()) > 0) {
                    if (node.getRightChild() == null) {
                        node.setRightChild(new Node<>(value, false));
                        node.getRightChild().setParent(node);
                        fixIfTwoReds(node.getRightChild());
                        break;
                    } else {
                        node = node.getRightChild();
                    }
                } else {
                    if (node.getLeftChild() == null) {
                        node.setLeftChild(new Node<>(value, false));
                        node.getLeftChild().setParent(node);
                        fixIfTwoReds(node.getLeftChild());
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
        Node<T> node = find(value), x;
        if (node == null)
            return false;

        if (node.getLeftChild() != null && node.getRightChild() != null) { //two childer
            Node<T> successor = node.getRightChild();
            while (successor.getLeftChild() != null) {
                successor = successor.getLeftChild();
            }
            x = successor.getRightChild();
            T val = successor.getValue();
            if (successor.getParent().getLeftChild() == successor)
                successor.getParent().setLeftChild(x);
            else
                successor.getParent().setRightChild(x);
            if (x != null)
                x.setParent(successor.getParent());
            node.setValue(val);

            if (successor.isBlack())
                fixDelete(x, successor.getParent());
        } else if (node.getLeftChild() != null && node.getRightChild() == null ||
                node.getLeftChild() == null && node.getRightChild() != null) { //one child
            x = node.getLeftChild() != null ? node.getLeftChild() : node.getRightChild();
            if (node == root) {
                x.setParent(null);
                root = x;
                if (!x.isBlack())
                    x.changeColor();
            } else {
                if (node.getParent().getLeftChild() == node)
                    node.getParent().setLeftChild(x);
                else
                    node.getParent().setRightChild(x);
                x.setParent(node.getParent());
                if (node.isBlack()) {
                    if (x.isBlack())
                        fixDelete(x, x.getParent());
                    else
                        x.changeColor();
                } else {
                    if (x.isBlack()) {
                        x.changeColor();
                        fixDelete(x, x.getParent());
                    }
                }
            }
        } else { //no children
            x = null;
            if (node == root) {
                root = null;
            } else {
                if (node.getParent().getLeftChild() == node)
                    node.getParent().setLeftChild(x);
                else
                    node.getParent().setRightChild(x);
                if (node.isBlack())
                    fixDelete(x, node.getParent());
            }
        }
        size--;
        return true;
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

    public void showInOrder(Node<T> node) {
        if (node != null) {
            showInOrder(node.getLeftChild());
            System.out.print(node.getValue());
            System.out.print(node.isBlack() ? "B " : "R ");
            showInOrder(node.getRightChild());
        }
    }

    public void showPreOrder(Node<T> node) {
        if (node != null) {
            System.out.print(node.getValue());
            System.out.print(node.isBlack() ? "B " : "R ");
            showPreOrder(node.getLeftChild());
            showPreOrder(node.getRightChild());
        }
    }

    public void showPostOrder(Node<T> node) {
        if (node != null) {
            showPostOrder(node.getLeftChild());
            showPostOrder(node.getRightChild());
            System.out.print(node.getValue());
            System.out.print(node.isBlack() ? "B " : "R ");
        }
    }

    public int getSize() {
        return size;
    }


}
