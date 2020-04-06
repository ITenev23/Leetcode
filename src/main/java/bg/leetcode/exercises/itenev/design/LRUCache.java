package bg.leetcode.exercises.itenev.design;

import bg.leetcode.exercises.itenev.common.DoublyLinkedList;

import java.util.HashMap;
import java.util.Map;

/**
 * https://en.wikipedia.org/wiki/Cache_replacement_policies#LRU
 * <p>
 * Design and implement a data structure for Least Recently Used (LRU) cache.
 * It should support the following operations: get and put.
 * <p>
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 * <p>
 * The cache is initialized with a positive capacity.
 * <p>
 * LRUCache cache = new LRUCache( 2 -> capacity)
 * <p>
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);       // returns 1
 * cache.put(3,3);    // evicts key 2
 * cache.get(2);       // returns -1 (not found)
 * cache.put(4,4);    // evicts key 1
 * cache.get(1);       // returns -1 (not found)
 * cache.get(3);       // returns 3
 * cache.get(4);       // returns 4
 */
public class LRUCache {
    private Map<Integer, DoublyLinkedList> cache = new HashMap<>();
    private int count;
    private int capacity;
    private DoublyLinkedList head;
    private DoublyLinkedList tail;

    public LRUCache(int capacity) {
        this.count = 0;
        this.capacity = capacity;

        head = new DoublyLinkedList();
        head.prev = null;

        tail = new DoublyLinkedList();
        tail.next = null;

        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DoublyLinkedList node = cache.get(key);
        if (node == null)
            return -1;

        // move the accessed node to the head;
        this.moveToHead(node);

        return node.value;
    }


    public void put(int key, int value) {
        DoublyLinkedList node = cache.get(key);

        if (node == null) {
            DoublyLinkedList newNode = new DoublyLinkedList();
            newNode.key = key;
            newNode.value = value;

            this.cache.put(key, newNode);
            this.addNode(newNode);

            ++count;

            if (count > capacity) {
                // pop the tail
                DoublyLinkedList tail = this.popTail();
                this.cache.remove(tail.key);
                --count;
            }
        } else {
            // update the value.
            node.value = value;
            this.moveToHead(node);
        }
    }

    /**
     * Always add the new node right after head;
     */
    private void addNode(DoublyLinkedList node) {
        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }

    /**
     * Remove an existing node from the linked list.
     */
    private void removeNode(DoublyLinkedList node) {
        DoublyLinkedList pre = node.prev;
        DoublyLinkedList post = node.next;

        pre.next = post;
        post.prev = pre;
    }

    /**
     * Move certain node in between to the head.
     */
    private void moveToHead(DoublyLinkedList node) {
        this.removeNode(node);
        this.addNode(node);
    }

    // pop the current tail.
    private DoublyLinkedList popTail() {
        DoublyLinkedList res = tail.prev;
        this.removeNode(res);
        return res;
    }


}
