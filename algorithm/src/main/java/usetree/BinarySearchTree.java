package usetree;

/**
 * used to
 * Created by tianjin on 6/14/16.
 */
public class BinarySearchTree<T extends Comparable<? super T>> {

    private BinaryNode<T> root;  //root节点

    public BinarySearchTree() {
        this.root = null;
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contain(T x) {
        return contain(x, root);
    }

    public T findMin() {
        if (isEmpty()) throw new IllegalArgumentException();
        return findMin(root).element;
    }

    public T findMax() {
        if (isEmpty()) throw new IllegalArgumentException();
        return findMax(root).element;
    }


    public void insert(T x) {
        root = insert(x, root);
    }

    public void remove(T x) {
        root = remove(x, root);
    }

    /**
     * Internal method to find an item in a subtree
     *
     * @param x is item to search for
     * @param t is the node that roots the subtree
     * @return node containing the mached item
     */
    private boolean contain(T x, BinaryNode<T> t) {
        if (t == null) {
            return false;
        }

        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {
            return contain(x, t.left);
        } else if (compareResult > 0) {
            return contain(x, t.right);
        } else {
            return true;
        }
    }

    /**
     * Internal method to find the smallest item in the subtree
     *
     * @param t the node that roots the subtree
     * @return the smallest item
     */
    private BinaryNode<T> findMin(BinaryNode<T> t) {
        if (t == null) {
            return null;
        } else if (t.left == null) {
            return t;
        } else {
            return findMin(t.left);
        }
    }


    /**
     * Internal method to find the largest item in the subtree
     *
     * @param t the node that roots the subtree
     * @return the largest item
     */
    private BinaryNode<T> findMax(BinaryNode<T> t) {
        if (t != null) {
            while (t.right != null) {
                t = t.right;
            }
        }
        return t;
    }

    /**
     * Internal method to insert into the subtree
     *
     * @param x the item to insert
     * @param t the node that roots the subtree
     * @return the new root of the subtree
     */
    private BinaryNode<T> insert(T x, BinaryNode<T> t) {
        if (t == null) {
            return new BinaryNode<T>(x, null, null);
        }

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0) {
            t.left = insert(x, t.left);
        } else if (compareResult > 0) {
            t.right = insert(x, t.right);
        }

        return t;
    }

    /**
     * Internal method to remove from a subtree
     *
     * @param x the item to remove
     * @param t the node that roots the subtree
     * @return the new root of the subtree
     */
    private BinaryNode<T> remove(T x, BinaryNode<T> t) {
        if (t == null) {
            return t;
        }

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0) {
            t.left = remove(x, t.left);
        } else if (compareResult > 0) {
            t.right = remove(x, t.right);
        } else if (t.left != null && t.right != null) {
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } else {
            t = t.left != null ? t.left : t.right;
        }
        return t;
    }


    /**
     * 查找二叉树节点类
     *
     * @param <T>
     */
    private static class BinaryNode<T> {
        private T element;
        private BinaryNode<T> left;
        private BinaryNode<T> right;

        public BinaryNode(T element) {
            this(element, null, null);
        }

        public BinaryNode(T element, BinaryNode<T> left, BinaryNode<T> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }
    }
}
