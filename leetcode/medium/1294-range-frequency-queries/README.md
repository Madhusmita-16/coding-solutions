# Q2. Range Frequency Queries

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Design a data structure to find the  **frequency**  of a given value in a given subarray.

The  **frequency**  of a value in a subarray is the number of occurrences of that value in the subarray.

Implement the `RangeFreqQuery` class:

- RangeFreqQuery(int[] arr) Constructs an instance of the class with the given 0-indexed integer array arr.
- int query(int left, int right, int value) Returns the frequency of value in the subarray arr[left...right].

A  **subarray**  is a contiguous sequence of elements within an array. `arr[left...right]` denotes the subarray that contains the elements of `nums` between indices `left` and `right` (**inclusive**).

 

 **Example 1:** 

```
Input
["RangeFreqQuery", "query", "query"]
[[[12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]], [1, 2, 4], [0, 11, 33]]
Output
[null, 1, 2]

Explanation
RangeFreqQuery rangeFreqQuery = new RangeFreqQuery([12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]);
rangeFreqQuery.query(1, 2, 4); // return 1. The value 4 occurs 1 time in the subarray [33, 4]
rangeFreqQuery.query(0, 11, 33); // return 2. The value 33 occurs 2 times in the whole array.

```

 

 **Constraints:** 

- 1 <= arr.length <= 105
- 1 <= arr[i], value <= 104
- 0 <= left <= right < arr.length
- At most 105 calls will be made to query

## Solution

**Language:** Java  
**Runtime:** 115 ms (beats 96.59%)  
**Memory:** 175.5 MB (beats 82.97%)  
**Submitted:** 2026-09-16T02:54:09.711Z  

```java
import java.util.*;

class RangeFreqQuery {

    private final Map<Integer, List<Integer>> map;

    public RangeFreqQuery(int[] arr) {
        map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            map.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }
    }

    public int query(int left, int right, int value) {
        List<Integer> indices = map.get(value);

        if (indices == null) {
            return 0;
        }

        // First index >= left
        int start = lowerBound(indices, left);

        // First index > right
        int end = upperBound(indices, right);

        return end - start;
    }

    private int lowerBound(List<Integer> list, int target) {
        int low = 0;
        int high = list.size();

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (list.get(mid) >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private int upperBound(List<Integer> list, int target) {
        int low = 0;
        int high = list.size();

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (list.get(mid) > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/range-frequency-queries/)