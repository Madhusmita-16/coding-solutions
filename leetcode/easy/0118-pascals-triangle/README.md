# Pascal's Triangle

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

Given an integer `numRows`, return the first numRows of  **Pascal's triangle**.

In  **Pascal's triangle**, each number is the sum of the two numbers directly above it as shown:

 

 **Example 1:** 

```
Input: numRows = 5
Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]

```

 **Example 2:** 

```
Input: numRows = 1
Output: [[1]]

```

 

 **Constraints:** 

- 1 <= numRows <= 30

## Solution

**Language:** Java  
**Runtime:** 1 ms (beats 95.49%)  
**Memory:** 43.7 MB (beats 16.60%)  
**Submitted:** 2026-09-27T17:31:31.863Z  

```java
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();

            // First element is always 1
            row.add(1);

            // Middle elements
            for (int j = 1; j < i; j++) {
                row.add(triangle.get(i - 1).get(j - 1)
                        + triangle.get(i - 1).get(j));
            }

            // Last element is always 1
            if (i > 0) {
                row.add(1);
            }

            triangle.add(row);
        }

        return triangle;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/pascals-triangle/)