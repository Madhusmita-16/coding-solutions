# Q2. License Key Formatting

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

You are given a license key represented as a string `s` that consists of only alphanumeric characters and dashes. The string is separated into `n + 1` groups by `n` dashes. You are also given an integer `k`.

We want to reformat the string `s` such that each group contains exactly `k` characters, except for the first group, which could be shorter than `k` but still must contain at least one character. Furthermore, there must be a dash inserted between two groups, and you should convert all lowercase letters to uppercase.

Return  *the reformatted license key*.

 

 **Example 1:** 

```
Input: s = "5F3Z-2e-9-w", k = 4
Output: "5F3Z-2E9W"
Explanation: The string s has been split into two parts, each part has 4 characters.
Note that the two extra dashes are not needed and can be removed.

```

 **Example 2:** 

```
Input: s = "2-5g-3-J", k = 2
Output: "2-5G-3J"
Explanation: The string s has been split into three parts, each part has 2 characters except the first part as it could be shorter as mentioned above.

```

 

 **Constraints:** 

- 1 <= s.length <= 105
- s consists of English letters, digits, and dashes '-'.
- 1 <= k <= 104

## Solution

**Language:** Java  
**Runtime:** 10 ms (beats 88.68%)  
**Memory:** 46.6 MB (beats 21.55%)  
**Submitted:** 2026-08-14T11:38:45.004Z  

```java
class Solution {
    public String licenseKeyFormatting(String s, int k) {

        StringBuilder result = new StringBuilder();
        int count = 0;

        // Traverse from right to left
        for (int i = s.length() - 1; i >= 0; i--) {

            char ch = s.charAt(i);

            // Skip existing dashes
            if (ch == '-') {
                continue;
            }

            // Add dash after every k characters
            if (count == k) {
                result.append('-');
                count = 0;
            }

            // Convert lowercase to uppercase
            result.append(Character.toUpperCase(ch));
            count++;
        }

        // We built the answer backwards
        return result.reverse().toString();
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/license-key-formatting/)