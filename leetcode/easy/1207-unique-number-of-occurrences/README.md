# Unique Number of Occurrences

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

Given an array of integers `arr`, return `true`  *if the number of occurrences of each value in the array is  **unique**  or* `false` *otherwise*.

 

 **Example 1:** 

```
Input: arr = [1,2,2,1,1,3]
Output: true
Explanation: The value 1 has 3 occurrences, 2 has 2 and 3 has 1. No two values have the same number of occurrences.
```

 **Example 2:** 

```
Input: arr = [1,2]
Output: false

```

 **Example 3:** 

```
Input: arr = [-3,0,1,-3,1,1,1,-3,10,0]
Output: true

```

 

 **Constraints:** 

- 1 <= arr.length <= 1000
- -1000 <= arr[i] <= 1000

## Solution

**Language:** Java  
**Runtime:** 0 ms  
**Memory:** 42.5 MB  
**Submitted:** 2026-08-27T08:28:28.447Z  

```java
import java.util.*;

class Solution {
    public boolean uniqueOccurrences(int[] arr) {

        // Count occurrences of each number
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Store frequencies
        Set<Integer> frequencies = new HashSet<>();

        for (int count : map.values()) {

            // Frequency already exists
            if (frequencies.contains(count)) {
                return false;
            }

            frequencies.add(count);
        }

        return true;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/unique-number-of-occurrences/)