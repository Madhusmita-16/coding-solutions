# Maximum Length Substring With Two Occurrences

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

Given a string `s`, return the  **maximum**  length of a substring such that it contains  *at most two occurrences*  of each character.

 

 **Example 1:** 

 **Input:**  s = "bcbbbcba"

 **Output:**  4

 **Explanation:** 

The following substring has a length of 4 and contains at most two occurrences of each character: `"bcbbbcba"`.

 **Example 2:** 

 **Input:**  s = "aaaa"

 **Output:**  2

 **Explanation:** 

The following substring has a length of 2 and contains at most two occurrences of each character: `"aaaa"`.

 

 **Constraints:** 

- 2 <= s.length <= 100
- s consists only of lowercase English letters.

## Solution

**Language:** Java  
**Runtime:** 1 ms (beats 100.00%)  
**Memory:** 43.3 MB (beats 94.88%)  
**Submitted:** 2026-08-14T10:40:32.430Z  

```java
class Solution {
    public int maximumLengthSubstring(String s) {

        int[] count = new int[26];

        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {

            char ch = s.charAt(right);
            count[ch - 'a']++;

            // If any character occurs more than twice,
            // shrink the window from the left.
            while (count[ch - 'a'] > 2) {
                count[s.charAt(left) - 'a']--;
                left++;
            }

            // Current window is valid
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/maximum-length-substring-with-two-occurrences/)