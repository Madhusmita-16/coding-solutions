# Scramble String

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

We can scramble a string s to get a string t using the following algorithm:

- If the length of the string is 1, stop.
- If the length of the string is > 1, do the following: Split the string into two non-empty substrings at a random index, i.e., if the string is s, divide it to x and y where s = x + y. Randomly decide to swap the two substrings or to keep them in the same order. i.e., after this step, s may become s = x + y or s = y + x. Apply step 1 recursively on each of the two substrings x and y.

Given two strings `s1` and `s2` of  **the same length**, return `true` if `s2` is a scrambled string of `s1`, otherwise, return `false`.

 

 **Example 1:** 

```
Input: s1 = "great", s2 = "rgeat"
Output: true
Explanation: One possible scenario applied on s1 is:
"great" --> "gr/eat" // divide at random index.
"gr/eat" --> "gr/eat" // random decision is not to swap the two substrings and keep them in order.
"gr/eat" --> "g/r / e/at" // apply the same algorithm recursively on both substrings. divide at random index each of them.
"g/r / e/at" --> "r/g / e/at" // random decision was to swap the first substring and to keep the second substring in the same order.
"r/g / e/at" --> "r/g / e/ a/t" // again apply the algorithm recursively, divide "at" to "a/t".
"r/g / e/ a/t" --> "r/g / e/ a/t" // random decision is to keep both substrings in the same order.
The algorithm stops now, and the result string is "rgeat" which is s2.
As one possible scenario led s1 to be scrambled to s2, we return true.

```

 **Example 2:** 

```
Input: s1 = "abcde", s2 = "caebd"
Output: false

```

 **Example 3:** 

```
Input: s1 = "a", s2 = "a"
Output: true

```

 

 **Constraints:** 

- s1.length == s2.length
- 1 <= s1.length <= 30
- s1 and s2 consist of lowercase English letters.

## Solution

**Language:** Java  
**Runtime:** 7 ms (beats 77.84%)  
**Memory:** 44.4 MB (beats 99.74%)  
**Submitted:** 2026-08-18T15:31:54.726Z  

```java
class Solution {
    public boolean isScramble(String s1, String s2) {
        int n = s1.length();

        if (s1.equals(s2)) {
            return true;
        }

        // dp[len][i][j]
        boolean[][][] dp = new boolean[n + 1][n][n];

        // Length 1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[1][i][j] = s1.charAt(i) == s2.charAt(j);
            }
        }

        // Build for increasing lengths
        for (int len = 2; len <= n; len++) {

            for (int i = 0; i + len <= n; i++) {
                for (int j = 0; j + len <= n; j++) {

                    // Try every possible split
                    for (int k = 1; k < len; k++) {

                        // Case 1: No swap
                        boolean noSwap =
                            dp[k][i][j] &&
                            dp[len - k][i + k][j + k];

                        // Case 2: Swap
                        boolean swap =
                            dp[k][i][j + len - k] &&
                            dp[len - k][i + k][j];

                        if (noSwap || swap) {
                            dp[len][i][j] = true;
                            break;
                        }
                    }
                }
            }
        }

        return dp[n][0][0];
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/scramble-string/)