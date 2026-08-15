# Implement Rand10() Using Rand7()

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given the  **API**  `rand7()` that generates a uniform random integer in the range `[1, 7]`, write a function `rand10()` that generates a uniform random integer in the range `[1, 10]`. You can only call the API `rand7()`, and you shouldn't call any other API. Please  **do not**  use a language's built-in random API.

Each test case will have one  **internal**  argument `n`, the number of times that your implemented function `rand10()` will be called while testing. Note that this is  **not an argument**  passed to `rand10()`.

 

 **Example 1:** 

```
Input: n = 1
Output: [2]

```

 **Example 2:** 

```
Input: n = 2
Output: [2,8]

```

 **Example 3:** 

```
Input: n = 3
Output: [3,8,10]

```

 

 **Constraints:** 

- 1 <= n <= 105

 

 **Follow up:** 

- What is the expected value for the number of calls to rand7() function?
- Could you minimize the number of calls to rand7()?

## Solution

**Language:** Java  
**Runtime:** 0 ms  
**Memory:** 42.5 MB  
**Submitted:** 2026-08-15T06:19:18.674Z  

```java
class Solution extends SolBase {
    public int rand10() {

        while (true) {
            int row = rand7();
            int col = rand7();

            int num = (row - 1) * 7 + col; // Uniformly 1..49

            if (num <= 40) {
                return 1 + (num - 1) % 10;
            }
        }
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/implement-rand10-using-rand7/)