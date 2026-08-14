# Q3. Repeated String Match

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given two strings `a` and `b`, return  *the minimum number of times you should repeat string* `a` *so that string*  `b`  *is a substring of it*. If it is impossible for `b`​​​​​​ to be a substring of `a` after repeating it, return `-1`.

 **Notice:**  string `"abc"` repeated 0 times is `""`, repeated 1 time is `"abc"` and repeated 2 times is `"abcabc"`.

 

 **Example 1:** 

```
Input: a = "abcd", b = "cdabcdab"
Output: 3
Explanation: We return 3 because by repeating a three times "abcdabcdabcd", b is a substring of it.

```

 **Example 2:** 

```
Input: a = "a", b = "aa"
Output: 2

```

 

 **Constraints:** 

- 1 <= a.length, b.length <= 104
- a and b consist of lowercase English letters.

## Solution

**Language:** Java  
**Runtime:** 0 ms  
**Memory:** 42.8 MB  
**Submitted:** 2026-08-14T11:47:56.732Z  

```java
class Solution {
    public int repeatedStringMatch(String a, String b) {

        StringBuilder sb = new StringBuilder();
        int count = 0;

        // Repeat until length is at least b.length()
        while (sb.length() < b.length()) {
            sb.append(a);
            count++;
        }

        // Check after the minimum required repetitions
        if (sb.toString().contains(b)) {
            return count;
        }

        // One extra repetition may be required
        sb.append(a);
        count++;

        if (sb.toString().contains(b)) {
            return count;
        }

        return -1;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/repeated-string-match/)