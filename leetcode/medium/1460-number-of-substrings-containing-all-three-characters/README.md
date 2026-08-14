# Q2. Number of Substrings Containing All Three Characters

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given a string `s` consisting only of characters  *a*,  *b*  and  *c*.

Return the number of substrings containing  **at least**  one occurrence of all these characters  *a*,  *b*  and  *c*.

 

 **Example 1:** 

```
Input: s = "abcabc"
Output: 10
Explanation: The substrings containing at least one occurrence of the characters a, b and c are "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again). 

```

 **Example 2:** 

```
Input: s = "aaacb"
Output: 3
Explanation: The substrings containing at least one occurrence of the characters a, b and c are "aaacb", "aacb" and "acb". 

```

 **Example 3:** 

```
Input: s = "abc"
Output: 1

```

 

 **Constraints:** 

- 3 <= s.length <= 5 x 104
- s only consists of 'a', 'b' or 'c' characters.

## Solution

**Language:** Java  
**Runtime:** 10 ms (beats 95.25%)  
**Memory:** 46.2 MB (beats 59.31%)  
**Submitted:** 2026-08-14T15:28:55.756Z  

```java
class Solution {
    public int numberOfSubstrings(String s) {

        int lastA = -1;
        int lastB = -1;
        int lastC = -1;

        int count = 0;

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            if (ch == 'a') {
                lastA = i;
            } else if (ch == 'b') {
                lastB = i;
            } else {
                lastC = i;
            }

            int min = Math.min(lastA, Math.min(lastB, lastC));

            if (min != -1) {
                count += min + 1;
            }
        }

        return count;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/)