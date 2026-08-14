# Q3. Sum of Scores of Built Strings

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

You are  **building**  a string `s` of length `n`  **one**  character at a time,  **prepending**  each new character to the  **front**  of the string. The strings are labeled from `1` to `n`, where the string with length `i` is labeled `si`.

- For example, for s = "abaca", s1 == "a", s2 == "ca", s3 == "aca", etc.

The  **score**  of `si` is the length of the  **longest common prefix**  between `si` and `sn` (Note that `s == sn`).

Given the final string `s`, return *the  **sum**  of the  **score**  of every* `si`.

 

 **Example 1:** 

```
Input: s = "babab"
Output: 9
Explanation:
For s1 == "b", the longest common prefix is "b" which has a score of 1.
For s2 == "ab", there is no common prefix so the score is 0.
For s3 == "bab", the longest common prefix is "bab" which has a score of 3.
For s4 == "abab", there is no common prefix so the score is 0.
For s5 == "babab", the longest common prefix is "babab" which has a score of 5.
The sum of the scores is 1 + 0 + 3 + 0 + 5 = 9, so we return 9.
```

 **Example 2:** 

```
Input: s = "azbazbzaz"
Output: 14
Explanation: 
For s2 == "az", the longest common prefix is "az" which has a score of 2.
For s6 == "azbzaz", the longest common prefix is "azb" which has a score of 3.
For s9 == "azbazbzaz", the longest common prefix is "azbazbzaz" which has a score of 9.
For all other si, the score is 0.
The sum of the scores is 2 + 3 + 9 = 14, so we return 14.

```

 

 **Constraints:** 

- 1 <= s.length <= 105
- s consists of lowercase English letters.

## Solution

**Language:** Java  
**Runtime:** 16 ms (beats 39.03%)  
**Memory:** 47.8 MB (beats 39.54%)  
**Submitted:** 2026-08-14T15:49:06.928Z  

```java
class Solution {
    public long sumScores(String s) {

        int n = s.length();
        int[] z = new int[n];

        int left = 0;
        int right = 0;

        long answer = n; // z[0] = n

        for (int i = 1; i < n; i++) {

            if (i <= right) {
                z[i] = Math.min(right - i + 1, z[i - left]);
            }

            while (i + z[i] < n &&
                   s.charAt(z[i]) == s.charAt(i + z[i])) {
                z[i]++;
            }

            if (i + z[i] - 1 > right) {
                left = i;
                right = i + z[i] - 1;
            }

            answer += z[i];
        }

        return answer;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/sum-of-scores-of-built-strings/)