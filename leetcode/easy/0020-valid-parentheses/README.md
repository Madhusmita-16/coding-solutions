# Q1. Valid Parentheses

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

Given a string `s` containing just the characters `'('`, `')'`, `'{'`, `'}'`, `'['` and `']'`, determine if the input string is valid.

An input string is valid if:

- Open brackets must be closed by the same type of brackets.
- Open brackets must be closed in the correct order.
- Every close bracket has a corresponding open bracket of the same type.

 

 **Example 1:** 

 **Input:**  s = "()"

 **Output:**  true

 **Example 2:** 

 **Input:**  s = "()[]{}"

 **Output:**  true

 **Example 3:** 

 **Input:**  s = "(]"

 **Output:**  false

 **Example 4:** 

 **Input:**  s = "([])"

 **Output:**  true

 **Example 5:** 

 **Input:**  s = "([)]"

 **Output:**  false

 

 **Constraints:** 

- 1 <= s.length <= 104
- s consists of parentheses only '()[]{}'.

## Solution

**Language:** Java  
**Runtime:** 3 ms (beats 86.07%)  
**Memory:** 43.2 MB (beats 73.09%)  
**Submitted:** 2026-08-27T09:15:03.332Z  

```java
import java.util.*;

class Solution {
    public boolean isValid(String s) {

        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {

            // Opening bracket
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            }

            // Closing bracket
            else {
                if (stack.isEmpty()) {
                    return false;
                }

                char top = stack.pop();

                if ((c == ')' && top != '(') ||
                    (c == ']' && top != '[') ||
                    (c == '}' && top != '{')) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/valid-parentheses/)