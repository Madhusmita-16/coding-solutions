# 1 to n Without Loops

![Difficulty](https://img.shields.io/badge/Difficulty-Basic-red)

## Problem

Given an positive integer, print numbers from 1 to without using loops.

Implement the function `printTillN()` to print the numbers from 1 to as space-separated integers.

**Examples
**

```
Input: n = 5
Output: 1 2 3 4 5
Explanation: We have to print numbers from 1 to 5.
```

```
Input: n = 10
Output: 1 2 3 4 5 6 7 8 9 10
Explanation: We have to print numbers from 1 to 10.
```

 **Constraints:** 
1 ≤ n ≤ 1000

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-09-01T05:33:43.883Z  

```java
class Solution {
    public void printTillN(int n) {
        if (n == 0) {
            return;
        }

        printTillN(n - 1);
        System.out.print(n + " ");
    }
}
```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/print-1-to-n-without-using-loops3621/1)