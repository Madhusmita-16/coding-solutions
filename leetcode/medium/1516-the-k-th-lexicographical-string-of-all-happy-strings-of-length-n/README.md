# The k-th Lexicographical String of All Happy Strings of Length n

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

A  **happy string**  is a string that:

- consists only of letters of the set ['a', 'b', 'c'].
- s[i] != s[i + 1] for all values of i from 1 to s.length - 1 (string is 1-indexed).

For example, strings  **"abc", "ac", "b"**  and  **"abcbabcbcb"**  are all happy strings and strings  **"aa", "baa"**  and  **"ababbc"**  are not happy strings.

Given two integers `n` and `k`, consider a list of all happy strings of length `n` sorted in lexicographical order.

Return  *the kth string*  of this list or return an  **empty string**  if there are less than `k` happy strings of length `n`.

 

 **Example 1:** 

```
Input: n = 1, k = 3
Output: "c"
Explanation: The list ["a", "b", "c"] contains all happy strings of length 1. The third string is "c".

```

 **Example 2:** 

```
Input: n = 1, k = 4
Output: ""
Explanation: There are only 3 happy strings of length 1.

```

 **Example 3:** 

```
Input: n = 3, k = 9
Output: "cab"
Explanation: There are 12 different happy string of length 3 ["aba", "abc", "aca", "acb", "bab", "bac", "bca", "bcb", "cab", "cac", "cba", "cbc"]. You will find the 9th string = "cab"

```

 

 **Constraints:** 

- 1 <= n <= 10
- 1 <= k <= 100

## Solution

**Language:** Java  
**Runtime:** 1 ms (beats 100.00%)  
**Memory:** 42.8 MB (beats 96.20%)  
**Submitted:** 2026-08-08T12:20:37.567Z  

```java
class Solution {
    public String getHappyString(int n, int k) {

        // Number of happy strings of length n:
        // 3 * 2^(n-1)
        int total = 3 * (1 << (n - 1));

        if (k > total) {
            return "";
        }

        StringBuilder result = new StringBuilder();

        // Each starting character ('a', 'b', 'c')
        // has 2^(n-1) strings.
        int groupSize = 1 << (n - 1);

        // Find the first character.
        if (k <= groupSize) {
            result.append('a');
        } else if (k <= 2 * groupSize) {
            result.append('b');
            k -= groupSize;
        } else {
            result.append('c');
            k -= 2 * groupSize;
        }

        // Construct remaining characters.
        for (int i = 1; i < n; i++) {

            groupSize /= 2;

            char prev = result.charAt(i - 1);

            char first;
            char second;

            // Determine the two possible characters
            // in lexicographical order.
            if (prev == 'a') {
                first = 'b';
                second = 'c';
            } else if (prev == 'b') {
                first = 'a';
                second = 'c';
            } else {
                first = 'a';
                second = 'b';
            }

            if (k <= groupSize) {
                result.append(first);
            } else {
                result.append(second);
                k -= groupSize;
            }
        }

        return result.toString();
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n/)