package bg.leetcode.exercises.itenev.common;

import bg.leetcode.exercises.itenev.design.LRUCache;

public class DoublyLinkedList {
    public int key;
    public int value;
    public DoublyLinkedList prev;
    public DoublyLinkedList next;

    public DoublyLinkedList(){}
    public DoublyLinkedList(int k, int v){
        this.key = k;
        this.value = v;
    }
}
