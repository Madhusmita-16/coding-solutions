# Longest Valid Parentheses

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

Given a string containing just the characters `'('` and `')'`, return  *the length of the longest valid (well-formed) parentheses **substring*.

 

 **Example 1:** 

```
Input: s = "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()".

```

 **Example 2:** 

```
Input: s = ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()".

```

 **Example 3:** 

```
Input: s = ""
Output: 0

```

 

 **Constraints:** 

- 0 <= s.length <= 3 * 104
- s[i] is '(', or ')'.

## Solution

**Language:** Java  
**Runtime:** 5 ms (beats 80.88%)  
**Memory:** 46.4 MB (beats 73.83%)  
**Submitted:** 2026-08-18T14:46:00.573Z  

```java
import java.util.*;

class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();

        // Base index before the start of a valid substring
        stack.push(-1);

        int maxLength = 0;

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                // Remove the matching '('
                stack.pop();

                if (stack.isEmpty()) {
                    // Current ')' cannot be matched
                    stack.push(i);
                } else {
                    // Length of current valid substring
                    maxLength = Math.max(
                        maxLength,
                        i - stack.peek()
                    );
                }
            }
        }

        return maxLength;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/longest-valid-parentheses/)