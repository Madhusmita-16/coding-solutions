# Maximize Active Section with Trade I

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given a binary string `s` of length `n`, where:

- '1' represents an active section.
- '0' represents an inactive section.

You can perform  **at most one trade**  to maximize the number of active sections in `s`. In a trade, you:

- Convert a contiguous block of '1's that is surrounded by '0's to all '0's.
- Afterward, convert a contiguous block of '0's that is surrounded by '1's to all '1's.

Return the  **maximum**  number of active sections in `s` after making the optimal trade.

 **Note:**  Treat `s` as if it is  **augmented**  with a `'1'` at both ends, forming `t = '1' + s + '1'`. The augmented `'1'`s  **do not**  contribute to the final count.

 

 **Example 1:** 

 **Input:**  s = "01"

 **Output:**  1

 **Explanation:** 

Because there is no block of `'1'`s surrounded by `'0'`s, no valid trade is possible. The maximum number of active sections is 1.

 **Example 2:** 

 **Input:**  s = "0100"

 **Output:**  4

 **Explanation:** 

- String "0100" → Augmented to "101001".
- Choose "0100", convert "101001" → "100001" → "111111".
- The final string without augmentation is "1111". The maximum number of active sections is 4.

 **Example 3:** 

 **Input:**  s = "1000100"

 **Output:**  7

 **Explanation:** 

- String "1000100" → Augmented to "110001001".
- Choose "000100", convert "110001001" → "110000001" → "111111111".
- The final string without augmentation is "1111111". The maximum number of active sections is 7.

 **Example 4:** 

 **Input:**  s = "01010"

 **Output:**  4

 **Explanation:** 

- String "01010" → Augmented to "1010101".
- Choose "010", convert "1010101" → "1000101" → "1111101".
- The final string without augmentation is "11110". The maximum number of active sections is 4.

 

 **Constraints:** 

- 1 <= n == s.length <= 105
- s[i] is either '0' or '1'

## Solution

**Language:** Java  
**Runtime:** 112 ms (beats 43.60%)  
**Memory:** 48.5 MB (beats 21.89%)  
**Submitted:** 2026-08-21T06:54:17.863Z  

```java
class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        String t = "1" + s + "1";

        int initialOnes = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') {
                initialOnes++;
            }
        }

        // Run lengths of t.
        int len = t.length();
        int[] runs = new int[len];
        char[] chars = new char[len];

        int m = 0;
        int i = 0;

        while (i < len) {
            char c = t.charAt(i);
            int j = i;

            while (j < len && t.charAt(j) == c) {
                j++;
            }

            chars[m] = c;
            runs[m] = j - i;
            m++;

            i = j;
        }

        int bestGain = 0;

        /*
         * Pattern:
         *
         *     0-block | 1-block | 0-block
         *
         * Remove the middle 1-block.
         * The two zero blocks merge and become 1s.
         *
         * Gain = leftZero + rightZero
         */
        for (i = 1; i < m - 1; i++) {
            if (chars[i] == '1'
                    && chars[i - 1] == '0'
                    && chars[i + 1] == '0') {

                int gain = runs[i - 1] + runs[i + 1];

                bestGain = Math.max(bestGain, gain);
            }
        }

        return initialOnes + bestGain;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/maximize-active-section-with-trade-i/)