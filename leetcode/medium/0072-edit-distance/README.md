# Edit Distance

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given two strings `word1` and `word2`, return  *the minimum number of operations required to convert `word1` to `word2`*.

You have the following three operations permitted on a word:

- Insert a character
- Delete a character
- Replace a character

 

 **Example 1:** 

```
Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')

```

 **Example 2:** 

```
Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation: 
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')

```

 

 **Constraints:** 

- 0 <= word1.length, word2.length <= 500
- word1 and word2 consist of lowercase English letters.

## Solution

**Language:** Java  
**Runtime:** 5 ms (beats 66.11%)  
**Memory:** 47.3 MB (beats 35.73%)  
**Submitted:** 2026-08-15T06:34:35.201Z  

```java
class Solution {
    public int minDistance(String word1, String word2) {

        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m + 1][n + 1];

        // Convert word1[0..i] to an empty string
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        // Convert empty string to word2[0..j]
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {

                    int insert = dp[i][j - 1];
                    int delete = dp[i - 1][j];
                    int replace = dp[i - 1][j - 1];

                    dp[i][j] = 1 + Math.min(
                        insert,
                        Math.min(delete, replace)
                    );
                }
            }
        }

        return dp[m][n];
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/edit-distance/)