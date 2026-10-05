# Score of Parentheses

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given a balanced parentheses string `s`, return  *the  **score**  of the string*.

The  **score**  of a balanced parentheses string is based on the following rule:

- "()" has score 1.
- AB has score A + B, where A and B are balanced parentheses strings.
- (A) has score 2 * A, where A is a balanced parentheses string.

 

 **Example 1:** 

```
Input: s = "()"
Output: 1

```

 **Example 2:** 

```
Input: s = "(())"
Output: 2

```

 **Example 3:** 

```
Input: s = "()()"
Output: 2

```

 

 **Constraints:** 

- 2 <= s.length <= 50
- s consists of only '(' and ')'.
- s is a balanced parentheses string.

## Solution

**Language:** Java  
**Runtime:** 0 ms (beats 100.00%)  
**Memory:** 42.8 MB (beats 48.23%)  
**Submitted:** 2026-10-05T05:52:21.756Z  

```java
class Solution {
    public int scoreOfParentheses(String s) {
        int score = 0;
        int depth = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                depth++;
            } else {
                depth--;

                // "()" contributes 2^depth
                if (s.charAt(i - 1) == '(') {
                    score += 1 << depth;
                }
            }
        }

        return score;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/score-of-parentheses/)