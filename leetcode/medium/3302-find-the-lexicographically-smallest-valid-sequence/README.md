# Find the Lexicographically Smallest Valid Sequence

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given two strings `word1` and `word2`.

A string `x` is called  **almost equal**  to `y` if you can change  **at most**  one character in `x` to make it  *identical*  to `y`.

A sequence of indices `seq` is called  **valid**  if:

- The indices are sorted in ascending order.
- Concatenating the characters at these indices in word1 in the same order results in a string that is almost equal to word2.

Return an array of size `word2.length` representing the lexicographically smallest  **valid**  sequence of indices. If no such sequence of indices exists, return an  **empty**  array.

 **Note**  that the answer must represent the  *lexicographically smallest array*,  **not**  the corresponding string formed by those indices.

 

 **Example 1:** 

 **Input:**  word1 = "vbcca", word2 = "abc"

 **Output:**  [0,1,2]

 **Explanation:** 

The lexicographically smallest valid sequence of indices is `[0, 1, 2]`:

- Change word1[0] to 'a'.
- word1[1] is already 'b'.
- word1[2] is already 'c'.

 **Example 2:** 

 **Input:**  word1 = "bacdc", word2 = "abc"

 **Output:**  [1,2,4]

 **Explanation:** 

The lexicographically smallest valid sequence of indices is `[1, 2, 4]`:

- word1[1] is already 'a'.
- Change word1[2] to 'b'.
- word1[4] is already 'c'.

 **Example 3:** 

 **Input:**  word1 = "aaaaaa", word2 = "aaabc"

 **Output:**  []

 **Explanation:** 

There is no valid sequence of indices.

 **Example 4:** 

 **Input:**  word1 = "abc", word2 = "ab"

 **Output:**  [0,1]

 

 **Constraints:** 

- 1 <= word2.length < word1.length <= 3 * 105
- word1 and word2 consist only of lowercase English letters.

## Solution

**Language:** Java  
**Runtime:** 34 ms (beats 75.00%)  
**Memory:** 136 MB (beats 94.44%)  
**Submitted:** 2026-08-08T12:15:50.164Z  

```java
class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        /*
         * dp[i] = number of characters that can be matched
         * from the END of word2 using word1[i...n-1].
         */
        int[] dp = new int[n + 1];

        int j = m - 1;

        for (int i = n - 1; i >= 0; i--) {
            dp[i] = dp[i + 1];

            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                dp[i]++;
                j--;
            }
        }

        int[] ans = new int[m];

        int i = 0;
        j = 0;

        /*
         * First phase:
         * Find the lexicographically smallest prefix.
         *
         * We greedily take matching characters.
         * If we encounter a mismatch, we can use our
         * one allowed replacement IF the remaining
         * characters can all be matched.
         */
        while (i < n && j < m) {

            if (word1.charAt(i) == word2.charAt(j)) {
                // Exact match: always prefer this index.
                ans[j] = i;
                j++;
            } else {
                /*
                 * Use the one allowed mismatch here.
                 *
                 * After using this mismatch, we need to
                 * match word2[j+1 ... m-1].
                 *
                 * Number of characters required:
                 * m - 1 - j
                 */
                if (dp[i + 1] >= m - 1 - j) {
                    ans[j] = i;
                    j++;

                    // Mismatch is now consumed.
                    i++;

                    break;
                }
            }

            i++;
        }

        /*
         * If we couldn't choose all characters, no solution.
         */
        if (j < m && i >= n) {
            return new int[0];
        }

        /*
         * The mismatch has now either been used or was not needed.
         * From here, match the remaining characters exactly.
         */
        while (i < n && j < m) {

            if (word1.charAt(i) == word2.charAt(j)) {
                ans[j] = i;
                j++;
            }

            i++;
        }

        /*
         * If all characters of word2 were matched,
         * ans is valid.
         */
        if (j == m) {
            return ans;
        }

        return new int[0];
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/find-the-lexicographically-smallest-valid-sequence/)