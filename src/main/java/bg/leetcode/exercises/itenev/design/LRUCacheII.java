package bg.leetcode.exercises.itenev.design;

import bg.leetcode.exercises.itenev.common.DoublyLinkedList;

import java.util.HashMap;
import java.util.Map;

class LRUCacheII {
    private int capacity, count;
    private Map<Integer, DoublyLinkedList> map;
    private DoublyLinkedList head;
    private DoublyLinkedList tail;

    public LRUCacheII(int capacity) {
        this.capacity = capacity;
        this.count = 0;
        map = new HashMap<>();
        head = new DoublyLinkedList();
        tail = new DoublyLinkedList();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DoublyLinkedList n = map.get(key);
        if (null == n) {
            return -1;
        }
        update(n);
        return n.value;
    }

    public void set(int key, int value) {
        DoublyLinkedList n = map.get(key);
        if (null == n) {
            n = new DoublyLinkedList(key, value);
            map.put(key, n);
            add(n);
            ++count;
        } else {
            n.value = value;
            update(n);
        }
        if (count > capacity) {
            DoublyLinkedList toDel = tail.prev;
            remove(toDel);
            map.remove(toDel.key);
            --count;
        }
    }

    private void update(DoublyLinkedList node) {
        remove(node);
        add(node);
    }

    private void add(DoublyLinkedList node) {
        DoublyLinkedList after = head.next;
        head.next = node;
        node.prev = head;
        node.next = after;
        after.prev = node;
    }

    private void remove(DoublyLinkedList node) {
        DoublyLinkedList before = node.prev, after = node.next;
        before.next = after;
        after.prev = before;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
