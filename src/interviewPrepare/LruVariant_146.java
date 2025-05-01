package interviewPrepare;

import java.util.*;

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
    static class LRUCache {
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
            cursize--;
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
                map.get(key).val = value;
                deleteNode(map.get(key));
                moveNodeToTail(map.get(key));
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

    static class LRUCacheBruteForce{
        private int capacity;
        private LinkedHashMap<Integer, Integer> cache;

        public LRUCacheBruteForce(int capacity) {
            this.capacity = capacity;
            // accessOrder: true ensures that the map is ordered by access order
            this.cache = new LinkedHashMap<>(capacity, 0.75f, true);
        }

        // Return the value of the key if it exists, otherwise return -1
        public int get(int key) {
            return cache.getOrDefault(key, -1);
        }

        // Update the value if the key exists. Otherwise, add the key-value pair to the cache.
        public void put(int key, int value) {
            if (cache.size() >= capacity) {
                // Evict the least recently used item
                int oldestKey = cache.keySet().iterator().next();
                cache.remove(oldestKey);
            }
            cache.put(key, value);
        }

        // Deletes the key-value pair if the key exists and returns true. Otherwise, return false.
        public boolean del(int key) {
            if (cache.containsKey(key)) {
                cache.remove(key);
                return true;
            }
            return false;
        }

        // Returns the most recently used key (the last accessed one)
        public int last() {
            if (cache.isEmpty()) return -1;
            Integer[] keys = cache.keySet().toArray(new Integer[0]);
            int lastKey = keys[keys.length - 1];
            return cache.get(lastKey);  // ✅ 返回 value
        }
    }


    public static void main(String[] args) {
        // 设置缓存容量
        int capacity = 3;
        LRUCache cache = new LRUCache(capacity);
        LRUCacheBruteForce bruteForceCache = new LRUCacheBruteForce(capacity);

        Random rand = new Random();

        // 操作记录
        List<String> operations = new ArrayList<>();
        List<String> results = new ArrayList<>();

        // 对比缓存的状态
        for (int i = 0; i <= 50; i++) {
            int op = rand.nextInt(4);
            switch (op) {
                case 0: // put
                    int key = rand.nextInt(10);
                    int value = rand.nextInt(100);
                    cache.put(key, value);
                    bruteForceCache.put(key, value);
                    operations.add(String.format("put(%d, %d)", key, value));
                    results.add("null");
                    break;

                case 1: // get
                    key = rand.nextInt(10);
                    int getVal = cache.get(key);
                    int getValBrute = bruteForceCache.get(key);
                    operations.add(String.format("get(%d)", key));
                    results.add(getVal == getValBrute ? String.valueOf(getVal) : "Mismatch");
                    break;

                case 2: // del
                    key = rand.nextInt(10);
                    boolean delSuccess = cache.del(key);
                    boolean delSuccessBrute = bruteForceCache.del(key);
                    operations.add(String.format("del(%d)", key));
                    results.add(delSuccess == delSuccessBrute ? String.valueOf(delSuccess) : "Mismatch");
                    break;

                case 3: // last
                    int lastVal = cache.last();
                    int lastValBrute = bruteForceCache.last();
                    operations.add("last()");
                    results.add(lastVal == lastValBrute ? String.valueOf(lastVal) : "Mismatch");
                    break;
            }
        }

        // 打印操作与结果
        System.out.println("== LRU Cache Test ==");
        for (int i = 0; i < operations.size(); i++) {
            System.out.printf("%-15s -> %s\n", operations.get(i), results.get(i));
        }
    }
}
