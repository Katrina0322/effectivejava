package usetree;

/**
 * used to
 * Created by tianjin on 6/15/16.
 */
public class AvlTree<T extends Comparable<T>> {

    private AvlNode<T> root;

    public AvlTree() {
        this.root = null;
    }

    /**
     * the height of the tree
     * @return
     */
    public int height(){ return height(root); }

    public boolean isEmpty() {
        return root == null;
    }

    public AvlNode<T> insert(T x){ return insert(x,root); }

    public AvlNode<T> find(T x){ return find(x,root); }

    public AvlNode<T> max(){ return findMax(root); }

    public AvlNode<T> min(){ return findMin(root); }

    public void printTree(){ printTree(root);}


    /**
     * @param x
     * @param t
     * @return
     */
    private AvlNode<T> insert(T x, AvlNode<T> t) {
        if (t == null) {
            return new AvlNode<T>(x);
        }

        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {
            t.left = insert(x, t.left);
            if (height(t.left) - height(t.right) == 2) {
                if (x.compareTo(t.left.element) < 0) {
                    rotateWithLeftChild(t);
                } else {
                    doubleWithLeftChild(t);
                }
            }

        } else if (compareResult > 0) {
            t.right = insert(x, t.right);
            if (height(t.right) - height(t.left) == 2) {
                if (x.compareTo(t.right.element) > 0) {
                    rotateRightChild(t);
                } else {
                    doubleWithRightChild(t);
                }
            }
        }
        t.height = Math.max(height(t.left), height(t.right)) + 1;
        return t;
    }


    private int height(AvlNode<T> t) {
        return t == null ? -1 : t.height;
    }


    private AvlNode findMin(AvlNode<T> t) {
        if (t == null)
            return t;

        while (t.left != null)
            t = t.left;
        return t;
    }

    private AvlNode findMax(AvlNode<T> t) {
        if (t == null)
            return t;

        while (t.right != null)
            t = t.right;
        return t;
    }


    private AvlNode find(T x, AvlNode<T> t) {
        while (t != null)
            if (x.compareTo(t.element) < 0)
                t = t.left;
            else if (x.compareTo(t.element) > 0)
                t = t.right;
            else
                return t;    // Match

        return null;   // No match
    }

    private void printTree(AvlNode<T> t) {
        if (t != null) {
            printTree(t.left);
            System.out.println(t.element);
            printTree(t.right);
        }
    }


    private AvlNode<T> rotateWithLeftChild(AvlNode<T> k2) {
        AvlNode<T> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;
        return k1;
    }

    private AvlNode<T> rotateRightChild(AvlNode<T> k1) {
        AvlNode<T> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
        k2.height = Math.max(height(k2.left), k1.height) + 1;
        return k2;
    }

    private AvlNode<T> doubleWithLeftChild(AvlNode<T> k3) {
        k3.left = rotateRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    private AvlNode<T> doubleWithRightChild(AvlNode<T> k2) {
        k2.right = rotateWithLeftChild(k2.right);
        return rotateRightChild(k2);
    }


    private static class AvlNode<T> {
        private T element;
        private AvlNode<T> left;
        private AvlNode<T> right;
        private int height;

        public AvlNode(T element) {
            this(element, null, null);
        }

        public AvlNode(T element, AvlNode<T> left, AvlNode<T> right) {
            this.element = element;
            this.left = left;
            this.right = right;
            this.height = 0;
        }
    }
}
