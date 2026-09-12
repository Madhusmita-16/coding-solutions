# Unique 3-Digit Even Numbers

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

You are given an array of digits called `digits`. Your task is to determine the number of  **distinct**  three-digit even numbers that can be formed using these digits.

 **Note** : Each  *copy*  of a digit can only be used  **once per number**, and there may  **not**  be leading zeros.

 

 **Example 1:** 

 **Input:**  digits = [1,2,3,4]

 **Output:**  12

 **Explanation:**  The 12 distinct 3-digit even numbers that can be formed are 124, 132, 134, 142, 214, 234, 312, 314, 324, 342, 412, and 432. Note that 222 cannot be formed because there is only 1 copy of the digit 2.

 **Example 2:** 

 **Input:**  digits = [0,2,2]

 **Output:**  2

 **Explanation:**  The only 3-digit even numbers that can be formed are 202 and 220. Note that the digit 2 can be used twice because it appears twice in the array.

 **Example 3:** 

 **Input:**  digits = [6,6,6]

 **Output:**  1

 **Explanation:**  Only 666 can be formed.

 **Example 4:** 

 **Input:**  digits = [1,3,5]

 **Output:**  0

 **Explanation:**  No even 3-digit numbers can be formed.

 

 **Constraints:** 

- 3 <= digits.length <= 10
- 0 <= digits[i] <= 9

## Solution

**Language:** Java  
**Runtime:** 3 ms (beats 88.27%)  
**Memory:** 46.4 MB (beats 35.72%)  
**Submitted:** 2026-09-12T00:39:16.761Z  

```java
class Solution {
    public int totalNumbers(int[] digits) {
        int[] freq = new int[10];

        // Count available copies of each digit
        for (int d : digits) {
            freq[d]++;
        }

        int count = 0;

        // Hundreds digit: 1-9 (no leading zero)
        for (int a = 1; a <= 9; a++) {
            if (freq[a] == 0) continue;

            // Tens digit: 0-9
            for (int b = 0; b <= 9; b++) {
                if (freq[b] == 0) continue;

                // Units digit must be even
                for (int c = 0; c <= 8; c += 2) {
                    if (freq[c] == 0) continue;

                    // Check whether we have enough copies
                    int[] used = new int[10];
                    used[a]++;
                    used[b]++;
                    used[c]++;

                    boolean possible = true;

                    for (int d = 0; d <= 9; d++) {
                        if (used[d] > freq[d]) {
                            possible = false;
                            break;
                        }
                    }

                    if (possible) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/unique-3-digit-even-numbers/)