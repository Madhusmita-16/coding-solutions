# Check if Strings Can be Made Equal With Operations II

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given two strings `s1` and `s2`, both of length `n`, consisting of  **lowercase**  English letters.

You can apply the following operation on  **any**  of the two strings  **any**  number of times:

- Choose any two indices i and j such that i < j and the difference j - i is even, then swap the two characters at those indices in the string.

Return `true` *if you can make the strings* `s1` *and* `s2` *equal, and* `false` *otherwise*.

 

 **Example 1:** 

```
Input: s1 = "abcdba", s2 = "cabdab"
Output: true
Explanation: We can apply the following operations on s1:
- Choose the indices i = 0, j = 2. The resulting string is s1 = "cbadba".
- Choose the indices i = 2, j = 4. The resulting string is s1 = "cbbdaa".
- Choose the indices i = 1, j = 5. The resulting string is s1 = "cabdab" = s2.

```

 **Example 2:** 

```
Input: s1 = "abe", s2 = "bea"
Output: false
Explanation: It is not possible to make the two strings equal.

```

 

 **Constraints:** 

- n == s1.length == s2.length
- 1 <= n <= 105
- s1 and s2 consist only of lowercase English letters.

## Solution

**Language:** Java  
**Runtime:** 5 ms (beats 89.42%)  
**Memory:** 48.1 MB (beats 32.31%)  
**Submitted:** 2026-08-08T12:59:26.861Z  

```java
class Solution {
    public boolean checkStrings(String s1, String s2) {
        int n = s1.length();

        // 0 = even positions, 1 = odd positions
        int[][] freq = new int[2][26];

        for (int i = 0; i < n; i++) {
            int parity = i % 2;

            freq[parity][s1.charAt(i) - 'a']++;
            freq[parity][s2.charAt(i) - 'a']--;
        }

        // All frequencies must be zero
        for (int parity = 0; parity < 2; parity++) {
            for (int c = 0; c < 26; c++) {
                if (freq[parity][c] != 0) {
                    return false;
                }
            }
        }

        return true;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/check-if-strings-can-be-made-equal-with-operations-ii/)