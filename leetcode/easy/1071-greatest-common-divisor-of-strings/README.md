# Greatest Common Divisor of Strings

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

For two strings `s` and `t`, we say "`t` divides `s`" if and only if `s = t + t + t +... + t + t` (i.e., `t` is concatenated with itself one or more times).

Given two strings `str1` and `str2`, return  *the largest string* `x` *such that* `x` *divides both* `str1` *and* `str2`.

 

 **Example 1:** 

 **Input:**  str1 = "ABCABC", str2 = "ABC"

 **Output:**  "ABC"

 **Example 2:** 

 **Input:**  str1 = "ABABAB", str2 = "ABAB"

 **Output:**  "AB"

 **Example 3:** 

 **Input:**  str1 = "LEET", str2 = "CODE"

 **Output:**  ""

 **Example 4:** 

 **Input:**  str1 = "AAAAAB", str2 = "AAA"

 **Output:**  ""​​​​​​​

 

 **Constraints:** 

- 1 <= str1.length, str2.length <= 1000
- str1 and str2 consist of English uppercase letters.

## Solution

**Language:** Java  
**Runtime:** 1 ms (beats 94.93%)  
**Memory:** 43.4 MB (beats 87.78%)  
**Submitted:** 2026-08-27T07:52:38.473Z  

```java
class Solution {
    public String gcdOfStrings(String str1, String str2) {

        // If they don't have the same repeating pattern,
        // no common divisor exists.
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }

        // Length of the GCD string
        int len = gcd(str1.length(), str2.length());

        return str1.substring(0, len);
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/greatest-common-divisor-of-strings/)