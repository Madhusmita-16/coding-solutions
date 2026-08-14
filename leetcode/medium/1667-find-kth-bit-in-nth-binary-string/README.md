# Q2. Find Kth Bit in Nth Binary String

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given two positive integers `n` and `k`, the binary string `Sn` is formed as follows:

- S1 = "0"
- Si = Si - 1 + "1" + reverse(invert(Si - 1)) for i > 1

Where `+` denotes the concatenation operation, `reverse(x)` returns the reversed string `x`, and `invert(x)` inverts all the bits in `x` (`0` changes to `1` and `1` changes to `0`).

For example, the first four strings in the above sequence are:

- S1 = "0"
- S2 = "011"
- S3 = "0111001"
- S4 = "011100110110001"

Return  *the*  `kth`  *bit*   *in*  `Sn`. It is guaranteed that `k` is valid for the given `n`.

 

 **Example 1:** 

```
Input: n = 3, k = 1
Output: "0"
Explanation: S3 is "0111001".
The 1st bit is "0".

```

 **Example 2:** 

```
Input: n = 4, k = 11
Output: "1"
Explanation: S4 is "011100110110001".
The 11th bit is "1".

```

 

 **Constraints:** 

- 1 <= n <= 20
- 1 <= k <= 2n - 1

## Solution

**Language:** Java  
**Runtime:** 0 ms (beats 100.00%)  
**Memory:** 42.2 MB (beats 95.39%)  
**Submitted:** 2026-08-14T15:50:53.975Z  

```java
class Solution {
    public char findKthBit(int n, int k) {

        // Base case: S1 = "0"
        if (n == 1) {
            return '0';
        }

        int mid = 1 << (n - 1);

        // Middle bit is always 1
        if (k == mid) {
            return '1';
        }

        // First half
        if (k < mid) {
            return findKthBit(n - 1, k);
        }

        // Second half
        int mirror = (1 << n) - k;

        char bit = findKthBit(n - 1, mirror);

        // Invert the bit
        return bit == '0' ? '1' : '0';
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/find-kth-bit-in-nth-binary-string/)