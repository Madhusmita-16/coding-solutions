# Find the String with LCP

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

We define the `lcp` matrix of any  **0-indexed**  string `word` of `n` lowercase English letters as an `n x n` grid such that:

- lcp[i][j] is equal to the length of the longest common prefix between the substrings word[i,n-1] and word[j,n-1].

Given an `n x n` matrix `lcp`, return the alphabetically smallest string `word` that corresponds to `lcp`. If there is no such string, return an empty string.

A string `a` is lexicographically smaller than a string `b` (of the same length) if in the first position where `a` and `b` differ, string `a` has a letter that appears earlier in the alphabet than the corresponding letter in `b`. For example, `"aabd"` is lexicographically smaller than `"aaca"` because the first position they differ is at the third letter, and `'b'` comes before `'c'`.

 

 **Example 1:** 

```
Input: lcp = [[4,0,2,0],[0,3,0,1],[2,0,2,0],[0,1,0,1]]
Output: "abab"
Explanation: lcp corresponds to any 4 letter string with two alternating letters. The lexicographically smallest of them is "abab".

```

 **Example 2:** 

```
Input: lcp = [[4,3,2,1],[3,3,2,1],[2,2,2,1],[1,1,1,1]]
Output: "aaaa"
Explanation: lcp corresponds to any 4 letter string with a single distinct letter. The lexicographically smallest of them is "aaaa". 

```

 **Example 3:** 

```
Input: lcp = [[4,3,2,1],[3,3,2,1],[2,2,2,1],[1,1,1,3]]
Output: ""
Explanation: lcp[3][3] cannot be equal to 3 since word[3,...,3] consists of only a single letter; Thus, no answer exists.

```

 

 **Constraints:** 

- 1 <= n == lcp.length == lcp[i].length <= 1000
- 0 <= lcp[i][j] <= n

## Solution

**Language:** Java  
**Runtime:** 5 ms (beats 100.00%)  
**Memory:** 172.3 MB (beats 80.45%)  
**Submitted:** 2026-08-08T12:46:11.912Z  

```java
class Solution {
    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        char[] word = new char[n];

        char current = 'a';

        // Greedy construction
        for (int i = 0; i < n; i++) {

            // Already assigned
            if (word[i] != '\0') {
                continue;
            }

            // We need another character
            if (current > 'z') {
                return "";
            }

            word[i] = current;

            // Every position j with lcp[i][j] > 0
            // must have the same character.
            for (int j = i + 1; j < n; j++) {
                if (lcp[i][j] > 0) {
                    word[j] = current;
                }
            }

            current++;
        }

        // Verify the entire LCP matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                int expected;

                if (word[i] != word[j]) {
                    expected = 0;
                } else {
                    if (i == n - 1 || j == n - 1) {
                        expected = 1;
                    } else {
                        expected = lcp[i + 1][j + 1] + 1;
                    }
                }

                if (lcp[i][j] != expected) {
                    return "";
                }
            }
        }

        return new String(word);
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/find-the-string-with-lcp/)