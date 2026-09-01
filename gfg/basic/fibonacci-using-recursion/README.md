# Nth Fibonacci Using Recursion

![Difficulty](https://img.shields.io/badge/Difficulty-Basic-red)

## Problem

Given a number  **n**, return the **nth**  Fibonacci number.

 **Note:**  If F(n) is nth fibonacci number then, F(n) = F(n-1) + F(n-2) ; where F(0) = 0 and F(1) = 1

 **Example:** 

```
Input: n = 3
Output: 2
Explanation: The Fibonacci sequence starts as 0, 1, 1, 2, 3, 5... The 3rd Fibonacci number is 2.
```

```
Input: n = 5
Output: 5
Explanation: The sequence is 0, 1, 1, 2, 3, 5... The 5th Fibonacci number is 5.

```

 **Constraints:** 
1 ≤ n ≤ 20

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-09-01T05:42:37.297Z  

```java
class Solution {
    static int nthFibonacci(int n) {
        int a = 0;
        int b = 1;

        for (int i = 0; i < n; i++) {
            int next = a + b;
            a = b;
            b = next;
        }

        return a;
    }
}
```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/fibonacci-using-recursion/1)