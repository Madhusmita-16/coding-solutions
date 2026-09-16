# Q3. All O`one Data Structure

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

Design a data structure to store the strings' count with the ability to return the strings with minimum and maximum counts.

Implement the `AllOne` class:

- AllOne() Initializes the object of the data structure.
- inc(String key) Increments the count of the string key by 1. If key does not exist in the data structure, insert it with count 1.
- dec(String key) Decrements the count of the string key by 1. If the count of key is 0 after the decrement, remove it from the data structure. It is guaranteed that key exists in the data structure before the decrement.
- getMaxKey() Returns one of the keys with the maximal count. If no element exists, return an empty string "".
- getMinKey() Returns one of the keys with the minimum count. If no element exists, return an empty string "".

 **Note**  that each function must run in `O(1)` average time complexity.

 

 **Example 1:** 

```
Input
["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
[[], ["hello"], ["hello"], [], [], ["leet"], [], []]
Output
[null, null, null, "hello", "hello", null, "hello", "leet"]

Explanation
AllOne allOne = new AllOne();
allOne.inc("hello");
allOne.inc("hello");
allOne.getMaxKey(); // return "hello"
allOne.getMinKey(); // return "hello"
allOne.inc("leet");
allOne.getMaxKey(); // return "hello"
allOne.getMinKey(); // return "leet"

```

 

 **Constraints:** 

- 1 <= key.length <= 10
- key consists of lowercase English letters.
- It is guaranteed that for each call to dec, key is existing in the data structure.
- At most 5 * 104 calls will be made to inc, dec, getMaxKey, and getMinKey.

## Solution

**Language:** Java  
**Runtime:** 3 ms  
**Memory:** 42.5 MB  
**Submitted:** 2026-09-16T02:50:03.296Z  

```java
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
```

---

[View on LeetCode](https://leetcode.com/problems/all-oone-data-structure/)