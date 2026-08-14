# Q1. Combinations

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given two integers `n` and `k`, return  *all possible combinations of*  `k`  *numbers chosen from the range*  `[1, n]`.

You may return the answer in  **any order**.

 

 **Example 1:** 

```
Input: n = 4, k = 2
Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
Explanation: There are 4 choose 2 = 6 total combinations.
Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to be the same combination.

```

 **Example 2:** 

```
Input: n = 1, k = 1
Output: [[1]]
Explanation: There is 1 choose 1 = 1 total combination.

```

 

 **Constraints:** 

- 1 <= n <= 20
- 1 <= k <= n

## Solution

**Language:** Java  
**Runtime:** 0 ms  
**Memory:** 42.4 MB  
**Submitted:** 2026-08-14T15:52:33.452Z  

```java
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(1, n, k, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int start, int n, int k,
                           List<Integer> current,
                           List<List<Integer>> result) {

        // Combination is complete
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Need enough numbers left to complete the combination
        int need = k - current.size();

        for (int i = start; i <= n - need + 1; i++) {
            current.add(i);

            backtrack(i + 1, n, k, current, result);

            current.remove(current.size() - 1);
        }
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/combinations/)