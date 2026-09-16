# Q1. Range Module

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

A Range Module is a module that tracks ranges of numbers. Design a data structure to track the ranges represented as  **half-open intervals**  and query about them.

A  **half-open interval**  `[left, right)` denotes all the real numbers `x` where `left <= x < right`.

Implement the `RangeModule` class:

- RangeModule() Initializes the object of the data structure.
- void addRange(int left, int right) Adds the half-open interval [left, right), tracking every real number in that interval. Adding an interval that partially overlaps with currently tracked numbers should add any numbers in the interval [left, right) that are not already tracked.
- boolean queryRange(int left, int right) Returns true if every real number in the interval [left, right) is currently being tracked, and false otherwise.
- void removeRange(int left, int right) Stops tracking every real number currently being tracked in the half-open interval [left, right).

 

 **Example 1:** 

```
Input
["RangeModule", "addRange", "removeRange", "queryRange", "queryRange", "queryRange"]
[[], [10, 20], [14, 16], [10, 14], [13, 15], [16, 17]]
Output
[null, null, null, true, false, true]

Explanation
RangeModule rangeModule = new RangeModule();
rangeModule.addRange(10, 20);
rangeModule.removeRange(14, 16);
rangeModule.queryRange(10, 14); // return True,(Every number in [10, 14) is being tracked)
rangeModule.queryRange(13, 15); // return False,(Numbers like 14, 14.03, 14.17 in [13, 15) are not being tracked)
rangeModule.queryRange(16, 17); // return True, (The number 16 in [16, 17) is still being tracked, despite the remove operation)

```

 

 **Constraints:** 

- 1 <= left < right <= 109
- At most 104 calls will be made to addRange, queryRange, and removeRange.

## Solution

**Language:** Java  
**Runtime:** 0 ms  
**Memory:** 42.5 MB  
**Submitted:** 2026-09-16T02:52:54.721Z  

```java
import java.util.*;

class RangeModule {

    // start -> end
    private final TreeMap<Integer, Integer> map;

    public RangeModule() {
        map = new TreeMap<>();
    }

    public void addRange(int left, int right) {
        if (left >= right) {
            return;
        }

        // Find the interval immediately before or at 'left'
        Map.Entry<Integer, Integer> entry = map.floorEntry(left);

        // If it overlaps/touches [left, right), merge it
        if (entry != null && entry.getValue() >= left) {
            left = Math.min(left, entry.getKey());
            right = Math.max(right, entry.getValue());
            map.remove(entry.getKey());
        }

        // Merge all intervals that overlap/touch the current range
        entry = map.ceilingEntry(left);

        while (entry != null && entry.getKey() <= right) {
            right = Math.max(right, entry.getValue());
            map.remove(entry.getKey());
            entry = map.ceilingEntry(left);
        }

        map.put(left, right);
    }

    public boolean queryRange(int left, int right) {
        if (left >= right) {
            return true;
        }

        // Find the interval with the greatest start <= left
        Map.Entry<Integer, Integer> entry = map.floorEntry(left);

        return entry != null && entry.getValue() >= right;
    }

    public void removeRange(int left, int right) {
        if (left >= right) {
            return;
        }

        // Check interval containing or starting before 'left'
        Map.Entry<Integer, Integer> entry = map.floorEntry(left);

        if (entry != null && entry.getValue() > left) {
            int start = entry.getKey();
            int end = entry.getValue();

            map.remove(start);

            // Keep the left part
            if (start < left) {
                map.put(start, left);
            }

            // Keep the right part
            if (end > right) {
                map.put(right, end);
                return;
            }
        }

        // Remove intervals completely/partially covered by [left, right)
        entry = map.ceilingEntry(left);

        while (entry != null && entry.getKey() < right) {
            int start = entry.getKey();
            int end = entry.getValue();

            map.remove(start);

            // Preserve the part after 'right'
            if (end > right) {
                map.put(right, end);
                break;
            }

            entry = map.ceilingEntry(left);
        }
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/range-module/)