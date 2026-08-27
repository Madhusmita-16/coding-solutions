# Q1. Minimum Remove to Make Valid Parentheses

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given a string s of `'('`, `')'` and lowercase English characters.

Your task is to remove the minimum number of parentheses (`'('` or `')'`, in any positions) so that the resulting  *parentheses string*  is valid and return  **any**  valid string.

Formally, a  *parentheses string*  is valid if and only if:

- It is the empty string, contains only lowercase characters, or
- It can be written as AB (A concatenated with B), where A and B are valid strings, or
- It can be written as (A), where A is a valid string.

 

 **Example 1:** 

```
Input: s = "lee(t(c)o)de)"
Output: "lee(t(c)o)de"
Explanation: "lee(t(co)de)", "lee(t(c)ode)" would also be accepted.

```

 **Example 2:** 

```
Input: s = "a)b(c)d"
Output: "ab(c)d"

```

 **Example 3:** 

```
Input: s = "))(("
Output: ""
Explanation: An empty string is also valid.

```

 

 **Constraints:** 

- 1 <= s.length <= 105
- s[i] is either '(', ')', or lowercase English letter.

## Solution

**Language:** Java  
**Runtime:** 8 ms (beats 95.88%)  
**Memory:** 47.4 MB (beats 41.71%)  
**Submitted:** 2026-08-27T09:19:26.089Z  

```java
class Solution {
    public String minRemoveToMakeValid(String s) {

        StringBuilder result = new StringBuilder();
        int open = 0;

        // First pass:
        // Remove invalid ')'
        for (char c : s.toCharArray()) {

            if (c == '(') {
                open++;
                result.append(c);
            } 
            else if (c == ')') {
                if (open > 0) {
                    open--;
                    result.append(c);
                }
            } 
            else {
                result.append(c);
            }
        }

        // Remove extra '(' from right to left
        for (int i = result.length() - 1; i >= 0 && open > 0; i--) {
            if (result.charAt(i) == '(') {
                result.deleteCharAt(i);
                open--;
            }
        }

        return result.toString();
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/)