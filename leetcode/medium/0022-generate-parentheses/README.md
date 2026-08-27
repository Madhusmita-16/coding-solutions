# Q3. Generate Parentheses

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given `n` pairs of parentheses, write a function to  *generate all combinations of well-formed parentheses*.

 

 **Example 1:** 

```
Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]

```

 **Example 2:** 

```
Input: n = 1
Output: ["()"]

```

 

 **Constraints:** 

- 1 <= n <= 8

## Solution

**Language:** Java  
**Runtime:** 2 ms (beats 69.83%)  
**Memory:** 44.8 MB (beats 44.21%)  
**Submitted:** 2026-08-27T09:02:31.618Z  

```java
import java.util.*;

class Solution {
    public List<String> generateParenthesis(int n) {

        List<String> result = new ArrayList<>();

        backtrack("", 0, 0, n, result);

        return result;
    }

    private void backtrack(
            String current,
            int open,
            int close,
            int n,
            List<String> result) {

        // A complete valid combination
        if (current.length() == 2 * n) {
            result.add(current);
            return;
        }

        // We can add an opening bracket if we haven't used all n
        if (open < n) {
            backtrack(current + "(", open + 1, close, n, result);
        }

        // We can add a closing bracket only when
        // there are unmatched opening brackets
        if (close < open) {
            backtrack(current + ")", open, close + 1, n, result);
        }
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/generate-parentheses/)