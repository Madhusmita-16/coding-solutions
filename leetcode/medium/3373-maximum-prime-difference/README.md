# Q3. Maximum Prime Difference

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given an integer array `nums`.

Return an integer that is the  **maximum**  distance between the  **indices**  of two (not necessarily different) prime numbers in `nums` *.* 

 

 **Example 1:** 

 **Input:**  nums = [4,2,9,5,3]

 **Output:**  3

 **Explanation:**  `nums[1]`, `nums[3]`, and `nums[4]` are prime. So the answer is `|4 - 1| = 3`.

 **Example 2:** 

 **Input:**  nums = [4,8,2,8]

 **Output:**  0

 **Explanation:**  `nums[2]` is prime. Because there is just one prime number, the answer is `|2 - 2| = 0`.

 

 **Constraints:** 

- 1 <= nums.length <= 3 * 105
- 1 <= nums[i] <= 100
- The input is generated such that the number of prime numbers in the nums is at least one.

## Solution

**Language:** Java  
**Runtime:** 12 ms (beats 48.09%)  
**Memory:** 117.4 MB (beats 6.94%)  
**Submitted:** 2026-08-14T18:00:19.117Z  

```java
class Solution {
    public int maximumPrimeDifference(int[] nums) {

        int firstPrime = -1;
        int lastPrime = -1;

        for (int i = 0; i < nums.length; i++) {

            if (isPrime(nums[i])) {

                if (firstPrime == -1) {
                    firstPrime = i;
                }

                lastPrime = i;
            }
        }

        return lastPrime - firstPrime;
    }

    private boolean isPrime(int num) {

        if (num < 2) {
            return false;
        }

        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/maximum-prime-difference/)