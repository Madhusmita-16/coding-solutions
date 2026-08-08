# Fancy Sequence

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

Write an API that generates fancy sequences using the `append`, `addAll`, and `multAll` operations.

Implement the `Fancy` class:

- Fancy() Initializes the object with an empty sequence.
- void append(val) Appends an integer val to the end of the sequence.
- void addAll(inc) Increments all existing values in the sequence by an integer inc.
- void multAll(m) Multiplies all existing values in the sequence by an integer m.
- int getIndex(idx) Gets the current value at index idx (0-indexed) of the sequence modulo 109 + 7. If the index is greater or equal than the length of the sequence, return -1.

 

 **Example 1:** 

```
Input
["Fancy", "append", "addAll", "append", "multAll", "getIndex", "addAll", "append", "multAll", "getIndex", "getIndex", "getIndex"]
[[], [2], [3], [7], [2], [0], [3], [10], [2], [0], [1], [2]]
Output
[null, null, null, null, null, 10, null, null, null, 26, 34, 20]

Explanation
Fancy fancy = new Fancy();
fancy.append(2);   // fancy sequence: [2]
fancy.addAll(3);   // fancy sequence: [2+3] -> [5]
fancy.append(7);   // fancy sequence: [5, 7]
fancy.multAll(2);  // fancy sequence: [5 *2, 7* 2] -> [10, 14]
fancy.getIndex(0); // return 10
fancy.addAll(3);   // fancy sequence: [10+3, 14+3] -> [13, 17]
fancy.append(10);  // fancy sequence: [13, 17, 10]
fancy.multAll(2);  // fancy sequence: [13 *2, 17* 2, 10*2] -> [26, 34, 20]
fancy.getIndex(0); // return 26
fancy.getIndex(1); // return 34
fancy.getIndex(2); // return 20

```

 

 **Constraints:** 

- 1 <= val, inc, m <= 100
- 0 <= idx <= 105
- At most 105 calls total will be made to append, addAll, multAll, and getIndex.

## Solution

**Language:** Java  
**Runtime:** 49 ms (beats 84.15%)  
**Memory:** 122.1 MB (beats 74.30%)  
**Submitted:** 2026-08-08T12:21:40.768Z  

```java
class Fancy {

    private static final long MOD = 1_000_000_007L;

    // Global transformation:
    // actualValue = storedValue * mul + add
    private long mul = 1;
    private long add = 0;

    private java.util.ArrayList<Long> sequence;

    public Fancy() {
        sequence = new java.util.ArrayList<>();
    }

    public void append(int val) {
        /*
         * We want:
         *
         * stored * mul + add = val
         *
         * Therefore:
         *
         * stored = (val - add) / mul
         *
         * Division modulo MOD is multiplication
         * by modular inverse.
         */
        long value = (val - add + MOD) % MOD;

        value = value * modPow(mul, MOD - 2) % MOD;

        sequence.add(value);
    }

    public void addAll(int inc) {
        add = (add + inc) % MOD;
    }

    public void multAll(int m) {
        mul = mul * m % MOD;
        add = add * m % MOD;
    }

    public int getIndex(int idx) {
        if (idx < 0 || idx >= sequence.size()) {
            return -1;
        }

        long value = sequence.get(idx);

        long result = value * mul % MOD;
        result = (result + add) % MOD;

        return (int) result;
    }

    private long modPow(long base, long exponent) {
        long result = 1;

        while (exponent > 0) {
            if ((exponent & 1) == 1) {
                result = result * base % MOD;
            }

            base = base * base % MOD;
            exponent >>= 1;
        }

        return result;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/fancy-sequence/)