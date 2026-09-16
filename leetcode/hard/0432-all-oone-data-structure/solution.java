import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class AllOne {

    class Node {
        int count;
        Set<String> keys;
        Node prev;
        Node next;

        Node(int count) {
            this.count = count;
            this.keys = new HashSet<>();
        }
    }

    private final Node head;
    private final Node tail;
    private final Map<String, Node> map;

    public AllOne() {
        head = new Node(0);
        tail = new Node(0);

        head.next = tail;
        tail.prev = head;

        map = new HashMap<>();
    }

    public void inc(String key) {

        // Key does not exist
        if (!map.containsKey(key)) {

            Node first = head.next;

            // Need a count-1 bucket
            if (first == tail || first.count != 1) {
                Node newNode = new Node(1);
                insertAfter(head, newNode);
                first = newNode;
            }

            first.keys.add(key);
            map.put(key, first);

            return;
        }

        // Key already exists
        Node current = map.get(key);
        Node next = current.next;

        // Need a bucket with count + 1
        if (next == tail || next.count != current.count + 1) {
            Node newNode = new Node(current.count + 1);
            insertAfter(current, newNode);
            next = newNode;
        }

        next.keys.add(key);
        map.put(key, next);

        current.keys.remove(key);

        // Remove empty bucket
        if (current.keys.isEmpty()) {
            removeNode(current);
        }
    }

    public void dec(String key) {

        Node current = map.get(key);

        // Count becomes 0
        if (current.count == 1) {
            current.keys.remove(key);
            map.remove(key);

            if (current.keys.isEmpty()) {
                removeNode(current);
            }

            return;
        }

        Node prev = current.prev;

        // Need a bucket with count - 1
        if (prev == head || prev.count != current.count - 1) {
            Node newNode = new Node(current.count - 1);
            insertAfter(prev, newNode);
            prev = newNode;
        }

        prev.keys.add(key);
        map.put(key, prev);

        current.keys.remove(key);

        // Remove empty bucket
        if (current.keys.isEmpty()) {
            removeNode(current);
        }
    }

    public String getMaxKey() {

        if (tail.prev == head) {
            return "";
        }

        return tail.prev.keys.iterator().next();
    }

    public String getMinKey() {

        if (head.next == tail) {
            return "";
        }

        return head.next.keys.iterator().next();
    }

    // Insert node after prev
    private void insertAfter(Node prev, Node node) {

        node.next = prev.next;
        node.prev = prev;

        prev.next.prev = node;
        prev.next = node;
    }

    // Remove node from linked list
    private void removeNode(Node node) {

        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}