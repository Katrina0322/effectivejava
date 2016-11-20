package usetree;

/**
 * Created by spark on 11/18/16.
 */
public class RedBlackTree<T extends Comparable<T>> {

    private Node<T> root;

    public void deleteMin(){
        if(!isRed(root.left) && !isRed(root.right)) root.color = true;
        root = deleteMin(root);
        if(!isEmpty()) root.color = false;
    }

    public void deleteMax(){
        if(!isRed(root.left) && !isRed(root.right)) root.color = true;
        root = deleteMax(root);
        if(!isEmpty()) root.color = false;
    }

    public void put(T x){
        root =  put(x,root);
        root.color = false;
    }


    public void delete(T x){
        if(!isRed(root.left) && !isRed(root.right)) root.color = true;
        root =  delete(x,root);
        if(!isEmpty()) root.color = false;
    }

    public boolean isEmpty() {
        return root == null;
    }
    
    /**
     * 指向该节点链接的颜色是否为红色
     * @param h
     * @return
     */
    private boolean isRed(Node<T> h){
        if(h== null) return false;
        return h.color;
    }
    
    private void flipColors(Node<T> h){
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    /**
     * 插入
     * @param t
     * @param h
     * @return
     */
    private Node<T> put(T t,Node<T> h){
        if( h == null) return new Node<>(t,1,true);
        int cmp = t.compareTo(h.element);
        if(cmp < 0){
            h.left = put(t,h.left);
        }else if(cmp > 0){
            h.right = put(t,h.right);
        }else{
            h.element = t;
        }
        
        if(isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if(isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if(isRed(h.left) && isRed(h.right))  flipColors(h);
        
        h.size = h.left.size + h.right.size + 1;
        return h;
    }

    /**
     * 查找
     * @param x
     * @param h
     * @return
     */
    private Node<T> find(T x,Node<T> h){
//        while(x != null) {
//            int cmp = x.compareTo(h.element);
//            if(cmp < 0) {
//                h = h.left;
//            }
//            else if(cmp > 0) {
//                h = h.right;
//            }
//            else {
//                return h;
//            }
//        }
//        return null;

        if(h == null) return null;
        int cmp = x.compareTo(h.element);
        if(cmp < 0){
            return find(x,h.left);
        }else if( cmp > 0){
            return find(x,h.right);
        }else{
            return h;
        }
    }

    private Node<T> delete(T x,Node<T> h){
        if(x.compareTo(h.element) < 0) {
            if(!isRed(h.left) && !isRed(h.left.left)) {
                h = moveRedLeft(h);
            }
            h.left = delete(x,h.left);
        }
        else {
            if(isRed(h.left)) {
                h = rotateRight(h);
            }
            if (x.compareTo(h.element) == 0 && (h.right == null)) {
                return null;
            }
            if (!isRed(h.right) && !isRed(h.right.left)) {
                h = moveRedRight(h);
            }
            if (x.compareTo(h.element) == 0) {
                Node<T> tmp = min(h.right);
                h.element = tmp.element;
                h.right = deleteMin(h.right);
            }
            else {
                h.right = delete(x,h.right);
            }
        }
        return balance(h);
    }
    
    private Node<T> deleteMin(Node<T> h){
        if(h.left == null) return null;
        if (!isRed(h.left) && !isRed(h.left.left)) {
            h = moveRedLeft(h);
        }

        h.left = deleteMin(h.left);
        return balance(h);
    }

    private Node<T> deleteMax(Node<T> h) {
        if (isRed(h.left))
            h = rotateRight(h);

        if (h.right == null)
            return null;

        if (!isRed(h.right) && !isRed(h.right.left))
            h = moveRedRight(h);

        h.right = deleteMax(h.right);

        return balance(h);
    }

    private Node<T> min(Node<T> h) {
        if (h.left == null) {
            return h;
        }
        else {
            return min(h.left);
        }
    }

    private Node<T> max(Node<T> h) {
        if (h.right == null) {
            return h;
        }
        else {
            return max(h.right);
        }
    }




    private Node<T> moveRedRight(Node<T> h){
        flipColors(h);
        if (isRed(h.left.left)) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    private Node<T> moveRedLeft(Node<T> h){
        flipColors(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }
    
    
    
    private Node<T> rotateLeft(Node<T> h){
        Node<T> x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = true;
        x.size = h.size;
        h.size = 1 + h.left.size + h.right.size;
        return x;
    }
    
    private Node<T> rotateRight(Node<T> h){
        Node<T> x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = true;
        x.size = h.size;
        h.size = 1 + h.left.size + h.right.size;
        return x;
    }

    private Node<T> balance(Node<T> h){
        if (isRed(h.right)) {
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }

        h.size = h.left.size + h.right.size + 1;
        return h;

    }
    
    
    private class Node<T>{
        private T element;
        private Node<T> left;
        private Node<T> right;
        private int size;
        private boolean color;

        public Node(T element, int size, boolean color) {
            this.element = element;
            this.size = size;
            this.color = color;
        }
    }
}
