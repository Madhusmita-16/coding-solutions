import java.util.HashMap;
import java.util.Map;

class LRUCache {

    class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> map;

    // Dummy nodes
    private final Node head;
    private final Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();

        head = new Node(0, 0);
        tail = new Node(0, 0);

        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }

        Node node = map.get(key);

        // Mark as most recently used
        remove(node);
        addFirst(node);

        return node.value;
    }

    public void put(int key, int value) {

        // Key already exists
        if (map.containsKey(key)) {
            Node node = map.get(key);

            node.value = value;

            // Updated key becomes most recently used
            remove(node);
            addFirst(node);

            return;
        }

        // New key
        Node node = new Node(key, value);
        map.put(key, node);
        addFirst(node);

        // Capacity exceeded
        if (map.size() > capacity) {
            Node lru = tail.prev;

            remove(lru);
            map.remove(lru.key);
        }
    }

    // Remove a node from the linked list
    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Add node immediately after head
    private void addFirst(Node node) {
        node.next = head.next;
        node.prev = head;

        head.next.prev = node;
        head.next = node;
    }
}