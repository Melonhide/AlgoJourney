package interviewPrepare;

import java.util.HashMap;
import java.util.Map;

public class LruVariant_146 {
    // Design an LRU (Least Recently Used) cache supporting the following operations:
    //
    // - get(key):     Return the value if the key exists, otherwise return -1
    // - put(key, val): Update the key if it exists, or add the key-value pair to the cache
    // - del(key):     Delete the key-value pair if it exists and return true, else false
    // - last():       Return the value of the most recently used item, or -1 if cache is empty
    //
    // All operations must run in O(1) average time complexity, except last(), which must run in O(1).
    //
    // Example:
    // LRUCache(2) → put(4,4), put(5,5), put(3,30)
    // Cache state: [5=5, 3=30]  (4 is evicted)
    // get(3) → 30
    // del(5) → true
    // last() → 3
    class LRUCache {
        static class Node{
            public int val;
            public int key;
            public Node next;
            public Node prev;

            public Node(int k, int v){
                key = k;
                val = v;
                next = null;
                prev = null;
            }
        }
        public int cursize;

        public int size;
        public Node head;
        public Node tail;
        public Map<Integer, Node> map;
        public LRUCache(int capacity) {
            map = new HashMap<>();
            size = capacity;
            head = new Node(-1,-1);
            tail = new Node(-1,-1);
            head.next = tail;
            tail.prev = head;
            cursize = 0;
        }

        public int get(int key) {
            if(!map.containsKey(key)){
                return -1;
            }
            deleteNode(map.get(key));
            moveNodeToTail(map.get(key));
            return map.get(key).val;
        }

        public boolean del(int key) {
            if(!map.containsKey(key)){
                return false;
            }
            deleteNode(map.get(key));
            map.remove(key);
            return true;
        }

        public int last() {
            if(cursize == 0){
                return -1;
            }

            return tail.prev.val;
        }

        public void put(int key, int value) {
            if(map.containsKey(key)){
                deleteNode(map.get(key));
                moveNodeToTail(map.get(key));
                map.get(key).val = value;
                return;
            }

            if(cursize == size){
                Node nodeToDelete = head.next;
                deleteNode(nodeToDelete);
                map.remove(nodeToDelete.key);
                cursize--;
            }

            Node cur = new Node(key, value);
            map.put(key, cur);
            cursize++;
            moveNodeToTail(cur);
            return;
        }

        public void moveNodeToTail(Node cur){
            Node p = tail.prev;
            p.next = cur;
            cur.prev = p;
            cur.next = tail;
            tail.prev = cur;
        }

        public void deleteNode(Node cur){
            Node p = cur.prev;
            Node n = cur.next;
            p.next = n;
            n.prev = p;
        }
    }

}
