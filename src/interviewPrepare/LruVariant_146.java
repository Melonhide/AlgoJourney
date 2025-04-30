package interviewPrepare;

public class LruVariant_146 {
    // Design an LRU (Least Recently Used) cache supporting the following operations:
    //
    // - get(key):     Return the value if the key exists, otherwise return -1
    // - put(key, val): Update the key if it exists, or add the key-value pair to the cache
    // - del(key):     Delete the key-value pair if it exists and return true, else false
    // - last():       Return the value of the least recently used item, or -1 if cache is empty
    //
    // All operations must run in O(1) average time complexity, except last(), which must run in O(1).
    //
    // Example:
    // LRUCache(2) → put(4,4), put(5,5), put(3,30)
    // Cache state: [5=5, 3=30]  (4 is evicted)
    // get(3) → 30
    // del(5) → true
    // last() → 3
}
