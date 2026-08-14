# Q2. Longest Happy Prefix

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

A string is called a  **happy prefix**  if it is a  **non-empty**  prefix which is also a suffix (excluding itself).

Given a string `s`, return  *the  **longest happy prefix**  of*  `s`. Return an empty string `""` if no such prefix exists.

 

 **Example 1:** 

```
Input: s = "level"
Output: "l"
Explanation: s contains 4 prefix excluding itself ("l", "le", "lev", "leve"), and suffix ("l", "el", "vel", "evel"). The largest prefix which is also suffix is given by "l".

```

 **Example 2:** 

```
Input: s = "ababab"
Output: "abab"
Explanation: "abab" is the largest prefix which is also suffix. They can overlap in the original string.

```

 

 **Constraints:** 

- 1 <= s.length <= 105
- s contains only lowercase English letters.

## Solution

**Language:** Java  
**Runtime:** 9 ms (beats 96.38%)  
**Memory:** 47.8 MB (beats 42.50%)  
**Submitted:** 2026-08-14T15:47:56.600Z  

```java
class Solution {
    public String longestPrefix(String s) {

        int n = s.length();
        int[] lps = new int[n];

        int len = 0;
        int i = 1;

        while (i < n) {

            if (s.charAt(i) == s.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len > 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        int length = lps[n - 1];

        return s.substring(0, length);
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/longest-happy-prefix/)