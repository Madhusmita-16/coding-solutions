# Reverse Bits

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

Reverse bits of a given 32 bits signed integer.

 

 **Example 1:** 

 **Input:**  n = 43261596

 **Output:**  964176192

 **Explanation:** 

Integer	Binary
43261596	00000010100101000001111010011100
964176192	00111001011110000010100101000000

 **Example 2:** 

 **Input:**  n = 2147483644

 **Output:**  1073741822

 **Explanation:** 

Integer	Binary
2147483644	01111111111111111111111111111100
1073741822	00111111111111111111111111111110

 

 **Constraints:** 

- 0 <= n <= 231 - 2
- n is even.

 

 **Follow up:**  If this function is called many times, how would you optimize it?

## Solution

**Language:** Java  
**Runtime:** 0 ms  
**Memory:** 42.4 MB  
**Submitted:** 2026-08-15T06:56:42.339Z  

```java
class Solution {
    public int reverseBits(int n) {

        int result = 0;

        for (int i = 0; i < 32; i++) {

            // Shift result left and add the last bit of n
            result = (result << 1) | (n & 1);

            // Move to the next bit
            n >>>= 1;
        }

        return result;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/reverse-bits/)