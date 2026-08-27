# Smallest Number in Infinite Set

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You have a set which contains all positive integers `[1, 2, 3, 4, 5,...]`.

Implement the `SmallestInfiniteSet` class:

- SmallestInfiniteSet() Initializes the SmallestInfiniteSet object to contain all positive integers.
- int popSmallest() Removes and returns the smallest integer contained in the infinite set.
- void addBack(int num) Adds a positive integer num back into the infinite set, if it is not already in the infinite set.

 

 **Example 1:** 

```
Input
["SmallestInfiniteSet", "addBack", "popSmallest", "popSmallest", "popSmallest", "addBack", "popSmallest", "popSmallest", "popSmallest"]
[[], [2], [], [], [], [1], [], [], []]
Output
[null, null, 1, 2, 3, null, 1, 4, 5]

Explanation
SmallestInfiniteSet smallestInfiniteSet = new SmallestInfiniteSet();
smallestInfiniteSet.addBack(2);    // 2 is already in the set, so no change is made.
smallestInfiniteSet.popSmallest(); // return 1, since 1 is the smallest number, and remove it from the set.
smallestInfiniteSet.popSmallest(); // return 2, and remove it from the set.
smallestInfiniteSet.popSmallest(); // return 3, and remove it from the set.
smallestInfiniteSet.addBack(1);    // 1 is added back to the set.
smallestInfiniteSet.popSmallest(); // return 1, since 1 was added back to the set and
                                   // is the smallest number, and remove it from the set.
smallestInfiniteSet.popSmallest(); // return 4, and remove it from the set.
smallestInfiniteSet.popSmallest(); // return 5, and remove it from the set.

```

 

 **Constraints:** 

- 1 <= num <= 1000
- At most 1000 calls will be made in total to popSmallest and addBack.

## Solution

**Language:** Java  
**Runtime:** 10 ms (beats 90.14%)  
**Memory:** 47.9 MB (beats 8.64%)  
**Submitted:** 2026-08-27T10:48:45.047Z  

```java
import java.util.*;

class SmallestInfiniteSet {

    private int next;
    private PriorityQueue<Integer> minHeap;
    private HashSet<Integer> set;

    public SmallestInfiniteSet() {
        next = 1;
        minHeap = new PriorityQueue<>();
        set = new HashSet<>();
    }

    public int popSmallest() {

        if (!minHeap.isEmpty()) {
            int smallest = minHeap.poll();
            set.remove(smallest);
            return smallest;
        }

        return next++;
    }

    public void addBack(int num) {

        // Only add numbers that were already popped
        // and are not currently in the heap.
        if (num < next && !set.contains(num)) {
            minHeap.offer(num);
            set.add(num);
        }
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/smallest-number-in-infinite-set/)