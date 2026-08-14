# Q3. The k-th Lexicographical String of All Happy Strings of Length n

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
**Memory:** 43.2 MB (beats 63.16%)  
**Submitted:** 2026-08-14T15:54:41.543Z  

```java
class Solution {
    public String getHappyString(int n, int k) {

        int total = 3 * (1 << (n - 1));

        // Fewer than k happy strings exist
        if (k > total) {
            return "";
        }

        StringBuilder ans = new StringBuilder();

        // Number of strings for each starting character
        int groupSize = 1 << (n - 1);

        // Choose first character
        if (k <= groupSize) {
            ans.append('a');
        } else if (k <= 2 * groupSize) {
            ans.append('b');
            k -= groupSize;
        } else {
            ans.append('c');
            k -= 2 * groupSize;
        }

        // Choose remaining characters
        for (int i = 1; i < n; i++) {

            groupSize /= 2;

            char prev = ans.charAt(i - 1);

            char first;
            char second;

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
                ans.append(first);
            } else {
                ans.append(second);
                k -= groupSize;
            }
        }

        return ans.toString();
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n/)