# Q2. Remove All Adjacent Duplicates in String II

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given a string `s` and an integer `k`, a `k`  **duplicate removal**  consists of choosing `k` adjacent and equal letters from `s` and removing them, causing the left and the right side of the deleted substring to concatenate together.

We repeatedly make `k`  **duplicate removals**  on `s` until we no longer can.

Return  *the final string after all such duplicate removals have been made*. It is guaranteed that the answer is  **unique**.

 

 **Example 1:** 

```
Input: s = "abcd", k = 2
Output: "abcd"
Explanation: There's nothing to delete.
```

 **Example 2:** 

```
Input: s = "deeedbbcccbdaa", k = 3
Output: "aa"
Explanation: 
First delete "eee" and "ccc", get "ddbbbdaa"
Then delete "bbb", get "dddaa"
Finally delete "ddd", get "aa"
```

 **Example 3:** 

```
Input: s = "pbbcggttciiippooaais", k = 2
Output: "ps"

```

 

 **Constraints:** 

- 1 <= s.length <= 105
- 2 <= k <= 104
- s only contains lowercase English letters.

## Solution

**Language:** Java  
**Runtime:** 6 ms (beats 98.81%)  
**Memory:** 47.3 MB (beats 47.21%)  
**Submitted:** 2026-08-27T09:16:12.535Z  

```java
class Solution {
    public String removeDuplicates(String s, int k) {

        char[] chars = new char[s.length()];
        int[] count = new int[s.length()];

        int top = -1;

        for (char c : s.toCharArray()) {

            // Add character to stack
            top++;
            chars[top] = c;

            // If same as previous character, increase count
            if (top > 0 && chars[top - 1] == c) {
                count[top] = count[top - 1] + 1;
            } else {
                count[top] = 1;
            }

            // Remove k consecutive characters
            if (count[top] == k) {
                top -= k;
            }
        }

        // Build result
        StringBuilder result = new StringBuilder();

        for (int i = 0; i <= top; i++) {
            result.append(chars[i]);
        }

        return result.toString();
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/)