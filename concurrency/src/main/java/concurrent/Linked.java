package concurrent;

import java.util.LinkedList;

/**
 * filename: Linked
 * Description:
 * Author: ubuntu
 * Date: 9/19/17 11:42 AM
 */
public class Linked {
    private Node first;
    private Node last;

    private static class Node{
        private Node next;
        private int a;

        public Node(int a) {
            this.a = a;
        }
    }

    public boolean isEmpty(){
        return first == null;
    }
    public Node pop(){
        Node tmp = first;
        first = tmp.next;
        tmp.next = null;
        return tmp;
    }

     public Node add(Node newNode){

         if(last == null){
             first = newNode;
         }else{
             last.next = newNode;
         }
         last = newNode;
         return newNode;
     }

}
