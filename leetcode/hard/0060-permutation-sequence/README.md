# Permutation Sequence

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

The set `[1, 2, 3,..., n]` contains a total of `n!` unique permutations.

By listing and labeling all of the permutations in order, we get the following sequence for `n = 3`:

- "123"
- "132"
- "213"
- "231"
- "312"
- "321"

Given `n` and `k`, return the `kth` permutation sequence.

 

 **Example 1:** 

```
Input: n = 3, k = 3
Output: "213"

```

 **Example 2:** 

```
Input: n = 4, k = 9
Output: "2314"

```

 **Example 3:** 

```
Input: n = 3, k = 1
Output: "123"

```

 

 **Constraints:** 

- 1 <= n <= 9
- 1 <= k <= n!

## Solution

**Language:** Java  
**Runtime:** 1 ms (beats 99.57%)  
**Memory:** 42.6 MB (beats 88.41%)  
**Submitted:** 2026-08-18T15:19:18.340Z  

```java
class Solution {
    public String getPermutation(int n, int k) {

        // Store numbers 1 to n
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            numbers.add(i);
        }

        // Convert k to 0-based index
        k--;

        // Calculate (n-1)!
        int factorial = 1;
        for (int i = 1; i < n; i++) {
            factorial *= i;
        }

        StringBuilder result = new StringBuilder();

        for (int i = n; i > 0; i--) {

            // Find which number should be selected
            int index = k / factorial;

            result.append(numbers.get(index));
            numbers.remove(index);

            // Update k for the remaining positions
            k %= factorial;

            // Update factorial
            if (i > 1) {
                factorial /= (i - 1);
            }
        }

        return result.toString();
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/permutation-sequence/)