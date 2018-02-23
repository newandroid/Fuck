package com.example.generics;

/**
 * Created by Administrator on 2017/11/21.
 */

public class LinkStack<T> {
    private Node<T> top = new Node<>();

    public static class Node<U> {
        U item;
        Node<U> next;

        public Node(){
            item = null;
            next = null;
        }

        public Node(U item,Node<U> next){
            this.item = item;
            this.next = next;
        }
        boolean end() {
            return item != null && next != null;
        }
    }
    public void push(T item){
        top = new Node<>(item,top);
    }
    public T pop(){
        T result = top.item;
        if (top.end())top= top.next;
        return result;
    }

}
