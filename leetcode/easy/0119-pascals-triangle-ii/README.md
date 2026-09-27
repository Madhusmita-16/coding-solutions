# Pascal's Triangle II

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

Given an integer `rowIndex`, return the `rowIndexth` (**0-indexed**) row of the  **Pascal's triangle**.

In  **Pascal's triangle**, each number is the sum of the two numbers directly above it as shown:

 

 **Example 1:** 

```
Input: rowIndex = 3
Output: [1,3,3,1]

```

 **Example 2:** 

```
Input: rowIndex = 0
Output: [1]

```

 **Example 3:** 

```
Input: rowIndex = 1
Output: [1,1]

```

 

 **Constraints:** 

- 0 <= rowIndex <= 33

 

 **Follow up:**  Could you optimize your algorithm to use only `O(rowIndex)` extra space?

## Solution

**Language:** Java  
**Runtime:** 0 ms  
**Memory:** 42.2 MB  
**Submitted:** 2026-09-27T17:32:26.223Z  

```java
class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>();

        long value = 1;

        for (int i = 0; i <= rowIndex; i++) {
            row.add((int) value);

            value = value * (rowIndex - i) / (i + 1);
        }

        return row;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/pascals-triangle-ii/)