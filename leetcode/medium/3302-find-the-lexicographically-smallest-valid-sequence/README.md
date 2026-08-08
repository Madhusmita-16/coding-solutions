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
**Runtime:** 32 ms (beats 86.11%)  
**Memory:** 137.1 MB (beats 80.56%)  
**Submitted:** 2026-08-08T12:55:52.040Z  

```java
import java.util.*;

class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // last[j] = rightmost position in word1 where word2[j]
        // can be matched while matching word2[j...m-1].
        int[] last = new int[m];

        int p = n - 1;

        for (int j = m - 1; j >= 0; j--) {
            while (p >= 0 && word1.charAt(p) != word2.charAt(j)) {
                p--;
            }

            if (p < 0) {
                // Even the suffix cannot be matched exactly.
                // But one mismatch may be used for one character.
                // We handle this naturally below.
                last[j] = -1;
            } else {
                last[j] = p;
                p--;
            }
        }

        int[] ans = new int[m];

        int j = 0;
        boolean changed = false;

        for (int i = 0; i < n && j < m; i++) {

            // Exact match
            if (word1.charAt(i) == word2.charAt(j)) {
                ans[j] = i;
                j++;
                continue;
            }

            // Try using our one allowed modification here.
            if (!changed) {
                boolean possible = false;

                // If this is the last character, we can always
                // change word1[i] to word2[j].
                if (j == m - 1) {
                    possible = true;
                } else {
                    /*
                     * After using the modification at i,
                     * word2[j+1...] must be matched exactly.
                     *
                     * We need its first feasible position
                     * to be after i.
                     */
                    if (last[j + 1] > i) {
                        possible = true;
                    }
                }

                if (possible) {
                    ans[j] = i;
                    j++;
                    changed = true;
                }
            }
        }

        if (j == m) {
            return ans;
        }

        return new int[0];
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/find-the-lexicographically-smallest-valid-sequence/)