# Lexicographically Largest String After Pair Transformations

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given an integer array `nums`.

For each integer `x` in `nums`, start with a string consisting of exactly `x` lowercase `'a'` characters.

You may perform the following operation any number of times (including zero):

- Choose two adjacent equal letters and replace them with the next letter in the alphabet.

For example, `"aa"` can be replaced with `"b"`, and `"bb"` can be replaced with `"c"`. The pair `"zz"` cannot be replaced.

For each `x`, determine the  **lexicographically largest**  string that can be obtained.

Return an array of strings where the `ith` string is the answer for `nums[i]`.

A string `a` is  **lexicographically larger**  than a string `b` if, at the first position where they differ, `a` contains a letter that appears later in the alphabet than the corresponding letter in `b`. If the first `min(a.length, b.length)` characters are equal, the longer string is lexicographically larger.

 

 **Example 1:** 

 **Input:**  nums = [2,5,7]

 **Output:**  ["b","ca","cba"]

 **Explanation:** 

- nums[0] = 2: "aa" → "b".
- nums[1] = 5: "aaaaa" → "baaa" → "bba" → "ca".
- nums[2] = 7: "aaaaaaa" → "baaaaa" → "bbaaa" → "bbba" → "cba".
- Therefore, ans = ["b", "ca", "cba"].

 **Example 2:** 

 **Input:**  nums = [3,9,1]

 **Output:**  ["ba","da","a"]

 **Explanation:** 

- nums[0] = 3: "aaa" → "ba".
- nums[1] = 9: "aaaaaaaaa" → "baaaaaaa" → "bbaaaaa" → "bbbaaa" → "bbbba" → "cbba" → "cca" → "da".
- nums[2] = 1: No transformation can be applied, so the result is "a".
- Therefore, ans = ["ba", "da", "a"].

 

 **Constraints:** 

- 1 <= nums.length <= 105
- 1 <= nums[i] <= 108

## Solution

**Language:** Java  
**Runtime:** 75 ms (beats 66.67%)  
**Memory:** 133.8 MB (beats 33.33%)  
**Submitted:** 2026-08-31T07:10:25.081Z  

```java
class Solution {
    public String[] largestString(int[] nums) {

        String[] ans = new String[nums.length];

        for (int i = 0; i < nums.length; i++) {

            long x = nums[i];
            StringBuilder sb = new StringBuilder();

            // z represents 2^25 'a's
            long zValue = 1L << 25;

            // Take as many z's as possible
            while (x >= zValue) {
                sb.append('z');
                x -= zValue;
            }

            // Represent the remaining value using y -> a
            for (int bit = 24; bit >= 0; bit--) {

                long value = 1L << bit;

                if (x >= value) {
                    sb.append((char) ('a' + bit));
                    x -= value;
                }
            }

            ans[i] = sb.toString();
        }

        return ans;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/lexicographically-largest-string-after-pair-transformations/)